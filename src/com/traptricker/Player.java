package com.traptricker;

import java.awt.*;

/**
 * This is an example of a game object, it inherits
 * from the GameObject class.
 */
public class Player extends GameObject {

    /**
     * Set initial properties the object
     */
    public Player(int x, int y, ID id) {
        super(x, y, 0, 0, id);
    }

    @Override
    public void tick() {
        x += xVelocity;
        y += yVelocity;

        if (x <= 0) x = 0;
        if (x >= Game.width - 70) x = Game.width - 70;
        if (y <= 0) y = 0;
        if (y >= Game.height - 96) y = Game.height - 96;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 48, 48);
    }

}
