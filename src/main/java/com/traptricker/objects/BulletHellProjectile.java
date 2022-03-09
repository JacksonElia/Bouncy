package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;

public class BulletHellProjectile extends GameObject {
  private final double rise;
  private final double run;
  private final Player player;
  private final Handler handler;
  private double actualX;
  private double actualY;

  public BulletHellProjectile(Game game, int x, int y, int xVelocity, int yVelocity, int radius, double rise, double run,
      ID id, Player player, Handler handler) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.rise  = rise;
    this.run = run;
    this.player = player;
    this.handler = handler;
    actualX = x;
    actualY = y;
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

