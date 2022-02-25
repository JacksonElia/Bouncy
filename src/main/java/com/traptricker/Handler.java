package com.traptricker;

import com.traptricker.objects.GameObject;
import com.traptricker.objects.ID;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * The class used to store, update, and render all the game objects.
 */
public class Handler {

  // Stores all the game objects
  public LinkedList<GameObject> objects = new LinkedList<>();

  public LinkedList<GameObject> objectsToRemove = new LinkedList<>();

  // Updates all the game objects
  public void tick() {
    // Safely removes objects
    removeObjects();
    // This for loop iterates through all the game objects
    for (GameObject object : objects) {
      object.tick();
    }
  }

  // Renders all the game objects
  public void render(Graphics g) {
    for (GameObject object : objects) {
      object.render(g);
    }
  }

  public void addObject(GameObject object) {
    this.objects.add(object);
  }

  public void removeObjects() {
    for (GameObject object : objectsToRemove) {
      this.objects.remove(object);
    }
  }

  public void removeAllNonPlayerObjects() {
    for (GameObject object : objects) {
      if (object.getID() != ID.Player) {
        objectsToRemove.add(object);
      }
    }
  }

}
