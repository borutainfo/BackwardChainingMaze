package com.boruta.backwardchaining.agent.structure;

import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a agent.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class Agent {
    private Position currentPosition;
    private List<Position> visitedPositions;

    /**
     * Instantiates a new agent.
     */
    public Agent() {
        this.currentPosition = new Position();
        this.visitedPositions = new ArrayList<>();
        this.addVisitedPosition(this.currentPosition);
    }

    /**
     * Go in given direction.
     *
     * @param direction direction
     */
    public void go(int direction) {
        this.currentPosition.go(direction);
        this.addVisitedPosition(currentPosition);
    }

    /**
     * Get current agent position.
     *
     * @return current position
     */
    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * Add position to visited.
     *
     * @param position position
     * @return boolean true for new position
     */
    public boolean addVisitedPosition(Position position) {
        if (this.visitedPositions.contains(position)) {
            return false;
        }

        this.visitedPositions.add(position.clone());
        return true;
    }

    /**
     * Get all visited positions.
     *
     * @return the visited positions
     */
    public List<Position> getVisitedPositions() {
        return visitedPositions;
    }
}
