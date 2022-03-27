package com.traptricker.graphics;

import com.traptricker.Game;
import com.traptricker.userinterface.HUD;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

public class BackgroundEffects {

  private final Game game;
  private final HUD hud;
  private Font gameFont;
  private int currentLevel = 1;
  private int levelUpMessageLifespan = 200;

  public BackgroundEffects(Game game, HUD hud) {
    this.game = game;
    this.hud = hud;

    // Adds the custom font
    try {
      gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/aAkhirTahun.ttf"))
          .deriveFont(20f);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void tick() {
    if (currentLevel != hud.getLevel()) {
      levelUpMessageLifespan--;
      if (levelUpMessageLifespan <= 0) {
        currentLevel = hud.getLevel();
        levelUpMessageLifespan = 200;
      }
    }
  }

  public void render(Graphics g) {
    if (currentLevel != hud.getLevel()) {
      levelUpMessage(g);
    }
  }

  private void levelUpMessage(Graphics g) {
    g.setFont(gameFont.deriveFont(100f));
    Color color;
    if (game.getTick() < 25) {
      color = new Color(40, 7, 20, 255);
    } else if (game.getTick() < 50) {
      color = Color.black;
    } else if (game.getTick() < 75) {
      color = new Color(20, 7, 40, 255);
    } else {
      color = Color.black;
    }
    g.setColor(color);
    g.fillRoundRect(game.getWidth() / 2 - 300, game.getHeight() / 2 - 100, 600, 200, 50, 50);
    g.setColor(Color.black);
    g.fillRoundRect(game.getWidth() / 2 - 280, game.getHeight() / 2 - 80, 560, 160, 50, 50);
    g.setColor(color);
    g.drawString("Level Up!", game.getWidth() / 2 - 263, game.getHeight() / 2 + 38);
  }

}
