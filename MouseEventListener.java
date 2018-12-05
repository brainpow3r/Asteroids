package com.company;

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
        ki_mouseEvent.shoot(e.getX(), e.getY());
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
