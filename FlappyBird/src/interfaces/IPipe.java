package interfaces;

import models.GameConfig;

import java.awt.*;

public interface IPipe {
    public void move(double deltaTime) ;
    public void draw(Graphics g) ;
    public boolean intersects(IBird bird) ;

    public Rectangle getBounds() ;
    public int getX() ;
    public int getY() ;
    public void setY(int y) ;
    public int getWidth() ;
    public boolean isPassed();
    public void setPassed(boolean passed) ;
}
