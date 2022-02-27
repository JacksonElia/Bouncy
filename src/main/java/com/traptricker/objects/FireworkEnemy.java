package com.traptricker.objects;

import com.traptricker.Game;
import java.awt.Graphics;

public class FireworkEnemy extends GameObject {

  public FireworkEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius,
      ID id) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
  }

  @Override
  public void tick() {

  }

  @Override
  public void render(Graphics g) {
  }
}
