package com.company;

import java.awt.*;

public class Player extends GameObject {

    public Player(double x, double y, double x_Vel, double y_Vel) {
        pos = new Vector2D(x, y);
        vel = new Vector2D(x_Vel, y_Vel);

        image = ImageLoader.getImage(1);
    }

    @Override
    public void tick() {
        pos.addVector(vel);
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

}
