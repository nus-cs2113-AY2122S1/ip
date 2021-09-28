package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a list of tasks with operations that can be performed
 */
public class TaskList {

    //attributes
    static ArrayList<Task> list;
    static ArrayList<Task> searchList;
    int taskCount = 0;

    //constructor
    public TaskList() {
        list = new ArrayList<>();
        searchList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        searchList = new ArrayList<>();
    }


    //methods

    /**
     * This method prints out the whole list of Todos/Deadlines/Events objects stored in the list
     */
    public static void printList() {
        int position = 1;
        System.out.println("    Here are the tasks in your list:");
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( "    " + position  + "." + list.get(i));
            position ++;
        }
    }


    public static void printSearchList() {
        int position = 1;
        System.out.println("    Here are the matching tasks in your list:");
        for( int i = 0 ; i < searchList.size() ; i ++ ) {
            System.out.println( "    " + position  + "." + searchList.get(i));
            position ++;
        }
    }
    
    /**
     * This method marks a Task in the list as "completed" according to the user input
     * @param word The String representation of the position of the specific Task in the list input by the User
     */
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

    /**
     * This method adds a new task to the end of its list of Tasks
     * @param newTask The Task objected to be added to the current list
     */
    public static void addTask(Task newTask) {
        //add task to list
        list.add(newTask);
        int size = list.size();
        //print statements
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + list.get(size - 1));
        System.out.println("    Now you have " + (size + 1) + " tasks in the list.");
    }

    /**
     * This method deletes a Task from the list according to the user input
     * @param word The String representation of the position of the specific Task in the list input by the user
     */
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

    public static void findTask(String phrase) {

        for(Task task: list) {
            if(task.description.contains(phrase)) {
                searchList.add(task);
            }
        }

        //print the search list
        printSearchList();
        searchList.clear();


    }

    /**
     * This method takes in a String representation of a Deadline Task, extracts out the description and deadline,
     * and creates a corresponding Deadline object
     * @param command the String representation of the description and deadline of the task
     * @return a Deadline object with description and deadline filled in
     */
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

        String stringDateTime = by.substring(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(stringDateTime, format);

        Deadlines newDeadline = new Deadlines(deadlineDescription.substring(1), dateTime);
        return newDeadline;

    }

    /**
     * This method takes in a String representation of an Event Task, extracts out the description and event time,
     * and creates a corresponding Event object
     * @param command the String representation of the description and time of the task
     * @return a Event object with description and time filled in
     */
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

        String stringDateTime = timeAllocation.substring(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(stringDateTime, format);


        Events newEvent = new Events(eventDescription.substring(1), dateTime);
        return newEvent;

    }

}
