package com.traptricker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The class used to handle keyboard input.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    // Called whenever a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        // This int corresponds to the key pressed
        int key = e.getKeyCode();
    }

    // Called whenever a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        int key = e.getKeyCode();
    }

}
