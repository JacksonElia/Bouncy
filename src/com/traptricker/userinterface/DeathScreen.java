package com.traptricker.userinterface;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DeathScreen {

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
    g.drawString(String.format("Score: %d", hud.getScore()), Game.width / 3 - 100 + 16,2 * Game.height / 3 + 32);
    g.drawString("High Score: %d", Game.width / 3 - 100 + 16,2 * Game.height / 3 + 80);

    // Right Box
    g.fillRect(2 * Game.width / 3 - 120 + 16, 2 * Game.height / 3 + 16, 200 - 32, 52);
    g.fillRect(2 * Game.width / 3 - 120 + 16, 2 * Game.height / 3 + 16 + 66, 200 - 32, 52);
    g.setColor(Color.black);
    g.drawString("Try Again", 2 * Game.width / 3 - 120 + 16 + 37, 2 * Game.height / 3 + 16 + 33);
    g.drawString("Back to Menu", 2 * Game.width / 3 - 120 + 16 + 17, 2 * Game.height / 3 + 16 + 66 + 33);
  }

}
