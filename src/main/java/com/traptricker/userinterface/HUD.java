package com.traptricker.userinterface;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.datastorage.CSVManager;
import com.traptricker.objects.GameObject;
import com.traptricker.objects.ID;
import com.traptricker.objects.TimeSlowPowerup;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

/**
 * This class handles all the in game User Interface or, Heads Up Display.
 */
public class HUD {

  public static int healthMax = 1000;

  public int health = 1000;
  public int score = 1;
  public int level = 1;
  public int shrinkPowerupTimeLeft = 0;
  public int shieldPowerupTimeLeft = 0;
  public int timeSlowPowerupTimeLeft = 0;

  private final Handler handler;
  private final Game game;
  private Font gameFont;

  public HUD(Handler handler, Game game) {
    this.handler = handler;
    this.game = game;

    // Adds the custom font
    try {
      gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/aAkhirTahun.ttf"))
          .deriveFont(20f);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void tick() {
    // Increases the score every tick and level every 1000 ticks
    score++;
    level = Math.floorDiv(score, 1000) + 1;
    // Has powerup time left count down
    if (game.getTick() == 1) {
      shrinkPowerupTimeLeft--;
      shieldPowerupTimeLeft--;
    }
    if (game.getTick() % TimeSlowPowerup.timeSlowTicksPerSecond == 0) {
      timeSlowPowerupTimeLeft--;
    }
    if (health <= 0) {
      game.setInterface_state(INTERFACE_STATE.DeathScreen);
      String[] nextLine;
      // Stores the high score and level
      try {
        nextLine = CSVManager.readHighScore();
        int highScore = Integer.parseInt(nextLine[0]);
        int highLevel = Integer.parseInt(nextLine[1]);
        if (highScore < score) {
          highScore = score;
        }
        if (highLevel < level) {
          highLevel = level;
        }
        CSVManager.writeHighScore(highScore, highLevel);
      } catch (IOException e) {
        e.printStackTrace();
      }

      // Kills the player
      for (GameObject object : handler.objects) {
        if (object.getID() == ID.Player) handler.objectsToRemove.add(object);
      }
    }
  }

  public void render(Graphics g) {
    // Draws out the HUD
    g.setColor(Color.darkGray);
    g.fillRect(20, 20, 300, 40);
    g.setColor(Color.red);
    g.fillRect(24, 24, 292 * health / healthMax, 32);
    g.setColor(Color.white);
    g.setFont(gameFont.deriveFont(20f));
    g.drawString(String.format("Score: %d", score), 20, 80);
    g.drawString(String.format("Level: %d", level), 20, 100);
    if (shrinkPowerupTimeLeft > 0) {
      g.drawString(String.format("Shrink left: %d", shrinkPowerupTimeLeft), game.getWidth() - 200,
          36);
    }
    if (shieldPowerupTimeLeft > 0) {
      g.drawString(String.format("Shield left: %d", shieldPowerupTimeLeft), game.getWidth() - 200,
          72);
    }
    if (timeSlowPowerupTimeLeft > 0) {
      g.drawString(String.format("Time Slow left: %d", timeSlowPowerupTimeLeft), game.getWidth() - 220,
          108);
    }
  }

  public void resetValues() {
    health = 1000;
    score = 1;
    level = 1;
    shrinkPowerupTimeLeft = 0;
    shieldPowerupTimeLeft = 0;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    // Makes sure the health can't go over the max
    this.health = Math.min(health, healthMax);
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getShrinkPowerupTimeLeft() {
    return shrinkPowerupTimeLeft;
  }

  public void setShrinkPowerupTimeLeft(int shrinkPowerupTimeLeft) {
    this.shrinkPowerupTimeLeft = shrinkPowerupTimeLeft;
  }

  public int getShieldPowerupTimeLeft() {
    return shieldPowerupTimeLeft;
  }

  public void setShieldPowerupTimeLeft(int shieldPowerupTimeLeft) {
    this.shieldPowerupTimeLeft = shieldPowerupTimeLeft;
  }

  public int getTimeSlowPowerupTimeLeft() {
    return timeSlowPowerupTimeLeft;
  }

  public void setTimeSlowPowerupTimeLeft(int timeSlowPowerupTimeLeft) {
    this.timeSlowPowerupTimeLeft = timeSlowPowerupTimeLeft;
  }

}
