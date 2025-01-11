package models;

import interfaces.IBird;
import interfaces.IPipe;
import utils.ImagesManager;
import utils.ScoreManager;

import java.awt.*;
import java.util.ArrayList;

public class GameRenderer {
    private final ImagesManager resources;

    public GameRenderer(ImagesManager resources) {
        this.resources = resources;
    }

    public void render(Graphics g, IBird bird, ArrayList<IPipe> pipes, ScoreManager scoreManager, boolean gameOver) {
        drawBackground(g);
        drawBird(g, bird);
        drawPipes(g, pipes);
        drawScore(g, scoreManager, gameOver);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(resources.getBackgroundImg(), 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
    }

    private void drawBird(Graphics g, IBird bird) {
        g.drawImage(resources.getBirdImg(), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);
    }

    private void drawPipes(Graphics g, ArrayList<IPipe> pipes) {
        for (IPipe pipe : pipes) {
            pipe.draw(g);
        }
    }

    private void drawScore(Graphics g, ScoreManager scoreManager, boolean gameOver) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        String scoreText = gameOver ?
                "Game Over: " + (int) scoreManager.getCurrentScore() :
                String.valueOf((int) scoreManager.getCurrentScore());
        g.drawString(scoreText, 10, 35);
        g.drawString("High Score: " + (int) scoreManager.getHighScore(), 10, 70);
    }
}