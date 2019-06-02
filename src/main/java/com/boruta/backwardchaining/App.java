package com.boruta.backwardchaining;

import com.boruta.backwardchaining.maze.factory.MazeFactory;
import com.boruta.backwardchaining.maze.panel.MazePanel;
import com.boruta.backwardchaining.maze.structure.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * The type App.
 */
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int size = Integer.valueOf(args[0]);
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
