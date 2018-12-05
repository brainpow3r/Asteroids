package com.company;

import java.awt.*;

public class EnemySpecial extends EnemySimple {

    private int esWidth = 16;
    private int esHeight = 16;

    public EnemySpecial(int x, int y, int x_Vel, int y_Vel) {
        pos = new Vector2D(x, y);
        vel = new Vector2D(x_Vel, y_Vel);

        try {
            canImageBeApplied(4);
        } catch (ImageApplyingException e){
            image = ImageLoader.getDefaultImage();
        }
    }

    public EnemySpecial() {
        pos = new Vector2D(0, 0);
        vel = new Vector2D(0, 0);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)getxPos(), (int)getyPos(), esWidth, esHeight);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)getxPos(), (int)getyPos(), null, null);
    }

    @Override
    // override this method, so that special enemies can bounce back from walls
    public void ensureNotGoingOffScreen() {
        int tempVel;

        if (getyPos() <= 0 || getyPos() >= 590) {
            tempVel = (-1) * getyVel();
            setyVel(tempVel);
        }

        if (getxPos() <= 0 || getxPos() >= 770) {
            tempVel = (-1) * getxVel();
            setxVel(tempVel);
        }

    }
}
