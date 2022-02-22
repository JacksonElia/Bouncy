package com.traptricker.objects;

import java.awt.Graphics;

/**
 * The class used to create all the objects in the game. It should have the basic variables and
 * methods that every game object has, e.g. x and y position
 */
public abstract class GameObject {

  protected int x, y, xVelocity, yVelocity, radius;
  protected ID id;

  public GameObject(int x, int y, int xVelocity, int yVelocity, int radius, ID id) {
    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.radius = radius;
    this.id = id;
  }

  // Updates the object
  public abstract void tick();

  // Renders the object
  public abstract void render(Graphics g);

  public void setId(ID id) {
    this.id = id;
  }

  public int getX() {
    return x;
  }

  // Getter and setter methods
  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getXVelocity() {
    return xVelocity;
  }

  public void setXVelocity(int xVelocity) {
    this.xVelocity = xVelocity;
  }

  public int getYVelocity() {
    return yVelocity;
  }

  public void setYVelocity(int yVelocity) {
    this.yVelocity = yVelocity;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public ID getID() {
    return id;
  }

}
