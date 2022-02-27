package com.traptricker.objects;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GameObject {

  public BasicEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius, ID id) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
  }

  @Override
  public void tick() {
    // Keeps the enemy on screen
    if (x <= 0 || x >= game.getWidth() - radius * 2) {
      xVelocity *= -1;
    }
    if (y <= 0 || y >= game.getHeight() - radius * 2) {
      yVelocity *= -1;
    }

    x += xVelocity;
    y += yVelocity;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.cyan);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
