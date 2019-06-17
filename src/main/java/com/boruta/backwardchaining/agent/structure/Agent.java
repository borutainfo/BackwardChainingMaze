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
    private Position target;
    private List<Position> knownPositions;
    private List<Integer> wayback;
    private boolean finished = false;
    private int numberOfActions;

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
        this.numberOfActions = 0;
    }

    /**
     * Go in given direction.
     *
     * @param direction direction
     */
    public void go(int direction) {
        this.incrementAction();
        this.currentPosition.go(direction);
        this.getEnergy().move();
        this.addKnownPosition(this.currentPosition);
        this.noteWayback(direction);
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
     */
    private void addKnownPosition(Position position) {
        if (this.knownPositions.contains(position)) {
            return;
        }

        this.knownPositions.add(position.clone());
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

    /**
     * Add current move direction to wayback.
     *
     * @param direction move direction
     */
    private void noteWayback(int direction) {
        if (this.wayback.size() > 0 && this.wayback.get(this.wayback.size() - 1) == direction) {
            this.wayback.remove(this.wayback.size() - 1);
        } else {
            this.wayback.add(OppositeDirectionHelper.getOppositeDirection(direction));
        }
    }

    /**
     * Is target already choosed?
     *
     * @return boolean
     */
    public boolean isTargetChoosed() {
        return this.target != null;
    }

    /**
     * Remove target.
     */
    public void removeTarget() {
        this.target = null;
    }

    /**
     * Set target.
     *
     * @param position position
     */
    public void setTarget(Position position) {
        this.target = position;
    }

    /**
     * Get choosed target.
     *
     * @return target
     */
    public Position getTarget() {
        return this.target;
    }

    /**
     * Is game finished boolean.
     *
     * @return boolean
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Set game as finished.
     *
     * @param finished finished
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Get number of agent actions.
     *
     * @return number of actions
     */
    public int getNumberOfActions() {
        return numberOfActions;
    }

    /**
     * Increment number of agent actions.
     */
    public void incrementAction() {
        this.numberOfActions++;
    }
}
