package com.boruta.backwardchaining.enemy.command;

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

    /**
     * Instantiates a new kill enemy command.
     *
     * @param maze the maze
     */
    public KillEnemyCommand(Maze maze) {
        this.maze = maze;
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

        this.maze.setField(field.setEnemy(EnemyConstant.STATUS_DEFEATED_ENEMY), enemyPosition);
        return true;
    }
}
