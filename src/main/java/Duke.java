package main.java;

import java.util.Scanner;

public class Duke {
    public static final int MAX_TASKS = 100;
    public static final String WELCOME_MESSAGE =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "Hello I'm Duke\nWhat can I do for you?";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";


    public static String getCommand(String userInput) {
        int index = userInput.indexOf(" ");
        if (index == -1) {
            return userInput;
        }
        return userInput.substring(index);
    }



    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        int index = 0;
        String userInput = "";
        Scanner in = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);
        userInput = in.nextLine();
        String inputCommand = getCommand(userInput);


        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(tasks[i].getStatus() + (i+1) + ". " + tasks[i].getTaskName()
                    );
                }
            }
            else if (userInput.contains("done")) {
                userInput = userInput.trim();
                int completedIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));
                tasks[completedIndex - 1].finishTask();
                System.out.println("Task " + completedIndex + " mark as done!");
            }
            else {
                Task temp = new Task(userInput);
                tasks[index] = temp;
                System.out.println("added: " + userInput);
                index++;
            }
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
