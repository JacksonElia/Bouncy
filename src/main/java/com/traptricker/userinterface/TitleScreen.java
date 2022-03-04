package com.traptricker.userinterface;

import com.traptricker.Game;
import com.traptricker.objects.Spawner;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

/**
 * This class renders and updates the screen seen when the player loads up the game.
 */
public class TitleScreen {

  public static int playButtonX;
  public static int playButtonY;
  public static int playButtonWidth = 300;
  public static int playButtonHeight = 100;
  public static int quitButtonX;
  public static int quitButtonY;
  public static int quitButtonWidth = 300;
  public static int quitButtonHeight = 100;

  private final Game game;
  private final Spawner spawner;
  private Font gameFont;

  public TitleScreen(Game game, Spawner spawner) {
    this.game = game;
    this.spawner = spawner;
    updateValues();
    for (int i = 0; i < 20; i++) {
      spawner.spawnTitleScreenEnemy();
    }

    // Adds the custom font
    try {
      gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/aAkhirTahun.ttf"))
          .deriveFont(20f);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void tick() {
    updateValues();
  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.setFont(gameFont.deriveFont(100f));
    g.drawString("Bouncy", game.getWidth() / 2 - 200, game.getHeight() / 6);
    g.fillRect(playButtonX, playButtonY, playButtonWidth, playButtonHeight);
    g.fillRect(quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight);
    g.setFont(gameFont.deriveFont(30f));
    g.drawString("Made by Traptricker", game.getWidth() / 2 - 185, game.getHeight() - 90);

    g.setColor(Color.black);
    g.setFont(gameFont.deriveFont(60f));
    g.drawString("Play", game.getWidth() / 2 - 80, game.getHeight() / 4 + 72);
    g.drawString("Quit", game.getWidth() / 2 - 75, game.getHeight() / 2 + 72);
  }

  private void updateValues() {
    playButtonX = game.getWidth() / 2 - 150;
    playButtonY = game.getHeight() / 4;
    quitButtonX = game.getWidth() / 2 - 150;
    quitButtonY = game.getHeight() / 2;
  }

}
