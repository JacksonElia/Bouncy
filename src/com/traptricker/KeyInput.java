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

        // Example for how to use input to move the player
        for (GameObject object : handler.objects) {
            if (object.getId() == ID.Player) {
                Player player = (Player) object;
                if (key == KeyEvent.VK_W) player.setYVelocity(-5);
                if (key == KeyEvent.VK_S) player.setYVelocity(5);
                if (key == KeyEvent.VK_A) player.setXVelocity(-5);
                if (key == KeyEvent.VK_D) player.setXVelocity(5);
            }
        }
    }

    // Called whenever a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        int key = e.getKeyCode();
        // Example for how to use input to move the player
        for (GameObject object : handler.objects) {
            if (object.getId() == ID.Player) {
                Player player = (Player) object;
                if (key == KeyEvent.VK_W) player.setYVelocity(0);
                if (key == KeyEvent.VK_S) player.setYVelocity(0);
                if (key == KeyEvent.VK_A) player.setXVelocity(0);
                if (key == KeyEvent.VK_D) player.setXVelocity(0);
            }
        }
    }

}
