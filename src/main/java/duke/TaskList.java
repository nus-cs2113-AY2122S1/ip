package duke;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    //attributes
    ArrayList<Task> list;
    int taskCount = 0;

    //constructor
    public TaskList() {
        list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }


    //methods
    public void printList() {
        int position = 1;
        System.out.println("    Here are the tasks in your list:");
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( "    " + position  + "." + list.get(i));
            position ++;
        }
    }

    public void markTaskDone(String word) {
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

    public void addTask(Task newTask) {
        //add task to list
        list.add(newTask);
        int size = list.size();
        //print statements
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + list.get(size - 1));
        System.out.println("    Now you have " + (size + 1) + " tasks in the list.");
    }

    public void deleteTask(String word) {
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



    public void distinguishCommand(String command) {
        //split into word array
        String[] words = command.split(" ");
        String firstWord = words[0];

        //determine command
        switch (firstWord) {
        case "list":
            printList();
            break;
        case "done":
            try {
                markTaskDone(words[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a done cannot be empty.");
            }
            break;
        case "todo":
            try {
                ToDos newToDo = new ToDos(command.substring(5));
                addTask(newToDo);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                Deadlines newDeadline = createNewDeadline(command.substring(9));
                addTask(newDeadline);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            //extract out event description and timeAllocation
            try {
                Events newEvent = createNewEvent(command.substring(6));
                addTask(newEvent);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        case "delete":
            try {
                deleteTask(words[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");

            break;
        }


    }





}
