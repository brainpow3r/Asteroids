package com.company;

import java.awt.*;

public abstract class GameObject {

    protected double xPos, yPos;                    //Object's position on screen
    protected double xVel, yVel;                    //Object's vertical and horizontal velocities

    public GameObject(double x, double y, double x_Vel, double y_Vel) {
        this.xPos = x;
        this.yPos = y;
        this.xVel = x_Vel;
        this.yVel = y_Vel;
    }

    public GameObject() {
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.xVel = 0.0;
        this.yVel = 0.0;
    }

    // this shit updates objects arguments like position, speed, etc.
    public abstract void tick();

    // this one render updated objects to screen
    public abstract void render(Graphics g);

    public String toString() {
        return "Postition: " + xPos + ";" + yPos + "    Velocity(x;y): " + xVel + ";" + yVel + "\n";
    }

    // GETTERS / SETTERS
    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }
}
