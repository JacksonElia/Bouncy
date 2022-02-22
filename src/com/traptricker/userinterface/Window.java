package com.traptricker.userinterface;

import com.traptricker.Game;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Window extends Canvas {

  public JFrame jFrame;

  public Window(Game game, int height, int width) {
    jFrame = new JFrame("Bouncy");

    jFrame.setPreferredSize(new Dimension(width, height));
    jFrame.setMaximumSize(new Dimension(width, height));
    jFrame.setMinimumSize(new Dimension(width, height));
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setResizable(false);
    jFrame.setLocationRelativeTo(null); // Makes the window start in the middle
    jFrame.add(game);
    jFrame.setVisible(true);

    // Transparent 16 x 16 pixel cursor image.
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    // Create a new blank cursor.
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        cursorImg, new Point(0, 0), "blank cursor");
    // Set the blank cursor to the JFrame.
    jFrame.getContentPane().setCursor(blankCursor);

    game.startThread();
  }

}
