package com.traptricker.userinterface;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleScreen {

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.setFont(new Font("SansSerif", Font.BOLD, 90));
    g.drawString("Bouncy", Game.width / 2 - 162, Game.height / 6);
    g.fillRect(Game.width / 2 - 150, Game.height / 4, 300, 100);
    g.fillRect(Game.width / 2 - 150, Game.height / 2, 300, 100);
    g.setFont(new Font("SansSerif", Font.BOLD, 40));
    g.drawString("Made by Traptricker", Game.width / 2 - 190, Game.height - 90);

    g.setColor(Color.black);
    g.setFont(new Font("SansSerif", Font.BOLD, 70));
    g.drawString("Play", Game.width / 2 - 70, Game.height / 4 + 75);
    g.drawString("Quit", Game.width / 2 - 70, Game.height / 2 + 75);

  }

}
