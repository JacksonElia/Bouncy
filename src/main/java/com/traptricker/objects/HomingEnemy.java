package com.traptricker.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This enemy always moves towards the player.
 */
public class HomingEnemy extends GameObject {

  private final Player player;

  public HomingEnemy(int x, int y, int xVelocity, int yVelocity, int radius, ID id, Player player) {
    super(x, y, xVelocity, yVelocity, radius, id);
    this.player = player;
  }

  @Override
  public void tick() {
    // Has + player radius - radius to get the homing enemy to target the center of the player
    int xDifference = player.getX() + player.getRadius() - radius - x;
    int yDifference = player.getY() + player.getRadius() - radius - y;
    double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    x += xVelocity * Math.round(xDifference / distance);
    y += yVelocity * Math.round(yDifference / distance);
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
