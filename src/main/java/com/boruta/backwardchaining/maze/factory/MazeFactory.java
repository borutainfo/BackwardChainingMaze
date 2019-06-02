package com.boruta.backwardchaining.maze.factory;


import com.boruta.backwardchaining.maze.structure.Field;
import com.boruta.backwardchaining.maze.structure.Maze;

import java.util.Random;

/**
 * Factory for random maze of given size.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class MazeFactory {
    private static final int DIRECTION_NORTH = 0;
    private static final int DIRECTION_SOUTH = 1;
    private static final int DIRECTION_EAST = 2;
    private static final int DIRECTION_WEST = 3;

    /**
     * Create random maze.
     *
     * @param size size of maze
     * @return generated maze
     */
    public static Maze createMaze(int size) {
        Maze maze = new Maze(size);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Field field = maze.getField(x, y);

                field.setNorthWall(true);
                field.setSouthWall(true);
                field.setWestWall(true);
                field.setEastWall(true);

                maze.setField(field, x, y);
            }
        }

        Random rand = new Random();
        boolean[][] visited = new boolean[size][size];
        int numberOfVisited = 1, x = 0, y = 0;

        while (numberOfVisited < size * size) {
            while (true) {
                int direction = rand.nextInt(4);

                if (direction == DIRECTION_NORTH && isDirectionPossible(size, x, y, direction)) {
                    if (!visited[x][++y]) {
                        maze.setField(maze.getField(x, y - 1).setNorthWall(false), x, y - 1);
                        visited[x][y] = true;
                        numberOfVisited++;
                    }

                    break;
                }

                if (direction == DIRECTION_SOUTH && isDirectionPossible(size, x, y, direction)) {
                    if (!visited[x][--y]) {
                        maze.setField(maze.getField(x, y + 1).setSouthWall(false), x, y + 1);
                        visited[x][y] = true;
                        numberOfVisited++;
                    }

                    break;
                }

                if (direction == DIRECTION_EAST && isDirectionPossible(size, x, y, direction)) {
                    if (!visited[++x][y]) {
                        maze.setField(maze.getField(x - 1, y).setEastWall(false), x - 1, y);
                        visited[x][y] = true;
                        numberOfVisited++;
                    }

                    break;
                }

                if (direction == DIRECTION_WEST && isDirectionPossible(size, x, y, direction)) {
                    if (!visited[--x][y]) {
                        maze.setField(maze.getField(x + 1, y).setWestWall(false), x + 1, y);
                        visited[x][y] = true;
                        numberOfVisited++;
                    }

                    break;
                }
            }
        }

        return maze;
    }

    /**
     * @param size      size of maze
     * @param x         coordinate
     * @param y         coordinate
     * @param direction direction to test
     * @return true if direction is possible, false otherwise
     */
    private static boolean isDirectionPossible(int size, int x, int y, int direction) {
        switch (direction) {
            case DIRECTION_NORTH:
                return y < (size - 1);
            case DIRECTION_SOUTH:
                return y > 0;
            case DIRECTION_EAST:
                return x < (size - 1);
            case DIRECTION_WEST:
                return x > 0;
            default:
                return false;
        }
    }
}
