package com.traptricker.userinterface;

import com.traptricker.Game;
import com.traptricker.objects.Spawner;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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

  public TitleScreen(Game game, Spawner spawner) {
    this.game = game;
    this.spawner = spawner;
    updateValues();
    for (int i = 0; i < 20; i++) {
      spawner.spawnTitleScreenEnemy();
    }
  }

  public void tick() {
    updateValues();
  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.setFont(new Font("Sans Serif", Font.BOLD, 90));
    g.drawString("Bouncy", game.getWidth() / 2 - 162, game.getHeight() / 6);
    g.fillRect(playButtonX, playButtonY, playButtonWidth, playButtonHeight);
    g.fillRect(quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight);
    g.setFont(new Font("Sans Serif", Font.BOLD, 40));
    g.drawString("Made by Traptricker", game.getWidth() / 2 - 190, game.getHeight() - 90);

    g.setColor(Color.black);
    g.setFont(new Font("Sans Serif", Font.BOLD, 70));
    g.drawString("Play", game.getWidth() / 2 - 70, game.getHeight() / 4 + 75);
    g.drawString("Quit", game.getWidth() / 2 - 70, game.getHeight() / 2 + 75);
  }

  private void updateValues() {
    playButtonX = game.getWidth() / 2 - 150;
    playButtonY = game.getHeight() / 4;
    quitButtonX = game.getWidth() / 2 - 150;
    quitButtonY = game.getHeight() / 2;
  }

}
