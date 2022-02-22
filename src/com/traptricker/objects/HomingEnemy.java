package com.traptricker.objects;


import java.awt.*;

public class HomingEnemy extends GameObject {

    private Player player;

    public HomingEnemy(int x, int y, int xVelocity, int yVelocity, int radius, ID id, Player player) {
        super(x, y, xVelocity, yVelocity, radius, id);
        this.player = player;
    }

    @Override
    public void tick() {
        // Has + radius - 12 to get the homing enemy to target the center of the player
        int xDifference = player.getX() + player.getRadius() - 12 - x;
        int yDifference = player.getY() + player.getRadius() - 12 - y;
        double distance = (int) Math.sqrt(xDifference * xDifference + yDifference * yDifference);
        x += xVelocity * Math.round(xDifference / distance);
        y += yVelocity * Math.round(yDifference / distance);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, radius * 2, radius * 2);
    }
}
