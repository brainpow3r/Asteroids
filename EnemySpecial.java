package com.company;

import java.awt.*;

public class EnemySpecial extends EnemySimple {

    public EnemySpecial(double x, double y, double x_Vel, double y_Vel) {
        pos = new Vector2D(x, y);
        vel = new Vector2D(x_Vel, y_Vel);

        image = ImageLoader.getImage(4);
    }

    public EnemySpecial() {
        pos = new Vector2D(0, 0);
        vel = new Vector2D(0, 0);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)getxPos(), (int)getyPos(), null, null);
    }

    @Override
    // override this method, so that special enemies can bounce back from walls
    public void ensureNotGoingOffScreen() {
        double tempVel;

        if (getyPos() <= 0 || getyPos() >= 590) {
            tempVel = (-1) * getyVel();
            setyVel(tempVel);
        }

        if (getxPos() <= 0 || getxPos() >= 770) {
            tempVel = (-1) * getxVel();
            setxVel(tempVel);
        }

    }

    public void destroy() {

        if ((pos.getX() > 800) || (pos.getX() < 0) && (pos.getY() > 640) || pos.getY() < 0){
            pos = new Vector2D(10000, 10000);
            vel = new Vector2D(0, 0);
        }

    }
}
