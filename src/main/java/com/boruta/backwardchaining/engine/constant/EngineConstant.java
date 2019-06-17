package com.boruta.backwardchaining.engine.constant;

/**
 * Basic application constants.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public final class EngineConstant {
    /**
     * Application title (window title).
     */
    public static final String APPLICATION_TITLE = "Maze by Sebastian Boruta";
    /**
     * Drools session name.
     */
    public static final String SESSION_NAME = "maze-rules";
    /**
     * Window size - width.
     */
    public static final int WINDOW_SIZE_WIDTH = 600;
    /**
     * Window size - height.
     */
    public static final int WINDOW_SIZE_HEIGHT = 650;
    /**
     * Input argument id for maze size.
     */
    public static final int ARGUMENT_MAZE_SIZE = 0;
    /**
     * Input argument id for number of enemies.
     */
    public static final int ARGUMENT_NUMBER_OF_ENEMIES = 1;
    /**
     * Input argument id for energy level.
     */
    public static final int ARGUMENT_ENERGY_LEVEL = 2;
    /**
     * Show maze from agents perspective (dimmed unknown fields).
     */
    public static final boolean AGENT_PERSPECTIVE_VIEW = false;
    /**
     * Run application in quiet mode.
     */
    public static final boolean QUIET_MODE = false;
}
