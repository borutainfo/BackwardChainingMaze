package com.boruta.backwardchaining.agent.structure;

import com.boruta.backwardchaining.navigation.helper.OppositeDirectionHelper;
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
    private List<Position> knownPositions;
    private List<Integer> wayback;
    private Integer lastMove;
    private List<Position> targets;
    private boolean finished = false;

    /**
     * Instantiates a new agent using given energy level.
     *
     * @param energy the energy
     */
    public Agent(Energy energy) {
        this.energy = energy;
        this.currentPosition = new Position();
        this.knownPositions = new ArrayList<>();
        this.wayback = new ArrayList<>();
        this.addKnownPosition(this.currentPosition);
        this.lastMove = 0;
        this.targets = new ArrayList<>();
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
        this.addKnownPosition(currentPosition);

        if (this.wayback.size() > 0 && this.wayback.get(this.wayback.size() - 1) == direction) {
            this.wayback.remove(this.wayback.size() - 1);
        } else {
            this.wayback.add(OppositeDirectionHelper.getOppositeDirection(direction));
        }
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
    public boolean addKnownPosition(Position position) {
        if (this.knownPositions.contains(position)) {
            return false;
        }

        this.knownPositions.add(position.clone());
        return true;
    }

    /**
     * Get all known positions.
     *
     * @return the known positions
     */
    public List<Position> getKnownPositions() {
        return knownPositions;
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
     * Get list of movements to return to base.
     *
     * @return way back to base
     */
    public List<Integer> getWayback() {
        return this.wayback;
    }

    public Integer getLastMove() {
        return this.lastMove;
    }

    public void resetTarget() {
        this.targets = new ArrayList<>();
    }

    public boolean isSingleTarget() {
        return targets.size() == 1;
    }

    public boolean noTarget() {
        return this.targets.size() <= 0;
    }

    public List<Position> getTargets() {
        return this.targets;
    }

    public Position getTarget() {
        return this.targets.get(0);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
