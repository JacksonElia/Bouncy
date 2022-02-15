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
    }

    @Override
    public void render(Graphics g) {
        // Makes a stupid looking player
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
        g.fillRect(x + 12, y, 8, 96);
        g.fillRect(x + 4,y + 88, 8, 32);
        g.fillRect(x + 20, y + 88, 8, 32);
        g.fillRect(x, y + 56, 32, 8);
    }

}
