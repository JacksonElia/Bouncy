package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;

public class ShooterEnemy extends GameObject {

  private final Player player;
  private final Handler handler;
  private final Spawner spawner;

  public ShooterEnemy(Game game, int x, int y, int xVelocity, int yVelocity,
      int radius, ID id, Player player, Handler handler, Spawner spawner) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.player = player;
    this.handler = handler;
    this.spawner = spawner;
  }

  @Override
  public void tick() {
    x += xVelocity;

    if (game.getTick() == 1) {
      spawner.addObjectToSpawn(
          new ShooterProjectile(game, x, y, 13, 13, 8, ID.ShooterProjectile, player, handler));
    }

    // Kills this object when it goes offscreen
    if ((x < -4 * radius) || (x > game.getWidth() + 4 * radius)) {
      handler.removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red.darker());
    g.fillRect(x, y, radius * 3, radius * 2);
  }

}
