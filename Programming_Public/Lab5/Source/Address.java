package com.nsi.lab5.commandwork;

public class Address {
    private String zipCode; //Поле может быть null
    public Address(String i){
        this.zipCode = i;
    }

    @Override
    public String toString() {
        return zipCode.toString();
    }
}