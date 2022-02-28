package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This enemy heals the player if they hit it.
 */
public class HealingEnemy extends GameObject {

  public START_SIDE start_side;

  private final Handler handler;

  public HealingEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius, ID id,
      Handler handler, START_SIDE start_side) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.handler = handler;
    this.start_side = start_side;
  }

  @Override
  public void tick() {
    x += xVelocity;
    y += yVelocity;

    // Kills the enemy after it goes off-screen
    switch (start_side) {
      case up:
        if (x < -10 || x > game.getWidth() || y > game.getHeight()) {
          handler.objectsToRemove.add(this);
        }
        break;
      case right:
        if (x < -10 || y < -10 || y > game.getHeight()) {
          handler.objectsToRemove.add(this);
        }
        break;
      case down:
        if (x < -10 || x > game.getWidth() || y < -10) {
          handler.objectsToRemove.add(this);
        }
        break;
      case left:
        if (x > game.getWidth() || y < -10 || y > game.getHeight()) {
          handler.objectsToRemove.add(this);
        }
        break;
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(x, y, radius * 2, radius * 2);
  }

  public enum START_SIDE {
    up(),
    right(),
    down(),
    left()
  }
}