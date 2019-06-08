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
    private Energy energy;
    private Position currentPosition;
    private List<Position> visitedPositions;
    private int lastMove;

    /**
     * Instantiates a new agent using given energy level.
     *
     * @param energy the energy
     */
    public Agent(Energy energy) {
        this.energy = energy;
        this.currentPosition = new Position();
        this.visitedPositions = new ArrayList<>();
        this.addVisitedPosition(this.currentPosition);
        this.lastMove = 0;
    }

    /**
     * Go in given direction.
     *
     * @param direction direction
     */
    public void go(int direction) {
        this.getEnergy().move();
        this.lastMove = direction;
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

    /**
     * Get energy.
     *
     * @return the energy
     */
    public Energy getEnergy() {
        return energy;
    }

    /**
     * Get last move direction.
     *
     * @return last move direction
     */
    public int getLastMove() {
        return this.lastMove;
    }
}
