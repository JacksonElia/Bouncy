package com.traptricker.objects;

import com.traptricker.Game;

import java.awt.*;

/**
 * This is the class for the player object, it inherits
 * from the GameObject class.
 */
public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, 0, 0, id);
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
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 48, 48);
    }

}
