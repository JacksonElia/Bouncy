package com.traptricker;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(Game game) {
        JFrame jFrame = new JFrame("Bouncy");

        jFrame.setPreferredSize(new Dimension(800, 800));
        jFrame.setMaximumSize(new Dimension(800, 800));
        jFrame.setMinimumSize(new Dimension(800, 800));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null); // Makes the window start in the middle
        jFrame.add(game);
        jFrame.setVisible(true);
        game.startThread();
    }
}
