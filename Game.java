package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas implements Runnable {

    private KeyInput gameKeyInput;
    private ImageLoader imgLoader;
    private ObjectHandler objHandler;
    private HUD gameHUD;
    private BufferedImage backgroundImage;
    private Thread thread;
    public Player player;
    private boolean running = false;
    public boolean increaseScore = false;
    public static boolean playerDead = false;
    private long scoreTimer;
    public static final int WIDTH = 800, HEIGHT = 640;


    public Game() {
        objHandler = new ObjectHandler();
        imgLoader = new ImageLoader();
        gameHUD = new HUD();
        player = new Player((WIDTH/2-16), (HEIGHT/2-16), 0, 0, objHandler);

        objHandler.addObject(player);
        objHandler.spawnEnemies(4, 2);

        try {
            backgroundImage = ImageLoader.getImage(0);
        } catch (ImageApplyingException e) {
            e.printStackTrace();
        }
        gameKeyInput = new KeyInput(objHandler);
        this.addKeyListener(gameKeyInput);


        new Window(WIDTH, HEIGHT, "Asteroids", this);
        this.addMouseListener(new MouseEventListener(gameKeyInput));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        // giving player immunity at the start of the game, so if enemy spawns very close to it, it doesn't lose a life
        player.immunity(System.currentTimeMillis());
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
            synchronized(this) {
                objHandler.tick();
            }
            gameHUD.tick(scoreTimer, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private synchronized void render() {

        BufferStrategy buffStrat = this.getBufferStrategy();

        if (buffStrat == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffStrat.getDrawGraphics();
        g.drawImage(backgroundImage, 0, 0, Color.black, null);

        synchronized(this) {
            objHandler.render(g);
            gameHUD.render(g);
        }


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

            while (delta >= 1) {
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
