package com.boruta.backwardchaining.engine.panel;

import com.boruta.backwardchaining.agent.structure.Agent;
import com.boruta.backwardchaining.engine.helper.ScoreLabelHelper;
import com.boruta.backwardchaining.maze.structure.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * Score panel for agent statistics.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class ScorePanel extends JPanel {
    private Maze maze;
    private Agent agent;

    private JLabel positionLabel;
    private JLabel enemiesLabel;
    private JLabel energyLabel;

    /**
     * Instantiates a new score panel.
     *
     * @param maze  the the maze
     * @param agent the agent
     */
    public ScorePanel(Maze maze, Agent agent) {
        this.maze = maze;
        this.agent = agent;

        this.positionLabel = new JLabel("", SwingConstants.LEFT);
        this.positionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(this.positionLabel);

        this.enemiesLabel = new JLabel("", SwingConstants.LEFT);
        this.enemiesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(this.enemiesLabel);

        this.energyLabel = new JLabel("", SwingConstants.LEFT);
        this.energyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(this.energyLabel);
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        this.positionLabel.setText(ScoreLabelHelper.getPositionLabel(this.agent));
        this.enemiesLabel.setText(ScoreLabelHelper.getEnemiesLabel(this.maze));
        this.energyLabel.setText(ScoreLabelHelper.getEnergyLabel(this.agent));
    }
}
