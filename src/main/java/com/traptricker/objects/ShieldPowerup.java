package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 * This powerup will block the next damage the player receives except instant death.
 * TODO: Add better mechanics for blocking overtime damage from firework and homing enemies.
 */
public class ShieldPowerup extends GameObject {

  private final Handler handler;
  private final START_CORNER start_corner;
  private final int cValue;
  private final int shieldTime;
  private int relativeX = 0;
  private static final File shieldSound = new File("src/main/resources/shield-powerup.wav");
  private static final File shieldBrokenSound = new File("src/main/resources/shield-broken.wav");

  public ShieldPowerup(Game game, int x, int y, int xVelocity, int yVelocity,
      int radius, int shieldTime, ID id, Handler handler, START_CORNER start_corner) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.handler = handler;
    this.start_corner = start_corner;
    this.shieldTime = shieldTime;
    cValue = y;
  }

  @Override
  public void tick() {
    relativeX += Math.abs(xVelocity);
    x += xVelocity;
    if (start_corner == START_CORNER.topLeft || start_corner == START_CORNER.topRight) {
      y = pathEquation(relativeX);
    } else {
      y = game.getHeight() - pathEquation(relativeX);
    }

    // Kills this object when it goes offscreen
    if ((x < -10 * radius) || (x > game.getWidth() + 10 * radius)) {
      handler.objectsToRemove.add(this);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.blue);
    g.fillRect(x, y, radius * 2, radius * 2);
  }

  // Returns the y value of the object
  private int pathEquation(int x) {
    // Equation: a*x(x-screenwidth)/b
    return -1 * (x * (x - game.getWidth()) / yVelocity) + cValue;
  }

  public static void playShieldSound() {
    SoundPlayer.playSound(shieldSound, -10f);
  }

  public static void playShieldBrokenSound() {
    SoundPlayer.playSound(shieldBrokenSound, -10f);
  }

  public int getShieldTime() {
    return shieldTime;
  }

  public enum START_CORNER {
    topLeft(),
    topRight(),
    bottomLeft(),
    bottomRight()
  }

}
