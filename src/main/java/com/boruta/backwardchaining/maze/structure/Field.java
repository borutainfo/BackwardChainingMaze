package com.boruta.backwardchaining.maze.structure;

import com.boruta.backwardchaining.enemy.constant.EnemyConstant;

/**
 * Class representing a single maze field.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class Field {
    private boolean northWall = true;
    private boolean southWall = true;
    private boolean eastWall = true;
    private boolean westWall = true;
    private int enemy = EnemyConstant.STATUS_NOT_ENEMY;

    /**
     * Is there a wall on the north side?
     *
     * @return the boolean
     */
    public boolean isNorthWall() {
        return northWall;
    }

    /**
     * Set the wall on the north side.
     *
     * @param northWall the north wall
     * @return Field
     */
    public Field setNorthWall(boolean northWall) {
        this.northWall = northWall;
        return this;
    }

    /**
     * Is there a wall on the south side?
     *
     * @return the boolean
     */
    public boolean isSouthWall() {
        return southWall;
    }

    /**
     * Set the wall on the south side.
     *
     * @param southWall the south wall
     * @return Field
     */
    public Field setSouthWall(boolean southWall) {
        this.southWall = southWall;
        return this;
    }

    /**
     * Is there a wall on the east side?
     *
     * @return the boolean
     */
    public boolean isEastWall() {
        return eastWall;
    }

    /**
     * Set the wall on the east side.
     *
     * @param eastWall the east wall
     * @return Field
     */
    public Field setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
        return this;
    }

    /**
     * Is there a wall on the west side?
     *
     * @return the boolean
     */
    public boolean isWestWall() {
        return westWall;
    }

    /**
     * Set the wall on the west side.
     *
     * @param westWall the west wall
     * @return Field
     */
    public Field setWestWall(boolean westWall) {
        this.westWall = westWall;
        return this;
    }

    /**
     * Is there an active enemy in the cell?
     *
     * @return boolean
     */
    public boolean isEnemyActive() {
        return enemy == EnemyConstant.STATUS_ACTIVE_ENEMY;
    }

    /**
     * Is there an defeated enemy corpse in the cell?
     *
     * @return boolean
     */
    public boolean isEnemyDefeated() {
        return enemy == EnemyConstant.STATUS_DEFEATED_ENEMY;
    }

    /**
     * Set the enemy in the cell.
     *
     * @param enemy the enemy
     * @return Field
     */
    public Field setEnemy(int enemy) {
        this.enemy = enemy;
        return this;
    }
}
