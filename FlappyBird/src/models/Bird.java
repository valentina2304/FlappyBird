package models;

import interfaces.IBird;

import java.awt.*;

class Bird implements IBird {
    private int x, y;
    private final int width;
    private final int height;
    private final Image img;
    private double velocityY;

    public Bird(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void updatePosition(double deltaTime) {
        velocityY += GameConfig.GRAVITY;
        if (velocityY > GameConfig.TERMINAL_VELOCITY) {
            velocityY = GameConfig.TERMINAL_VELOCITY;
        }
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void jump() {
        velocityY = GameConfig.JUMP_VELOCITY;
    }

    public void reset(int startY) {
        y = startY;
        velocityY = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getImage() {
        return img;
    }

}