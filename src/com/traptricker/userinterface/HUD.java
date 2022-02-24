package com.traptricker.userinterface;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.objects.GameObject;
import com.traptricker.objects.ID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * This class handles all the in game User Interface or, Heads Up Display.
 */
public class HUD {

  public int health = 1000;
  public int score = 1;
  public int level = 1;

  private final Handler handler;
  private final Game game;

  public HUD(Handler handler, Game game) {
    this.handler = handler;
    this.game = game;
  }

  public void tick() {
    // Increases the score every tick and level every 1000 ticks
    score++;
    level = Math.floorDiv(score, 1000);
    if (health <= 0) {
      game.setInterface_state(INTERFACE_STATE.DeathScreen);
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
    g.fillRect(24, 24, 292 * health / 1000, 32);
    g.setColor(Color.white);
    g.setFont(new Font("Sans Serif", Font.PLAIN, 10));
    g.drawString(String.format("Score: %d", score), 20, 80);
    g.drawString(String.format("Level: %d", level), 20, 95);
  }

  public void resetValues() {
    health = 1000;
    score = 1;
    level = 1;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
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

}
