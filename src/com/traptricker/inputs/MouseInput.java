package com.traptricker.inputs;

import com.traptricker.Handler;
import com.traptricker.objects.GameObject;
import com.traptricker.objects.ID;
import com.traptricker.userinterface.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

  public boolean mouseOnWindow = false;

  private final Handler handler;

  public MouseInput(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    mouseOnWindow = true;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    mouseOnWindow = false;
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
