package org.example.journeytosammich;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NoiseMaker {
    private String sound;
    private boolean loop;

    public NoiseMaker(String reSound) {
        sound=reSound;
    }
    public NoiseMaker(String reSound,boolean reLoop) {
        sound=reSound;
        loop=reLoop;
    }

    public void startAudio() { //resource: https://www.youtube.com/watch?v=P856ukheHeE
        AudioInputStream audioStream = null;
        InputStream inputStream= getClass().getResourceAsStream(sound);
        try {
            audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
        } catch (UnsupportedAudioFileException e) {
             throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        clip.start();
    }
}

