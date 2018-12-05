package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas implements Runnable {

    private ImageLoader imgLoader;
    private ObjectHandler objHandler;
    private HUD gameHUD;
    private BufferedImage backgroundImage;
    private Random r;
    private Thread thread;
    public Player player;
    private boolean running = false;
    public boolean increaseScore = false;
    private long scoreTimer;
    private static final int WIDTH = 800, HEIGHT = 640;


    public Game() {
        r = new Random();
        objHandler = new ObjectHandler();
        imgLoader = new ImageLoader();
        gameHUD = new HUD();

        player = new Player((WIDTH/2-16), (HEIGHT/2-16), 0, 0);

        objHandler.addObject(player);
        objHandler.addObject(new EnemySimple(r.nextInt(800), r.nextInt(640), r.nextInt(4 + 1 + 4) - 4, r.nextInt(4 + 1 + 4) - 4));
        objHandler.addObject(new EnemySimple(r.nextInt(800), r.nextInt(640), r.nextInt(4 + 1 + 4) - 4, r.nextInt(4 + 1 + 4) - 4));
        objHandler.addObject(new EnemySimple(r.nextInt(800), r.nextInt(640), r.nextInt(4 + 1 + 4) - 4, r.nextInt(4 + 1 + 4) - 4));
        objHandler.addObject(new EnemySimple(r.nextInt(800), r.nextInt(640), r.nextInt(4 + 1 + 4) - 4, r.nextInt(4 + 1 + 4) - 4));
        objHandler.addObject(new EnemySpecial(r.nextInt(800), r.nextInt(640), r.nextInt(7 + 1 +7 ) - 7, r.nextInt(7 + 1 + 7) - 7));

        backgroundImage = imgLoader.getImage(0);
        this.addKeyListener(new KeyInput(objHandler));

        new Window(WIDTH, HEIGHT, "Asteroids", this);
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
        objHandler.removeObjects();
    }

    private void tick() {

        if (increaseScore) {
            scoreTimer = System.currentTimeMillis();
            increaseScore = false;
        }
        try {
            objHandler.tick();
            gameHUD.tick(scoreTimer, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {

        BufferStrategy buffStrat = this.getBufferStrategy();

        if (buffStrat == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffStrat.getDrawGraphics();
        g.drawImage(backgroundImage, 0, 0, Color.black, null);

        objHandler.render(g);
        gameHUD.render(g);

        g.dispose();
        buffStrat.show();

    }

    public void run() {

        //-------------MAIN GAME LOOP--------------
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        scoreTimer = System.currentTimeMillis();
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
