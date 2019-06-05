package com.boruta.backwardchaining.enemy.constant;

import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Constants needed to placing enemies in maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class EnemyConstant {
    /**
     * Default number of enemies.
     */
    public static final int DEFAULT_NUMBER_OF_ENEMIES = 16;
    /**
     * Agent base position, forbidden for enemies.
     */
    public static final Position FORBIDDEN_ENEMY_POSITION = new Position(0, 0);
    /**
     * Field status: free from enemies
     */
    public static final int STATUS_NOT_ENEMY = 0;
    /**
     * Field status: enemy is waiting
     */
    public static final int STATUS_ACTIVE_ENEMY = 1;
    /**
     * Field status: enemy is waiting
     */
    public static final int STATUS_DEFEATED_ENEMY = 2;
}
