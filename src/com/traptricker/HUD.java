package com.traptricker;

import java.awt.*;

public class HUD {

    public static int health = 100;
    public static int score = 0;
    public static int level = 1;

    public void tick() {
        score++;
        level = Math.floorDiv(score, 1000);
    }

    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(20, 20, 300, 40);
        g.setColor(Color.red);
        g.fillRect(24, 24, 292 * health / 100, 32);
        g.setColor(Color.white);
        g.drawString(String.format("Score: %d", score), 20, 80);
        g.drawString(String.format("Level: %d", level), 20, 95);
    }

}
