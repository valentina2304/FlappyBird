package utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundsManager {
    public static void playSound(String soundFile) {
        try {
            URL resource = SoundsManager.class.getResource(soundFile);
            if (resource == null) {
                throw new IllegalArgumentException("File not found: " + soundFile);
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}