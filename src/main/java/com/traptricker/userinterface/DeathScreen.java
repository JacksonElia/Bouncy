package com.traptricker.userinterface;

import com.traptricker.Game;
import com.traptricker.datastorage.CSVManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

/**
 * This class renders and updates the screen seen when the player dies.
 */
public class DeathScreen {

  public static int tryAgainButtonX;
  public static int tryAgainButtonY;
  public static int tryAgainButtonWidth = 168;
  public static int tryAgainButtonHeight = 52;
  public static int backToMenuButtonX;
  public static int backToMenuButtonY;
  public static int backToMenuButtonWidth = 168;
  public static int backToMenuButtonHeight = 52;

  private final Game game;
  private final HUD hud;

  public DeathScreen(Game game, HUD hud) {
    this.game = game;
    this.hud = hud;

    updateValues();
  }

  public void tick() {
    updateValues();
  }

  public void render(Graphics g) {
    // Death Message
    g.setColor(Color.white);
    g.fillRect(0, game.getHeight() / 3 - 4, game.getWidth(), 4);
    g.fillRect(0, 2 * game.getHeight() / 3 - 4 - 50, game.getWidth(), 4);
    g.setColor(Color.black);
    g.fillRect(0, game.getHeight() / 3, game.getWidth(), game.getHeight() / 3 - 3 - 50);
    g.setColor(Color.red);
    g.setFont(new Font("Times New Roman", Font.PLAIN, 120));
    g.drawString("YOU DIED", game.getWidth() / 2 - 320, game.getHeight() / 3 + 145);

    // Bottom Boxes
    g.setColor(Color.white);
    g.fillRect(game.getWidth() / 3 - 100, 2 * game.getHeight() / 3, 200, 150);
    g.fillRect(2 * game.getWidth() / 3 - 120, 2 * game.getHeight() / 3, 200, 150);
    g.setColor(Color.black);
    g.fillRect(game.getWidth() / 3 - 100 + 4, 2 * game.getHeight() / 3 + 4, 200 - 8, 150 - 8);
    g.fillRect(2 * game.getWidth() / 3 - 120 + 4, 2 * game.getHeight() / 3 + 4, 200 - 8, 150 - 8);

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
    g.drawString(String.format("Score: %d", hud.getScore()), game.getWidth() / 3 - 84,
        2 * game.getHeight() / 3 + 32);
    g.drawString(String.format("Level: %d", hud.getLevel()), game.getWidth() / 3 - 84,
        2 * game.getHeight() / 3 + 64);
    g.drawString(String.format("High Score: %s", nextLine[0]), game.getWidth() / 3 - 84,
        2 * game.getHeight() / 3 + 96);
    g.drawString(String.format("High Level: %s", nextLine[1]), game.getWidth() / 3 - 84,
        2 * game.getHeight() / 3 + 128);

    // Right Box
    g.fillRect(tryAgainButtonX, tryAgainButtonY, tryAgainButtonWidth, tryAgainButtonHeight);
    g.fillRect(backToMenuButtonX, backToMenuButtonY, backToMenuButtonWidth, backToMenuButtonHeight);
    g.setColor(Color.black);
    g.drawString("Try Again", 2 * game.getWidth() / 3 - 120 + 16 + 37,
        2 * game.getHeight() / 3 + 16 + 33);
    g.drawString("Back to Menu", 2 * game.getWidth() / 3 - 120 + 16 + 17,
        2 * game.getHeight() / 3 + 16 + 66 + 33);
  }

  private void updateValues() {
    tryAgainButtonX = 2 * game.getWidth() / 3 - 104;
    tryAgainButtonY = 2 * game.getHeight() / 3 + 16;
    backToMenuButtonX = 2 * game.getWidth() / 3 - 104;
    backToMenuButtonY = 2 * game.getHeight() / 3 + 82;
  }

}
