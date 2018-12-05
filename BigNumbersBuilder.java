package com.company;

public class BigNumbersBuilder{
    BigNumbers number;
    public BigNumbersBuilder(String string){
        number = new BigNumbers(string);
    }
    public BigNumbers build(){
        return new BigNumbers(number);
    }
}
