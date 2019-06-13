package com.boruta.backwardchaining.navigation.helper;

import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.query.GetAvailableDirectionsQuery;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for discovering maze positions.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class KnownPositionsHelper {
    /**
     * Get positions that are visible from current position.
     *
     * @param currentPosition current position
     * @param maze            maze
     * @return visible positions
     */
    public static List<Position> getVisiblePositions(Position currentPosition, Maze maze) {
        List<Position> visiblePositions = new ArrayList<>();
        visiblePositions.add(currentPosition.clone());

        GetAvailableDirectionsQuery getAvailableDirectionsQuery = new GetAvailableDirectionsQuery(maze);
        List<Integer> availableDirections = getAvailableDirectionsQuery.execute(currentPosition);

        for (Integer direction : availableDirections) {
            Position clonedPosition = currentPosition.clone();
            while (true) {
                clonedPosition.go(direction);
                List<Integer> availableDirectionsForClonedFields = getAvailableDirectionsQuery.execute(clonedPosition);

                if (!visiblePositions.contains(clonedPosition)) {
                    visiblePositions.add(clonedPosition.clone());
                }

                if (!availableDirectionsForClonedFields.contains(direction)) {
                    break;
                }
            }
        }

        return visiblePositions;
    }
}
