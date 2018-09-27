package com.company;

import java.awt.*;
import java.util.LinkedList;

public class ObjectHandler {

    LinkedList<GameObject> myObjects = new LinkedList<GameObject>();

    public void tick() {
        for(int i = 0; i < myObjects.size(); i++) {

            GameObject tempObject = myObjects.get(i);
            tempObject.tick();


        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < myObjects.size(); i++) {

            GameObject tempObject = myObjects.get(i);
            tempObject.render(g);

        }
    }

    public void addObject(GameObject o){
        this.myObjects.add(o);
    }

    public void removeObject(GameObject o){
        this.myObjects.remove(o);
    }

}
