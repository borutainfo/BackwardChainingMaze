package com.boruta.backwardchaining.engine.panel;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.enemy.command.KillEnemyCommand;
import com.boruta.backwardchaining.maze.constant.MazeDrawConstant;
import com.boruta.backwardchaining.engine.draw.MazeDraw;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.helper.OppositeDirectionHelper;
import com.boruta.backwardchaining.navigation.query.GetAvailableDirectionsQuery;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Maze panel for visualisation.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class MazePanel extends JPanel {
    private Maze maze;
    private Agent agent;

    /**
     * Instantiates a new maze panel.
     *
     * @param maze the the maze
     */
    public MazePanel(Maze maze, Agent agent) {
        this.maze = maze;
        this.agent = agent;

        GetAvailableDirectionsQuery getAvailableDirectionsQuery = new GetAvailableDirectionsQuery(maze);
        KillEnemyCommand killEnemyCommand = new KillEnemyCommand(maze);
        Random rand = new Random();
        final int[] direction = {-1};

        Timer timer = new Timer(200, e -> {
            List<Integer> availableDirections = getAvailableDirectionsQuery.execute(agent.getCurrentPosition());
            if (availableDirections.size() > 1) {
                availableDirections.remove(Integer.valueOf(OppositeDirectionHelper.getOppositeDirection(direction[0])));
            }
            direction[0] = availableDirections.get(rand.nextInt(availableDirections.size()));
            agent.go(direction[0]);
            killEnemyCommand.execute(agent.getCurrentPosition());
            repaint();
        });
        timer.start();
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        setBackground(Color.white);

        Dimension windowSize = new Dimension(maze.getSize() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN * 2,
                maze.getSize() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN * 2);

        this.setPreferredSize(windowSize);

        MazeDraw.draw(page, maze, agent);
    }
}
