package com.traptricker;

import java.awt.*;

/**
 * This class handles all the in game User Interface
 * or, Heads Up Display.
 */
public class HUD {

    public int health = 100;
    public int score = 0;
    public int level = 1;

    public void tick() {
        // Increases the score every tick and level every 1000 ticks
        score++;
        level = Math.floorDiv(score, 1000);
    }

    public void render(Graphics g) {
        // Draws out the HUD
        g.setColor(Color.darkGray);
        g.fillRect(20, 20, 300, 40);
        g.setColor(Color.red);
        g.fillRect(24, 24, 292 * health / 100, 32);
        g.setColor(Color.white);
        g.drawString(String.format("Score: %d", score), 20, 80);
        g.drawString(String.format("Level: %d", level), 20, 95);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

}
