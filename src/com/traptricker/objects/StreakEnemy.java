package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.Handler;

import java.awt.*;

public class StreakEnemy extends GameObject {

    public START_SIDE start_side;

    private Handler handler;

    public enum START_SIDE {
        up(),
        right(),
        down(),
        left()
    }

    public StreakEnemy(int x, int y, int xVelocity, int yVelocity, int radius, ID id, Handler handler, START_SIDE start_side) {
        super(x, y, xVelocity, yVelocity, radius, id);
        this.handler = handler;
        this.start_side = start_side;
    }

    @Override
    public void tick() {
        x += xVelocity;
        y += yVelocity;

        // Kills the enemy after it goes off-screen
        switch (start_side) {
            case up:
                if (x < -10 || x > Game.width || y > Game.height) handler.objectsToRemove.add(this);
            case right:
                if (x < -10 || y < -10 || y > Game.height) handler.objectsToRemove.add(this);
            case down:
                if (x < -10 || x > Game.width || y < -10) handler.objectsToRemove.add(this);
            case left:
                if (x > Game.width || y < -10 || y > Game.height) handler.objectsToRemove.add(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, radius * 2, radius * 2);
    }
}