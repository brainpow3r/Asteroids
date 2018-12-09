package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable {

    private KeyInput gameKeyInput;
    private ImageLoader imgLoader;
    private ObjectHandler objHandler;
    private HUD gameHUD;
    private BufferedImage backgroundImage;
    private Thread thread;
    private MainMenu menu;
    private boolean running = false;
    private long scoreTimer;
    public static enum STATE {
        MENU,
        GAME,
        HIGHSCORES,
        GAMEOVER
    };
    public static STATE gameState = STATE.MENU;
    public Player player;
    public boolean increaseScore = false;
    public static boolean restart = false;
    public static final int WIDTH = 800, HEIGHT = 640;


    public Game() {
        menu = new MainMenu();
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
        gameKeyInput = new KeyInput(objHandler, gameHUD);
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
        if (restart) {
            restart();
            restart = false;
        }

        if (gameState == STATE.GAME) {

            if (increaseScore) {
                scoreTimer = System.currentTimeMillis();
                increaseScore = false;
            }
            try {
                synchronized (this) {
                    objHandler.tick();
                }
                gameHUD.tick(scoreTimer, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private synchronized void render() {
        BufferStrategy buffStrat = this.getBufferStrategy();

        if (buffStrat == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffStrat.getDrawGraphics();

        if (gameState == STATE.GAME) {
            try {
                backgroundImage = ImageLoader.getImage(0);
            } catch (ImageApplyingException e) {
                e.printStackTrace();
            }
            g.drawImage(backgroundImage, 0, 0, Color.black, null);
            synchronized (this) {
                objHandler.render(g);
                gameHUD.render(g);
            }

        } else if (gameState == STATE.MENU) {
            try {
                backgroundImage = ImageLoader.getImage(7);
            } catch (ImageApplyingException e) {
                e.printStackTrace();
            }
            g.drawImage(backgroundImage, 0, 0, Color.black, null);
            menu.render(g);
        } else if (gameState == STATE.GAMEOVER) {
            Graphics2D g2d = (Graphics2D) g;
            Font fnt0 = new Font("arial", Font.BOLD, 36);
            g.setFont(fnt0);
            g.setColor(Color.white);
            Rectangle playAgain = new Rectangle (290, 260, 250, 50);
            Rectangle exit = new Rectangle (290, 320, 250, 50);

            try {
                backgroundImage = ImageLoader.getImage(7);
            } catch (ImageApplyingException e) {
                e.printStackTrace();
            }

            g2d.draw(playAgain);
            g.drawString("PLAY AGAIN?", playAgain.x+4, playAgain.y+37);
            g2d.draw(exit);
            g.drawString("EXIT", playAgain.x+78, playAgain.y+97);

        }

        g.dispose();
        buffStrat.show();
    }

    public void restart() {
        try {
            backgroundImage = ImageLoader.getImage(0);
        } catch (ImageApplyingException e) {
            e.printStackTrace();
        }
        objHandler.removeObjects();
        player.reset(objHandler);
        objHandler.addObject(player);
        objHandler.spawnEnemies(4, 2);
        gameHUD.reset();
        gameState = STATE.GAME;
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
