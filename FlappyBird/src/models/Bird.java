package models;

import interfaces.IBird;

import java.awt.*;
import java.awt.geom.AffineTransform;

class Bird implements IBird {
    private int x, y;
    private final int width;
    private final int height;
    private final Image img;
    private double velocityY;
    private double rotationAngle;
    private static final double MAX_ROTATION_UP = -20.0;
    private static final double MAX_ROTATION_DOWN = 10.0;
    private static final double ROTATION_SPEED = 5.0;

    public Bird(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.rotationAngle = 0;
    }

    public void updatePosition(double deltaTime) {
        velocityY += GameConfig.GRAVITY;

        if (velocityY > GameConfig.TERMINAL_VELOCITY) {
            velocityY = GameConfig.TERMINAL_VELOCITY;
        }

        y += velocityY;
        y = Math.max(y, 0);

        if (velocityY < 0) {
            rotationAngle = MAX_ROTATION_UP;
        } else {
            rotationAngle = Math.min(MAX_ROTATION_DOWN,
                    rotationAngle + ROTATION_SPEED * deltaTime * 60);
        }
    }

    public void jump() {
        velocityY = GameConfig.JUMP_VELOCITY;
        rotationAngle = MAX_ROTATION_UP;
    }

    public void reset(int startY) {
        y = startY;
        velocityY = 0;
        rotationAngle = 0;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        g2d.translate(x + width / 2.0, y + height / 2.0);
        g2d.rotate(Math.toRadians(rotationAngle));
        g2d.drawImage(img, -width / 2, -height / 2, width, height, null);

        g2d.setTransform(old);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return img; }

}