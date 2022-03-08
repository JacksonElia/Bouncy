package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 * This powerup will shrink the player so that it is easier to dodge enemies.
 */
public class ShrinkPowerup extends GameObject {

  private final Handler handler;
  private final START_SIDE start_side;
  private final int cValue;
  private final double equationYVelocity;
  private final int shrinkRadius;
  private int relativeX = 0;
  private final int shrinkTime;
  private static final File shrinkSound = new File("src/main/resources/shrink-sound.wav");
  private static final File growSound = new File("src/main/resources/grow-sound.wav");

  public ShrinkPowerup(Game game, int x, int y, int xVelocity, double yVelocity,
      int radius, int shrinkRadius, int shrinkTime, ID id, Handler handler, START_SIDE start_side) {
    super(game, x, y, xVelocity, (int) yVelocity, radius, id);
    this.handler = handler;
    this.start_side = start_side;
    equationYVelocity = yVelocity;
    this.cValue = y;
    this.y = -(2 * radius);
    this.shrinkRadius = shrinkRadius;
    this.shrinkTime = shrinkTime;
  }

  @Override
  public void tick() {
    relativeX += Math.abs(xVelocity);
    x += xVelocity;

    // Makes the object behave correctly position-wise
    switch (start_side) {
      case up:
        y = game.getHeight() - pathEquation(game.getWidth() - relativeX);
        break;
      case right:
        y = pathEquation(relativeX);
        break;
      case down:
        y = pathEquation(x);
        break;
      case left:
        y = game.getHeight() - pathEquation(relativeX);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + start_side);
    }

    // Kills this object when it goes offscreen
    if ((x < -10 * radius) || (x > game.getWidth() + 10 * radius)) {
      handler.removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(x, y, radius * 2, radius * 2);
  }

  // Returns the y value of the object
  private int pathEquation(int x) {
    // Equation: a^x
    int returnInt = (int) Math.pow(equationYVelocity, x) + cValue;
    if (returnInt > game.getHeight()) {
      returnInt = game.getHeight() + 4 * radius;
    }
    return returnInt;
  }

  public int getShrinkRadius() {
    return shrinkRadius;
  }

  public int getShrinkTime() {
    return shrinkTime;
  }

  public static void playShrinkSound() {
    SoundPlayer.playSound(shrinkSound, -15f);
  }

  public static void playGrowSound() {
    SoundPlayer.playSound(growSound, -15f);
  }

  public enum START_SIDE {
    up(),
    right(),
    down(),
    left()
  }

}
