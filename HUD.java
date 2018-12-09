package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    
    private BigNumbersBuilder builder1 = new BigNumbersBuilder("000000000000000000");
    private BigNumbersBuilder builder2 = new BigNumbersBuilder("1000000000000");
    private BigNumbersBuilder builder3 = new BigNumbersBuilder("500000000");
    private BigNumbers playerScore = builder1.build();
    private BigNumbers scoreIncrease = builder2.build();
    private BigNumbers scoreForEnemy = builder3.build();
    private ScoreSystem scoreLoader;

    public static Integer gameLevel = 1;
    public static int playerLives = 3;
    public static boolean playerCollidedWithEnemy = false;
    public static boolean enemyDestroyed = false;
    private BufferedImage image;

    public HUD() {
        scoreLoader = new ScoreSystem();
        try {
            image = ImageLoader.getImage(2);
        } catch (ImageApplyingException e) {
            e.printStackTrace();
        }
    }

    public void tick(long timestamp, Game ref) {
        long tempTime = System.currentTimeMillis();
        long diff = tempTime - timestamp;

        if (diff >= 1000) {
            playerScore = playerScore.add(playerScore, scoreIncrease);
            ref.increaseScore = true;
            if (playerCollidedWithEnemy) {
                playerLives--;
                playerCollidedWithEnemy = false;
            }
        }

        if (enemyDestroyed) {
            increaseScore();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < playerLives; i++) {
            g.drawImage(image, 15+(i*40), 15, null, null);
        }
        g.setColor(Color.white);
        g.drawString("Score: " + playerScore.printNumber(), 600, 25);
        g.drawString("Level: " + HUD.gameLevel.toString(), 600, 35);
        if (playerLives == 0) {
            saveHighScore();
            Game.gameState = Game.STATE.GAMEOVER;
        }
    }

    public void increaseScore() {
        enemyDestroyed = false;
        playerScore.add(playerScore, scoreForEnemy);
    }

    public void reset() {
        builder1 = new BigNumbersBuilder("000000000000000000");
        builder2 = new BigNumbersBuilder("1000000000000");
        builder3 = new BigNumbersBuilder("500000000");
        playerScore = builder1.build();
        scoreIncrease = builder2.build();
        scoreForEnemy = builder3.build();

        gameLevel = 1;
        playerLives = 3;
        playerCollidedWithEnemy = false;
        enemyDestroyed = false;
    }

    public void saveHighScore() {
        scoreLoader.saveScore(playerScore.printNumber());
    }

}
