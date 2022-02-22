package com.traptricker.objects;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GameObject {


  public BasicEnemy(int x, int y, int xVelocity, int yVelocity, int radius, ID id) {
    super(x, y, xVelocity, yVelocity, radius, id);
  }

  @Override
  public void tick() {
    // Keeps the enemy on screen
    if (x <= 0 || x >= Game.width - 32) {
      xVelocity *= -1;
    }
    if (y <= 0 || y >= Game.height - 64) {
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
