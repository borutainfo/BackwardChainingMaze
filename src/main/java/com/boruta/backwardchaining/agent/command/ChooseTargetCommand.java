package com.boruta.backwardchaining.agent.command;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.maze.helper.NeighborsHelper;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Command to choose next agent target.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class ChooseTargetCommand {
    private Maze maze;
    private Agent agent;

    /**
     * Instantiates a new choose target command.
     *
     * @param maze  the maze
     * @param agent the agent
     */
    public ChooseTargetCommand(Maze maze, Agent agent) {
        this.maze = maze;
        this.agent = agent;
    }

    /**
     * Choose next agent target.
     *
     * @return the boolean
     */
    public boolean execute() {
        ArrayList<Position> potentialTargets = new ArrayList<>();
        for (Position position : this.agent.getKnownPositions()) {
            potentialTargets.add(position.clone());
        }
        Collections.reverse(potentialTargets);

        // set enemy as target
        for (Position position : potentialTargets) {
            if (this.maze.getField(position).isEnemyActive()) {
                this.agent.setTarget(position.clone());
                return true;
            }
        }

        // set unexplored field as target
        for (Position position : potentialTargets) {
            for (Position neighborPosition : NeighborsHelper.getNeighbors(position, this.maze)) {
                if (!this.agent.getKnownPositions().contains(neighborPosition)) {
                    this.agent.setTarget(position.clone());
                    return true;
                }
            }
        }

        // set base as target
        if (!this.agent.getCurrentPosition().equals(new Position(0, 0))) {
            this.agent.setTarget(new Position(0, 0));
            return true;
        }

        return false;
    }
}
