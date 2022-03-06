package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 * This enemy always moves towards the player.
 */
public class HomingEnemy extends GameObject {

  private final Player player;

  private static final File humSound = new File("src/main/resources/es-electric-hum-1_htVFDSP5.wav");

  public HomingEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius, ID id,
      Player player) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.player = player;
  }

  @Override
  public void tick() {
    // Has + player radius - radius to get the homing enemy to target the center of the player
    int xDifference = player.getX() + player.getRadius() - x - radius;
    int yDifference = player.getY() + player.getRadius() - y - radius;
    double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    x += xVelocity * Math.round(xDifference / distance);
    y += yVelocity * Math.round(yDifference / distance);

    // Makes volume of sound gradually decrease with distance
    // TODO: Smooth out sound
    // TODO: Add a counter to cap how many of these sounds can be played
    if (game.getTick() % 5 == 0) {
      float soundVolume;
      if (distance < 200) {
        soundVolume = -25;
      } else if (distance < 300) {
        soundVolume = -35;
      } else if (distance < 400) {
        soundVolume = -45;
      } else if (distance < 500) {
        soundVolume = -55;
      } else if (distance < 600) {
        soundVolume = -70;
      } else {
        soundVolume = -80;
      }
      SoundPlayer.playSound(humSound, soundVolume);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
