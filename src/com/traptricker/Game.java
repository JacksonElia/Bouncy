package com.traptricker;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * The class used to connect the whole java project
 * into a game.
 */
public class Game extends Canvas implements Runnable {

    public static int height = 800;
    public static int width = 1000;

    private Thread thread;
    private Boolean running = false;

    private final Handler handler = new Handler();

    public Game() {
        // Tells the program to listen for key inputs
        this.addKeyListener(new KeyInput(handler));
        // Makes the window
        new Window(this, height, width);
        // Adds a player object to the game
        handler.addObject(new Player(100, 100, ID.Player));
        handler.addObject(new SmallEnemy(100, 100, 5, 5, ID.SmallEnemy));
    }

    public static void main(String[] args) {
        new Game();
    }

    public synchronized void startThread() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stopThread() {
        try {
            thread.join();
            running = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a commonly-used game loop in Java. It is
     * continuously updating and rendering the game.
     */
    @Override
    public void run() {
        // Standard Java game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            // The game is being updated amountOfTicks (60) times per second
            while (delta >= 1) {
                tick();
                delta--;
            }
            // The game is being rendered as much as possible
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stopThread();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Covering the window with a black box
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        handler.render(g);

        g.dispose();
        bs.show();
    }

}
