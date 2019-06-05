package com.boruta.backwardchaining.engine.service;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.enemy.constant.EnemyConstant;
import com.boruta.backwardchaining.engine.constant.EngineConstant;
import com.boruta.backwardchaining.maze.constant.MazeBuildConstant;
import com.boruta.backwardchaining.maze.factory.MazeFactory;
import com.boruta.backwardchaining.engine.panel.MazePanel;
import com.boruta.backwardchaining.maze.structure.Maze;

import javax.swing.*;
import java.awt.*;

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

        if (this.arguments.length > EngineConstant.ARGUMENT_MAZE_SIZE && this.arguments[EngineConstant.ARGUMENT_MAZE_SIZE] != null) {
            mazeSize = Integer.valueOf(this.arguments[EngineConstant.ARGUMENT_MAZE_SIZE]);
        }

        if (this.arguments.length > EngineConstant.ARGUMENT_NUMBER_OF_ENEMIES && this.arguments[EngineConstant.ARGUMENT_NUMBER_OF_ENEMIES] != null) {
            numberOfEnemies = Integer.valueOf(this.arguments[EngineConstant.ARGUMENT_NUMBER_OF_ENEMIES]);
        }

        Maze maze = MazeFactory.createMaze(mazeSize, numberOfEnemies);
        Agent agent = new Agent();

        JFrame frame = new JFrame(EngineConstant.APPLICATION_TITLE);
        MazePanel panel = new MazePanel(maze, agent);
        JScrollPane scrollPane = new JScrollPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(EngineConstant.WINDOW_SIZE_WIDTH, EngineConstant.WINDOW_SIZE_HEIGHT);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
