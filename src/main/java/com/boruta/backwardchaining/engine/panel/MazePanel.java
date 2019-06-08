package com.boruta.backwardchaining.engine.panel;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.maze.constant.MazeDrawConstant;
import com.boruta.backwardchaining.engine.draw.MazeDraw;
import com.boruta.backwardchaining.maze.structure.Maze;

import javax.swing.*;
import java.awt.*;

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

        setBackground(Color.white);

        Dimension windowSize = new Dimension(maze.getSize() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN * 2,
                maze.getSize() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN * 2);

        this.setPreferredSize(windowSize);
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        MazeDraw.draw(page, maze, agent);
    }
}
