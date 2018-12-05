package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private ObjectHandler handler;
    private GameObject player;

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


        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
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

    private void findPlayer () {

        for(int i = 0; i < handler.myObjects.size(); i++) {
            if (handler.myObjects.get(i) instanceof Player) {
                player = handler.myObjects.get(i);
            } else if ((i == handler.myObjects.size() - 1) && (player == null)) {
                System.out.println("object \"Player\" was not found!\n");
            }
        }

    }
}
