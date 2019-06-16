package com.boruta.backwardchaining.engine.service;

import com.boruta.backwardchaining.agent.constant.AgentConstant;
import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.agent.structure.Energy;
import com.boruta.backwardchaining.enemy.constant.EnemyConstant;
import com.boruta.backwardchaining.engine.constant.EngineConstant;
import com.boruta.backwardchaining.engine.panel.MazePanel;
import com.boruta.backwardchaining.engine.panel.ScorePanel;
import com.boruta.backwardchaining.maze.constant.MazeBuildConstant;
import com.boruta.backwardchaining.maze.factory.MazeFactory;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.helper.KnownPositionsHelper;
import com.boruta.backwardchaining.navigation.structure.Position;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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

        for (Position position : KnownPositionsHelper.getVisiblePositions(agent.getCurrentPosition(), maze)) {
            if (!agent.getKnownPositions().contains(position)) {
                agent.getKnownPositions().add(position);
            }
        }

        JFrame appFrame = new JFrame(EngineConstant.APPLICATION_TITLE);
        MazePanel mazePanel = new MazePanel(maze, agent);
        ScorePanel scorePanel = new ScorePanel(maze, agent);
        JScrollPane scrollPane = new JScrollPane(mazePanel);

        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(EngineConstant.WINDOW_SIZE_WIDTH, EngineConstant.WINDOW_SIZE_HEIGHT);
        appFrame.add(scrollPane, BorderLayout.PAGE_START);
        appFrame.add(scorePanel, BorderLayout.PAGE_END);
        appFrame.setVisible(true);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession(EngineConstant.SESSION_NAME);

        kSession.insert(maze);
        kSession.insert(agent);

        kSession.addEventListener(new DefaultAgendaEventListener() {
            public void afterMatchFired(AfterMatchFiredEvent event) {
                super.afterMatchFired(event);

                try {
                    appFrame.repaint();
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        kSession.fireAllRules();
    }
}
