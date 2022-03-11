package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.userinterface.HUD;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * This boss stays in place and fires a lot of projectiles
 */
public class BulletHellBoss extends GameObject {

  private final Handler handler;
  private Player player;
  private final HUD hud;
  private final Spawner spawner;
  private final Random random = new Random();
  private int tickKeep = 0;
  private int lifespan;
  private int attackNumber;
  private int xLocation;
  private int yLocation;
  private double rise;
  private double run;
  private double actualX;
  private double actualY;

  public BulletHellBoss(Game game, int x, int y, int xVelocity, int yVelocity,
      int radius, int lifespan, ID id, Handler handler, Player player, HUD hud, Spawner spawner) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.handler = handler;
    this.player = player;
    this.hud = hud;
    this.spawner = spawner;
    this.lifespan = lifespan;
    xLocation = x;
    yLocation = y;
    actualX = x;
    actualY = y;
  }

  @Override
  public void tick() {
    // Makes the boss approach the random location
    if (Math.abs(actualX - xLocation) > 3) {
      actualX += run * xVelocity;
      x = (int) actualX;
    }

    if (Math.abs(actualY - yLocation) > 3) {
      actualY += rise * yVelocity;
      y = (int) actualY;
    }

    // Gets a random attack
    int initialAttackNumber = attackNumber;
    while (tickKeep == 0 && initialAttackNumber == attackNumber) {
      attackNumber = random.nextInt(3);
    }

    // Changes the position every 300 ticks
    if (tickKeep < Game.initialTicksPerSecond * 3) {
      tickKeep++;
    } else {
      tickKeep = 0;
      xLocation = random.nextInt(game.getWidth() - 2 * radius) + radius;
      yLocation = random.nextInt(game.getHeight() - 2 * radius) + radius;
      double xDifference = xLocation - radius - x;
      double yDifference = yLocation - radius - y;
      double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
      rise = yDifference / distance;
      run = xDifference / distance;
    }

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
    if (hud.getScore() % 5 == 0) {
      // Gets rise and run in this way so that the attack doesn't break with the time slow powerup
      double rise = Math.sin((2 * Math.PI * (hud.getScore() % Game.initialTicksPerSecond)) / Game.initialTicksPerSecond);
      double run = Math.cos((2 * Math.PI * (hud.getScore() % Game.initialTicksPerSecond)) / Game.initialTicksPerSecond);
      spawner.addObjectToSpawn(
          new BulletHellProjectile(game, x + radius, y + radius, 8, 8, 10, rise, run,
              ID.BulletHellProjectile, player, handler));
    }
  }

  private void burstAttack() {
    // Shoots projectiles out in all directions in bursts
    if (hud.getScore() % 50 == 0) {
      for (double i = 0; i < 16; i++) {
        double rise = Math.sin(2 * Math.PI * i / 12);
        double run = Math.cos((2 * Math.PI) * i / 12);
        spawner.addObjectToSpawn(
            new BulletHellProjectile(game, x + radius, y + radius, 8, 8, 10, rise, run,
                ID.BulletHellProjectile, player, handler));
      }
    }
  }

  private void targetAttack() {
    // Approaches the location of the player in a straight line
    if (hud.getScore() % 10 == 0) {
      // This deals with the rare NullPointerException
      while (player == null) {
        System.out.println("This is a bug you probs forgot about");
        for (GameObject object : handler.objects) {
          if (object.getID() == ID.Player) {
            player = (Player) object;
          }
        }
      }
      double xDifference = player.getX() + player.getRadius() - radius - x;
      double yDifference = player.getY() + player.getRadius() - radius - y;
      double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
      double rise = yDifference / distance;
      double run = xDifference / distance;
      spawner.addObjectToSpawn(
          new BulletHellProjectile(game, x + radius, y + radius, 11, 11, 10, rise, run,
              ID.BulletHellProjectile, player, handler));
    }
  }

}
