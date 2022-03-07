package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 * This enemy moves very slowly and is very visible but will instantly kill the player if touched.
 */
public class InstantDeathEnemy extends GameObject {

  private final double rise;
  private final double run;
  private final Player player;
  private final Handler handler;
  private Color color = Color.magenta;
  private double actualX;
  private double actualY;

  private static final File sirenSound = new File("src/main/resources/air-raid-siren_djgTknvd.wav");

  public InstantDeathEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius,
      ID id, Player player, Handler handler) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.player = player;
    this.handler = handler;
    int xDifference = player.getX() + player.getRadius() - radius - x;
    int yDifference = player.getY() + player.getRadius() - radius - y;
    double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    actualX = x;
    actualY = y;
    rise = yDifference / distance;
    run = xDifference / distance;

    SoundPlayer.playSound(sirenSound, -20f);
  }

  @Override
  public void tick() {
    // Makes the enemy approach the player's original position
    actualX += run * (xVelocity + .3);
    actualY += rise * (yVelocity + .3);
    x = (int) Math.round(actualX);
    y = (int) Math.round(actualY);

    // Will change color 4 times a second
    if (game.getTick() % 50 < 25) {
      color = Color.pink;
    } else {
      color = Color.magenta;
    }

    // Kills this object when it goes offscreen
    if ((x < -4 * radius) || (x > game.getWidth() + 4 * radius) || (y < -4 * radius) || (y
        > game.getHeight() + 4 * radius)) {
      handler.removeObject(this);
    }

  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
