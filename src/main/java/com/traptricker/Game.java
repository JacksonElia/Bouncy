package com.traptricker;

import com.traptricker.inputs.KeyInput;
import com.traptricker.inputs.MouseInput;
import com.traptricker.objects.GameObject;
import com.traptricker.objects.ID;
import com.traptricker.objects.Player;
import com.traptricker.objects.Spawner;
import com.traptricker.sound.SoundPlayer;
import com.traptricker.userinterface.DeathScreen;
import com.traptricker.userinterface.HUD;
import com.traptricker.userinterface.INTERFACE_STATE;
import com.traptricker.userinterface.TitleScreen;
import com.traptricker.userinterface.Window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.io.File;

/**
 * The class used to connect the whole java project into a game.
 */
public class Game extends Canvas implements Runnable {

  private final Handler handler;
  private final HUD hud;
  private final Spawner spawner;
  private final Window window;
  private final TitleScreen titleScreen;
  private final DeathScreen deathScreen;
  public INTERFACE_STATE interface_state;
  public int tick = 0;
  private Boolean running = false;
  private Thread thread;

  public Game() {
    handler = new Handler();
    hud = new HUD(handler, this);
    spawner = new Spawner(this, handler, hud);
    window = new Window(this,
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
            .getWidth(),
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
            .getHeight());
    titleScreen = new TitleScreen(this, spawner);
    deathScreen = new DeathScreen(this, hud);

    interface_state = INTERFACE_STATE.TitleScreen;

    // Tells the program to listen for key and mouse inputs
    this.addKeyListener(new KeyInput(this, window));
    MouseInput mouseInput = new MouseInput(handler, this);
    this.addMouseListener(mouseInput);
    this.addMouseMotionListener(mouseInput);

    // Makes bounce sound on start up
    SoundPlayer.playSound(new File("src/main/resources/sfx-boing10.wav"), 0f);
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
    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      // The game is being updated amountOfTicks (100) times per second
      while (delta >= 1) {
        tick %= amountOfTicks;
        tick++;
        tick();
        delta--;
      }
      // The game is being rendered as much as possible
      if (running) {
        render();
      }
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
      }
    }
    stopThread();
  }

  private void tick() {
    handler.tick();
    // Handles how the game acts on different states of the game
    if (interface_state == INTERFACE_STATE.Game) {
      hud.tick();
      spawner.tick();
    } else if (interface_state == INTERFACE_STATE.TitleScreen) {
      titleScreen.tick();
    } else if (interface_state == INTERFACE_STATE.DeathScreen) {
      deathScreen.tick();
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
    g.fillRect(0, 0, getWidth(), getHeight());

    // Where the rendering happens
    handler.render(g);
    if (interface_state == INTERFACE_STATE.Game) {
      hud.render(g);
    } else if (interface_state == INTERFACE_STATE.TitleScreen) {
      titleScreen.render(g);
    } else if (interface_state == INTERFACE_STATE.DeathScreen) {
      deathScreen.render(g);
    }

    g.dispose();
    bs.show();
  }

  public INTERFACE_STATE getInterface_state() {
    return interface_state;
  }

  public void setInterface_state(INTERFACE_STATE interface_state) {
    this.interface_state = interface_state;
    if (interface_state == INTERFACE_STATE.Game) {
      // Resets the game to the start
      hud.resetValues();
      handler.removeAllNonPlayerObjects();
      // Kills the current player object if there is one
      for (GameObject object : handler.objects) {
        if (object.getID() == ID.Player) {
          handler.objectsToRemove.add(object);
        }
      }
      // Adds a player object to the game
      handler.addObject(
          new Player(this, getWidth() / 2 + 24, getHeight() / 2 + 24, 24, ID.Player, handler, hud));
      window.hideCursor();
    } else if (interface_state == INTERFACE_STATE.TitleScreen) {
      handler.removeAllNonPlayerObjects();
      window.showCursor();
      for (int i = 0; i < 20; i++) {
        spawner.spawnTitleScreenEnemy();
      }
    } else if (interface_state == INTERFACE_STATE.DeathScreen) {
      window.showCursor();
    }
  }

  public int getTick() {
    return tick;
  }

}
