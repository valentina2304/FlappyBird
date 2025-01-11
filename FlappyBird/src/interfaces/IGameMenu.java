package interfaces;

import models.GameConfig;
import utils.ScoreManager;

import java.awt.*;

public interface IGameMenu {
    public void draw(Graphics g, ScoreManager scoreManager);
    private void drawStatistics(Graphics2D g2d, ScoreManager scoreManager){}
    private void drawCenteredString(String text, Graphics2D g2d, int y){}
    private void drawMenuOptions(Graphics2D g2d) {}
    public void moveUp() ;
    public void moveDown() ;
    public void toggleStats(boolean show) ;
    public boolean isShowingStats() ;
    public int getSelectedOption() ;
    void setVisible(boolean visible);
    public boolean isVisible() ;
}
