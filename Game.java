package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 800, HEIGHT = 640;

    private Thread thread;
    private boolean running = false;
    private ObjectHandler objHandler;

    public Game() {
        new Window(WIDTH, HEIGHT, "Asteroids", this);
        objHandler = new ObjectHandler();

        objHandler.addObject(new Player(25, 25, 0.5, 0.5));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {

        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void tick() {
        objHandler.tick();
    }

    private void render() {

        BufferStrategy buffStrat = this.getBufferStrategy();

        if (buffStrat == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffStrat.getDrawGraphics();

        g.setColor(Color.blue);
        g.fillRect(0, 0 , WIDTH, HEIGHT);

        objHandler.render(g);

        g.dispose();
        buffStrat.show();

    }

    public void run() {

        //-------------MAIN GAME LOOP--------------

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }

        }

        stop();

    }

}
