package models;

public class GameConfig {
    public static final int BOARD_WIDTH = 360;
    public static final int BOARD_HEIGHT = 640;
    public static final int PIPE_SPAWN_INTERVAL = 1500;
    public static final int FRAME_RATE = 60;
    public static final int JUMP_VELOCITY = -5;
    public static final int PIPE_VELOCITY = -3;
    public static final int BIRD_START_X = BOARD_WIDTH / 8;
    public static final int BIRD_START_Y = BOARD_HEIGHT / 2;
    public static final int BIRD_WIDTH = 34;
    public static final int BIRD_HEIGHT = 24;
    public static final int PIPE_WIDTH = 64;
    public static final int PIPE_HEIGHT = 512;
    public static final int PIPE_OPENING = BOARD_HEIGHT / 4;
    public static final double GRAVITY = 0.4;
    public static final double TERMINAL_VELOCITY = 10.0;
}

