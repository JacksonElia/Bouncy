package com.traptricker.userinterface;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleScreen {

  public static int playButtonX = Game.width / 2 - 150;
  public static int playButtonY = Game.height / 4;
  public static int playButtonWidth = 300;
  public static int playButtonHeight = 100;
  public static int quitButtonX = Game.width / 2 - 150;
  public static int quitButtonY = Game.height / 2;
  public static int quitButtonWidth = 300;
  public static int quitButtonHeight = 100;

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.setFont(new Font("Sans Serif", Font.BOLD, 90));
    g.drawString("Bouncy", Game.width / 2 - 162, Game.height / 6);
    g.fillRect(playButtonX, playButtonY, playButtonWidth, playButtonHeight);
    g.fillRect(quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight);
    g.setFont(new Font("Sans Serif", Font.BOLD, 40));
    g.drawString("Made by Traptricker", Game.width / 2 - 190, Game.height - 90);

    g.setColor(Color.black);
    g.setFont(new Font("Sans Serif", Font.BOLD, 70));
    g.drawString("Play", Game.width / 2 - 70, Game.height / 4 + 75);
    g.drawString("Quit", Game.width / 2 - 70, Game.height / 2 + 75);
  }

}
