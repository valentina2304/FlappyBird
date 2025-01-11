package utils;

import javax.swing.*;
import java.awt.*;

public class ImagesManager {
    private final Image backgroundImg;
    private final Image birdImg;
    private final Image topPipeImg;
    private final Image bottomPipeImg;

    public ImagesManager() {
        backgroundImg = loadImage("/resources/flappybirdbg.png");
        birdImg = loadImage("/resources/flappybird.png");
        topPipeImg = loadImage("/resources/toppipe.png");
        bottomPipeImg = loadImage("/resources/bottompipe.png");
    }

    private Image loadImage(String path) {
        return new ImageIcon(getClass().getResource(path)).getImage();
    }

    public Image getBackgroundImg() { return backgroundImg; }
    public Image getBirdImg() { return birdImg; }
    public Image getTopPipeImg() { return topPipeImg; }
    public Image getBottomPipeImg() { return bottomPipeImg; }
}