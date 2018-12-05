package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class HUD {
    
    private BigNumbersBuilder builder1 = new BigNumbersBuilder("0000000000000000000");
    private BigNumbersBuilder builder2 = new BigNumbersBuilder("1000000000000");
    private BigNumbers playerScore = builder1.build();
    private BigNumbers scoreIncrease = builder2.build();


    public int playerLives = 3;
    private BufferedImage image;

    public HUD() {
        image = ImageLoader.getImage(2);
    }

    public void tick(long timestamp, Game ref) {
        long tempTime = System.currentTimeMillis();
        long diff = tempTime - timestamp;

        if (diff >= 1000) {
            playerScore = playerScore.add(playerScore, scoreIncrease);
            System.out.println(playerScore.printNumber());
            ref.increaseScore = true;

        }
    }

    public void render(Graphics g) {
        g.drawImage(image, 15, 15, null, null);
        g.drawImage(image, 55, 15, null, null);
        g.drawImage(image, 95, 15, null, null);
    }

}
