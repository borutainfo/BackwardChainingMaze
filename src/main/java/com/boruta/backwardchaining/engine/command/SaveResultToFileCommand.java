package com.boruta.backwardchaining.engine.command;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.maze.structure.Field;
import com.boruta.backwardchaining.maze.structure.Maze;
import com.boruta.backwardchaining.navigation.structure.Position;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Command for saving result to file.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class SaveResultToFileCommand {
    private Maze maze;
    private Agent agent;

    /**
     * Instantiates a new save result to file command.
     *
     * @param maze  maze
     * @param agent agent
     */
    public SaveResultToFileCommand(Maze maze, Agent agent) {
        this.maze = maze;
        this.agent = agent;
    }

    /**
     * Execute command (save result to file).
     */
    public void execute() {
        int size = maze.getSize(), activeEnemies = 0, defeatedEnemies = 0;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Field field = maze.getField(new Position(x, y));
                if (field.isEnemyDefeated()) {
                    defeatedEnemies++;
                }
                if (field.isEnemyActive()) {
                    activeEnemies++;
                }
            }
        }

        String result = "";
        result += agent.getKnownPositions().size() + ",";
        result += (maze.getSize() * maze.getSize()) + ",";
        result += defeatedEnemies + ",";
        result += (defeatedEnemies + activeEnemies) + ",";
        result += agent.getNumberOfActions();

        System.out.println(result);

        try {
            this.appendDataToFile(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendDataToFile(String data) throws IOException {
        File file = new File("results.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(data + "\r\n");
        fileWriter.close();
    }
}
