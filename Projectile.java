package com.company;

import java.awt.*;
import java.util.function.Predicate;

public class Projectile extends GameObject {
    private Vector2D velocityVector = new Vector2D(5, 5);
    private boolean offScreen;
    private long keyValue;
    private ObjectHandler handler;
    private int projWidth = 8;
    private int projHeight = 8;

    public Projectile(int x, int y, int x_Vel, int y_Vel, long key, ObjectHandler handler) {
        setxPos(x);
        setyPos(y);

        if (x_Vel < Game.WIDTH/2 && y_Vel < Game.HEIGHT/2) {
            setxVel((x_Vel*-1)/50);
            setyVel((y_Vel*-1)/50);
        } else if (x_Vel >= Game.WIDTH/2 && y_Vel < Game.HEIGHT/2) {
            setxVel((x_Vel)/50);
            setyVel((y_Vel*-1)/50);
        } else if (x_Vel < Game.WIDTH/2 && y_Vel >= Game.HEIGHT/2) {
            setxVel((x_Vel*-1)/50);
            setyVel((y_Vel)/50);
        } else {
            setxVel((x_Vel)/10);
            setyVel((y_Vel)/10);
        }

        try {
            canImageBeApplied(6);
        } catch (ImageApplyingException e) {
            e.printStackTrace();
            image = ImageLoader.getDefaultImage();
        }

        this.handler = handler;
        keyValue = key;
    }

    @Override
    public void tick() {
        pos.addVector(vel);
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)getxPos(), (int)getyPos(), null, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)getxPos(), (int)getyPos(), projWidth, projHeight);
    }

    private void collision() {
        long timestamp = 0;

        handler.myObjects.forEach( obj -> {
            if ((obj instanceof EnemySimple || obj instanceof EnemySpecial) && getBounds().intersects(obj.getBounds())) {
                HUD.enemyDestroyed = true;
            }
        });

        Predicate<GameObject> objPredicate = p -> (p instanceof EnemySimple || p instanceof EnemySpecial) && getBounds().intersects(p.getBounds());
        handler.myObjects.removeIf(objPredicate);

    }

    public boolean hasGoneOffScreen() {

        // LEFT SIDE
        if (pos.getX() < 0) {
            return true;
        } else if (pos.getX() > 800) {
            return true;
        } else if (pos.getY() < 0) {
            return true;
        } else if (pos.getY() > 640) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void ensureNotGoingOffScreen() {
        // ////// //
    }

    @Override
    public void canImageBeApplied(int index) throws ImageApplyingException {
        super.canImageBeApplied(index);
    }

    public long getKeyValue() {
        return keyValue;
    }

    public void setOffScreen(boolean value) {
        offScreen = value;
    }

    public boolean getOffScreen(){
        return offScreen;
    }
}
