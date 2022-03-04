package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.userinterface.HUD;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This is the class for the player object, it inherits from the GameObject class.
 */
public class Player extends GameObject {

  private final Handler handler;
  private final HUD hud;
  private final int initialRadius;

  public boolean isShrunk = false;
  public int shrinkRadius;
  public boolean isShielded = false;

  public Player(Game game, int x, int y, int radius, ID id, Handler handler, HUD hud) {
    super(game, x, y, 0, 0, radius, id);
    this.handler = handler;
    this.hud = hud;
    initialRadius = radius;
  }

  @Override
  public void setX(int x) {
    super.setX(x);
    // Keeps the player on screen
    if (x <= 0) {
      this.x = 0;
    }
    if (x >= game.getWidth() - 2 * radius) {
      this.x = game.getWidth() - 2 * radius;
    }
  }

  @Override
  public void setY(int y) {
    super.setY(y);
    // Keeps the player on screen
    if (y <= 0) {
      this.y = 0;
    }
    if (y >= game.getHeight() - 2 * radius) {
      this.y = game.getHeight() - 2 * radius;
    }
  }

  @Override
  public void tick() {
    // Powerup effects
    if (hud.getShrinkPowerupTimeLeft() > 0) {
      radius = shrinkRadius;
    } else {
      radius = initialRadius;
    }
    isShielded = hud.getShieldPowerupTimeLeft() > 0;

    // Collision
    for (GameObject object : handler.objects) {
      // Uses circular collision
      if (object.getID() != ID.Player && (
          (x + radius - object.getX() - object.getRadius()) * (x + radius - object.getX()
              - object.getRadius()) + ((y + radius - object.getY() - object.getRadius()) * (
              y + radius
                  - object.getY() - object.getRadius())) <= ((radius + object.getRadius()) * (
              radius
                  + object.getRadius())))) {
        switch (object.getID()) {
          /*
          Enemies
           */
          case BasicEnemy:
            handler.objectsToRemove.add(object);
            if (isShielded) {
              isShielded = false;
              break;
            }
            hud.setHealth(hud.getHealth() - 100);
            break;
          case StreakEnemy:
            handler.objectsToRemove.add(object);
            if (isShielded) {
              isShielded = false;
              break;
            }
            hud.setHealth(hud.getHealth() - 300);
            break;
          case HealingEnemy:
            hud.setHealth(hud.getHealth() + 150);
            handler.objectsToRemove.add(object);
            break;
          case HomingEnemy:
            if (isShielded) {
              isShielded = false;
              break;
            }
            hud.setHealth(hud.getHealth() - 2);
            break;
          case InstantDeathEnemy:
            if (isShielded) {
              isShielded = false;
              break;
            }
            hud.setHealth(hud.getHealth() - HUD.healthMax);
            break;
          /*
          Powerups
          */
          case ShrinkPowerup:
            handler.objectsToRemove.add(object);
            shrinkRadius = ((ShrinkPowerup) object).getShrinkRadius();
            hud.setShrinkPowerupTimeLeft(((ShrinkPowerup) object).getShrinkTime());
            break;
          case ShieldPowerup:
            handler.objectsToRemove.add(object);
            hud.setShieldPowerupTimeLeft(((ShieldPowerup) object).getShieldTime());
          default:
        }
      }
      // FireworkEnemies have two radii, so collision is different,
      if (object.getID() == ID.FireworkEnemy) {
        int initialRadius = ((FireworkEnemy) object).getInitialRadius();
        if ((x + radius - object.getX() - initialRadius) * (x + radius - object.getX()
            - initialRadius)
            + (y + radius - object.getY() - initialRadius) * (y + radius - object.getY()
            - initialRadius) < (
            (radius + object.getRadius()) * (radius + object.getRadius()))) {
          hud.setHealth(hud.getHealth() - 5);
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillOval(x, y, radius * 2, radius * 2);
    if (isShielded) {
      g.setColor(new Color(160, 160, 255, 120));
      g.fillOval(x - radius / 2, y - radius / 2, radius * 3, radius * 3);
    }

  }

}
