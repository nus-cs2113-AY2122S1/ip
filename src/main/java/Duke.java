import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    //declarations
    public static String line = "------------------------------------------------------------------------------------------\n";
    private static int quitFlag = 0; //if 0, take user input. Otherwise, don't.

    //declare task array and keep track of how many tasks stored
    public static Task[] t = new Task[100];
    public static int taskCount = 0;

    //Program starts with this greeting
    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "Hello! I'm Duke.\n" + logo + "What can i do for you?\n" + line);
    }

    //Program exits with this ending
    public static void sayBye(String input) throws DukeException{                                                       //bye
        if (input.length() != 3) {
            throw new DukeException("You didn't say bye properly, but i get it! Adios for now!\n");                     //bye 9
        } else {
            System.out.println(line + "\n" + "Ciao! More tasks to do later!\n" + line);                                 //bye
            updateQuitFlag();
        }
        System.exit(0);
    }

    //updates flag so that no more user input is taken
    public static void updateQuitFlag() {
        quitFlag = 1;
    }

    //Lists out all tasks stored and their statuses
    public static void sayList(String input) throws DukeException{                                                      //list
        if (input.length() == 4) {
            if (taskCount > 0) {
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + t[i].toString() + "\n");
                }
                System.out.println(line);
            }
            else {
                throw new DukeException("Hold your horses, we haven't even started listing yet!");                      //list when taskCount = 0
            }
        } else {
            throw new DukeException("Invalid input! Ask me nicely to list!");                                           //list 7
        }
    }

    //Marks a stored task as done
    public static void sayDone(String input) throws DukeException {                                                     //done 1
        if (taskCount != 0) {
            try {
                String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
                int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
                t[finalTaskNumber].markAsDone();
                System.out.println(line + "\n" + "Kudos! One less thing to stress about!\n");                           //done 1
                System.out.println("  " + t[finalTaskNumber].toString() + "\n" + line);
            } catch (NullPointerException e) {
                System.out.println(line + "\nInvalid task number entered! Please try again!\n" + line);                 //done 101
            }
        } else {
            throw new DukeException("Calm down, we haven't even started listing out tasks yet!");                       //done 1 when taskCount = 0
        }
    }

    //Adds a new todo task and prints it                                                                                //todo borrow book
    public static void sayTodo(String input) {
        if (input.length() == 4) {
            System.out.println(line + "\nDid you forget what you were gonna say?\n" + line);                            //todo
        } else {
            int endIndex = input.length();
            String taskName = input.substring(5, endIndex);
            t[taskCount] = new Todo(taskName);
            System.out.println(line + "\n");
            System.out.println("That's the spirit! I've added this task:\n");                                           //todo borrow book
            System.out.println(t[taskCount].toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    //Adds a new deadline task and prints it
    public static void sayDeadline(String input) {                                                                      //deadline return book /by Sunday
        if (input.length() == 8) {
            System.out.println(line + "\nAre you kidding me? You didn't even tell me what you were supposed to do!\n"); //deadline
            System.out.println(line);
        } else if (!input.contains("/")) {
            System.out.println(line + "\nAre you kidding me? You forgot to tell me your deadline again?\n" + line);     //deadline return book
        } else {
            int endIndex = input.lastIndexOf("/");
            String taskName = input.substring(9, endIndex);
            int endIndex2 = input.length();
            String by = input.substring(endIndex + 4, endIndex2);
            t[taskCount] = new Deadline(taskName, by);
            System.out.println(line + "\n");
            System.out.println("Got it. I've added this task:\n");                                                      //deadline return book /by Sunday
            System.out.println(t[taskCount].toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    //Adds a new event task and prints it
    public static void sayEvent(String input) {                                                                         //event project meeting /at Mon 2-4pm
        if (input.length() == 5) {
            System.out.println(line + "\nAre you kidding me? You didn't even tell me what you were supposed to do!\n"); //event
            System.out.println(line);
        } else if (!input.contains("/")) {
            System.out.println(line + "\nAre you kidding me? You forgot to tell me your event timing again?\n" + line); //event project meeting
        } else {
            int endIndex = input.lastIndexOf("/");
            String taskName = input.substring(6, endIndex);
            int endIndex2 = input.length();
            String at = input.substring(endIndex + 4, endIndex2);
            t[taskCount] = new Event(taskName, at);
            System.out.println(line + "\n");
            System.out.println("Got it. I've added this task:\n");                                                      //event project meeting /at Tue 3-7pm
            System.out.println(t[taskCount].toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    //Creates scanner, takes in user input & filters it to different methods
    public static void inputSort() throws DukeException {
        System.out.println("Enter your wish: " + "\n" + line);
        while (quitFlag == 0) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String actionWord = input.split(" ")[0];
            switch (actionWord) {
            case "bye":
                sayBye(input);
                break;
            case "list":
                sayList(input);
                break;
            case "done":
                sayDone(input);
                break;
            case "todo":
                sayTodo(input);
                break;
            case "deadline":
                sayDeadline(input);
                break;
            case "event":
                sayEvent(input);
                break;
            default:
                System.out.println(line + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
            }
        }
    }

    //Main
    public static void main(String[] args) throws DukeException {
        start();
        inputSort();
    }
}

