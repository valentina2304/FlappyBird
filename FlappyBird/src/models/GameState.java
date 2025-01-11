package models;

import interfaces.IBird;
import interfaces.IPipe;
import utils.ImagesManager;
import utils.ScoreManager;
import utils.SoundsManager;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private final IBird bird;
    private final ArrayList<IPipe> pipes;
    private final Random random;
    private final ScoreManager scoreManager;
    private boolean gameOver;
    private boolean gameStarted;

    public GameState(ImagesManager resources) {
        this.bird = new Bird(GameConfig.BIRD_START_X, GameConfig.BIRD_START_Y,
                GameConfig.BIRD_WIDTH, GameConfig.BIRD_HEIGHT,
                resources.getBirdImg());
        this.pipes = new ArrayList<IPipe>();
        this.random = new Random();
        this.scoreManager = new ScoreManager();
        this.gameStarted = false;
    }

    public void reset() {
        gameOver = false;
        gameStarted = false;
        scoreManager.resetScore();
        pipes.clear();
        bird.reset(GameConfig.BIRD_START_Y);
    }

    public void startGame() {
        if (!gameStarted) {
            gameStarted = true;
        }
    }

    public void placePipes(ImagesManager resources) {
        if (!gameStarted) return;

        int randomPipeY = -GameConfig.PIPE_HEIGHT / 4 - random.nextInt(GameConfig.PIPE_HEIGHT / 2);

        IPipe topPipe = new Pipe(GameConfig.BOARD_WIDTH, 0,
                GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT,
                resources.getTopPipeImg());
        topPipe.setY(randomPipeY);

        IPipe bottomPipe = new Pipe(GameConfig.BOARD_WIDTH, 0,
                GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT,
                resources.getBottomPipeImg());
        bottomPipe.setY(topPipe.getY() + GameConfig.PIPE_HEIGHT + GameConfig.PIPE_OPENING);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void update(double deltaTime) {
        if (gameOver) return;
        if (!gameStarted) {
            float hoverOffset = (float) Math.sin(System.currentTimeMillis() / 150.0) * 3.0f;
            bird.reset((int)(GameConfig.BIRD_START_Y + hoverOffset));
            return;
        }

        bird.updatePosition(deltaTime);
        updatePipes(deltaTime);
        checkCollisions();
    }

    private void updatePipes(double deltaTime) {
        if (!gameStarted) return;

        pipes.removeIf(pipe -> pipe.getX() + pipe.getWidth() < 0);

        for (IPipe pipe : pipes) {
            pipe.move(deltaTime);
            if (!pipe.isPassed() && bird.getX() > pipe.getX() + pipe.getWidth()) {
                scoreManager.incrementScore();
                pipe.setPassed(true);
                SoundsManager.playSound("/resources/collectpoints.wav");
            }
        }
    }

    private void checkCollisions() {
        if (!gameStarted) return;

        if (bird.getY() > GameConfig.BOARD_HEIGHT) {
            endGame();
            return;
        }

        for (IPipe pipe : pipes) {
            if (pipe.intersects(bird)) {
                endGame();
                return;
            }
        }
    }

    private void endGame() {
        SoundsManager.playSound("/resources/fail.wav");
        gameOver = true;
        scoreManager.updateHighScore();
    }

    // Getters
    public IBird getBird() { return bird; }
    public ArrayList<IPipe> getPipes() { return pipes; }
    public ScoreManager getScoreManager() { return scoreManager; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameStarted() { return gameStarted; }
}