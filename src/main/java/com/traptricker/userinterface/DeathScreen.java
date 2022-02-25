package com.traptricker.userinterface;

import com.traptricker.Game;
import com.traptricker.datastorage.CSVManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

public class DeathScreen {

  public static int tryAgainButtonX = 2 * Game.width / 3 - 104;
  public static int tryAgainButtonY = 2 * Game.height / 3 + 16;
  public static int tryAgainButtonWidth = 168;
  public static int tryAgainButtonHeight = 52;
  public static int backToMenuButtonX = 2 * Game.width / 3 - 104;
  public static int backToMenuButtonY = 2 * Game.height / 3 + 82;
  public static int backToMenuButtonWidth = 168;
  public static int backToMenuButtonHeight = 52;

  private final HUD hud;

  public DeathScreen(HUD hud) {
    this.hud = hud;
  }

  public void tick() {

  }

  public void render(Graphics g) {
    // Death Message
    g.setColor(Color.white);
    g.fillRect(0, Game.height / 3 - 4, Game.width, 4);
    g.fillRect(0, 2 * Game.height / 3 - 4 - 50, Game.width, 4);
    g.setColor(Color.black);
    g.fillRect(0, Game.height / 3, Game.width, Game.height / 3 - 3 - 50);
    g.setColor(Color.red);
    g.setFont(new Font("Times New Roman", Font.PLAIN, 120));
    g.drawString("YOU DIED", Game.width / 2 - 320, Game.height / 3 + 145);

    // Bottom Boxes
    g.setColor(Color.white);
    g.fillRect(Game.width / 3 - 100, 2 * Game.height / 3, 200, 150);
    g.fillRect(2 * Game.width / 3 - 120, 2 * Game.height / 3, 200, 150);
    g.setColor(Color.black);
    g.fillRect(Game.width / 3 - 100 + 4, 2 * Game.height / 3 + 4, 200 - 8, 150 - 8);
    g.fillRect(2 * Game.width / 3 - 120 + 4, 2 * Game.height / 3 + 4, 200 - 8, 150 - 8);

    // Left Box
    g.setColor(Color.white);
    g.setFont(new Font("Sans Serif", Font.BOLD, 20));
    // Deals with high scores
    String[] nextLine = {"1", "1"};
    try {
      nextLine = CSVManager.readHighScore();
    } catch (IOException e) {
      e.printStackTrace();
    }
    g.drawString(String.format("Score: %d", hud.getScore()), Game.width / 3 - 84,
        2 * Game.height / 3 + 32);
    g.drawString(String.format("Level: %d", hud.getLevel()), Game.width / 3 - 84,
        2 * Game.height / 3 + 64);
    g.drawString(String.format("High Score: %s", nextLine[0]), Game.width / 3 - 84,
        2 * Game.height / 3 + 96);
    g.drawString(String.format("High Level: %s", nextLine[1]), Game.width / 3 - 84,
        2 * Game.height / 3 + 128);

    // Right Box
    g.fillRect(tryAgainButtonX, tryAgainButtonY, tryAgainButtonWidth, tryAgainButtonHeight);
    g.fillRect(backToMenuButtonX, backToMenuButtonY, backToMenuButtonWidth, backToMenuButtonHeight);
    g.setColor(Color.black);
    g.drawString("Try Again", 2 * Game.width / 3 - 120 + 16 + 37, 2 * Game.height / 3 + 16 + 33);
    g.drawString("Back to Menu", 2 * Game.width / 3 - 120 + 16 + 17,
        2 * Game.height / 3 + 16 + 66 + 33);
  }

}
