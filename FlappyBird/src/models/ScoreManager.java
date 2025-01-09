package models;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ScoreManager {
    private double currentScore;
    private double highScore;

    public ScoreManager() {
        this.highScore = loadHighScore();
    }

    public double getCurrentScore() { return currentScore; }
    public double getHighScore() { return highScore; }

    public void incrementScore() {
        currentScore += 0.5;
    }

    public void resetScore() {
        currentScore = 0;
    }

    public boolean isNewHighScore() {
        return currentScore > highScore;
    }

    public void updateHighScore() {
        if (isNewHighScore()) {
            highScore = currentScore;
            saveHighScore(highScore);
        }
    }

    private double loadHighScore() {
        File file = new File("highscore.txt");
        if (!file.exists()) return 0.0;

        try (Scanner scanner = new Scanner(file)) {
            scanner.useLocale(Locale.US);
            while (scanner.hasNext()) {
                String content = scanner.next();
                try {
                    return Double.parseDouble(content);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid data found: [" + content + "]");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private void saveHighScore(double highScore) {
        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.printf(Locale.US, "%.1f", highScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}