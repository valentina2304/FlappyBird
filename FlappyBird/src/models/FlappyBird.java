package models;

import utils.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBird extends JPanel implements KeyListener {
    private final GameState gameState;
    private final ResourceManager resources;
    private final GameRenderer renderer;
    private final GameLoop gameLoop;
    private final GameMenu gameMenu;

    public FlappyBird() {
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        resources = new ResourceManager();
        gameState = new GameState(resources);
        renderer = new GameRenderer(resources);
        gameMenu = new GameMenu();

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

        // Draw "Press SPACE to start" message if game hasn't started
        if (!gameState.isGameStarted() && !gameState.isGameOver()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.setColor(Color.WHITE);
            String message = "Press SPACE to start";
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(message);
            g2d.drawString(message,
                    (GameConfig.BOARD_WIDTH - textWidth) / 2,
                    GameConfig.BOARD_HEIGHT / 2);
        }

        if (gameMenu.isVisible()) {
            gameMenu.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameMenu.isVisible()) {
            handleMenuInput(e);
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    if (gameState.isGameOver()) {
                        startGame();
                    } else if (!gameState.isGameStarted()) {
                        gameState.startGame();
                        utils.Sounds.playSound("/resources/wing.wav");
                        gameState.getBird().jump();
                    } else {
                        utils.Sounds.playSound("/resources/wing.wav");
                        gameState.getBird().jump();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (gameState.isGameStarted()) {  // Only allow menu when game has started
                        toggleMenu(true);
                    }
                    break;
            }
        }
    }

    private void toggleMenu(boolean visible) {
        gameMenu.setVisible(visible);
        gameLoop.setPaused(visible);
        repaint();
    }

    private void handleMenuInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameMenu.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                gameMenu.moveDown();
                break;
            case KeyEvent.VK_ENTER:
                executeMenuOption();
                break;
            case KeyEvent.VK_ESCAPE:
                toggleMenu(false);
                break;
        }
        repaint();
    }

    private void executeMenuOption() {
        switch (gameMenu.getSelectedOption()) {
            case 0: // Continue
                toggleMenu(false);
                break;
            case 1: // Replay
                toggleMenu(false);
                startGame();
                break;
            case 2: // Quit
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}