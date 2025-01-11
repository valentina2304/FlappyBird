package models;

import utils.ScoreManager;
import utils.ImagesManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameMenu {
    private final String[] options = {"Continue", "Statistics", "Replay", "Quit"};
    private int selectedOption = 0;
    private boolean isVisible = false;
    private boolean showingStats = false;

    public void draw(Graphics g, ScoreManager scoreManager) {
        if (!isVisible) return;

        Graphics2D g2d = (Graphics2D) g;
        // Adăugăm anti-aliasing pentru text mai smooth
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Background semi-transparent
        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);

        if (showingStats) {
            drawStatistics(g2d, scoreManager);
        } else {
            drawMenuOptions(g2d);
        }
    }

    private void drawStatistics(Graphics2D g2d, ScoreManager scoreManager) {
        // Desenăm un panou pentru statistici
        int panelX = GameConfig.BOARD_WIDTH / 6;
        int panelY = GameConfig.BOARD_HEIGHT / 6;
        int panelWidth = GameConfig.BOARD_WIDTH * 2/3;
        int panelHeight = GameConfig.BOARD_HEIGHT * 2/3;

        // Background pentru panoul de statistici
        g2d.setColor(new Color(0, 0, 0, 230));
        g2d.fillRoundRect(panelX, panelY, panelWidth, panelHeight, 20, 20);

        // Border pentru panel
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(panelX, panelY, panelWidth, panelHeight, 20, 20);

        // Titlu
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        drawCenteredString("Statistics", g2d, panelY + 40);

        // Scor maxim
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        drawCenteredString("High Score: " + (int)scoreManager.getHighScore(),
                g2d, panelY + 80);

        // Top 5 Scoruri
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        drawCenteredString("Top 5 Scores", g2d, panelY + 120);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        java.util.List<ScoreManager.ScoreEntry> topScores = scoreManager.getTopScores(5);
        int yOffset = panelY + 150;
        for (ScoreManager.ScoreEntry entry : topScores) {
            drawCenteredString(entry.toString(), g2d, yOffset);
            yOffset += 25;
        }

        // Instructions
        g2d.setFont(new Font("Arial", Font.ITALIC, 14));
        drawCenteredString("Press ESC to return", g2d,
                GameConfig.BOARD_HEIGHT - 40);
    }

    private void drawCenteredString(String text, Graphics2D g2d, int y) {
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (GameConfig.BOARD_WIDTH - metrics.stringWidth(text)) / 2;
        g2d.drawString(text, x, y);
    }

    private void drawMenuOptions(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        int startY = GameConfig.BOARD_HEIGHT / 2 - (options.length * 40);

        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.WHITE);
            }

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(options[i]);
            int x = (GameConfig.BOARD_WIDTH - textWidth) / 2;
            int y = startY + (i * 50);

            g2d.drawString(options[i], x, y);
        }
    }

    public void moveUp() {
        if (!isVisible || showingStats) return;
        selectedOption = (selectedOption - 1 + options.length) % options.length;
    }

    public void moveDown() {
        if (!isVisible || showingStats) return;
        selectedOption = (selectedOption + 1) % options.length;
    }

    public void toggleStats(boolean show) {
        showingStats = show;
    }

    public boolean isShowingStats() {
        return showingStats;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
        if (!visible) {
            showingStats = false;
        }
        selectedOption = 0;
    }

    public boolean isVisible() {
        return isVisible;
    }
}