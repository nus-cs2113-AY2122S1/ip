package duke;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    //attributes
    static ArrayList<Task> list;
    int taskCount = 0;

    //constructor
    public TaskList() {
        list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }


    //methods
    public static void printList() {
        int position = 1;
        System.out.println("    Here are the tasks in your list:");
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( "    " + position  + "." + list.get(i));
            position ++;
        }
    }

    public static void markTaskDone(String word) {
        //convert string number to int number
        int position = Integer.parseInt(word);
        //if task does not exist, return
        if (position > list.size()) {
            System.out.println("    Sorry! No such task!");
            return;
        }
        //if task exist, mark task as done
        Task task = list.get(position - 1);
        task.setDone();
        //print notification
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public static void addTask(Task newTask) {
        //add task to list
        list.add(newTask);
        int size = list.size();
        //print statements
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + list.get(size - 1));
        System.out.println("    Now you have " + (size + 1) + " tasks in the list.");
    }

    public static void deleteTask(String word) {
        int size = list.size();
        //convert string number to int number
        int position = Integer.parseInt(word);
        //if task does not exist, return
        if (position > list.size()) {
            System.out.println("    Sorry! No such task!");
            return;
        }
        Task task = list.get(position - 1);
        list.remove(position - 1);
        //print statements
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + (size - 1) + " tasks in the list.");
    }


    public static Deadlines createNewDeadline(String command) {
        String[] words = command.split(" ");
        String by = "";
        String deadlineDescription = "";
        boolean foundBy = false;
        for(int i = 0 ; i < words.length ; i ++) {
            if(foundBy) {
                by = by + " " + words[i];
            } else {
                if(words[i].equals("/by")) {
                    foundBy = true;
                } else {
                    deadlineDescription = deadlineDescription + " " + words[i];
                }
            }
        }
        Deadlines newDeadline = new Deadlines(deadlineDescription.substring(1), by.substring(1));
        return newDeadline;

    }

    public static Events createNewEvent(String command) {

        String[] words = command.split(" ");
        String timeAllocation = "";
        String eventDescription = "";
        boolean foundAt = false;
        for(int i = 0 ; i < words.length ; i ++) {
            if(foundAt) {
                timeAllocation = timeAllocation + " " + words[i];
            } else {
                if(words[i].equals("/at")) {
                    foundAt = true;
                } else {
                    eventDescription =  eventDescription + " " + words[i];
                }
            }
        }
        Events newEvent = new Events(eventDescription.substring(1), timeAllocation.substring(1));
        return newEvent;

    }









}
