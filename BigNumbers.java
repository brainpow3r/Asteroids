package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BigNumbers implements BigNumbersInterface , Comparable<BigNumbers> {
    private ArrayList<Integer> number = new ArrayList<Integer>();
    private int size;
    private boolean sign;
    BigNumbers(String input){
        size = input.length();
        sign = false;
        if(input.charAt(0) == '-'){
            sign = true;
            size = input.length() - 1;
        }
        storeNumber2(input);
    }
    BigNumbers(){
        sign = false;
        size = 1;
        storeNumber2("0");
    }
    BigNumbers(BigNumbers another){
        this.number.addAll(another.getNumber());
        this.size = another.getSize();
        this.sign = another.sign;
    }
    private void storeNumber2(String input){
        if(sign){
            input = input.substring(0, 0) + input.substring(0 + 1);
        }
        int lenght = input.length();
        String cut;
        int start = 0;
        int last = 1;
        while(lenght > 0){
            cut = input.substring(start,last);
            int y = Integer.parseInt(cut);
            number.add(y);
            lenght = lenght - 1;
            start += 1;
            last += 1;
        }
    }
    public BigNumbers add(BigNumbers first1, BigNumbers second1){
        ArrayList<Integer> firstL = new ArrayList<>();
        ArrayList<Integer> secondL = new ArrayList<>();
        int firstLeng = first1.getSize();
        int secondLeng = second1.getSize();
        firstL.addAll(first1.getNumber());
        secondL.addAll(second1.getNumber());
        BigNumbers first = new BigNumbers(first1);
        BigNumbers second = new BigNumbers(second1);
        ArrayList<Integer> sum = new ArrayList<Integer>();
        int stop = Math.max(firstLeng, secondLeng);
        int tick = 0;
        int carry = 0;
        boolean negative = false;
        if(first.isNegative() && second.isNegative()){
            negative = true;
        }
        if(!first.isNegative() && second.isNegative()){
            second.removeSign();
            BigNumbers z = new BigNumbers(first.sub(first, second));
            return z;
        }
        if(first.isNegative() && !second.isNegative()){
            first.removeSign();
            return new BigNumbers(first.sub(second, first));
        }
        while( stop > 0){
            //  System.out.print("SAS" + secondLeng);
            int eq;
            if(secondLeng - 1 - tick < 0){
                eq = firstL.get(firstLeng - 1 - tick);
                if(carry == 1){
                    eq += 1;
                    carry = 0;
                }
                if(eq >= 10){
                    sum.add(tick, eq - 10);
                    carry = 1;
                }
                else{
                    sum.add(tick, eq);
                }
                stop -= 1;
                tick += 1;
            }
            else if(firstLeng - 1 - tick < 0){
                eq = secondL.get(secondLeng - 1 - tick);
                if(carry == 1){
                    eq += 1;
                    carry = 0;
                }
                if(eq >= 10){
                    sum.add(tick, eq - 10);
                    carry = 1;
                }
                else{
                    sum.add(tick, eq);
                }
                stop -= 1;
                tick += 1;
            }
            else{
                eq = firstL.get(firstLeng - 1 - tick) + secondL.get(secondLeng - 1 - tick);
                if(carry == 1){
                    eq += 1;
                    carry = 0;
                }
                if(eq >= 10){
                    sum.add(tick, eq - 10);
                    carry = 1;
                }
                else{
                    sum.add(tick, eq);
                }
                stop -= 1;
                tick += 1;
            }
        }
        String answer = "";
        for(int i = sum.size() - 1; i >= 0; i--){

            answer = answer + Integer.toString(sum.get(i));
        }
        BigNumbers a = new BigNumbers(answer);
        if(negative){
            a.addSign();
        }
        return a;
    }
    public int getSize(){
        return size;
    }
    public ArrayList<Integer> getNumber(){
        return number;
    }
    public boolean isNegative(){
        if(sign == true){
            return true;
        }
        else
            return false;
    }
    public BigNumbers sub(BigNumbers first1, BigNumbers second1){
        ArrayList<Integer> firstL = new ArrayList<>();
        ArrayList<Integer> secondL = new ArrayList<>();
        int firstLeng = first1.getSize();
        int secondLeng = second1.getSize();
        firstL.addAll(first1.getNumber());
        secondL.addAll(second1.getNumber());
        BigNumbers first = new BigNumbers(first1);
        BigNumbers second = new BigNumbers(second1);
        ArrayList<Integer> sub = new ArrayList<Integer>();
        int stop = Math.max(firstLeng, secondLeng);
        int tick = 0;
        int carry = 0;
        boolean negative = false;
        if(first.isNegative() && second.isNegative()){
           second.removeSign();
           return new BigNumbers(first.add(first, second));
        }
        if(first.isNegative() && !second.isNegative()){
            second.addSign();
            return new BigNumbers(first.add(first, second));
        }
        if(!first.isNegative() && second.isNegative()) {
            second.removeSign();
            return new BigNumbers(first.add(first, second));
        }

        if(firstLeng - secondLeng < 0){ // neigiamas jei 2 ilgesnis
            negative = true;
        }
        if(firstLeng == secondLeng) {
            System.out.print("JIESKO");
            negative = true;
            ArrayList<Integer> equ = new ArrayList<>();
            boolean equal = false;
          //  System.out.print(stop);
            for (int i = 0; i < stop; i++) {
                if (firstL.get(i) == secondL.get(i)) {
                    equ.add(1);
                }
                else {
                    equ.add(0);
                }
                if (firstL.get(i) > secondL.get(i)) {
                    negative = false;
                    break;
                }
                if (firstL.get(i) < secondL.get(i)) {
                    negative = true;
                    break;
                }
            }
           // System.out.print("EQUUUU" + equ);
            int count = 0;
            for (int i = 0; i < equ.size(); i++) {
                if(equ.get(i) == 1){
                    count += 1;
                }
            }
            if(count == equ.size()){
                return new BigNumbers();
            }
        }
        System.out.print("JIESKO");
        while( stop > 0) {
            int eq = 0;

            if(negative == true) {
                BigNumbers object = new BigNumbers(first.sub(second, first));
                object.addSign();
                return object;
            }
            if( negative == false) {
                if (secondLeng - 1 - tick < 0) {
                    eq = firstL.get(firstLeng - tick - 1);
                }
                else {
                    eq = firstL.get(firstLeng - tick - 1) - secondL.get(secondLeng - tick - 1);
                    if (eq < 0) {
                        firstL.set(firstLeng - tick - 2, firstL.get(firstLeng - tick - 2) - 1);
                        eq = firstL.get(firstLeng - tick - 1) + 10 - secondL.get(secondLeng - tick - 1);
                    }
                }
                sub.add(tick, eq);
                tick += 1;
                stop -= 1;
              //  System.out.print(" NEG FALSE" + second.getNumber());
            }

        }
       // System.out.print("b" + second.getNumber());
       // System.out.print("Sub: " + sub);
        String answer = "";
        boolean firstZero = true;
       /* if(negative == true){
            for(int i = sub.size() - 1; i >= 0; i--){
                if(i == sub.size() - 1){
                    answer = answer + "-";
                }
                if(Integer.toString(sub.get(i)) != "0"){
                    firstZero = false;
                }
                if(firstZero == false) {
                    answer = answer + Integer.toString(sub.get(i));
                }
            }
        } */
        if(negative == false){
            for(int i = sub.size() - 1; i >= 0; i--){
                if(sub.get(i) != 0){
                    firstZero = false;
                }
                //   System.out.print("ints" + Integer.toString(sub.get(i)));
                if(firstZero == false) {
                    answer = answer + Integer.toString(sub.get(i));
                }
            }
        }
        BigNumbers a = new BigNumbers(answer);
        //System.out.print("b" + a.getNumber());
        /*if(negative == true){
            a.addSign();
        } */
        return a;
    }

    public BigNumbers mul(BigNumbers first1, BigNumbers second1) {
        ArrayList<Integer> firstL = new ArrayList<>();
        ArrayList<Integer> secondL = new ArrayList<>();
        int firstLeng = first1.getSize();
        int secondLeng = second1.getSize();
        firstL.addAll(first1.getNumber());
        secondL.addAll(second1.getNumber());
        BigNumbers first = new BigNumbers(first1);
        BigNumbers second = new BigNumbers(second1);
        ArrayList<Integer> mul = new ArrayList<Integer>();
        int stop = firstLeng * secondLeng;
        int tick1 = 0;
        int tick2 = 0;
        int line = 0;
        int carry = 0;
        ArrayList<Integer> side1 = new ArrayList<Integer>();
        ArrayList<Integer> side2 = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> side = new ArrayList<ArrayList<Integer>>();
        while(stop  > 0){
            int eq = 0;
            eq = firstL.get(firstLeng - 1 - tick1) * secondL.get(secondLeng - 1 - tick2) + carry;
            //  System.out.print("EQ" + carry);
            if(carry > 0){
                carry = 0;
            }
            if (eq >= 10) {
                carry = eq / 10;
                eq = eq % 10;
            }
            if(tick1 == 0) {
                for (int k = 0; k < line; k++) {
                    side1.add(k, 0);
                    //      System.out.print("side1" + side1);
                }
                if(line == 0){
                    side1.add(tick1, eq);
                }
                if(line > 0){
                    System.out.print("eq" + eq);
                    side1.add(eq);
                }
            }
            else {
                side1.add(eq);
                //     System.out.print("side11" + side1);
            }
            //   System.out.print("tick1" + tick1);
            tick1 += 1;
            if(tick1 == firstLeng){
                if(carry > 0){
                    side1.add(carry);
                    carry = 0;

                }
                if(side1.size() != firstLeng + secondLeng ){
                    int save = (firstLeng + secondLeng) - side1.size();
                    for(int z = 0; z < save; z++){
                        side1.add(0);
                    }
                    //     System.out.print("side11" + side1);
                }
                side.add(line, new ArrayList<Integer>(side1));
                //  System.out.print("WHAAAAA" + side);
                side1.clear();
            }

            if (tick1 == firstLeng) {
                tick2 += 1;
                tick1 = 0;
                line += 1;
                stop -= 1;
            }
            else
                stop -= 1;

        }
         /*int sum = 0;
         int j = 0;
         ArrayList<Integer> free = new ArrayList<Integer>();
         for(int i = 0; i <= secondLeng; i++ ){
                 if (i == 0) {
                     mul.add(side.get(i).get(i));
                 }
                 if (i > 0 && i != secondLeng - 1 ) {
                     mul.add(side.get(j).get(i) + side.get(j).get(i));
                 }
         }*/

        int sum = 0;
        carry = 0;

        for(int i = 0; i < side.get(line - 1).size(); i++ ){
            System.out.print(side.get(line - 1).size());
            for(int j = 0; j < secondLeng;j++) {
                System.out.print("v");
                sum = sum + side.get(j).get(i);
                System.out.print("c");
            }
            sum = sum + carry;
            carry = 0;
            if( sum >= 10){
                carry = sum/10;
                sum = sum%10;
            }
            mul.add(i, sum);
            sum = 0;
        }
        System.out.print(mul);

        boolean negative = false;
        if(!first.isNegative() && second.isNegative()){
            negative = true;
        }

        if(first.isNegative() && !second.isNegative()){
            negative = true;
        }
        String answer = "";
        boolean firstZero = true;
        if(negative == true){
            for(int i = mul.size() - 1; i >= 0; i--){
                if(i == mul.size() - 1){
                    answer = answer + "-";
                }
                if(Integer.toString(mul.get(i)) != "0"){
                    firstZero = false;
                }
                if(firstZero == false) {
                    answer = answer + Integer.toString(mul.get(i));
                }
            }
        }
        if(negative == false){
            for(int i = mul.size() - 1; i >= 0; i--){
                if(mul.get(i) != 0){
                    firstZero = false;
                }
                //  System.out.print("ints" + Integer.toString(mul.get(i)));
                if(firstZero == false) {
                    answer = answer + Integer.toString(mul.get(i));
                }
            }
        }
        // System.out.print("raaa" + answer);
        BigNumbers a = new BigNumbers(answer);
        return a;
    }
    public BigNumbers div(BigNumbers firstX, BigNumbers secondX) {
        ArrayList<Integer> firstL = new ArrayList<>();
        ArrayList<Integer> secondL = new ArrayList<>();
        int firstLeng = firstX.getSize();
        int secondLeng = secondX.getSize();
        firstL.addAll(firstX.getNumber());
        secondL.addAll(secondX.getNumber());

        BigNumbers first = new BigNumbers(firstX);
        BigNumbers second = new BigNumbers(secondX);

        ArrayList<Integer> div;

        String ats = "";
        int dif = firstLeng - secondLeng;

        if (dif < 0) {
            BigNumbers a = new BigNumbers("0");
            return a;
        }
        int mul1 = 1;
        for (int i = 0; i < dif - 1; i++) {
            mul1 = mul1 * 10;
        }

        String mult = Integer.toString(mul1);
        BigNumbers mul = new BigNumbers(mult);

        BigNumbers second1 = new BigNumbers(first.mul(second, mul));
        System.out.print("SIZE: "+ second1.getSize());

        BigNumbers first1 = new BigNumbers(first.printNumber());
        int count = 0;
        System.out.print("Second1: "+ second1.getNumber());

        int stop = firstLeng;
        boolean negative = true;
        if(firstLeng == secondLeng) {
            negative = true;
            ArrayList<Integer> equ = new ArrayList<>();
            boolean equal = false;
            System.out.print(stop);
            for (int i = 0; i < stop; i++) {
                if (firstL.get(i) == secondL.get(i)) {
                    equ.add(1);
                }
                else {
                    equ.add(0);
                }
                if (firstL.get(i) > secondL.get(i)) {
                    negative = false;
                    break;
                }
                if (firstL.get(i) < secondL.get(i)) {
                    negative = true;
                    break;
                }
            }

            System.out.print("EQUUUU" + equ);
            int counta = 0;

            for (int i = 0; i < equ.size(); i++) {
                if(equ.get(i) == 1){
                    counta += 1;
                }
            }
            if(counta == equ.size() || negative){
                return new BigNumbers();
            }
            int times = 0;
            String ats1 = "";
            while(!first1.isNegative()){
                first1 = first1.sub(first1, second1);
                times += 1;
                System.out.print("\n FIRST: "+ first1.isNegative());
            }
            times -= 1;
            System.out.print("\n ATS: "+ ats1);
            ats1 = ats1 + Integer.toString(times);
            return new BigNumbers(ats1);
        }

        for (int i = 0; i < dif; i++) {
            while (!first1.isNegative()) {
                System.out.print("\n First111: "+ first1.getNumber());
                System.out.print("\n First111s : "+ first1.isNegative());
                System.out.print("\n First111size : "+ first1.getSize());
                System.out.print("\n Second111: "+ second1.getNumber());
                System.out.print("\n Second111S : "+ second1.isNegative());
                System.out.print("\n Secobd111size : "+ second1.getSize());
                first1 = first1.sub(first1, second1);
                System.out.print("FirstZZ: "+ first1.getNumber());
                count += 1;
            }
            System.out.print("ISEEEEEEEEJOM: "+ first1.getNumber());
            first1 = first1.add(first1, second1);
            System.out.print("FIRST11: "+ first1.getNumber());
            System.out.print("FIRST11S: "+ first1.getSize());
            count -= 1;
            System.out.print("Count: "+ count);
            ats = ats + Integer.toString(count);
            count = 0;
            System.out.print("SIZEEEEEEEEEEEEEE: "+ second1.getSize());
            second1.getNumber().remove(second1.size - 1);
            second1.setSize(second1.getSize() - 1);
            System.out.print("Second2S: "+ second1.getSize());
            System.out.print("Second2: "+ second1.getNumber());
        }

         /*
         first1 = first1.add(first1, second1);
         System.out.print("try" + first1); */
        return new BigNumbers(ats);
    }

    public void removeSign(){
        sign = false;
    }
    public void addSign(){
        sign = true;
    }
    public String printNumber(){
        String number = "";
        if(sign == true){
            number = number + "-";
        }
        for(int i = 0; i < this.number.size(); i++){
            number = number + this.number.get(i);
        }
        return number;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int isFirstBigger(BigNumbers first, BigNumbers second){
        if(first.getSize() > second.getSize()){
            return 1;
        }
        if(second.getSize() > first.getSize()){
            return -1;
        }
        int stop = first.getSize();
        ArrayList<Integer> firstL = first.getNumber();
        ArrayList<Integer> secondL = second.getNumber();
        for(int i = 0; i < stop ; i++){
            if(firstL.get(i) > secondL.get(i)){
                return 1;
            }
            if(secondL.get(i) > firstL.get(i)){
                return -1;
            }
        }
        System.out.print("Equal numbers");
        return 0;
    }
    @Override
    public int compareTo( final BigNumbers o) {
        if(isFirstBigger(this, o) == 1){
            return 1;
        }
        if(isFirstBigger(this, o) == -1){
            return -1;
        }
        return 0;
    }
}