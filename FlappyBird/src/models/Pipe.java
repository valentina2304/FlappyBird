package models;

import interfaces.IBird;
import interfaces.IPipe;

import java.awt.*;

class Pipe implements IPipe {
    private double exactX;
    private int x, y;
    private final int width;
    private final int height;
    private boolean passed;
    private final Image img;

    public Pipe(int x, int y, int width, int height, Image img) {
        this.exactX=x;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.passed = false;
    }

    public void move(double deltaTime) {
        exactX += GameConfig.PIPE_VELOCITY * deltaTime * 60;
        x = (int) exactX;    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    public boolean intersects(IBird bird) {
        return bird.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}