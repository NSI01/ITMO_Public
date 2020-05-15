package com.nsi.lab5.commandwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to Match entered Information about Worker
 */
public class DataAnalyze {

    private boolean match = false;

    String[] dataArray;
    Matcher matcher;


    Pattern datapattern = Pattern.compile("[a-zA-zА-Яа-я]+;[-]*\\d{1,3},\\d{1,};[0-9.]+;[A-Z_]{5,26};[A-Z_]{7,};\\w+,[A-Z]{6,},[A-ZА-Я]{1}[a-zа-я-]{1,}");

    public  DataAnalyze(){
        dataArray = new String[6];
    };

    public DataAnalyze(String inmatch) throws Exception {
        Matcher matcher = datapattern.matcher(inmatch);
        System.out.print(" In analyze - ");
        System.out.println(inmatch );
        this.match = matcher.lookingAt();
        this.dataArray = inmatch.split(";", 6);
        try {
           // this.dataArray = inmatch.split(";", 6);
        if (!match) this.getmatch();
            System.out.println("Element data matches data format - : " + this.match);

        } catch (Exception e) {
            throw  e;
        }
    }

    /**
     * Matches Worker's Name
     * @return Is match or Not
     * @throws Exception - name of dissmatch
     */
    public boolean isMatchName() throws Exception {
        if (Pattern.matches("\\w+", (dataArray[0] = dataArray[0].trim()))) return true;
        else {
            System.out.println("Incorrect Name " );
            throw new Exception("Name");
        }
    }
    /**
     * Matches Worker's Coordinate
     * @return Is match or Not
     * @throws Exception - name of dissmatch
     */
    public boolean isMatchCoordinate() throws Exception {
        String[] coordinates;
        try {
            coordinates = dataArray[1].split(",", 2);
            coordinates[0] = coordinates[0].trim();
            coordinates[1] = coordinates[1].trim();
        } catch (Exception i) {
            throw new Exception("Coordinates");
        }
        if (Integer.parseInt(coordinates[0]) > Integer.valueOf(-678) && Pattern.matches("[+-]{0,1}[0-9]+",coordinates[1])) {
            dataArray[1]= coordinates[0].concat("," + coordinates[1]);
            return true;
        }
        else {
            throw new Exception("Coordinates");
        }

    }
    /**
     * Matches Worker's Salary
     * @return Is match or Not
     * @throws Exception - name of dissmatch
     */
    public boolean isMatchSalary() throws Exception {
        try {
            dataArray[2] = dataArray[2].trim();
            if (Float.parseFloat(dataArray[2]) > (float) 0.0) return true;
            else return false;
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Wrong Salary - " );
            throw new Exception("Salary");
        }
    }
    /**
     * Matches Worker's Position
     * @return Is match or Not
     * @throws Exception - name of dissmatch
     */
    public boolean isMatchPosition() throws Exception {
        dataArray[4] = dataArray[4].trim();
        dataArray[4] = dataArray[4].toUpperCase();
        if (Position.getPosition(dataArray[4]) != null) return true;
        else
            throw new Exception("Uncorrect Position");
    }
    /**
     * Matches Worker's Status
     * @return Is match or Not
     * @throws Exception - name of dissmatch
     */
    public boolean isMatchStatus() throws Exception {
        try {
            dataArray[3] = dataArray[3].trim();
            dataArray[3] = dataArray[3].toUpperCase();
            if (Status.getStatus(dataArray[3]) != null) return true;
            else
                throw new Exception("Uncorrect Status");
        } catch(Exception e){
            throw new Exception("Uncorrect Status");
        }
    }

    /**
     * Matches Worker's Organization
     * @return Is match or Not
     * @throws Exception - name of dissmatch
     */
    public boolean isMatchOrg() throws Exception {
        String[] orgarray = null;
        try {
            orgarray = dataArray[5].split(",", 3);
            orgarray[0] = orgarray[0].trim();
            orgarray[1] = orgarray[1].trim();
            orgarray[1] = orgarray[1].toUpperCase();
            orgarray[2] = orgarray[2].trim();
            orgarray[2] = (orgarray[2].substring(0,1).toUpperCase() + orgarray[2].substring(1));
        } catch (Exception e) {
            throw new Exception("Organization Info");
        }
        if (OrganizationType.getOrgType(orgarray[1]) == null) {
            System.out.println("Incorrect Type of Organization " );
            throw new Exception("Org");
        } else if (!Pattern.matches("[a-zA-zА-Яа-я-]+", orgarray[2])) {
            System.out.println("Incorrect City name " );
            throw new Exception("City Incorrect");
        } else if (!Pattern.matches("[\\w' 'А-Яа-я]+", orgarray[0])) {
            throw new Exception("Incorrect Name of Organization");
        } else {
            dataArray[5] = (orgarray[0] + "," + orgarray[1] + "," +  orgarray[2]);
            return true;
        }
    }

    /**
     * Matches All Worker's Data
     * @return String[] of Data
     * @throws Exception - name of dissmatch
     */
    public String[] getmatch() throws Exception {
        //if (this.match) {
            try {
                match = isMatchName();
                match = isMatchCoordinate();
                match = isMatchSalary();
                match = isMatchStatus();
                match = isMatchPosition();
                match = isMatchOrg();
            } catch (Exception e) {
                throw e;
            }
            ;
        //}
        return dataArray;
    }
}

