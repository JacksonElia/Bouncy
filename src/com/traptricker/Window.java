package com.traptricker;

import javax.swing.*;
import java.awt.*;

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
        game.startThread();
    }

}
