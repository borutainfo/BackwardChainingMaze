package com.boruta.backwardchaining.engine.draw;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.engine.constant.EngineConstant;
import com.boruta.backwardchaining.maze.constant.MazeDrawConstant;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.awt.*;

/**
 * Class is responsible for drawing the maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class MazeDraw {
    /**
     * Draw a maze.
     *
     * @param graphics graphics
     * @param maze     maze
     * @param agent    current position
     */
    public static void draw(Graphics graphics, Maze maze, Agent agent) {
        int size = maze.getSize();

        // draw visited positions
        graphics.setColor(new Color(224, 224, 224));
        for (Position position : agent.getKnownPositions()) {
            graphics.fillRect(position.getX() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                    (size - position.getY() - 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                    MazeDrawConstant.FIELD_SIZE,
                    MazeDrawConstant.FIELD_SIZE);
        }

        // draw base
        graphics.setColor(Color.yellow);
        graphics.fillRect(MazeDrawConstant.MARGIN + 1,
                (size - 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + 1,
                MazeDrawConstant.FIELD_SIZE - 1,
                MazeDrawConstant.FIELD_SIZE - 1);

        // draw maze
        graphics.setColor(Color.BLACK);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (maze.getField(new Position(x, (size - y - 1))).isNorthWall()) {
                    graphics.drawLine(x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (x + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN);
                }

                if (maze.getField(new Position(x, (size - y - 1))).isSouthWall()) {
                    graphics.drawLine(x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (y + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (x + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (y + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN);
                }

                if (maze.getField(new Position(x, (size - y - 1))).isEastWall()) {
                    graphics.drawLine((x + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (x + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (y + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN);
                }

                if (maze.getField(new Position(x, (size - y - 1))).isWestWall()) {
                    graphics.drawLine(x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                            (y + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN);
                }
            }
        }

        // draw active enemies
        graphics.setColor(Color.RED);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (maze.getField(new Position(x, (size - y - 1))).isEnemyActive()) {
                    graphics.fillOval(x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + MazeDrawConstant.SOLDIER_SIZE / 2,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + MazeDrawConstant.SOLDIER_SIZE / 2,
                            MazeDrawConstant.SOLDIER_SIZE, MazeDrawConstant.SOLDIER_SIZE);
                }
            }
        }

        // draw defeated enemies
        graphics.setColor(Color.RED);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (maze.getField(new Position(x, (size - y - 1))).isEnemyDefeated()) {
                    graphics.drawLine(x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + 5,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + 5,
                            (x + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN - 5,
                            (y + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN - 5);

                    graphics.drawLine(x * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + 5,
                            (y + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN - 5,
                            (x + 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN - 5,
                            y * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + 5);
                }
            }
        }

        // draw agent
        graphics.setColor(Color.GREEN);
        graphics.fillOval(agent.getCurrentPosition().getX() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + MazeDrawConstant.SOLDIER_SIZE / 2,
                (size - agent.getCurrentPosition().getY() - 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN + MazeDrawConstant.SOLDIER_SIZE / 2,
                MazeDrawConstant.SOLDIER_SIZE, MazeDrawConstant.SOLDIER_SIZE);


        // optional: agent view
        if (EngineConstant.AGENT_PERSPECTIVE_VIEW) {
            graphics.setColor(Color.ORANGE);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Position position = new Position(x, y);
                    if (!agent.getKnownPositions().contains(position)) {
                        graphics.fillRect(position.getX() * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                                (size - position.getY() - 1) * MazeDrawConstant.FIELD_SIZE + MazeDrawConstant.MARGIN,
                                MazeDrawConstant.FIELD_SIZE,
                                MazeDrawConstant.FIELD_SIZE);
                    }
                }
            }
        }
    }
}
