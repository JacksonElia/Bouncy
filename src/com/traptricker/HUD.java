package com.traptricker;

import java.awt.*;

public class HUD {

    public static int health = 100;

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(20, 20, 300, 40);
        g.setColor(Color.red);
        g.fillRect(24, 24, 292 * health / 100, 32);
    }

}
