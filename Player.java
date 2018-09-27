package com.company;

import java.awt.*;

public class Player extends GameObject {

    public Player(double x, double y, double x_Vel, double y_Vel) {
        super(x, y, x_Vel, y_Vel);
    }

    @Override
    public void tick() {
        this.xPos += xVel;
        this.yPos += yVel;
        System.out.println(toString());
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)this.xPos, (int)this.yPos, 64, 64);
    }
}
