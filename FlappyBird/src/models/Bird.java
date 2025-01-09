package models;

import java.awt.*;

class Bird {
    private int x, y;
    private final int width;
    private final int height;
    private final Image img;
    private int velocityY;

    public Bird(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void updatePosition(int gravity) {
        velocityY += gravity;
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void jump() {
        velocityY = -9;
    }

    public void reset(int startY) {
        y = startY;
        velocityY = 0;
    }

//    public void draw(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.translate(x + width / 2.0, y + height / 2.0);
//        g2d.rotate(Math.toRadians(rotationAngle));
//        g2d.drawImage(img, -width / 2, -height / 2, width, height, null);
//        g2d.rotate(-Math.toRadians(rotationAngle));
//        g2d.translate(-(x + width / 2.0), -(y + height / 2.0));
//    }

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