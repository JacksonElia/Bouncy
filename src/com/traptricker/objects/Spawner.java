package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.HUD;
import com.traptricker.Handler;

import java.util.Random;

/**
 * This class handles the spawning of all the enemies
 * based on certain things like level.
 */
public class Spawner {

    private Handler handler;
    private HUD hud;
    private Random random;

    private int scoreKeep = 0;

    public Spawner(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        this.random = new Random();
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 100) {
            scoreKeep = 0;
            handler.addObject(new SmallEnemy(random.nextInt(Game.width), random.nextInt(Game.height),
                    random.nextInt(10) - 5, random.nextInt(10) - 5, ID.SmallEnemy));
        }
    }
}
