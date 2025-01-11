package interfaces;

import models.GameConfig;
import interfaces.IPipe;
import utils.ImagesManager;
import utils.ScoreManager;
import utils.SoundsManager;

import java.util.ArrayList;

public interface IGameState {
    public void reset() ;
    public void startGame() ;
    public void placePipes(ImagesManager resources) ;
    public void update(double deltaTime) ;
    private void updatePipes(double deltaTime){}
    private void checkCollisions(){}
    private void endGame(){}

    public IBird getBird() ;
    public ArrayList<IPipe> getPipes();
    public ScoreManager getScoreManager();
    public boolean isGameOver();
    public boolean isGameStarted();
}
