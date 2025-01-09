package models;

import utils.ResourceManager;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private final Bird bird;
    private final ArrayList<Pipe> pipes;
    private final Random random;
    private final ScoreManager scoreManager;
    private boolean gameOver;
    private boolean gameStarted;  // New field for tracking if game has started

    public GameState(ResourceManager resources) {
        this.bird = new Bird(GameConfig.BIRD_START_X, GameConfig.BIRD_START_Y,
                GameConfig.BIRD_WIDTH, GameConfig.BIRD_HEIGHT,
                resources.getBirdImg());
        this.pipes = new ArrayList<>();
        this.random = new Random();
        this.scoreManager = new ScoreManager();
        this.gameStarted = false;  // Initialize as not started
    }

    public void reset() {
        gameOver = false;
        gameStarted = false;  // Reset game started state
        scoreManager.resetScore();
        pipes.clear();
        bird.reset(GameConfig.BIRD_START_Y);
    }

    public void startGame() {
        if (!gameStarted) {
            gameStarted = true;
        }
    }

    public void placePipes(ResourceManager resources) {
        if (!gameStarted) return;  // Don't place pipes if game hasn't started

        int randomPipeY = -GameConfig.PIPE_HEIGHT / 4 - random.nextInt(GameConfig.PIPE_HEIGHT / 2);

        Pipe topPipe = new Pipe(GameConfig.BOARD_WIDTH, 0,
                GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT,
                resources.getTopPipeImg());
        topPipe.setY(randomPipeY);

        Pipe bottomPipe = new Pipe(GameConfig.BOARD_WIDTH, 0,
                GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT,
                resources.getBottomPipeImg());
        bottomPipe.setY(topPipe.getY() + GameConfig.PIPE_HEIGHT + GameConfig.PIPE_OPENING);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void update(double deltaTime) {
        if (gameOver) return;
        if (!gameStarted) {
            // Make bird hover in place before game starts
            float hoverOffset = (float) Math.sin(System.currentTimeMillis() / 150.0) * 3.0f;
            bird.reset((int)(GameConfig.BIRD_START_Y + hoverOffset));
            return;
        }

        bird.updatePosition(1);
        updatePipes(deltaTime);
        checkCollisions();
    }

    private void updatePipes(double deltaTime) {
        if (!gameStarted) return;  // Don't update pipes if game hasn't started

        pipes.removeIf(pipe -> pipe.getX() + pipe.getWidth() < 0);

        for (Pipe pipe : pipes) {
            pipe.move(deltaTime);
            if (!pipe.isPassed() && bird.getX() > pipe.getX() + pipe.getWidth()) {
                scoreManager.incrementScore();
                pipe.setPassed(true);
                utils.Sounds.playSound("/resources/collectpoints.wav");
            }
        }
    }

    private void checkCollisions() {
        if (!gameStarted) return;  // Don't check collisions if game hasn't started

        if (bird.getY() > GameConfig.BOARD_HEIGHT) {
            endGame();
            return;
        }

        for (Pipe pipe : pipes) {
            if (pipe.intersects(bird)) {
                endGame();
                return;
            }
        }
    }

    private void endGame() {
        utils.Sounds.playSound("/resources/fail.wav");
        gameOver = true;
        scoreManager.updateHighScore();
    }

    // Getters
    public Bird getBird() { return bird; }
    public ArrayList<Pipe> getPipes() { return pipes; }
    public ScoreManager getScoreManager() { return scoreManager; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameStarted() { return gameStarted; }  // New getter
}