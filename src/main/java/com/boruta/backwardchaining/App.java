package com.boruta.backwardchaining;

import com.boruta.backwardchaining.maze.constant.MazeBuildConstant;
import com.boruta.backwardchaining.maze.factory.MazeFactory;
import com.boruta.backwardchaining.maze.panel.MazePanel;
import com.boruta.backwardchaining.maze.structure.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * Main class of application.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int size = MazeBuildConstant.DEFAULT_SIZE;
        if (args.length > 0 && args[0] != null) {
            size = Integer.valueOf(args[0]);
        }

        Maze maze = MazeFactory.createMaze(size);

        JFrame frame = new JFrame("Random maze");
        MazePanel panel = new MazePanel(maze);
        JScrollPane scrollPane = new JScrollPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
