package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

public class BasicEnemy extends GameObject {

  private static final File bounceSound = new File("src/main/resources/ball-bounce.wav");

  public BasicEnemy(Game game, int x, int y, int xVelocity, int yVelocity, int radius, ID id) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
  }

  @Override
  public void tick() {
    // Keeps the enemy on screen
    if (x <= 0 || x >= game.getWidth() - radius * 2) {
      xVelocity *= -1;
      SoundPlayer.playSound(bounceSound, -15f);
    }
    if (y <= 0 || y >= game.getHeight() - radius * 2) {
      yVelocity *= -1;
      SoundPlayer.playSound(bounceSound, -15f);
    }

    x += xVelocity;
    y += yVelocity;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.cyan);
    g.fillRect(x, y, radius * 2, radius * 2);
  }
}
