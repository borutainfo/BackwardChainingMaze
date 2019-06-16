package com.boruta.backwardchaining.navigation.helper;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.maze.helper.NeighborsHelper;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.maze.structure.Path;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for getting paths from target position.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class PathsFromTargetHelper {
    /**
     * Get paths from current target to known positions.
     *
     * @param maze  maze
     * @param agent agent
     * @return paths
     */
    public static List<Path> getPaths(Maze maze, Agent agent) {
        List<Path> result = new ArrayList<>();

        List<Position> workingPosition = new ArrayList<>();
        workingPosition.add(agent.getTarget());

        List<Position> usedPosition = new ArrayList<>();
        usedPosition.add(agent.getTarget());

        while (workingPosition.size() > 0) {
            for (Position neighborPosition : NeighborsHelper.getNeighbors(workingPosition.get(0), maze)) {
                if (agent.getKnownPositions().contains(neighborPosition) && !usedPosition.contains(neighborPosition)) {
                    usedPosition.add(neighborPosition.clone());
                    workingPosition.add(neighborPosition.clone());
                    result.add(new Path(workingPosition.get(0), neighborPosition.clone()));
                }
            }
            workingPosition.remove(0);
        }

        return result;
    }
}
