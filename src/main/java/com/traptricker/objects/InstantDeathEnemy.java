package com.traptricker.objects;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This enemy moves very slowly and is very visible but will instantly kill the player if touched.
 */
public class InstantDeathEnemy extends GameObject {

  private Color color = Color.magenta;
  private double actualX;
  private double actualY;
  private final double rise;
  private final double run;

  private final Player player;

  public InstantDeathEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius,
      ID id, Player player) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.game = game;
    this.player = player;
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

    // Will change color 4 times a second
    if (game.getTick() % 50 < 25) {
      color = Color.pink;
    } else {
      color = Color.magenta;
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
