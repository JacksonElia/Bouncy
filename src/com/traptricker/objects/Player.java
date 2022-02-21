package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.HUD;
import com.traptricker.Handler;

import java.awt.*;

/**
 * This is the class for the player object, it inherits
 * from the GameObject class.
 */
public class Player extends GameObject {

    public static int radius = 24;

    private Handler handler;
    private HUD hud;

    public Player(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, 0, 0, id);
        this.handler = handler;
        this.hud = hud;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        // Keeps the player on screen
        if (x <= 0) this.x = 0;
        if (x >= Game.width - 70) this.x = Game.width - 70;
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        // Keeps the player on screen
        if (y <= 0) this.y = 0;
        if (y >= Game.height - 96) this.y = Game.height - 96;
    }

    @Override
    public void tick() {
        // Collision
        for (GameObject object : handler.objects) {
            if (((this.getX() - object.getX()) * (this.getX() - object.getX()) +
                    (this.getY() - object.getY()) * (this.getY() - object.getY()))
                    <= ((radius + 8) * (radius + 8))) {
                if (object.getId() == ID.SmallEnemy) {
                    hud.setHealth(hud.getHealth() - 10);
                    handler.objectsToRemove.add(object);
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
