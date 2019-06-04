package com.boruta.backwardchaining.maze.factory;


import com.boruta.backwardchaining.enemy.command.PlaceRandomEnemiesCommand;
import com.boruta.backwardchaining.maze.command.DestroyWallCommand;
import com.boruta.backwardchaining.navigation.query.GetAvailableDirectionsQuery;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Factory for random maze of given size.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class MazeFactory {
    /**
     * Create random maze using Aldous-Broder algorithm.
     *
     * @param size size of maze
     * @return generated maze
     */
    public static Maze createMaze(int size, int numberOfEnemies) {
        Maze maze = new Maze(size);
        Position currentPosition = new Position(), movedPosition;

        Random rand = new Random();
        List<Integer> availableDirections;
        List<String> visited = new ArrayList<>();
        int direction;

        GetAvailableDirectionsQuery getAvailableDirectionsQuery = new GetAvailableDirectionsQuery(maze, true);
        DestroyWallCommand destroyWallCommand = new DestroyWallCommand(maze);

        visited.add(currentPosition.clone().toString());

        while (visited.size() < size * size) {
            availableDirections = getAvailableDirectionsQuery.execute(currentPosition);
            direction = availableDirections.get(rand.nextInt(availableDirections.size()));

            movedPosition = currentPosition.clone().go(direction);

            if (!visited.contains(movedPosition.toString())) {
                destroyWallCommand.execute(currentPosition, direction);
                visited.add(movedPosition.clone().toString());
            }

            currentPosition = movedPosition;
        }

        PlaceRandomEnemiesCommand placeRandomEnemiesCommand = new PlaceRandomEnemiesCommand(maze);
        placeRandomEnemiesCommand.execute(numberOfEnemies);

        return maze;
    }
}
