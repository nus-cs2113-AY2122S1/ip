package duke;

import duke.task.Task;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Duke {

    public static Task[] list = new Task[100];
    public static String[] dukeLines = new String[100];
    public static int dukeLineCount = 0;
    public static int listCount = 0;
    public final static String LINESEPARATER =  "\t______________________________________________________________________";

    public static void addTask(Task item) {
        list[listCount++] = item;
    }
    private boolean active = false;

    public Duke() {
        active = true;
        printLine();
        System.out.println("\tHello from");
        System.out.println("\t ____        _        ");
        System.out.println("\t| _ \\ _   _| | _____ ");
        System.out.println("\t| | | | | | | |/ / _ \\");
        System.out.println("\t| |_| | |_| |   <  __/");
        System.out.println("\t|____/ \\__,_|_|\\_\\___|");
        endLine();
    }

    public boolean getStatus() {
        return active;
    }

    public void printLine() {
        System.out.println(LINESEPARATER);
    }
    public void endLine() {
        int max_len= 0 ;
        for(int i = 0 ;i < dukeLineCount; i++) {
            if(dukeLines[i].length() > max_len) {
                max_len = dukeLines[i].length();
            }
        }
        for(int i = 0 ;i < dukeLineCount; i++) {
            for(int j=0; j<LINESEPARATER.length()-max_len+3; j++)
            {
                System.out.print(" ");
            }
            System.out.println(dukeLines[i]);
        }
        printLine();
        System.out.print("\t");
        dukeLineCount = 0;
    }

    public void endDuke() {

        dukePrint("Bye. Hope to see you again soon!");
        endLine();
        active = false;
    }

    public void greet() {
        //printLine();
        dukePrint("Hello! I'm Duke");
        dukePrint("What can I do for you?");
        endLine();
    }

    public void unknownAction() {
        //printLine();
        dukePrint("Sorry! I don't understand");
        endLine();
    }

    public void addList(Task item) {
        //printLine();
        list[listCount++] = item;
        dukePrint("Got it. I've added this task:");
        dukePrint(list[listCount-1].toString());
        dukePrint("Now you have " + listCount + " tasks in the list.");
        endLine();
    }

    public void listOut() {
        //printLine();
        if(listCount==0) {
            dukePrint("Woohooo no tasks due ~~~~");
        }
        else{
            dukePrint("Now you have " + listCount + " tasks in the list.");
        }
        for(int i = 0; i< listCount; i++) {
            dukePrint((i+1) + ". "+list[i].toString());
        }
        endLine();
    }

    public void markDone(String line) {
        try {
            line = line.replaceAll("[^(\\d)]", "");
            int index = Integer.parseInt(line);
            index -= 1;
            list[index].setDone(true);
            dukePrint("Congrats! you have done the following task");
            dukePrint(list[index].toString());
        } catch (NumberFormatException e) {
            dukePrint("\tplease enter the index of the task");
        } catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
            dukePrint("number entered is invalid");
        }
        endLine();
    }

    public void dukePrint(String line) {
        dukeLines[dukeLineCount++] = line;
    }
}
