package com.boruta.backwardchaining.maze.structure;

import com.boruta.backwardchaining.maze.exception.InvalidCoordinatesException;
import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Class representing a entire maze consisting of multiple fields.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class Maze {
    private int size;
    private Field[][] fields;

    /**
     * Instantiates a new maze.
     *
     * @param size size of maze
     */
    public Maze(int size) {
        this.size = size;
        this.fields = new Field[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.fields[x][y] = new Field();
            }
        }
    }

    /**
     * Get field from given position.
     *
     * @param position position
     * @return field
     */
    public Field getField(Position position) {
        if (this.isPositionInvalid(position)) {
            throw new InvalidCoordinatesException();
        }

        return this.fields[position.getX()][position.getY()];
    }

    /**
     * Set field at given position.
     *
     * @param field    field
     * @param position position
     * @return field
     */
    public Field setField(Field field, Position position) {
        if (this.isPositionInvalid(position)) {
            throw new InvalidCoordinatesException();
        }

        return this.fields[position.getX()][position.getY()] = field;
    }

    private boolean isPositionInvalid(Position position) {
        return position.getX() < 0 || position.getY() < 0 || position.getX() >= this.size || position.getY() >= this.size;
    }

    /**
     * Gets maze size.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }
}
