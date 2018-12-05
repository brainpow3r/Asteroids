package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected BufferedImage image;
    protected Vector2D pos;                    //Object's position on screen
    protected Vector2D vel;                    //Object's vertical and horizontal velocities

    public GameObject(int x, int y, int x_Vel, int y_Vel) {
        pos = new Vector2D(x, y);
        vel = new Vector2D(x_Vel, y_Vel);
    }

    public GameObject() {
        pos = new Vector2D(0, 0);
        vel = new Vector2D(0, 0);
    }

    // this updates objects arguments like position, speed, etc.
    public abstract void tick();

    // this one render updated objects to screen
    public abstract void render(Graphics g);

    // method to ensure, that once object goes outside the screen, it respawns in the opposite side
    public abstract void ensureNotGoingOffScreen();

    // method used to detect collisions
    public abstract Rectangle getBounds();

    // this one's here just for the sake of requirements
    public String toString() {
        return "Postition: " + pos.getX() + ";" + pos.getY() + "    Velocity(x;y): " + vel.getX() + ";" + vel.getY() + "\n";
    }

    // used to throw exceptions
    public void canImageBeApplied(int index) throws ImageApplyingException {
        try {
            image = ImageLoader.getImage(index);
        } catch (ImageApplyingException e) {
            throw e;
        }
    }

    // GETTERS / SETTERS
    public int getxPos() { return pos.getX(); }

    public void setxPos(int xPos) {
        pos.setX(xPos);
    }

    public int getyPos() {
        return pos.getY();
    }

    public void setyPos(int yPos) {
        pos.setY(yPos);
    }

    public int getxVel() {
        return vel.getX();
    }

    public void setxVel(int xVel) {
        vel.setX(xVel);
    }

    public int getyVel() {
        return vel.getY();
    }

    public void setyVel(int yVel) {
        vel.setY(yVel);
    }
}
