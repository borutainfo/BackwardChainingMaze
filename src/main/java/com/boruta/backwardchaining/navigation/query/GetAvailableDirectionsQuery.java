package com.boruta.backwardchaining.navigation.query;

import com.boruta.backwardchaining.navigation.constant.NavigationConstant;
import com.boruta.backwardchaining.maze.structure.Field;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Query for moving around the maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class GetAvailableDirectionsQuery {
    private Maze maze;
    private boolean builderMode = false;

    /**
     * Instantiates a new available directions query.
     *
     * @param maze maze
     */
    public GetAvailableDirectionsQuery(Maze maze) {
        this.maze = maze;
    }

    /**
     * Instantiates a new available directions query in builder mode.
     *
     * @param maze        maze
     * @param builderMode builder mode
     */
    public GetAvailableDirectionsQuery(Maze maze, boolean builderMode) {
        this.maze = maze;
        this.builderMode = builderMode;
    }

    /**
     * Gets available directions.
     *
     * @param position the position
     * @return the available directions
     */
    public List<Integer> execute(Position position) {
        List<Integer> availableDirections = new ArrayList<>();
        int size = maze.getSize();

        if (builderMode) {
            if (position.getY() < (size - 1)) {
                availableDirections.add(NavigationConstant.DIRECTION_NORTH);
            }
            if (position.getY() > 0) {
                availableDirections.add(NavigationConstant.DIRECTION_SOUTH);
            }
            if (position.getX() < (size - 1)) {
                availableDirections.add(NavigationConstant.DIRECTION_EAST);
            }
            if (position.getX() > 0) {
                availableDirections.add(NavigationConstant.DIRECTION_WEST);
            }
            return availableDirections;
        }

        Field currentField = this.maze.getField(position);

        if (!currentField.isNorthWall()) {
            availableDirections.add(NavigationConstant.DIRECTION_NORTH);
        }
        if (!currentField.isSouthWall()) {
            availableDirections.add(NavigationConstant.DIRECTION_SOUTH);
        }
        if (!currentField.isWestWall()) {
            availableDirections.add(NavigationConstant.DIRECTION_WEST);
        }
        if (!currentField.isEastWall()) {
            availableDirections.add(NavigationConstant.DIRECTION_EAST);
        }

        return availableDirections;
    }
}
