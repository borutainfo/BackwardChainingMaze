package com.boruta.backwardchaining.agent.structure;

import com.boruta.backwardchaining.agent.constant.AgentConstant;
import com.boruta.backwardchaining.agent.exception.NotEnoughEnergyException;

/**
 * Class representing agent energy.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class Energy {
    private int energy;

    /**
     * Instantiates a new energy using default value.
     */
    public Energy() {
        this.energy = AgentConstant.DEFAULT_ENERGY;
    }

    /**
     * Instantiates a new energy using giving value.
     *
     * @param energy the energy
     */
    public Energy(int energy) {
        this.energy = energy;
    }

    /**
     * Get energy.
     *
     * @return energy
     */
    public int currentLevel() {
        return energy;
    }

    /**
     * Reduce energy by moving.
     */
    public void move() {
        if ((this.energy - AgentConstant.ENERGY_FOR_MOVE) < 0) {
            throw new NotEnoughEnergyException();
        }

        this.energy -= AgentConstant.ENERGY_FOR_MOVE;
    }

    /**
     * Reduce energy by killing.
     */
    public void killEnemy() {
        if ((this.energy - AgentConstant.ENERGY_FOR_KILLING) < 0) {
            throw new NotEnoughEnergyException();
        }

        this.energy -= AgentConstant.ENERGY_FOR_KILLING;
    }
}
