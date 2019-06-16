package com.boruta.backwardchaining.maze.structure;

import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Class representing path between two positions
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
@SuppressWarnings("unused")
public class Path {
    @org.kie.api.definition.type.Position(0)
    private Position pathTo;
    @org.kie.api.definition.type.Position(1)
    private Position pathFrom;

    /**
     * Instantiates a new path.
     *
     * @param pathTo   path to
     * @param pathFrom path from
     */
    public Path(Position pathTo, Position pathFrom) {
        this.pathTo = pathTo;
        this.pathFrom = pathFrom;
    }

    /**
     * Get path to.
     *
     * @return path to
     */
    public Position getPathTo() {
        return pathTo;
    }

    /**
     * Get path from.
     *
     * @return path from
     */
    public Position getPathFrom() {
        return pathFrom;
    }
}
