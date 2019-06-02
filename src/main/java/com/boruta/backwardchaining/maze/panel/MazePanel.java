package com.boruta.backwardchaining.maze.panel;

import com.boruta.backwardchaining.maze.constant.MazeDrawConstant;
import com.boruta.backwardchaining.maze.draw.MazeDraw;
import com.boruta.backwardchaining.maze.structure.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * The type Maze panel.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class MazePanel extends JPanel {
    private Maze maze;

    /**
     * Instantiates a new Maze panel.
     *
     * @param theMaze the the maze
     */
    public MazePanel(Maze theMaze) {
        maze = theMaze;
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        setBackground(Color.white);

        Dimension windowSize = new Dimension(maze.getSize() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN * 2,
                maze.getSize() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN * 2);

        this.setPreferredSize(windowSize);

        MazeDraw.draw(page, maze, 0, 0); // todo agent position
    }
}
