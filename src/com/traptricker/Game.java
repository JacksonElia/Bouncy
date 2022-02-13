package com.traptricker;

import java.awt.*;

public class Game extends Canvas implements Runnable {

    // TODO: Find out if we actually need a serialVersionUID

    public Game() {
        new Window(this);
    }

    public synchronized void start() {

    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new Game();
    }

}
