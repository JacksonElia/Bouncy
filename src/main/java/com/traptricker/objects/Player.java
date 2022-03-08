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

  public int shrinkRadius;
  public int playerIFrames = 0;
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
    // Ticks down IFrames
    if (playerIFrames > 0) {
      playerIFrames--;
    }
    // Powerup effects
    if (hud.getShrinkPowerupTimeLeft() > 0) {
      radius = shrinkRadius;
    } else {
      radius = initialRadius;
    }

    if (hud.getShrinkPowerupTimeLeft() == 1 && game.getTick() == 5) {
      ShrinkPowerup.playGrowSound();
    }
    if (hud.getTimeSlowPowerupTimeLeft() == 1 && game.getTick() == 5) {
      TimeSlowPowerup.playTimeSpeedSound();
    }

    isShielded = hud.getShieldPowerupTimeLeft() > 0;

    if (hud.getTimeSlowPowerupTimeLeft() > 0) {
      game.setTicksPerSecond(20);
    } else {
      game.setTicksPerSecond(Game.initialTicksPerSecond);
    }

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
            handler.removeObject(object);
            if (isShielded) {
              hud.setShieldPowerupTimeLeft(0);
              ShieldPowerup.playShieldBrokenSound();
              break;
            }
            hud.setHealth(hud.getHealth() - 100);
            break;
          case StreakEnemy:
            handler.removeObject(object);
            if (isShielded) {
              hud.setShieldPowerupTimeLeft(0);
              ShieldPowerup.playShieldBrokenSound();
              break;
            }
            hud.setHealth(hud.getHealth() - 300);
            break;
          case HealingEnemy:
            hud.setHealth(hud.getHealth() + 150);
            handler.removeObject(object);
            HealingEnemy.playHealSound();
            break;
          case HomingEnemy:
            if (isShielded) {
              hud.setShieldPowerupTimeLeft(0);
              ShieldPowerup.playShieldBrokenSound();
              break;
            }
            hud.setHealth(hud.getHealth() - 2);
            break;
          case InstantDeathEnemy:
            // Shield won't protect from instant death enemy
            if (isShielded) {
              hud.setShieldPowerupTimeLeft(0);
              ShieldPowerup.playShieldBrokenSound();
              break;
            }
            hud.setHealth(hud.getHealth() - HUD.healthMax);
            break;
          case ShooterEnemy:
            // Breaks shooter on collision
            handler.removeObject(object);
          /*
          Projectiles
           */
          case ShooterProjectile:
            handler.removeObject(object);
            if (isShielded) {
              hud.setShieldPowerupTimeLeft(0);
              ShieldPowerup.playShieldBrokenSound();
              break;
            }
            hud.setHealth(hud.getHealth() - 70);
            break;
          /*
          Bosses
           */
          case BouncyBoss:
            if (playerIFrames == 0) {
              playerIFrames = 75;
              if (isShielded) {
                hud.setShieldPowerupTimeLeft(0);
                ShieldPowerup.playShieldBrokenSound();
                break;
              }
              hud.setHealth(hud.getHealth() - 200);
            }
            break;
          /*
          Powerups
          */
          case ShrinkPowerup:
            handler.removeObject(object);
            shrinkRadius = ((ShrinkPowerup) object).getShrinkRadius();
            hud.setShrinkPowerupTimeLeft(((ShrinkPowerup) object).getShrinkTime());
            ShrinkPowerup.playShrinkSound();
            break;
          case ShieldPowerup:
            handler.removeObject(object);
            hud.setShieldPowerupTimeLeft(((ShieldPowerup) object).getShieldTime());
            ShieldPowerup.playShieldSound();
            break;
          case TimeSlowPowerup:
            handler.removeObject(object);
            hud.setTimeSlowPowerupTimeLeft(((TimeSlowPowerup) object).getTimeSlowTime());
            TimeSlowPowerup.playTimeSlowSound();
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
          if (isShielded) {
            hud.setShieldPowerupTimeLeft(0);
            break;
          }
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
