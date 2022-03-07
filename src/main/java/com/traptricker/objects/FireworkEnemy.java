package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

/**
 * This enemy travels in an arc similar to sqrt(x), and explodes when it nears the player.
 * initialRadius is the radius of the fire work body, radius is the explosion radius.
 */
public class FireworkEnemy extends GameObject {

  private final Player player;
  private final Handler handler;
  private final START_CORNER start_corner;
  private final int cValue;
  private final int initialRadius;
  private int relativeX = 0;
  private Color color = Color.orange;
  private Boolean isExploding = false;
  private int tickCounterAfterExploding = 0;

  private static final File explosionSound = new File("src/main/resources/explosion.wav");

  public FireworkEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius,
      ID id, Player player, Handler handler, START_CORNER start_corner, int initialRadius) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.player = player;
    this.handler = handler;
    this.start_corner = start_corner;
    this.initialRadius = initialRadius;
    cValue = y;
  }

  @Override
  public void tick() {
    // Executed normally
    if (!isExploding) {
      // relativeX makes pathEquation() work by acting as time rather than position
      // otherwise, the y value just acts as a function of x, so it travels the arc backwards e.g. it
      // plateaus at first, then curves at the end
      relativeX += Math.abs(xVelocity);
      x += xVelocity;
      if (start_corner == START_CORNER.topLeft || start_corner == START_CORNER.topRight) {
        y = pathEquation(relativeX);
      } else {
        y = game.getHeight() - pathEquation(relativeX);
      }

      // Checks if player is nearby, if so, explodes
      if (((player.getX() + player.getRadius() - x - initialRadius) * (
          player.getX() + player.getRadius()
              - x - initialRadius) + (player.getY() + player.getRadius() - y - initialRadius)
          * (player.getY() + player.getRadius() - y - initialRadius))
          < radius * radius) {
        if (!isExploding) {
          SoundPlayer.playSound(explosionSound, -10f);
          isExploding = true;
          color = Color.red;
        }
      }
      // Kills this object when it goes offscreen
      if ((x < -4 * initialRadius) || (x > game.getWidth() + 4 * initialRadius) || (y
          < -4 * initialRadius) || (y
          > game.getHeight() + 4 * initialRadius)) {
        handler.removeObject(this);
      }
      // Executed when exploding, checks for when to kill the object
    } else {
      tickCounterAfterExploding++;
      if (tickCounterAfterExploding >= 100) {
        handler.removeObject(this);
      }
    }
  }

  @Override
  public void render(Graphics g) {
    if (!isExploding) {
      g.setColor(color);
      g.fillRect(x, y, initialRadius * 2, initialRadius * 2);
    } else {
      if (tickCounterAfterExploding < 20) {
        g.setColor(color);
        g.fillOval(x - initialRadius * 7, y - initialRadius * 7, radius * 2,
            radius * 2);
      }
    }
  }

  // Returns the y value of the object
  private int pathEquation(int x) {
    // Equation: a*sqrt(x)
    return (int) Math.abs((yVelocity * Math.sqrt(x) / xVelocity)) + cValue;
  }

  public int getInitialRadius() {
    return initialRadius;
  }

  public enum START_CORNER {
    topLeft(),
    topRight(),
    bottomLeft(),
    bottomRight()
  }

}
