package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sounds {
    public static void playSound(String soundFile) {
        try {
            URL resource = Sounds.class.getResource(soundFile);
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