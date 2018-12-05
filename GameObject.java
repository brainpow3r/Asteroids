package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected BufferedImage image;
    protected Vector2D pos;                    //Object's position on screen
    protected Vector2D vel;                    //Object's vertical and horizontal velocities

    public GameObject(double x, double y, double x_Vel, double y_Vel) {
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

    // this one's here just for the sake of requirements
    public String toString() {
        return "Postition: " + pos.getX() + ";" + pos.getY() + "    Velocity(x;y): " + vel.getX() + ";" + vel.getY() + "\n";
    }

    // GETTERS / SETTERS
    public double getxPos() { return pos.getX(); }

    public void setxPos(double xPos) {
        pos.setX(xPos);
    }

    public double getyPos() {
        return pos.getY();
    }

    public void setyPos(double yPos) {
        pos.setY(yPos);
    }

    public double getxVel() {
        return vel.getX();
    }

    public void setxVel(double xVel) {
        vel.setX(xVel);
    }

    public double getyVel() {
        return vel.getY();
    }

    public void setyVel(double yVel) {
        vel.setY(yVel);
    }
}
