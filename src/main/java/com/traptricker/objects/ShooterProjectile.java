package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This projectile is shot by the ShooterEnemy, it travels fast but does minimal damage.
 */
public class ShooterProjectile extends GameObject {

  private final double rise;
  private final double run;
  private final Player player;
  private final Handler handler;
  private double actualX;
  private double actualY;

  public ShooterProjectile(Game game, int x, int y, int xVelocity, int yVelocity, int radius,
      ID id, Player player, Handler handler) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.player = player;
    this.handler = handler;
    int xDifference = player.getX() + player.getRadius() - radius - x;
    int yDifference = player.getY() + player.getRadius() - radius - y;
    double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    actualX = x;
    actualY = y;
    rise = yDifference / distance;
    run = xDifference / distance;
  }

  @Override
  public void tick() {
    // Makes the enemy approach the player's original position
    actualX += run * xVelocity;
    actualY += rise * yVelocity;
    x = (int) Math.round(actualX);
    y = (int) Math.round(actualY);

    // Kills this object when it goes offscreen
    if ((x < -4 * radius) || (x > game.getWidth() + 4 * radius) || (y < -4 * radius) || (y
        > game.getHeight() + 4 * radius)) {
      handler.removeObject(this);
    }

  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red.brighter());
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
