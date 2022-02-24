package com.traptricker.inputs;

import com.traptricker.Game;
import com.traptricker.Handler;
import com.traptricker.objects.GameObject;
import com.traptricker.objects.ID;
import com.traptricker.userinterface.DeathScreen;
import com.traptricker.userinterface.INTERFACE_STATE;
import com.traptricker.userinterface.TitleScreen;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The class used to handle mouse input.
 */
public class MouseInput implements MouseListener, MouseMotionListener {

  private final Handler handler;
  private final Game game;

  public MouseInput(Handler handler, Game game) {
    this.game = game;
    this.handler = handler;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int button = e.getButton();
    if (button == MouseEvent.BUTTON1) {
      if (game.getInterface_state() == INTERFACE_STATE.TitleScreen) {
        // Checks if user clicked the play button
        if (e.getX() >= TitleScreen.playButtonX
            && e.getX() <= TitleScreen.playButtonX + TitleScreen.playButtonWidth
            && e.getY() >= TitleScreen.playButtonY
            && e.getY() <= TitleScreen.playButtonY + TitleScreen.playButtonHeight) {
          game.setInterface_state(INTERFACE_STATE.Game);
        }
        // Checks if user clicked the quit button
        else if (e.getX() >= TitleScreen.quitButtonX
            && e.getX() <= TitleScreen.quitButtonX + TitleScreen.quitButtonWidth
            && e.getY() >= TitleScreen.quitButtonY
            && e.getY() <= TitleScreen.quitButtonY + TitleScreen.quitButtonHeight) {
          System.exit(1);
        }

      } else if (game.getInterface_state() == INTERFACE_STATE.DeathScreen) {
        // Checks if user clicked the try again button
        if (e.getX() >= DeathScreen.tryAgainButtonX
            && e.getX() <= DeathScreen.tryAgainButtonX + DeathScreen.tryAgainButtonWidth
            && e.getY() >= DeathScreen.tryAgainButtonY
            && e.getY() <= DeathScreen.tryAgainButtonY + DeathScreen.tryAgainButtonHeight) {
          game.setInterface_state(INTERFACE_STATE.Game);
        }
        // Checks if user clicked the back to menu button
        else if (e.getX() >= DeathScreen.backToMenuButtonX
            && e.getX() <= DeathScreen.backToMenuButtonX + DeathScreen.backToMenuButtonWidth
            && e.getY() >= DeathScreen.backToMenuButtonY
            && e.getY() <= DeathScreen.backToMenuButtonY + DeathScreen.backToMenuButtonHeight) {
          game.setInterface_state(INTERFACE_STATE.TitleScreen);
        }

      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // Keeps the player moving while dragging
    mouseMoved(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // Moves the player to the mouse's position
    for (GameObject object : handler.objects) {
      if (object.getID() == ID.Player) {
        object.setX(e.getX() - 24);
        object.setY(e.getY() - 24);
      }
    }
  }

}
