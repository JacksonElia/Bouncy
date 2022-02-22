package com.traptricker.objects;

import com.traptricker.Game;
import com.traptricker.userinterface.HUD;
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

    public Spawner(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        this.random = new Random();
    }

    public void tick() {

        if (hud.getScore() % 100 == 0) spawnBasicEnemy();

        if (hud.getScore() % 200 == 0) spawnStreakEnemy();

        if (hud.getScore() % 500 == 0) spawnHomingEnemy();

    }

    public void spawnBasicEnemy() {
        handler.addObject(new BasicEnemy(random.nextInt(Game.width), random.nextInt(Game.height),
                random.nextInt(10) - 5, random.nextInt(10) - 5, 8, ID.BasicEnemy));
    }

    public void spawnStreakEnemy() {
        // Gets a random start_side
        StreakEnemy.START_SIDE start_side = StreakEnemy.START_SIDE.values()[random.nextInt(StreakEnemy.START_SIDE.values().length)];
        int x, y, xVelocity, yVelocity = 0;
        switch (start_side) {
            case up:
                x = random.nextInt(Game.width - (Game.width / 6)) - (Game.width / 6);
                y = -10;
                xVelocity = random.nextInt(10) - 5;
                yVelocity = 10;
                break;
            case right:
                x = Game.width + 10;
                y = random.nextInt(Game.height - (Game.height / 6)) - (Game.height / 6);
                xVelocity = -10;
                yVelocity = random.nextInt(10) - 5;
                break;
            case down:
                x = random.nextInt(Game.width - (Game.width / 6)) - (Game.width / 6);
                y = Game.height + 10;
                xVelocity = random.nextInt(10) - 5;
                yVelocity = -10;
                break;
            case left:
                x = -10;
                y = random.nextInt(Game.height - (Game.height / 6)) - (Game.height / 6);
                xVelocity = 10;
                yVelocity = random.nextInt(10) - 5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + start_side);
        }
        handler.addObject(new StreakEnemy(x, y,
                xVelocity, yVelocity, 5, ID.StreakEnemy, handler, start_side));
    }

    public void spawnHomingEnemy() {
        Player player = null;
        for (GameObject object : handler.objects) {
            if (object.getID() == ID.Player) player = (Player) object;
        }
        handler.addObject(new HomingEnemy(random.nextInt(Game.width), random.nextInt(Game.height),
                1, 1, 12, ID.HomingEnemy, player));
    }

}
