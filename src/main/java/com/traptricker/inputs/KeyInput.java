package com.traptricker.inputs;

import com.traptricker.Game;
import com.traptricker.userinterface.INTERFACE_STATE;
import com.traptricker.userinterface.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The class used to handle keyboard input.
 */
public class KeyInput extends KeyAdapter {

  private final Game game;
  private final Window window;

  public KeyInput(Game game, Window window) {
    this.game = game;
    this.window = window;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    // Switch statement that checks what key was pressed
    switch (e.getKeyCode()) {
      case KeyEvent.VK_ESCAPE:
        // Closes the game
        System.exit(1);
        break;
      case KeyEvent.VK_R:
        // Starts the game/resets the current game
        game.setInterface_state(INTERFACE_STATE.Game);
        break;
      case KeyEvent.VK_F11:
        // Puts application in or out of full screen
        window.changeFullScreen();
        window.fullScreen();
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    super.keyReleased(e);
    int key = e.getKeyCode();
  }

}
