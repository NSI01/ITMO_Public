package com.nsi.lab5.commandwork;

/**
 * Worker's Position
 */
public enum Position {
    MANAGER(){
        @Override
        public String toString() {
            return "MANAGER";
        }
    },
    LABORER(){@Override
    public String toString() {
        return "LABORER";
    }},
    ENGINEER(){@Override
    public String toString() {
        return "ENGINEER";
    }},
    HEAD_OF_DEPARTMENT(){@Override
    public String toString() {
        return "HEAD_OF_DEPARTMENT";
    }},
    DEVELOPER(){@Override
    public String toString() {
        return "DEVELOPER";
    }};
    /**
     *
     * @param i - entered Position in String format
     * @return returns Enum format
     */
    public static Position getPosition (String i){
        switch (i) {
            case "MANAGER" : { return MANAGER; }
            case "LABORER": { return LABORER; }
            case "ENGINEER": { return ENGINEER; }
            case "HEAD_OF_DEPARTMENT": { return HEAD_OF_DEPARTMENT; }
            case "DEVELOPER": { return DEVELOPER; }};
            System.out.println("Uncorrect Position: "  + i);
            return null;

    };

}
