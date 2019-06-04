package com.boruta.backwardchaining.enemy.command;

import com.boruta.backwardchaining.enemy.constant.EnemyConstant;
import com.boruta.backwardchaining.enemy.exception.InvalidNumberOfEnemies;
import com.boruta.backwardchaining.enemy.helper.RandomPositionHelper;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Command for placing enemies at random maze positions.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class PlaceRandomEnemiesCommand {
    private Maze maze;

    /**
     * Instantiates a new place random enemies command.
     *
     * @param maze maze
     */
    public PlaceRandomEnemiesCommand(Maze maze) {
        this.maze = maze;
    }

    /**
     * Place enemies in maze (given number of enemies).
     *
     * @param numberOfEnemies number of enemies
     */
    public void execute(int numberOfEnemies) {
        List<String> enemiesPositions = new ArrayList<>();
        int size = maze.getSize();

        if ((size * size) - 1 < numberOfEnemies) {
            throw new InvalidNumberOfEnemies();
        }

        enemiesPositions.add(EnemyConstant.FORBIDDEN_ENEMY_POSITION.toString());

        while (enemiesPositions.size() <= numberOfEnemies) {
            Position randomPosition = RandomPositionHelper.getRandomMazePosition(size);

            if (!enemiesPositions.contains(randomPosition.toString())) {
                maze.setField(maze.getField(randomPosition).setEnemy(true), randomPosition);
                enemiesPositions.add(randomPosition.toString());
            }
        }
    }
}
