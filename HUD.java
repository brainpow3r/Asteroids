package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    
    private BigNumbersBuilder builder1 = new BigNumbersBuilder("0000000000000000");
    private BigNumbersBuilder builder2 = new BigNumbersBuilder("1000000000000");
    private BigNumbersBuilder builder3 = new BigNumbersBuilder("500000000");
    private BigNumbers playerScore = builder1.build();
    private BigNumbers scoreIncrease = builder2.build();
    private BigNumbers scoreForEnemy = builder3.build();

    public static int playerLives = 3;
    public static boolean playerCollidedWithEnemy = false;
    public static boolean enemyDestroyed = false;
    private BufferedImage image;

    public HUD() {

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
        g.drawString(playerScore.printNumber(), 650, 25);

        if (playerLives == 0) {
            Game.playerDead = true;
        }
    }

    public void increaseScore() {
        enemyDestroyed = false;
        playerScore.add(playerScore, scoreForEnemy);
    }


}
