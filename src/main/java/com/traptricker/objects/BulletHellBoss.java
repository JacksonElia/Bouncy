package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * This boss stays in place and fires a lot of projectiles
 */
public class BulletHellBoss extends GameObject {

  private final Handler handler;
  private final Player player;
  private final Spawner spawner;
  private final Random random = new Random();
  private int tickKeep = 0;
  private int lifespan;
  private int attackNumber;

  public BulletHellBoss(Game game, int x, int y, int xVelocity, int yVelocity,
      int radius, int lifespan, ID id, Handler handler, Player player, Spawner spawner) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.handler = handler;
    this.player = player;
    this.spawner = spawner;
    this.lifespan = lifespan;
  }

  @Override
  public void tick() {

    if (tickKeep == 0) {
      attackNumber = random.nextInt(3);
    }
    if (tickKeep < Game.initialTicksPerSecond * 3) {
      tickKeep++;
    } else {
      tickKeep = 0;
    }
    attackNumber = 1;
    switch (attackNumber) {
      case 0:
        spiralAttack();
        break;
      case 1:
        burstAttack();
        break;
      case 2:
        targetAttack();
        break;
    }

    lifespan--;
    if (lifespan <= 0) {
      handler.removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red.darker());
    g.fillRect(x, y, radius * 2, radius * 2);
  }

  private void spiralAttack() {
    // Makes a turning spiral of projectiles
    if (tickKeep % (Game.initialTicksPerSecond / 20) == 0) {
      double rise = Math.sin((2 * Math.PI * game.getTick() + tickKeep) / game.getTicksPerSecond());
      double run = Math.cos((2 * Math.PI * game.getTick() + tickKeep) / game.getTicksPerSecond());
      spawner.addObjectToSpawn(
          new BulletHellProjectile(game, x + radius, y + radius, 8, 8, 8, rise, run,
              ID.BulletHellProjectile, player, handler));
    }
  }

  private void burstAttack() {
    // Shoots projectiles out in all directions in bursts
    if (tickKeep % (Game.initialTicksPerSecond / 2) == 0) {
      for (double i = 0; i < 12; i++) {
        double offset = 0;
        System.out.println(game.getTick());
        if (game.getTick() <= game.getTicksPerSecond() / 2) {
          offset = Math.PI;
        }
        double rise = Math.sin(2 * Math.PI * i / 12 + offset);
        double run = Math.cos((2 * Math.PI) * i / 12 + offset);
        spawner.addObjectToSpawn(
            new BulletHellProjectile(game, x + radius, y + radius, 8, 8, 8, rise, run,
                ID.BulletHellProjectile, player, handler));
      }
    }
  }

  private void targetAttack() {
    // Approaches the location of the player in a straight line
    if (game.getTick() % (Game.initialTicksPerSecond / 10) == 0) {
      double xDifference = player.getX() + player.getRadius() - radius - x;
      double yDifference = player.getY() + player.getRadius() - radius - y;
      double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
      double rise = yDifference / distance;
      double run = xDifference / distance;
      spawner.addObjectToSpawn(
          new BulletHellProjectile(game, x + radius, y + radius, 14, 14, 8, rise, run,
              ID.BulletHellProjectile, player, handler));
    }
  }

}
