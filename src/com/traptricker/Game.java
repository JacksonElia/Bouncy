package com.traptricker;

import com.traptricker.inputs.KeyInput;
import com.traptricker.inputs.MouseInput;
import com.traptricker.objects.ID;
import com.traptricker.objects.Player;
import com.traptricker.objects.Spawner;
import com.traptricker.userinterface.HUD;
import com.traptricker.userinterface.INTERFACE_STATE;
import com.traptricker.userinterface.TitleScreen;
import com.traptricker.userinterface.Window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * The class used to connect the whole java project into a game.
 */
public class Game extends Canvas implements Runnable {

  public static int height = 800;
  public static int width = 1000;

  public INTERFACE_STATE interface_state;

  private final Handler handler;
  private final HUD hud;
  private final Spawner spawner;
  private final TitleScreen titleScreen;

  private Boolean running = false;
  private Thread thread;

  public Game() {
    handler = new Handler();
    hud = new HUD();
    spawner = new Spawner(handler, hud);
    titleScreen = new TitleScreen();

    new Window(this, height, width);

    interface_state = INTERFACE_STATE.TitleScreen;

    // Tells the program to listen for key and mouse inputs
    this.addKeyListener(new KeyInput(handler));
    MouseInput mouseInput = new MouseInput(handler);
    this.addMouseListener(mouseInput);
    this.addMouseMotionListener(mouseInput);

    if (interface_state == INTERFACE_STATE.Game) {
      // Adds a player object to the game
      handler.addObject(new Player(height / 2, width / 2, 24, ID.Player, handler, hud));
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This is a commonly-used game loop in Java. It is continuously updating and rendering the game.
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
    if (interface_state == INTERFACE_STATE.Game) {
      hud.tick();
      spawner.tick();
    } else if (interface_state == INTERFACE_STATE.TitleScreen) {
      titleScreen.tick();
    }
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
    if (interface_state == INTERFACE_STATE.Game) {
      hud.render(g);
    } else if (interface_state == INTERFACE_STATE.TitleScreen) {
      titleScreen.render(g);
    }

    g.dispose();
    bs.show();
  }

}
