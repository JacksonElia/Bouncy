package com.traptricker;

import java.awt.*;

public class SmallEnemy extends GameObject {


    public SmallEnemy(int x, int y, int xVelocity, int yVelocity, ID id) {
        super(x, y, xVelocity, yVelocity, id);
    }

    @Override
    public void tick() {
        x += xVelocity;
        y += yVelocity;

        // Keeps the enemy on screen
        if (y <= 0 || y >= Game.height - 64) yVelocity *= -1;
        if (x <= 0 || x >= Game.width - 32) xVelocity *= -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 16, 16);
    }
}
