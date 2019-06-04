package com.boruta.backwardchaining.enemy.helper;

import com.boruta.backwardchaining.navigation.structure.Position;

import java.awt.*;
import java.util.Random;

/**
 * Helper for generation random position in maze.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class RandomPositionHelper {
    /**
     * Get random maze position.
     *
     * @param mazeSize maze size
     * @return random maze position
     */
    public static Position getRandomMazePosition(int mazeSize) {
        Random rand = new Random();
        Point randomPoint = new Point(rand.nextInt(mazeSize), rand.nextInt(mazeSize));
        return new Position(randomPoint);
    }
}
