package com.boruta.backwardchaining.engine.helper;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.maze.structure.Field;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

/**
 * Helper for text labels used to display stats.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class ScoreLabelHelper {
    /**
     * Get text for position label.
     *
     * @param agent agent
     * @return content of position label
     */
    public static String getPositionLabel(Agent agent) {
        return "Position: [x=" + agent.getCurrentPosition().getX() + ",y=" + agent.getCurrentPosition().getY() + "] ";
    }

    /**
     * Get text for enemies label.
     *
     * @param maze maze
     * @return content of enemies label
     */
    public static String getEnemiesLabel(Maze maze) {
        int size = maze.getSize(), activeEnemies = 0, defeatedEnemies = 0;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Field field = maze.getField(new Position(x, y));
                if (field.isEnemyActive()) {
                    activeEnemies++;
                }
                if (field.isEnemyDefeated()) {
                    defeatedEnemies++;
                }
            }
        }

        return "Enemies: " + activeEnemies + " / " + (activeEnemies + defeatedEnemies) + " ";
    }

    /**
     * Get text for energy label.
     *
     * @param agent agent
     * @return content of energy label
     */
    public static String getEnergyLabel(Agent agent) {
        return "Energy: " + agent.getEnergy().currentLevel() + " ";
    }

    /**
     * Get text for maze knowledge label.
     *
     * @param agent agent
     * @param maze  maze
     * @return maze knowledge
     */
    public static String getMazeKnowledge(Agent agent, Maze maze) {
        int totalSize = maze.getSize() * maze.getSize();
        int mazeKnowledgeLevel = (agent.getKnownPositions().size() * 100) / (maze.getSize() * maze.getSize());
        return "Exploration: " + agent.getKnownPositions().size() + " / " + totalSize + " = " + mazeKnowledgeLevel + " %";
    }
}
