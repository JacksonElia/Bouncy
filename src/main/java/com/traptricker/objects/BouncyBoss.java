package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * This boss is just a big version of the basic enemy that picks up speed over time.
 */
public class BouncyBoss extends GameObject {

  private final Handler handler;
  private final Random random = new Random();
  private int lifespan;

  public BouncyBoss(Game game, int x, int y, int xVelocity, int yVelocity, int radius, int lifespan,
      ID id, Handler handler) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.handler = handler;
    this.lifespan = lifespan;
  }

  @Override
  public void tick() {
    // Lifespan decreases every tick, a lifespan of 1000 correlates to 1000 score
    lifespan--;
    // Keeps the enemy on screen and gradually speeds the boss up
    if (x <= 0 || x >= game.getWidth() - radius * 2) {
      xVelocity *= -1;
      if (xVelocity > 0) {
        xVelocity += random.nextInt(4);
      } else {
        xVelocity -= random.nextInt(4);
      }
    }
    if (y <= 0 || y >= game.getHeight() - radius * 2) {
      yVelocity *= -1;
      if (yVelocity > 0) {
        yVelocity += random.nextInt(3);
      } else {
        yVelocity -= random.nextInt(3);
      }
    }

    x += xVelocity;
    y += yVelocity;

    if (lifespan <= 0) {
      handler.objectsToRemove.add(this);
    }

  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
