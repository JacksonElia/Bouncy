package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.sound.SoundPlayer;
import com.traptricker.userinterface.Window;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

public class BasicEnemy extends GameObject {

  // Gets the assets used by every BasicEnemy
  private static final File bounceSound = new File("src/main/resources/ball-bounce.wav");
  private static final Image enemyGif = new ImageIcon("src/main/resources/transparency_test.gif").getImage();

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
    // Draws the gif for the enemy
    g.drawImage(enemyGif, x, y, radius * 2, radius * 2, Window.jFrame);
  }
}
