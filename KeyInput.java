package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;

public class KeyInput extends KeyAdapter {
    private ObjectHandler handler;
    private GameObject player;
    public static int mouseClick_x;
    public static int mouseClick_y;

    public KeyInput (ObjectHandler handler){
        this.handler = handler;
        findPlayer();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                player.setyVel(-5);
                break;
            case KeyEvent.VK_DOWN:
                player.setyVel(5);
                break;
            case KeyEvent.VK_LEFT:
                player.setxVel(-5);
                break;
            case KeyEvent.VK_RIGHT:
                player.setxVel(5);
                break;
        }


        if (key == KeyEvent.VK_ESCAPE) {
            handler.removeObjects();
            System.exit(1);
        }

        // parodymui, kad objektu istrynimas veikia
        /*if (key == KeyEvent.VK_Y) {
            try {
                System.out.println(handler.myProjectiles.get((long)10).toString());
            } catch (NullPointerException exc) {
                System.out.println("index " + 10 + ": null");
            }
        }*/
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                player.setyVel(0);
                break;
            case KeyEvent.VK_DOWN:
                player.setyVel(0);
                break;
            case KeyEvent.VK_LEFT:
                player.setxVel(0);
                break;
            case KeyEvent.VK_RIGHT:
                player.setxVel(0);
                break;
        }
    }

    public void shoot(int x, int y) {

        handler.addProjectile(player.getxPos(), player.getyPos(), x, y);
    }

    private void findPlayer () {

        player = handler.myObjects.stream().filter(obj -> obj instanceof Player).collect(Collectors.toList()).get(0);

    }
}
