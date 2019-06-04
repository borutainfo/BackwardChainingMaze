package com.boruta.backwardchaining.maze.command;

import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.constant.NavigationConstant;
import com.boruta.backwardchaining.navigation.helper.OppositeDirectionHelper;
import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Command used to remove a walls in a maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class DestroyWallCommand {
    private Maze maze;

    /**
     * Instantiates a new destroy wall command.
     *
     * @param maze maze
     */
    public DestroyWallCommand(Maze maze) {
        this.maze = maze;
    }

    /**
     * Destroy wall.
     *
     * @param position  position
     * @param direction direction
     */
    public void execute(Position position, int direction) {
        this.destroyWallSingleSide(position, direction);

        Position movedPosition = position.clone().go(direction);
        int oppositeDirection = OppositeDirectionHelper.getOppositeDirection(direction);

        this.destroyWallSingleSide(movedPosition, oppositeDirection);
    }

    private void destroyWallSingleSide(Position position, int direction) {
        switch (direction) {
            case NavigationConstant.DIRECTION_NORTH:
                this.maze.setField(this.maze.getField(position).setNorthWall(false), position);
                break;
            case NavigationConstant.DIRECTION_SOUTH:
                this.maze.setField(this.maze.getField(position).setSouthWall(false), position);
                break;
            case NavigationConstant.DIRECTION_EAST:
                this.maze.setField(this.maze.getField(position).setEastWall(false), position);
                break;
            case NavigationConstant.DIRECTION_WEST:
                this.maze.setField(this.maze.getField(position).setWestWall(false), position);
                break;
        }
    }
}
