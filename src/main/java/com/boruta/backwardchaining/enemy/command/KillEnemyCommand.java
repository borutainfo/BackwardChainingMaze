package com.boruta.backwardchaining.enemy.command;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.enemy.constant.EnemyConstant;
import com.boruta.backwardchaining.maze.structure.Field;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Command for killing enemies in maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class KillEnemyCommand {
    private Maze maze;
    private Agent agent;

    /**
     * Instantiates a new kill enemy command.private Maze maze;
     *
     * @param maze the maze
     */
    public KillEnemyCommand(Maze maze, Agent agent) {
        this.maze = maze;
        this.agent = agent;
    }

    /**
     * Kill enemy that stationed at given position.
     *
     * @param enemyPosition enemy position
     * @return boolean true if enemy has been killed
     */
    public boolean execute(Position enemyPosition) {
        Field field = this.maze.getField(enemyPosition);

        if (!field.isEnemyActive()) {
            return false;
        }

        this.agent.getEnergy().killEnemy();
        this.maze.setField(field.setEnemy(EnemyConstant.STATUS_DEFEATED_ENEMY), enemyPosition);
        return true;
    }
}
