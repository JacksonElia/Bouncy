package com.traptricker.sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class is used to play sound effects and music.
 */
public class SoundPlayer {

  public static synchronized void playSound(File clipFile, Float changeInDecibels) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          class AudioListener implements LineListener {

            private boolean done = false;

            @Override
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
          try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile)) {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
              FloatControl gainControl = (FloatControl) clip.getControl(
                  FloatControl.Type.MASTER_GAIN);
              gainControl.setValue(changeInDecibels);
              clip.start();
              listener.waitUntilDone();
            } finally {
              clip.close();
            }
          }
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
