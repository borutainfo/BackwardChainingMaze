package com.boruta.backwardchaining.maze.structure;

import com.boruta.backwardchaining.maze.exception.InvalidCoordinatesException;

/**
 * Class representing a entire maze consisting of multiple fields.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class Maze {
    private int size;
    private Field[][] fields;

    /**
     * Instantiates a new Maze.
     *
     * @param size the size
     */
    public Maze(int size) {
        this.size = size;
        this.fields = new Field[size][size]; // creates array of Cells

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.fields[x][y] = new Field();
            }
        }
    }

    /**
     * Gets field.
     *
     * @param x the x
     * @param y the y
     * @return the field
     */
    public Field getField(int x, int y) {
        this.validateCoordinates(x, y);
        return this.fields[x][y];
    }

    /**
     * Sets field.
     *
     * @param field the field
     * @param x     the x
     * @param y     the y
     * @return the field
     */
    public Field setField(Field field, int x, int y) {
        this.validateCoordinates(x, y);
        this.propagateWalls(field, x, y);
        return this.fields[x][y] = field;
    }

    private void validateCoordinates(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) {
            throw new InvalidCoordinatesException();
        }
    }

    private void propagateWalls(Field field, int x, int y) {
        if (x > 0) {
            this.fields[x - 1][y] = this.getField(x - 1, y).setEastWall(field.isWestWall());
        }

        if (x < (size - 1)) {
            this.fields[x + 1][y] = this.getField(x + 1, y).setWestWall(field.isEastWall());
        }

        if (y > 0) {
            this.fields[x][y - 1] = this.getField(x, y - 1).setNorthWall(field.isSouthWall());
        }

        if (y < (size - 1)) {
            this.fields[x][y + 1] = this.getField(x, y + 1).setSouthWall(field.isNorthWall());
        }
    }

    public int getSize() {
        return size;
    }
}
