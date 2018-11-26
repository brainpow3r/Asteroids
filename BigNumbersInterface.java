package com.company;

import java.util.ArrayList;

public interface BigNumbersInterface {
    public BigNumbers add(BigNumbers first1, BigNumbers second1);
    public int getSize();
    public ArrayList<Integer> getNumber();
    public boolean isNegative();
    public BigNumbers sub(BigNumbers first1, BigNumbers second1);
    public BigNumbers mul(BigNumbers first1, BigNumbers second1);
    public BigNumbers div(BigNumbers firstX, BigNumbers secondX);
    public void removeSign();
    public void addSign();
    public String printNumber();
    public void setSize(int size);
    public int isFirstBigger(BigNumbers first, BigNumbers second);
}
