package com.company;

import java.awt.*;

public class Player extends GameObject {
    private long timestamp = 0;
    public boolean immune = false;
    public boolean hit = false;
    private int pWidth = 21;
    private int pHeight = 32;
    private ObjectHandler handler;

    public Player(int x, int y, int x_Vel, int y_Vel, ObjectHandler handler) {
        pos = new Vector2D(x, y);
        vel = new Vector2D(x_Vel, y_Vel);

        try {
            canImageBeApplied(5);
        } catch (ImageApplyingException e) {
            e.printStackTrace();
            image = ImageLoader.getDefaultImage();
        }

        this.immune = true;
        this.handler = handler;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)getxPos(), (int)getyPos(), pWidth, pHeight);
    }

    private void collision() {
        long timestamp = 0;
        for (int i = 1; i < handler.myObjects.size(); i++) {            // starts from 1 because Player is at position 0

            GameObject tempObj = handler.myObjects.get(i);

            if (((tempObj instanceof EnemySimple) || (tempObj instanceof EnemySpecial)) && !immune) {
                if(getBounds().intersects(tempObj.getBounds())) {

                    HUD.playerCollidedWithEnemy = true;
                    immune = true;
                    hit = true;

                    try {
                        canImageBeApplied(5);
                    } catch (ImageApplyingException e) {
                        e.printStackTrace();
                        image = ImageLoader.getDefaultImage();
                    }
                }
            }

        }

    }

    public void immunity(long timestamp) {
        long currTime = System.currentTimeMillis();
        long diff = currTime - timestamp;

        if (immune && (diff >= 2000)) {
            try {
                canImageBeApplied(1);
            } catch (ImageApplyingException e) {
                e.printStackTrace();
                image = ImageLoader.getDefaultImage();
            }

            immune = false;
        }
    }

    @Override
    public void tick() {
        pos.addVector(vel);

        if (hit) {
            timestamp = System.currentTimeMillis();
            hit = false;
        } else {
            immunity(timestamp);
            collision();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)getxPos(), (int)getyPos(), null, null);
    }

    @Override
    public void ensureNotGoingOffScreen() {
        // LEFT SIDE
        if (pos.getX() >= 0 && pos.getX() <= 800) {
            pos.addVector(-vel.getX(), 0);
        } else if (pos.getX() < 0) {
            pos.setX(790);
        }

        // RIGHT SIDE
        if (pos.getX() >= 0 && pos.getX() <= 800) {
            pos.addVector(vel.getX(), 0);
        } else if (pos.getX() > 800) {
            pos.setX(10);
        }

        // TOP BORDER
        if (pos.getY() >= 0 && pos.getY() <= 640) {
            pos.addVector(0, -vel.getY());
        } else if (pos.getY() < 0) {
            pos.setY(630);
        }

        // BOTTOM BORDER
        if (pos.getY() >= 0 && pos.getY() <= 640) {
            pos.addVector(0, vel.getY());
        } else if (pos.getY() > 640) {
            pos.setY(10);
        }

    }

    public void reset(ObjectHandler h) {
        setxPos((Game.WIDTH/2-16));
        setyPos((Game.HEIGHT/2-16));
        setxVel(0);
        setyVel(0);
        try {
            canImageBeApplied(5);
        } catch (ImageApplyingException e) {
            e.printStackTrace();
            image = ImageLoader.getDefaultImage();
        }
        handler = h;
        immune = true;
    }

}
