package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;

public class ShrinkPowerup extends GameObject {

  private final Handler handler;
  private final START_SIDE start_side;
  private final int cValue;
  private int relativeX = 0;
  private final double equationYVelocity;

  public ShrinkPowerup(Game game, int x, int y, int xVelocity, double yVelocity,
      int radius, ID id, Handler handler, START_SIDE start_side) {
    super(game, x, y, xVelocity, (int) yVelocity, radius, id);
    this.handler = handler;
    this.start_side = start_side;
    equationYVelocity = yVelocity;
    this.cValue = y;
    this.y = -(2 * radius);
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
//    if ((x < -10 * radius) || (x > game.getWidth() + 10 * radius)) handler.objectsToRemove.add(this);
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(x, y, radius * 2, radius * 2);
  }

  // Returns the y value of the object
  private int pathEquation(int x) {
    // Equation: a^x
    return (int) Math.pow(equationYVelocity, x) + cValue;
  }

  public enum START_SIDE {
    up(),
    right(),
    down(),
    left()
  }

}
