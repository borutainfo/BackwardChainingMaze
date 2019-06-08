package com.boruta.backwardchaining.engine.service;

import com.boruta.backwardchaining.agent.constant.AgentConstant;
import com.boruta.backwardchaining.agent.exception.NotEnoughEnergyException;
import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.agent.structure.Energy;
import com.boruta.backwardchaining.enemy.command.KillEnemyCommand;
import com.boruta.backwardchaining.enemy.constant.EnemyConstant;
import com.boruta.backwardchaining.engine.constant.EngineConstant;
import com.boruta.backwardchaining.engine.panel.ScorePanel;
import com.boruta.backwardchaining.maze.constant.MazeBuildConstant;
import com.boruta.backwardchaining.maze.factory.MazeFactory;
import com.boruta.backwardchaining.engine.panel.MazePanel;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.helper.OppositeDirectionHelper;
import com.boruta.backwardchaining.navigation.query.GetAvailableDirectionsQuery;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Application run service.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class ApplicationService {
    private String[] arguments;

    /**
     * Instantiates a new application service.
     *
     * @param arguments arguments
     */
    public ApplicationService(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Run application.
     */
    public void run() {
        int mazeSize = MazeBuildConstant.DEFAULT_SIZE;
        int numberOfEnemies = EnemyConstant.DEFAULT_NUMBER_OF_ENEMIES;
        int energyLevel = AgentConstant.DEFAULT_ENERGY;

        if (this.arguments.length > EngineConstant.ARGUMENT_MAZE_SIZE && this.arguments[EngineConstant.ARGUMENT_MAZE_SIZE] != null) {
            mazeSize = Integer.valueOf(this.arguments[EngineConstant.ARGUMENT_MAZE_SIZE]);
        }

        if (this.arguments.length > EngineConstant.ARGUMENT_NUMBER_OF_ENEMIES && this.arguments[EngineConstant.ARGUMENT_NUMBER_OF_ENEMIES] != null) {
            numberOfEnemies = Integer.valueOf(this.arguments[EngineConstant.ARGUMENT_NUMBER_OF_ENEMIES]);
        }

        if (this.arguments.length > EngineConstant.ARGUMENT_ENERGY_LEVEL && this.arguments[EngineConstant.ARGUMENT_ENERGY_LEVEL] != null) {
            energyLevel = Integer.valueOf(this.arguments[EngineConstant.ARGUMENT_ENERGY_LEVEL]);
        }

        Maze maze = MazeFactory.createMaze(mazeSize, numberOfEnemies);
        Energy energy = new Energy(energyLevel);
        Agent agent = new Agent(energy);

        JFrame appFrame = new JFrame(EngineConstant.APPLICATION_TITLE);
        MazePanel mazePanel = new MazePanel(maze, agent);
        ScorePanel scorePanel = new ScorePanel(maze, agent);
        JScrollPane scrollPane = new JScrollPane(mazePanel);

        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(EngineConstant.WINDOW_SIZE_WIDTH, EngineConstant.WINDOW_SIZE_HEIGHT);
        appFrame.add(scrollPane, BorderLayout.PAGE_START);
        appFrame.add(scorePanel, BorderLayout.PAGE_END);
        appFrame.setVisible(true);

        GetAvailableDirectionsQuery getAvailableDirectionsQuery = new GetAvailableDirectionsQuery(maze);
        KillEnemyCommand killEnemyCommand = new KillEnemyCommand(maze, agent);
        Random rand = new Random();

        Timer timer = new Timer(100, e -> {
            List<Integer> availableDirections = getAvailableDirectionsQuery.execute(agent.getCurrentPosition());

            if (availableDirections.size() > 1) {
                availableDirections.remove(Integer.valueOf(OppositeDirectionHelper.getOppositeDirection(agent.getLastMove())));
            }

            int direction = availableDirections.get(rand.nextInt(availableDirections.size()));

            try {
                agent.go(direction);
                killEnemyCommand.execute(agent.getCurrentPosition());
            } catch (NotEnoughEnergyException exception) {
                ((Timer) e.getSource()).stop();
            }

            appFrame.repaint();
        });
        timer.start();
    }
}
