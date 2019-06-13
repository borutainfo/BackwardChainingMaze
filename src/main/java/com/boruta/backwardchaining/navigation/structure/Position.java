package com.boruta.backwardchaining.navigation.structure;

import com.boruta.backwardchaining.navigation.constant.NavigationConstant;

import java.awt.*;

/**
 * Position in the maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class Position implements Cloneable {
    private Point currentPosition;

    /**
     * Create new position located at [0,0].
     */
    public Position() {
        this.currentPosition = new Point(0, 0);
    }

    /**
     * Create new position located at given start position.
     *
     * @param startPosition current position
     */
    public Position(Point startPosition) {
        this.currentPosition = startPosition;
    }

    /**
     * Create new position located at given start position.
     *
     * @param x position x
     * @param y position y
     */
    public Position(int x, int y) {
        this.currentPosition = new Point(x, y);
    }

    /**
     * Get X position.
     *
     * @return current position X (as int)
     */
    public int getX() {
        return (int) this.currentPosition.getX();
    }

    /**
     * Get Y position.
     *
     * @return current position Y (as int)
     */
    public int getY() {
        return (int) this.currentPosition.getY();
    }

    /**
     * Move to one of four directions.
     *
     * @param direction the direction
     * @return current position
     */
    public Position go(int direction) {
        switch (direction) {
            case NavigationConstant.DIRECTION_NORTH:
                return this.goNorth();
            case NavigationConstant.DIRECTION_SOUTH:
                return this.goSouth();
            case NavigationConstant.DIRECTION_EAST:
                return this.goEast();
            case NavigationConstant.DIRECTION_WEST:
                return this.goWest();
            default:
                return this;
        }
    }

    /**
     * Move to north.
     *
     * @return current position
     */
    public Position goNorth() {
        this.currentPosition.y++;
        return this;
    }

    /**
     * Move to south.
     *
     * @return current position
     */
    public Position goSouth() {
        this.currentPosition.y--;
        return this;
    }

    /**
     * Move to east.
     *
     * @return current position
     */
    public Position goEast() {
        this.currentPosition.x++;
        return this;
    }

    /**
     * Move to west.
     *
     * @return current position
     */
    public Position goWest() {
        this.currentPosition.x--;
        return this;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Position clone() {
        return new Position(this.currentPosition.x, this.currentPosition.y);
    }

    @Override
    public String toString() {
        return currentPosition.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Position) {
            return this.toString().equals(object.toString());
        }
        return super.equals(object);
    }

    public int getDirection(Position otherPosition) {
        if (otherPosition.getX() > this.currentPosition.getX()) {
            return NavigationConstant.DIRECTION_EAST;
        }
        if (otherPosition.getX() < this.currentPosition.getX()) {
            return NavigationConstant.DIRECTION_WEST;
        }
        if (otherPosition.getY() > this.currentPosition.getY()) {
            return NavigationConstant.DIRECTION_NORTH;
        }
        if (otherPosition.getY() < this.currentPosition.getY()) {
            return NavigationConstant.DIRECTION_SOUTH;
        }
        return -1;
    }
}
