package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.userinterface.HUD;
import java.util.Random;

/**
 * This class handles the spawning of all the enemies based on certain things like level.
 */
public class Spawner {

  private final Handler handler;
  private final HUD hud;
  private final Random random;

  public Spawner(Handler handler, HUD hud) {
    this.handler = handler;
    this.hud = hud;
    this.random = new Random();
  }

  public void tick() {

    if (hud.getScore() % 200 == 0) {
      spawnBasicEnemy();
    }

    if (hud.getScore() % 300 == 0) {
      spawnStreakEnemy();
    }

    if (hud.getScore() % 1000 == 0) {
      spawnHomingEnemy();
    }

  }

  public void spawnBasicEnemy() {
    // Stops the enemy from spawning too close to the player
    int x = random.nextInt(Game.width - 32);
    int y = random.nextInt(Game.width - 64);
    int playerX = 0;
    int playerY = 0;
    for (GameObject object : handler.objects) {
      if (object.getID() == ID.Player) {
        playerX = object.getX();
        playerY = object.getY();
      }
    }
    while ((x - playerX) * (x - playerX) + (y - playerY) * (y - playerY) < 4000) {
      x = random.nextInt(Game.width - 32);
      y = random.nextInt(Game.height - 64);
    }

    // Stops the enemy from spawning with no velocity
    int xVelocity = 0;
    int yVelocity = 0;
    while (xVelocity + yVelocity == 0) {
      xVelocity = random.nextInt(10) - 5;
      yVelocity = random.nextInt(10) - 5;
    }
    handler.addObject(new BasicEnemy(x, y, xVelocity, yVelocity, 8, ID.BasicEnemy));
  }

  public void spawnStreakEnemy() {
    // Gets a random start_side
    StreakEnemy.START_SIDE start_side = StreakEnemy.START_SIDE.values()[random.nextInt(
        StreakEnemy.START_SIDE.values().length)];
    int x, y, xVelocity, yVelocity;
    switch (start_side) {
      case up:
        x = random.nextInt(Game.width - (Game.width / 6)) - (Game.width / 6);
        y = -10;
        xVelocity = random.nextInt(10) - 5;
        yVelocity = 10;
        break;
      case right:
        x = Game.width + 10;
        y = random.nextInt(Game.height - (Game.height / 6)) - (Game.height / 6);
        xVelocity = -10;
        yVelocity = random.nextInt(10) - 5;
        break;
      case down:
        x = random.nextInt(Game.width - (Game.width / 6)) - (Game.width / 6);
        y = Game.height + 10;
        xVelocity = random.nextInt(10) - 5;
        yVelocity = -10;
        break;
      case left:
        x = -10;
        y = random.nextInt(Game.height - (Game.height / 6)) - (Game.height / 6);
        xVelocity = 10;
        yVelocity = random.nextInt(10) - 5;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + start_side);
    }
    handler.addObject(
        new StreakEnemy(x, y, xVelocity, yVelocity, 5, ID.StreakEnemy, handler, start_side));
  }

  public void spawnHomingEnemy() {
    Player player = null;
    // Stops the enemy from spawning too close to the player
    int x = random.nextInt(Game.width - 32);
    int y = random.nextInt(Game.width - 64);
    int playerX = 0;
    int playerY = 0;
    for (GameObject object : handler.objects) {
      if (object.getID() == ID.Player) {
        player = (Player) object;
        playerX = player.getY();
        playerY = player.getY();
      }
    }

    while ((x - playerX) * (x - playerX) + (y - playerY) * (y - playerY) < 4000) {
      x = random.nextInt(Game.width - 32);
      y = random.nextInt(Game.height - 64);
    }
    handler.addObject(new HomingEnemy(x, y, 1, 1, 12, ID.HomingEnemy, player));
  }

  public void levelOne() {

  }

}
