package com.nsi.lab5.commandwork;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Scanner;

import static com.nsi.lab5.commandwork.Main.infilepath;


/**
 * Abstract class contains methods that determines Commands and preparing data to run them
 */
public abstract   class CommandInitializator {
    /**
     *
     * @param seth - Main Collection
     *  Reads Commands
     */
    public static void intializecommand(LinkedHashSet<Worker> seth){
        Scanner inscanner = new Scanner(System.in);
        String instring = "";

        System.out.println("Input main command please :");
        try{
        if (true) {
            while(instring.equals("")){
                instring = inscanner.nextLine();
            }
        } else {
            instring = "";
        } } catch (Exception e) {
            System.out.println("Something went wrong and System.in closed ");
            System.out.println("Programm will not work, without input, please rerun programm");
            System.exit(1);
            System.gc();
        }
        System.out.println("\"" + instring + "\"");
        initialize(instring.replace((char)13,' ').replace((char)10,' ').trim().split(" ",3), seth);
    }

    /**
     * determines Commands and prepares data
     * @param inarray
     * @param seth
     */
    public static void initialize(String[] inarray,LinkedHashSet<Worker> seth) {

        switch (inarray[0].trim()) {
            case "help": { /**help command*/System.out.println("Using " + inarray[0]);
            Main.commandhistory.add(inarray[0]);
            TerminalCommands.help();
            }
            ;
            break;

            case "info": { /**info command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                TerminalCommands.info(seth);
            }
            ;
            break;

            case "show": { /**show command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                TerminalCommands.show(seth);
            }
            break
            ;
            case "exit": { /**show command*/System.out.println("See you soon - " + inarray[0]);
                Main.commandhistory.clear();
                seth.clear();
                seth = null;
                System.exit(0);
            }
            ;
            break;

            case "add": { /**add command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                System.out.println("Worker has been added - " +TerminalCommands.add(TerminalCommands.getWorker(),seth));
                Main.setw_change_date =  ZonedDateTime.now();
            }
            ;
            break;

            case "update_by_id": { /**update command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                try {
                    TerminalCommands.update_id(Integer.parseInt(inarray[1]), TerminalCommands.getWorker(), seth);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(" ID should be integer: " + inarray[1]);
                    break;
                }
                Main.setw_change_date =  ZonedDateTime.now();
                System.out.println("Updating " + inarray[1] + " done");
            }
            ;
            break;

            case "remove_by_id": { /**remove_by_id command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                try{
                TerminalCommands.remove_by_id(Integer.parseInt(inarray[1]),seth);
            } catch (Exception e) {
                System.out.println(" ID should be integer: " + e.getMessage());
                break;}
                Main.setw_change_date =  ZonedDateTime.now();
                System.out.println("Removing done");
            }
            ;
            break;

            case "clear": { /**clear command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                Main.setw_change_date =  ZonedDateTime.now();
                TerminalCommands.clear(seth);
            }
            ;
            break;

            case "save": { /**remove_by_id command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                TerminalCommands.save(seth);
            }
            break;

            case "execute_script": {
                System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                try {
                    if (Files.exists(Paths.get(inarray[1].trim()), LinkOption.NOFOLLOW_LINKS)) {
                        File scriptfile = new File(inarray[1].trim());
                        System.out.println(scriptfile);
                        TerminalCommands.execute_script(scriptfile, seth);
                    } else System.out.println("Check file existing and retype command ");
                } catch (Exception e) {
                    System.out.println("Wrong command Data");
                }
                ;
            }
            ;
            break;

            case "add_if_min": { /**add_if_min command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                System.out.println("Element added - " + TerminalCommands.add_if_min(TerminalCommands.getWorker(),seth));
                Main.setw_change_date =  ZonedDateTime.now();
            }
            ;
            break;

            case "remove_greater": { /**remove_greater command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                TerminalCommands.remove_greater(TerminalCommands.getWorker(),seth);
                Main.setw_change_date =  ZonedDateTime.now();
                System.out.println("Removing done");
            }
            ;
            break;

            case "history": { /**history command*/System.out.println("Using " + inarray[0]);
                TerminalCommands.history(Main.commandhistory);
                Main.commandhistory.add(inarray[0]);
            }
            ;
            break;

            case "average_of_salary": { /**average_of_salary command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                System.out.println(TerminalCommands.average_of_salary(seth));
            }
            ;
            break;

            case "group_counting_by_coordinates": { /**group_counting_by_coordinates command*/
                System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                TerminalCommands.group_counting_by_coordinates(seth);
            }
            ;
            break;

            case "print_descending": { /**print_descending command*/System.out.println("Using " + inarray[0]);
                Main.commandhistory.add(inarray[0]);
                TerminalCommands.print_descending(seth);
            }
            ;
            break;

            default:{
                System.out.println("Input correct command please\n" + "To see list of commands use \'help\' command ");
            }


        }
    }
}
