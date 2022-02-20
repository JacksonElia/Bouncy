package com.traptricker;

import com.traptricker.inputs.KeyInput;
import com.traptricker.inputs.MouseInput;
import com.traptricker.objects.ID;
import com.traptricker.objects.Player;
import com.traptricker.objects.SmallEnemy;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * The class used to connect the whole java project
 * into a game.
 */
public class Game extends Canvas implements Runnable {

    public static int height = 800;
    public static int width = 1000;

    private Window window;
    private HUD hud;
    private Random random;
    private Thread thread;
    private Boolean running = false;

    private final Handler handler = new Handler();

    public Game() {
        // Tells the program to listen for key and mouse inputs
        this.addKeyListener(new KeyInput(handler));
        MouseInput mouseInput = new MouseInput(handler, window);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);

        window = new Window(this, height, width);
        hud = new HUD();
        this.random = new Random();

        // Adds a player object to the game
        handler.addObject(new Player(height / 2, width / 2, ID.Player, handler));

        // Adds 10 enemies to the game
        for (int i = 0; i < 20; i++) {
            handler.addObject(new SmallEnemy(random.nextInt(width), random.nextInt(height),
                    random.nextInt(10) - 5, random.nextInt(10) - 5, ID.SmallEnemy));
        }
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
        this.requestFocus();
        // Standard Java game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 100.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
//            System.out.println(Arrays.toString(window.getCursorLocation()));
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
        hud.tick();
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

        // Where the rendering happens
        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

}
