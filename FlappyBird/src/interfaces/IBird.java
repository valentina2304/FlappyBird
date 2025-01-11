package interfaces;

import java.awt.*;

public interface IBird {
    public void updatePosition(double deltaTime);
    public void jump();
    public void reset(int startY);
    public void draw(Graphics g);
    public Rectangle getBounds();
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public Image getImage();
}
