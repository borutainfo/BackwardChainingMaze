package com.boruta.backwardchaining.navigation.helper;

import com.boruta.backwardchaining.navigation.constant.NavigationConstant;

/**
 * Helper for getting opposite direction.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class OppositeDirectionHelper {
    /**
     * Get opposite direction.
     *
     * @param direction direction
     * @return opposite direction
     */
    public static int getOppositeDirection(int direction) {
        switch (direction) {
            case NavigationConstant.DIRECTION_NORTH:
                return NavigationConstant.DIRECTION_SOUTH;
            case NavigationConstant.DIRECTION_SOUTH:
                return NavigationConstant.DIRECTION_NORTH;
            case NavigationConstant.DIRECTION_EAST:
                return NavigationConstant.DIRECTION_WEST;
            case NavigationConstant.DIRECTION_WEST:
                return NavigationConstant.DIRECTION_EAST;
        }
        return -1;
    }
}
