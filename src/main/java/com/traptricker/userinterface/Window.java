package com.traptricker.userinterface;

import com.traptricker.Game;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * This class takes care of the base window.
 */
public class Window extends Canvas {

  public JFrame jFrame;
  public Boolean isFullscreen = false;

  private final Game game;

  public Window(Game game, int width, int height) {
    jFrame = new JFrame("Bouncy");
    this.game = game;

    jFrame.setPreferredSize(new Dimension(width, height));
    jFrame.setMaximumSize(new Dimension(width, height));
    jFrame.setMinimumSize(new Dimension(width, height));
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setResizable(true);
    jFrame.setLocationRelativeTo(null); // Makes the window start in the middle
    jFrame.add(game);
    jFrame.setVisible(true);
    game.startThread();
  }

  public void showCursor() {
    jFrame.getContentPane().setCursor(Cursor.getDefaultCursor());
  }

  public void hideCursor() {
    // Transparent 16 x 16 pixel cursor image.
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    // Create a new blank cursor.
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        cursorImg, new Point(0, 0), "blank cursor");
    // Set the blank cursor to the JFrame.
    jFrame.getContentPane().setCursor(blankCursor);
  }

  // Puts the window in or out of fullscreen
  public void fullScreen() {
    jFrame.dispose();
    if (isFullscreen) {
      jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      jFrame.setUndecorated(true);
    } else {
      jFrame.setExtendedState(JFrame.NORMAL);
      jFrame.setUndecorated(false);
    }
    jFrame.setVisible(true);
    game.requestFocus();
  }

  public void changeFullScreen() {
    isFullscreen = !isFullscreen;
  }

}
