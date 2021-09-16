package com.duke;

import com.file.FileManager;
import com.task.Deadline;
import com.task.Event;
import com.task.Task;
import com.task.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    public static FileManager fileTask;
    static ArrayList<Task> list = new ArrayList<>();
    public static String[] dukeLines = new String[100];
    public static int dukeLineCount = 0;
    public final static String LINESEPARATER =  "\t______________________________________________________________________";

    public static void addTask(Task item) {
        list.add(item);
    }
    private boolean active = false;

    public Duke(){
        active = true;
        printLine();
        System.out.println("\tHello from");
        System.out.println("\t ____        _        ");
        System.out.println("\t| _ \\ _   _| | _____ ");
        System.out.println("\t| | | | | | | |/ / _ \\");
        System.out.println("\t| |_| | |_| |   <  __/");
        System.out.println("\t|____/ \\__,_|_|\\_\\___|");
        loadFromFile();
        endLine();
    }


    public void loadFromFile(){
        try {
            fileTask = new FileManager("Task.txt");
            ArrayList<String> Lines = fileTask.readFile();
            for(String line : Lines) {
                if(line.contains("[T]")) {
                    String description = line.substring(6);
                    list.add(new Todo(description));
                }
                else if (line.contains("[E]")) {
                    String description = line.substring(6, line.indexOf("("));
                    String ddl = line.substring(line.indexOf("(")+1,line.indexOf(")"));
                    list.add(new Event(description,ddl));
                }else if (line.contains("[D]")) {
                    String description = line.substring(6, line.indexOf("("));
                    String ddl = line.substring(line.indexOf("(")+1,line.indexOf(")"));
                    list.add(new Deadline(description,ddl));
                }
                list.get(list.size()-1).setDone(line.contains("[X]"));
            }
        } catch (FileNotFoundException e) {
            dukePrint("unable to locate file");
        }catch (IOException e) {
            dukePrint("unable to create file");
        }
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
        dukePrint("Hello! I'm Duke");
        dukePrint("What can I do for you?");
        endLine();
    }


    public void unknownAction() {
        dukePrint("Sorry! I don't understand");
        endLine();
    }


    public void addList(Task item) {
        list.add(item);
        dukePrint("Got it. I've added this task:");
        dukePrint(list.get(list.size()-1).toString());
        dukePrint("Now you have " + list.size() + " tasks in the list.");
        try {
            //fileTask.addToFile(list.get(list.size()-1));
            fileTask.rewriteFile(list);
        }catch (IOException e) {
            dukePrint("unable to write to file");
        }
        endLine();
    }


    public void listOut() {
        if(list.size()==0) {
            dukePrint("Woohooo no tasks due ~~~~");
        }
        else{
            dukePrint("Now you have " + list.size() + " tasks in the list.");
        }
        for(int i = 0; i< list.size(); i++) {
            dukePrint((i+1) + ". "+list.get(i).toString());
        }
        endLine();
    }


    public void markDone(String line) {
        try {
            line = line.replaceAll("[^(\\d)]", "");
            int index = Integer.parseInt(line);
            index -= 1;
            list.get(index).setDone(true);
            dukePrint("Congrats! you have done the following task");
            dukePrint(list.get(index).toString());
            fileTask.rewriteFile(list);
        } catch (NumberFormatException e) {
            dukePrint("\tplease enter the index of the task");
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            dukePrint("number entered is invalid");
        } catch (IOException e) {
            dukePrint("Unable to write files");
            endLine();
        }
        endLine();
    }


    public void deleteItem(String line) {
        try {
            line = line.replaceAll("[^(\\d)]", "");
            int index = Integer.parseInt(line);
            index -= 1;
            String deletedItem = list.get(index).toString();
            list.remove(index);
            fileTask.rewriteFile(list);
            dukePrint("I have deleted the following task");
            dukePrint(deletedItem);
            dukePrint("Now you have " + list.size() + " tasks in the list.");
            endLine();
        } catch (NumberFormatException e) {
            dukePrint("\tPlease enter the index of the task.");
            listOut();
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            dukePrint("Number entered is invalid.");
            listOut();
        } catch (IOException e) {
            dukePrint("Unable to write files");
            endLine();
        }
    }


    public void dukePrint(String line) {
        dukeLines[dukeLineCount++] = line;
    }
}
