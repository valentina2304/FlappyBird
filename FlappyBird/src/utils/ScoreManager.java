package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class ScoreManager {
    private double currentScore;
    private double highScore;
    private List<ScoreEntry> scoreHistory;
    private static final String SCORES_FILE = "scores.txt";

    public static class ScoreEntry implements Comparable<ScoreEntry> {
        private final double score;
        private final LocalDateTime timestamp;

        public ScoreEntry(double score, LocalDateTime timestamp) {
            this.score = score;
            this.timestamp = timestamp;
        }

        public ScoreEntry(String line) {
            // Format: score|timestamp
            String[] parts = line.split("\\|");
            this.score = Double.parseDouble(parts[0]);
            this.timestamp = LocalDateTime.parse(parts[1]);
        }

        @Override
        public int compareTo(ScoreEntry other) {
            return Double.compare(other.score, this.score);
        }

        @Override
        public String toString() {
            return String.format("%.1f pts - %s", score,
                    timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        public String toFileString() {
            return String.format(Locale.US, "%.1f|%s", score, timestamp);
        }
    }

    public ScoreManager() {
        scoreHistory = new ArrayList<>();
        loadScores();
    }

    private void loadScores() {
        try {
            File file = new File(SCORES_FILE);
            if (!file.exists()) {
                highScore = 0;
                return;
            }

            List<String> lines = Files.readAllLines(file.toPath());
            if (!lines.isEmpty()) {
                highScore = Double.parseDouble(lines.get(0));

                for (int i = 1; i < lines.size(); i++) {
                    try {
                        scoreHistory.add(new ScoreEntry(lines.get(i)));
                    } catch (Exception e) {
                        System.err.println("Error parsing score entry: " + lines.get(i));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading scores: " + e.getMessage());
            highScore = 0;
        }
    }

    private void saveScores() {
        try {
            List<String> lines = new ArrayList<>();
            // Salvăm high score-ul pe prima linie
            lines.add(String.format(Locale.US, "%.1f", highScore));

            for (ScoreEntry entry : scoreHistory) {
                lines.add(entry.toFileString());
            }

            Files.write(Path.of(SCORES_FILE), lines);
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }

    public void incrementScore() {
        currentScore += 0.5;
    }

    public void updateHighScore() {
        if (currentScore > highScore) {
            highScore = currentScore;
        }
        scoreHistory.add(new ScoreEntry(currentScore, LocalDateTime.now()));
        saveScores();
    }

    public List<ScoreEntry> getTopScores(int n) {
        List<ScoreEntry> sortedScores = new ArrayList<>(scoreHistory);
        Collections.sort(sortedScores);
        return sortedScores.isEmpty() ?
                new ArrayList<>() :
                sortedScores.subList(0, Math.min(n, sortedScores.size()));
    }

    public void resetScore() {
        currentScore = 0;
    }

    public double getCurrentScore() { return currentScore; }
    public double getHighScore() { return highScore; }
    public List<ScoreEntry> getScoreHistory() { return new ArrayList<>(scoreHistory); }
}