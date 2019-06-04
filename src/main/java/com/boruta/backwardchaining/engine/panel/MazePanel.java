package com.boruta.backwardchaining.engine.panel;

import com.boruta.backwardchaining.maze.constant.MazeDrawConstant;
import com.boruta.backwardchaining.engine.draw.MazeDraw;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.query.GetAvailableDirectionsQuery;
import com.boruta.backwardchaining.navigation.structure.Position;

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
    private Position position;

    /**
     * Instantiates a new maze panel.
     *
     * @param maze the the maze
     */
    public MazePanel(Maze maze) {
        this.maze = maze;
        position = new Position();

        GetAvailableDirectionsQuery getAvailableDirectionsQuery = new GetAvailableDirectionsQuery(maze);
        Random rand = new Random();

        Timer timer = new Timer(200, e -> {
            List<Integer> availableDirections = getAvailableDirectionsQuery.execute(position);
            int direction = availableDirections.get(rand.nextInt(availableDirections.size()));
            position.go(direction);
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

        MazeDraw.draw(page, maze, position);
    }
}
