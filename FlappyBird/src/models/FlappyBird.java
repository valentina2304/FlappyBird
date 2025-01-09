package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBird extends JPanel implements KeyListener {
    private final GameState gameState;
    private final ResourceManager resources;
    private final GameRenderer renderer;
    private final GameLoop gameLoop;

    public FlappyBird() {
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        resources = new ResourceManager();
        gameState = new GameState(resources);
        renderer = new GameRenderer(resources);

        gameLoop = new GameLoop(
                e -> {
                    if (e instanceof GameUpdateEvent) {
                        double deltaTime = ((GameUpdateEvent) e).getDeltaTime();
                        gameState.update(deltaTime);
                        repaint();
                    }
                },
                () -> gameState.placePipes(resources)
        );

        startGame();
    }

    private void startGame() {
        gameState.reset();
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, gameState.getBird(), gameState.getPipes(),
                gameState.getScoreManager(), gameState.isGameOver());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameState.isGameOver()) {
                startGame();
            } else {
                utils.Sounds.playSound("/resources/wing.wav");
                gameState.getBird().jump();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}