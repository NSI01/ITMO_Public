package com.nsi.lab5.commandwork;

import jdk.internal.util.xml.impl.ReaderUTF8;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static com.nsi.lab5.commandwork.Main.infilepath;

public abstract class TerminalCommands {
    /**
     * shows list of commands
     */
    public static void help() {
        System.out.println("You can use this cammands: ");
        System.out.println("help                           |   shows list of commands");
        System.out.println("show                           |   shows standard information about Collection");
        System.out.println("update_id   \"id\"               |   shows ID-worker information in Collection");
        System.out.println("remove_by_id   \"id\"            |   removes ID-worker from Collection");
        System.out.println("add                            |   adds Worker in Collection");
        System.out.println("exit                           |   exits from program without saving");
        System.out.println("clear                          |   clears Collection");
        System.out.println("print_descending               |   shows descending Collection");
        System.out.println("add_if_min                     |   adds Worker if his Salary bigger than lowest Salary in Collection ");
        System.out.println("group_counting_by_coordinates  |   creates and shows groups that formed by Coordinates");
        System.out.println("save                           |   saves Collection in file");
        System.out.println("execute_script  \"file_name\"    |   running commands from \"file_name\"");
        System.out.println("average_of_salary              |   shows average Salary of Workers ");
        System.out.println("history                        |   shows Commands history");
        System.out.println("remove_greater                 |   removes Workers with bigger Salary from Collection");
    }

    ;

    /**
     * shows information about Main collection
     *
     * @param seti - Main Collection
     */
    public static void info(LinkedHashSet<Worker> seti) {
        System.out.println("Type is : " + seti.getClass() + " Size is : " + seti.size() + " Creation Date : " + Main.setw_init_date + " Last Change Date : " + Main.setw_change_date);
    }

    ;

    /**
     * shows Collection
     *
     * @param setsh - Main Collection
     */
    public static void show(LinkedHashSet<Worker> setsh) {
        if (setsh.isEmpty()) System.out.println("Empty Collection");
        Iterator<Worker> wor = setsh.iterator();
        Worker h;
        while (wor.hasNext()) {
            System.out.println(wor.next());
        }
        wor = null;
        h = null;
    }

    ;

    /**
     * adds  i to  setad
     *
     * @param i     - Worker
     * @param setad - Main collection
     */
    public static boolean add(Worker i, LinkedHashSet<Worker> setad) {
        return setad.add(i);

    }

    ;

    /**
     * Updates Worker's(ID) information
     *
     * @param i     - Worker
     * @param setup - Main collection
     * @param id    - ID of Updated Worker
     */
    public static void update_id(int id, Worker i, LinkedHashSet<Worker> setup) throws Exception {
        remove_by_id(id, setup);
        add(i, setup);
    }

    ;

    /**
     * Removes Worker's(ID) from Collection
     *
     * @param setid - Main collection
     * @param id    - ID of Updated Worker
     */
    public static void remove_by_id(int id, LinkedHashSet<Worker> setid) throws Exception {

        Iterator<Worker> wor = setid.iterator();

        Worker worker = null;

        boolean present = false;

        while (wor.hasNext()) {
            worker = wor.next();
            if ((worker.getId()) == id) {
                present = true;
                break;
            }
        }
        ;
        if (!present) throw new Exception("ID not present");
        setid.remove(worker);
        wor = null;
        worker = null;
    }

    /**
     * Clears Collection setcl
     *
     * @param setcl - Main Collection
     */

    public static void clear(LinkedHashSet<Worker> setcl) {

        setcl.clear();
    }

    /**
     * Saves Collection in infilepath in csv format
     *
     * @param sets
     */

    public static void save(LinkedHashSet<Worker> sets) {
        BufferedOutputStream outstream = null;
        Iterator<Worker> wor = sets.iterator();
        try {
            outstream = new BufferedOutputStream(Files.newOutputStream(infilepath, StandardOpenOption.TRUNCATE_EXISTING));
            if (wor.hasNext()) {
                while (wor.hasNext()) {
                    outstream.write(wor.next().tocsv().getBytes());
                    outstream.write(13);
                    outstream.write(10);
                }
            } else {
                outstream.write("Empty".getBytes());
                System.out.println("Writing empty collection");
            }
        } catch (IOException e) {
            System.out.println("There exception in saving please check savingfile");
            e.getMessage();
            System.out.println("If you wanna try again print save command again or type input another command");
        } finally {
            try {
                outstream.close();
            } catch (IOException e) {
            }
        }
        outstream = null;
        wor = null;
    }

    ;

    /**
     * Run's Script
     *
     * @param scriptfile - File of commands
     * @param sete       - Main collection
     */
    public static void execute_script(File scriptfile, LinkedHashSet<Worker> sete) {
        BufferedInputStream instream;
        try {
            instream = new BufferedInputStream(new FileInputStream(scriptfile));
            String command = new String();
            int a;

            while ((a = instream.read()) != -1) {
                if ((char) a == '\n') {
                    if(command.startsWith("execute_script")) {
                        System.out.println("I can't run execute_script from scriptfile it will provoke Recursive");
                        command = "";
                        continue;
                    }
                    CommandInitializator.initialize(command.split(" "), sete);
                    command = "";
                    continue;
                }
                command = (command + (char) a);
            }
        } catch (IOException e) {
            if(e.getClass().equals(java.io.FileNotFoundException.class)){
                System.out.println("Cannot run Recursive");
            } else {System.out.println("File access right wrong\nPlease correct file access rights and  retype  command");}
        }
        catch (StackOverflowError t){
            System.out.println("We have: " + t.getMessage());
        }

    }

    ;

    /**
     * Exit's program without saing
     */
    public static void exit() {

        System.exit(0);

    }

    ;

    /**
     * @param element - Entered Worker
     * @param setadd  - Main Collection
     * @return Is added
     */
    public static boolean add_if_min(Worker element, LinkedHashSet<Worker> setadd) {
        Iterator<Worker> wor = setadd.iterator();
        Worker h;
        TreeMap<Float, Worker> sortedSet = new TreeMap<Float, Worker>();
        while (wor.hasNext()) {
            h = wor.next();
            sortedSet.put(h.getSalary(), h);
        }
        System.out.println(sortedSet + "  " + sortedSet.firstKey().floatValue() + " " + element.getSalary() + " " + (sortedSet.firstKey().floatValue() > element.getSalary()));
        if ((sortedSet.firstKey().floatValue() > element.getSalary())) {
            setadd.add(element);
            return true;
        }
        sortedSet.clear();
        sortedSet = null;
        wor = null;
        h = null;
        return false;
    }

    ;

    /**
     * Removes Workers with bigger Salary
     *
     * @param element - Worker
     * @param setrem  - Main collection
     */
    public static void remove_greater(Worker element, LinkedHashSet<Worker> setrem) {
        setrem.removeIf(worker -> worker.getSalary() > element.getSalary());

    }

    ;

    /**
     * Shows Command history
     *
     * @param commandhistory - Array with commands
     */
    public static void history(ArrayDeque<String> commandhistory) {
        Iterator<String> s = commandhistory.descendingIterator();
        int n = 0;
        while (s.hasNext() && n <= 12) {
            n++;
            System.out.println(s.next());
        }
        n = 0;
        s = null;
    }

    ;

    /**
     * Shows Average Salary of Worker's in Collection setavs
     *
     * @param setavs - Main collection
     */
    public static Float average_of_salary(LinkedHashSet<Worker> setavs) {
        Iterator<Worker> wor = setavs.iterator();

        Float AvSalary = (float) 0.0;

        while (wor.hasNext()) {
            AvSalary = AvSalary + wor.next().getSalary();

        }

        wor = null;

        return
                AvSalary / setavs.size();
    }

    ;

    /**
     * Makes groups based on Worker's Coordinate
     *
     * @param setid - Main collection
     */
    public static void group_counting_by_coordinates(LinkedHashSet<Worker> setid) {

        Iterator<Worker> wor = setid.iterator();
        UniqList<Coordinates> setiduniq = new UniqList<Coordinates>();

        while (wor.hasNext()) {
            setiduniq.add(wor.next().getCoordinates());
        }

        for (Coordinates c : setiduniq) {
            wor = setid.iterator();
            int count = 0;
            System.out.println("In group " + c + " :");
            while (wor.hasNext()) {
                Worker h = wor.next();
                if (h.getCoordinates().equals(c)) {
                    System.out.println(h);
                    count++;
                }
            }
            System.out.println("There is " + count + " element");
        }
        wor = null;
        setiduniq.clear();
        setiduniq = null;

    }

    ;

    /**
     * Prints Descending view of setdesc
     *
     * @param setdesc - Main collection
     */
    public static void print_descending(LinkedHashSet<Worker> setdesc) {
        Iterator<Worker> wor = setdesc.iterator();
        Worker h;
        TreeMap<Float, Worker> sortedSet = new TreeMap<Float, Worker>();
        while (wor.hasNext()) {
            h = wor.next();
            sortedSet.put(h.getSalary(), h);
        }

        wor = sortedSet.descendingMap().values().iterator();

        while (wor.hasNext()) {
            h = wor.next();
            System.out.println(h);
        }

        sortedSet.clear();
        sortedSet = null;
        h = null;

    }



    public static String reads() {
        String instr = "";
        Scanner newscanner = new Scanner(System.in);
           try{
            newscanner.reset();
        do {
             instr = newscanner.nextLine();
        } while (instr == "");
        newscanner = null;
        System.gc();
    } catch (Exception e) {
               System.out.println("Something went wrong and System.in closed ");
               System.out.println("Programm will not work, without input, please rerun programm");
               System.exit(1);
               System.gc();
           }
           finally { System.gc();

           }


        System.gc();

        return instr;

    }


    ;

    /**
     * Initialize Worker from Terminal
     * @return Worker {@link Worker}
     */
    public static Worker getWorker() {

        DataAnalyze analyzeworkinf = new DataAnalyze();

        boolean bool = false;


        System.out.println("Let's get information about Worker: ");

        while(!bool) {
            System.out.println("Enter Worker's name: ");
                analyzeworkinf.dataArray[0] = TerminalCommands.reads();
            try{
                bool = analyzeworkinf.isMatchName();
            } catch(Exception e){
                System.out.println("Wrong - " + e.getMessage());
                bool = false;
            }
        }
        bool = false;

        while(!bool) {
            System.out.println("Enter " + analyzeworkinf.dataArray[0] + "'s Coordinates.x (x > -678) :");
                analyzeworkinf.dataArray[1] = TerminalCommands.reads();
            System.out.println("Enter Coordinates.y :");
            String str = "";
            /*while(String str != "")
            {
                analyzeworkinf.dataArray[4] = TerminalCommands.reads();
            }*/
            analyzeworkinf.dataArray[1] = analyzeworkinf.dataArray[1].concat("," + TerminalCommands.reads());
            try{
                bool = analyzeworkinf.isMatchCoordinate();
            } catch(Exception e){
                System.out.println("Wrong - " + e.getMessage());
                bool = false;
            }
        }
        bool = false;
        while(!bool) {
            System.out.println("Enter " + analyzeworkinf.dataArray[0] + "'s Salary");
                analyzeworkinf.dataArray[2] = TerminalCommands.reads();

            try{
                bool = analyzeworkinf.isMatchSalary();
            } catch(Exception e){
                System.out.println("Wrong - " + e.getMessage() );
                bool = false;
            }
        }
        bool = false;
        while(!bool) {
            System.out.println("Enter " + analyzeworkinf.dataArray[0] + "'s Status \nIt can be: HIRED, FIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION");
                analyzeworkinf.dataArray[3] = TerminalCommands.reads();

            try{
                bool = analyzeworkinf.isMatchStatus();
            } catch(Exception e){
                System.out.println("Wrong - " + e.getMessage());
                bool = false;
            }
        }
        bool = false;
        while(!bool) {
            System.out.println("Enter " + analyzeworkinf.dataArray[0] + "'s Position\nIt can be:" + Position.DEVELOPER + ", " + Position.HEAD_OF_DEPARTMENT +", "+ Position.ENGINEER +", "+ Position.LABORER +", "+ Position.MANAGER);

                analyzeworkinf.dataArray[4] = TerminalCommands.reads();

            try{
                bool = analyzeworkinf.isMatchPosition();
            } catch(Exception e){
                System.out.println("Wrong - " + e.getMessage());
                bool = false;
            }
        }
        bool = false;
        while(!bool) {
            System.out.println("Enter " + analyzeworkinf.dataArray[0] + "'s Organization Name");
            analyzeworkinf.dataArray[5] = TerminalCommands.reads();
            System.out.println("Enter Type of Organization It can be: " + OrganizationType.PRIVATE_LIMITED_COMPANY+", " + OrganizationType.GOVERNMENT +", "+ OrganizationType.PUBLIC);
            analyzeworkinf.dataArray[5] = analyzeworkinf.dataArray[5].concat("," + TerminalCommands.reads());
            System.out.println("Enter Organization's City or ZipCode");
            analyzeworkinf.dataArray[5] = analyzeworkinf.dataArray[5].concat("," + TerminalCommands.reads());

            try{
                bool = analyzeworkinf.isMatchOrg();
            } catch(Exception e){
                System.out.println("Wrong - " + e.getMessage());
                bool = false;
            }
        }

        if (bool) {System.out.println("We are done!");}
       try {
           return new Worker(analyzeworkinf.getmatch());
       } catch(Exception e) {
           System.out.println("What sh@t is going on(" + e.getClass() );

       }

       return null;

    }
    ;

    public static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception e)
        {
            System.out.println("ClearWindow\n\n\n");
        }
    }

}
