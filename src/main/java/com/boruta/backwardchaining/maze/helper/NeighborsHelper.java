package com.boruta.backwardchaining.maze.helper;

import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.query.GetAvailableDirectionsQuery;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for getting neighbors of given position.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class NeighborsHelper {
    /**
     * Get neighbors.
     *
     * @param position position
     * @param maze     maze
     * @return neighbors
     */
    public static List<Position> getNeighbors(Position position, Maze maze) {
        List<Position> neighbors = new ArrayList<>();

        GetAvailableDirectionsQuery getAvailableDirectionsQuery = new GetAvailableDirectionsQuery(maze);
        List<Integer> availableDirections = getAvailableDirectionsQuery.execute(position);

        for (int direction : availableDirections) {
            Position clonedPosition = position.clone();
            clonedPosition.go(direction);
            neighbors.add(clonedPosition);
        }

        return neighbors;
    }
}
