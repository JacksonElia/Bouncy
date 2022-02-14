package com.traptricker;

import java.awt.*;

/**
 * This is an example of a game object, it inherits
 * from the GameObject class.
 */
public class Player extends GameObject {

    private final int xVelocity = 2;
    private final int yVelocity = 1;

    /**
     * Set initial properties the object
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
        // e.g.
        setX(x + 300);
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
