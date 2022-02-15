package com.traptricker;

import java.awt.*;

/**
 * The class used to create all the objects in the game.
 * It should have the basic variables and methods that every
 * game object has, e.g. x and y position
 */
public abstract class GameObject {

    protected int x, y, xVelocity, yVelocity;
    protected ID id;

    public GameObject(int x, int y, int xVelocity, int yVelocity, ID id) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.id = id;
    }

    // Updates the object
    public abstract void tick();
    // Renders the object
    public abstract void render(Graphics g);

    // Getter and setter methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public ID getId() {
        return id;
    }

}
