package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 * This powerup will "freeze time" by setting the velocity of everything to zero
 */
public class TimeSlowPowerup extends GameObject {

  public static double timeSlowTicksPerSecond = 20.0;

  private final Handler handler;
  private final START_CORNER start_corner;
  private final int cValue;
  private final int timeSlowTime;
  private int relativeX = 0;

  private static final File timeSlowSound = new File("src/main/resources/time-slow.wav");
  private static final File timeSpeedSound = new File("src/main/resources/time-speed.wav");

  public TimeSlowPowerup(Game game, int x, int y, int xVelocity, int yVelocity,
      int radius, int timeSlowTime, ID id, Handler handler, START_CORNER start_corner) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.handler = handler;
    this.start_corner = start_corner;
    this.timeSlowTime = timeSlowTime;
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
      handler.removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.magenta.darker());
    g.fillRect(x, y, radius * 2, radius * 2);
  }

  // Returns the y value of the object
  private int pathEquation(int x) {
    // Equation: a*(x-screenwidth/2)^3
    return (int) (-yVelocity * (Math.pow((x - game.getWidth() / 2), 3) / 4000000)) + cValue;
  }

  public int getTimeSlowTime() {
    return timeSlowTime;
  }

  public static void playTimeSlowSound() {
    SoundPlayer.playSound(timeSlowSound, -10f);
  }

  public static void playTimeSpeedSound() {
    SoundPlayer.playSound(timeSpeedSound, -10f);
  }

  public enum START_CORNER {
    topLeft(),
    topRight(),
    bottomLeft(),
    bottomRight()
  }

}
