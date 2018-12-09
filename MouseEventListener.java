package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventListener implements MouseListener {

    private KeyInput ki_mouseEvent;

    public MouseEventListener(KeyInput ki) {
        ki_mouseEvent = ki;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.MENU) {

            if (MainMenu.menuState == MainMenu.MENUSTATES.MENU) {
                if (mx >= 290 && mx <= 540) {
                    if (my >= 185 && my <= 235) {
                        //play
                        Game.gameState = Game.STATE.GAME;
                    }
                }
            }

            if (mx >= 290 && mx <= 540) {
                if (my >= 260 && my <= 310) {
                    // highscores
                    MainMenu.menuState = MainMenu.MENUSTATES.HIGHSCORES;
                }
            }

            if (MainMenu.menuState == MainMenu.MENUSTATES.HIGHSCORES) {
                if (mx >= 600 && mx <= 710) {
                    if (my >= 300 && my <= 410) {
                        MainMenu.menuState = MainMenu.MENUSTATES.MENU;
                    }
                }
            }

            if (MainMenu.menuState == MainMenu.MENUSTATES.MENU) {
                if (mx >= 290 && mx <= 540) {
                    if (my >= 335 && my <= 385) {
                        //quit
                        System.exit(1);
                    }
                }
            }
        }

        if (Game.gameState == Game.STATE.GAMEOVER) {
            if (mx >= 290 && mx <= 540) {
                if (my >= 260 && my <= 310) {
                    Game.restart = true;
                }
            }

            if (mx >= 290 && mx <= 540) {
                if (my >= 320 && my <= 370) {

                    System.exit(1);
                }
            }
        }


        if (Game.gameState == Game.STATE.GAME) {
            ki_mouseEvent.shoot(mx, my);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
