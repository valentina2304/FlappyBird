package models;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameLoop {
    private final Timer gameTimer;
    private final Timer pipeTimer;
    private long lastUpdateTime;
    private boolean isPaused;

    public GameLoop(ActionListener updateAction, Runnable pipeSpawnAction) {
        lastUpdateTime = System.nanoTime();

        gameTimer = new Timer(1000 / GameConfig.FRAME_RATE, e -> {
            if (!isPaused) {
                long currentTime = System.nanoTime();
                double deltaTime = (currentTime - lastUpdateTime) / 1_000_000_000.0;
                lastUpdateTime = currentTime;
                deltaTime = Math.min(deltaTime, 0.1);
                updateAction.actionPerformed(new GameUpdateEvent(e.getSource(), e.getID(), deltaTime));
            }
        });
        gameTimer.setCoalesce(true);

        pipeTimer = new Timer(GameConfig.PIPE_SPAWN_INTERVAL, e -> {
            if (!isPaused) {
                pipeSpawnAction.run();
            }
        });
        pipeTimer.setCoalesce(true);
    }

    public void start() {
        isPaused = false;
        lastUpdateTime = System.nanoTime();
        gameTimer.start();
        pipeTimer.start();
    }

    public void stop() {
        isPaused = true;
        gameTimer.stop();
        pipeTimer.stop();
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}