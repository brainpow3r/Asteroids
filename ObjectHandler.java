package com.company;

import java.awt.*;
import java.util.*;

public class ObjectHandler {
    private Random r;
    private long projectileCount = 0;

    public static LinkedList<GameObject> myObjects = new LinkedList<>();
    public HashMap<Long, Projectile> myProjectiles = new HashMap<>();

    // update function for all gameObjects
    public synchronized void tick() {

        synchronized(this) {
            myObjects.forEach(x -> {
                x.tick();
                x.ensureNotGoingOffScreen();
            });
        }



        synchronized (this) {
            myProjectiles.forEach((k, v) -> {
                v.tick();
                v.setOffScreen(v.hasGoneOffScreen());
            });

            myProjectiles.entrySet().removeIf(e -> e.getValue().getOffScreen());

        }

    }

    // render function for all game gameObjects
    public synchronized void render(Graphics g) {
        try {

            synchronized(this) {
                myObjects.parallelStream().forEach(x -> x.render(g));

                myProjectiles.forEach((k, v) -> {
                    v.render(g);
                });
            }

        } catch (NullPointerException e) {
            e.getCause();
        }
    }

    public void spawnEnemies(int countSimple, int countSpecial) {
        r = new Random();
        int simpleEnemies = 0;
        int specialEnemes = 0;

        for (int i = 0; i < countSimple; i++) {
            addObject(new EnemySimple(r.nextInt(760), r.nextInt(610), r.nextInt(4 + 1 + 4) - 4, r.nextInt(4 + 1 + 4) - 4));
        }

        for (int i = 0; i < countSpecial; i++) {
            addObject(new EnemySpecial(r.nextInt(760), r.nextInt(610), r.nextInt(4 + 1 + 4) - 4, r.nextInt(4 + 1 + 4) - 4));
        }



    }

    public void addProjectile(int x_pos, int y_pos, int x_Vel, int y_Vel) {
        synchronized(this) {
            projectileCount++;
            myProjectiles.put(projectileCount, new Projectile(x_pos, y_pos, x_Vel, y_Vel, projectileCount, this));
        }
    }


    public void addObject(GameObject o){
        myObjects.add(o);
    }

    public void removeObjects(){

        synchronized(this) {
            // removing projectiles
            Set set = myProjectiles.entrySet();
            myProjectiles.entrySet().removeAll(set);

            //removing objects
            myObjects.clear();
        }


    }

}
