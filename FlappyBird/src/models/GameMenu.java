package models;

import javax.swing.*;
import java.awt.*;

public class GameMenu {
    private final String[] options = {"Continue", "Replay", "Quit"};
    private int selectedOption = 0;
    private boolean isVisible = false;

    public void draw(Graphics g) {
        if (!isVisible) return;

        // Semi-transparent background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);

        // Menu options
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        int startY = GameConfig.BOARD_HEIGHT / 2 - (options.length * 40);

        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.WHITE);
            }

            // Center the text
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(options[i]);
            int x = (GameConfig.BOARD_WIDTH - textWidth) / 2;
            int y = startY + (i * 50);

            g2d.drawString(options[i], x, y);
        }
    }

    public void moveUp() {
        if (!isVisible) return;
        selectedOption = (selectedOption - 1 + options.length) % options.length;
    }

    public void moveDown() {
        if (!isVisible) return;
        selectedOption = (selectedOption + 1) % options.length;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
        selectedOption = 0; // Reset selection when showing menu
    }

    public boolean isVisible() {
        return isVisible;
    }
}