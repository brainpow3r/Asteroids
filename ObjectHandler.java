package com.company;

import java.awt.*;
import java.util.LinkedList;

public class ObjectHandler {

    public static LinkedList<GameObject> myObjects = new LinkedList<>();

    // update function for all gameObjects
    public void tick() {
        for(int i = 0; i < myObjects.size(); i++) {

            GameObject tempObject = myObjects.get(i);
            tempObject.tick();
            tempObject.ensureNotGoingOffScreen();

        }
    }

    // render function for all game gameObject
    public void render(Graphics g) {
        for(int i = 0; i < myObjects.size(); i++) {

            GameObject tempObject = myObjects.get(i);
            tempObject.render(g);

        }
    }

    public void addObject(GameObject o){
        myObjects.add(o);
    }

    public void removeObject(GameObject o){
        this.myObjects.remove(o);
    }

    public void removeObjects(){
        for (int i = 0; i < myObjects.size(); i++) {
            myObjects.remove();
        }

    }

}
