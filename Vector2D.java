package com.company;

import java.util.Arrays;
import java.util.List;

public class Vector2D {

    private int x, y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0);
    }

    public void addVector(Vector2D vec) {

        List<Integer> xV = Arrays.asList(x, vec.getX());
        List<Integer> yV = Arrays.asList(y, vec.getY());

        xV.stream().reduce((x, y) -> x+y).ifPresent(s -> this.x = s);
        yV.stream().reduce((x, y) -> x+y).ifPresent(s -> this.y = s);
        //x += vec.getX();
        //y += vec.getY();
    }

    public void addVector(double x, double y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return (x + "; " + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
