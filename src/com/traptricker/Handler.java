package com.traptricker;

import java.awt.*;
import java.util.LinkedList;

/**
 * The class used to store, update, and render all
 * the game objects.
 */
public class Handler {

    // Stores all the game objects that we have
    LinkedList<GameObject> objects = new LinkedList<>();

    // Updates all the game objects
    public void tick() {
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

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

}
