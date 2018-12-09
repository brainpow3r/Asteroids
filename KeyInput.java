package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;

public class KeyInput extends KeyAdapter {
    private ObjectHandler handler;
    private GameObject player;
    private HUD hud;
    public static int mouseClick_x;
    public static int mouseClick_y;

    public KeyInput (ObjectHandler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        findPlayer();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (Game.gameState == Game.STATE.GAME) {
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
        }

        if (key == KeyEvent.VK_ESCAPE) {
            handler.removeObjects();
            hud.saveHighScore();
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (Game.gameState == Game.STATE.GAME) {
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
    }

    public void shoot(int x, int y) {
        if (Game.gameState == Game.STATE.GAME) {
            handler.addProjectile(player.getxPos(), player.getyPos(), x, y);
        }
    }

    private void findPlayer () {

        player = handler.myObjects.stream().filter(obj -> obj instanceof Player).collect(Collectors.toList()).get(0);

    }
}
