/**
 * @author Nazar Rusakov
 * Package of 5-th Lab
 */
package com.nsi.lab5.commandwork;

import sun.security.provider.PolicyParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author Nazar Rusakov
 * Main class
 */

public class Main {
    /**
     * {@value} Shows last time when Collection was modified
     */
    static ZonedDateTime setw_change_date = null;
    /**
     * {@value} Shows last time when Collection was created
     */
    static ZonedDateTime setw_init_date;
    /**
     * {@value} Array contains history of Commands
     */
    static ArrayDeque<String> commandhistory = new ArrayDeque<>(13);
    //final static String temp = System.getProperty("user.dir");
    /**
     * {@value} Name of database file
     */
    static String filename = null;
    /**
     * {@value} Path of databasefile
     */
    static Path infilepath;
    /**
     * {@value} Makes ID of workers
     */
    static Integer idcount = Integer.valueOf(0);

    /**
     * Running starts there
     * @param args - Database filename
     */
    public static void main(String[] args) {

        //filename = args[0];
        //System.out.println(filename);

        /**
         *
         */
        LinkedHashSet<Worker> setw = new LinkedHashSet<Worker>();
        setw_init_date =  ZonedDateTime.now();
        Main.setw_change_date = ZonedDateTime.now();
        /*
        *Reading data from In_File
         */

        try {
            filename = args[0];
        }catch (Exception e) {};

        while (!getData(setw)){};
        System.out.println("Reading database " + infilepath + " done");

        TerminalCommands.clearConsole();

        /*
        *Running commands
         */
        System.out.println("Please input command |  Type \"help\" command to show list of command   |");

        do {
                CommandInitializator.intializecommand(setw);
        } while (true);


    }
    /**
     * @param setw - Our Collection
     * @return boolean - was DataBase read or not
     * @exception Exception - Something Wrong with Database file
     */
    public static boolean getData (LinkedHashSet<Worker>  setw) {

        //}PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ, PosixFilePermission.OWNER_READ);
        String workerdata;
        IOException l = new IOException("File doesn't exist");
        int b;
        try {
            infilepath = Paths.get(filename).toAbsolutePath();
            if (infilepath == null) throw l;
            BufferedInputStream instream = new BufferedInputStream(Files.newInputStream(infilepath, StandardOpenOption.READ));
            workerdata = new String();
            while ((b = instream.read()) != -1) {
                if ((char) b == '\n') {
                    workerdata = workerdata.substring(0, workerdata.length() - 1);
                    setw.add(new Worker(new DataAnalyze(workerdata).getmatch()));
                    workerdata = "";
                    continue;
                }
                workerdata = (workerdata + (char) b);
            }
        } catch (Exception e) {
            System.out.println("Something wrong with In_File. Exception Message is: " + e.getMessage() );
            if(e.getClass() == java.nio.file.AccessDeniedException.class) System.out.println("I can't read file, pls change access rights");
            if(e.getClass() == java.lang.NullPointerException.class) System.out.println("Empty File Name");
            if(e.getClass() == java.nio.file.NoSuchFileException.class) System.out.println("Check file existing");
            System.out.println("Do you want to:\ncorrect_file       | gives time to correct database file ");
            System.out.println("change_name        | asks new In_File name");
            System.out.println("try_reset_access   | if it possible changes file access right, else asks to do it yourself\n or exit?");
            String i = TerminalCommands.reads();
            switch (i.trim()) {
                case "change_name": {
                    System.out.println("Input filename: ");
                    Main.filename = TerminalCommands.reads();
                    infilepath = null;
                    workerdata = null;
                    b = 0;
                    return getData(setw);
                }
                case "change_file": {
                    System.out.println("Type \"ready\" when you will done with file changing");
                    if (TerminalCommands.reads().equals("ready")) {
                        workerdata = null;
                        b = 0;
                        return getData(setw);
                    }
                }
                case "try_reset_access": {
                    System.out.println("Now file : " + infilepath + " is :\nRead access: " + Files.isReadable(infilepath) + "\nCan write: " + Files.isWritable(infilepath) + "\nCan execute: " + Files.isExecutable(infilepath));
                    System.out.println("I will try to reset access, but if problem still being, reset them by yourself\nPrint \"ok\" to start procedure");
                    if (TerminalCommands.reads().equals("ok")) {
                        try {
                            File infile = new File("filename");
                            System.out.println("Now file : " + infilepath + " is :\nSet Read access: " + infile.setReadable(true) + "\nSet Write access: " + infile.setWritable(true) + "\nSet Execute Access: " + infile.setExecutable(true));
                        } catch (Exception io) {
                            getData(setw);
                        }
                        //System.out.println("Now file : " + infile + " is :\nRead access: " + Files. + "\nCan write: " + infile.setWritable(true,true) + "\nCan execute: " + infile.setExecutable(true,true));
                        workerdata = null;
                        b = 0;
                        return getData(setw);
                    }
                }
                case "exit":
                    TerminalCommands.exit();
                    break;
                default: {
                    System.out.println("You have put uncorrect command");
                    workerdata = null;
                    b = 0;
                    return false;
                }
            }
        }
            workerdata = null;
            b = 0;
            return true;
        }
}



