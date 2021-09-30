package duke;

import tasks.*;


import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class TaskList {
    protected static Tasks[] list = new Tasks[100];
    protected static final Tasks[] List = new Tasks[100];
    protected static int listSize = 0;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");



    public static void deleteTask(int deleteNo) { //delete a task
        int noTasksBefore = listSize;
        System.out.println("Noted. I've removed this task:");
        System.out.println(List[listSize-1]);

        for (int i=deleteNo-1; i<noTasksBefore; i++)
        {
            List[i]= List[i+1];
        }
        List[noTasksBefore]=null;
        listSize= listSize-1;
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    public static void setDone(int doneNumber) { //set a task as done
        List[doneNumber - 1].setDone("X");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(List[doneNumber - 1]);
    }

    public static void setDeadline(String item, String timing) { //set deadline
        LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

        List[listSize] = new Deadline(item, dateTime);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");


        //System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));

    }

    public static void setEvent(String item, String timing) { //set event
        List[listSize] = new Event(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }

    public static void setTodo(String item) { //set todo
        List[listSize] = new Todo(item);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }

    public static void list() { //list out all the items
        for (int i = 0; i < listSize; i++) {
            System.out.println(i + 1 + "." + List[i]);
        }
    }

    public static void listLast() {
        System.out.println(List[listSize]);
    } //command called when todos are written to list what was last written

    public static void search(String tobeSearched) { //finding the item based on keyword
        int foundCounter =1;
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < listSize; i++) {
                if ((List[i].seeDescription()).contains(tobeSearched)) {
                    System.out.println(foundCounter + "." + List[i]);
                }
            }
        }

        }
