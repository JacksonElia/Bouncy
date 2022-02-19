package com.traptricker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    public boolean mouseOnWindow = false;

    private Handler handler;
    private Window window;

    public MouseInput(Handler handler, Window window) {
        this.handler = handler;
        this.window = window;
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Moves the player to the mouse's position
        for (GameObject object : handler.objects) {
            if (object.getId() == ID.Player) {
                object.setX(e.getX() - 16);
                object.setY(e.getY() - 16);
            }
        }
    }

}
