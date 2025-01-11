package interfaces;

import utils.ScoreManager;

import java.awt.*;
import java.util.ArrayList;

public interface IGameRenderer {
    public void render(Graphics g, IBird bird, ArrayList<IPipe> pipes, ScoreManager scoreManager, boolean gameOver) ;
    private void drawBackground(Graphics g) {}
    private void drawPipes(Graphics g, ArrayList<IPipe> pipes) {}
    private void drawScore(Graphics g, ScoreManager scoreManager, boolean gameOver) {}
}
