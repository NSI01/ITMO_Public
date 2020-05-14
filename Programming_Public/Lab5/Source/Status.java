package com.nsi.lab5.commandwork;

/**
 * Worker's Status
 * Enum
 */
public enum Status {
    FIRED(){
        @Override
    public String toString() {
        return "FIRED";
    }},
    HIRED(){@Override
    public String toString() {
        return "HIRED";
    }},
    RECOMMENDED_FOR_PROMOTION(){@Override
    public String toString() {
        return "RECOMMENDED_FOR_PROMOTION";
    }},
    REGULAR(){@Override
    public String toString() {
        return "REGULAR";
    }},
    PROBATION(){@Override
    public String toString() {
        return "PROBATION";
    }};
    /**
     *
     * @param i - entered Status in String format
     * @return returns Enum format of Status
     */
     public static Status getStatus(String i) {
         switch (i) {
             case "HIRED" : { return HIRED; }
             case "FIRED": { return FIRED; }
             case "RECOMMENDED_FOR_PROMOTION": { return RECOMMENDED_FOR_PROMOTION; }
             case "REGULAR": { return REGULAR; }
             case "PROBATION": { return PROBATION; }};
         System.out.println("Incorrect Status: " + i);
         return null;

     }

}