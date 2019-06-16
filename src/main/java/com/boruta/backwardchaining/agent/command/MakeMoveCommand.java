package com.boruta.backwardchaining.agent.command;

import com.boruta.backwardchaining.agent.constant.AgentConstant;
import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.enemy.command.KillEnemyCommand;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.helper.KnownPositionsHelper;
import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Command to make move and execute all associated actions.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class MakeMoveCommand {
    private Maze maze;
    private Agent agent;
    private int proposedDirection;

    /**
     * Instantiates a new make move command.
     *
     * @param maze              maze
     * @param agent             agent
     * @param proposedDirection proposed direction
     */
    public MakeMoveCommand(Maze maze, Agent agent, int proposedDirection) {
        this.maze = maze;
        this.agent = agent;
        this.proposedDirection = proposedDirection;
    }

    /**
     * Make move.
     */
    public void execute() {
        if(agent.getEnergy().currentLevel() <= 0) {
            return;
        }

        int direction = this.proposedDirection;

        // not enough energy, agent have to coma back to base
        int movePrice = AgentConstant.ENERGY_FOR_MOVE;
        Position positionAfterMove = this.agent.getCurrentPosition().clone().go(direction);
        if (this.maze.getField(positionAfterMove).isEnemyActive()) {
            movePrice += AgentConstant.ENERGY_FOR_KILLING;
        }
        if (this.agent.getWayback().size() + movePrice > this.agent.getEnergy().currentLevel()) {
            direction = this.agent.getWayback().get(this.agent.getWayback().size() - 1); // change direction
        }

        this.agent.go(direction);
        for (Position position : KnownPositionsHelper.getVisiblePositions(this.agent.getCurrentPosition(), this.maze)) {
            if (!this.agent.getKnownPositions().contains(position)) {
                this.agent.getKnownPositions().add(position.clone());
            }
        }

        KillEnemyCommand killEnemyCommand = new KillEnemyCommand(this.maze, this.agent);
        killEnemyCommand.execute(this.agent.getCurrentPosition());

        if (this.agent.getCurrentPosition().equals(this.agent.getTarget())) {
            this.agent.removeTarget();
        }
    }
}
