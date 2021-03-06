package com.game.util;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;
import java.io.File;
import java.io.IOException;

//Credit to http://stackoverflow.com/a/577926

public class SoundUtil {
    public static void play(File clipFile) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
            private boolean done = false;

            public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }

            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) {
                    wait();
                }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                //GameMain.musicPlaying = true;
                listener.waitUntilDone();
            } finally {
                clip.close();
                //GameMain.musicPlaying = false;
            }
        } finally {
            audioInputStream.close();
        }
    }

}
