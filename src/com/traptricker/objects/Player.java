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

  public Player(int x, int y, int radius, ID id, Handler handler, HUD hud) {
    super(x, y, 0, 0, radius, id);
    this.handler = handler;
    this.hud = hud;
  }

  @Override
  public void setX(int x) {
    super.setX(x);
    // Keeps the player on screen
    if (x <= 0) {
      this.x = 0;
    }
    if (x >= Game.width - 70) {
      this.x = Game.width - 70;
    }
  }

  @Override
  public void setY(int y) {
    super.setY(y);
    // Keeps the player on screen
    if (y <= 0) {
      this.y = 0;
    }
    if (y >= Game.height - 96) {
      this.y = Game.height - 96;
    }
  }

  @Override
  public void tick() {
    // Collision
    for (GameObject object : handler.objects) {
      // Uses circular collision
      if (((x + radius - object.getX() - object.getRadius()) * (x + radius - object.getX()
          - object.getRadius()) + ((y + radius - object.getY() - object.getRadius()) * (y + radius
          - object.getY() - object.getRadius())) <= ((radius + object.getRadius()) * (radius
          + object.getRadius())))) {
        if (object.getID() == ID.BasicEnemy) {
          hud.setHealth(hud.getHealth() - 100);
          handler.objectsToRemove.add(object);
        } else if (object.getID() == ID.StreakEnemy) {
          hud.setHealth(hud.getHealth() - 300);
          handler.objectsToRemove.add(object);
        } else if (object.getID() == ID.HomingEnemy) {
          hud.setHealth(hud.getHealth() - 2);
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillOval(x, y, radius * 2, radius * 2);
  }

}
