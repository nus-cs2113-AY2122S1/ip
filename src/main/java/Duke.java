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
    public static final String INVALID_COMMAND = "Please enter a valid command";


    public static String getCommand(String userInput) {
        int index = userInput.indexOf(" ");
        if (index == -1) {
            return userInput;
        }
        return userInput.substring(0, index).toLowerCase();
    }

    public static String getInput(String userInput) {
        int index = userInput.indexOf(" ");
        return userInput.substring(index + 1).trim();
    }

    public static void main(String[] args) {
        Assistant duke = new Assistant();
        String userInput = "";
        Scanner in = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);
        userInput = in.nextLine();
        String inputCommand = getCommand(userInput);
        String input = "";
        int index = 0;
        while (!inputCommand.equals(COMMAND_EXIT)) {
            switch (inputCommand) {
            case COMMAND_LIST:
                Assistant.listTasks();
                break;
            case COMMAND_DONE:
                int completedIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));
                Assistant.completeTask(completedIndex);
                break;
            case COMMAND_DEADLINE:
                input = getInput(userInput);
                index = input.indexOf("/");
                if (index == -1) {
                    System.out.println(INVALID_COMMAND);
                }
                else {
                    Assistant.addTask(new Deadline(input.substring(0, index), input.substring(index + 1)));
                }
                break;
            case COMMAND_EVENT:
                input = getInput(userInput);
                index = input.indexOf("/");
                if (index == -1) {
                    System.out.println(INVALID_COMMAND);
                }
                else {
                    Assistant.addTask(new Event(input.substring(0, index), input.substring(index + 1)));
                }
                break;
            case COMMAND_TODO:
                input = getInput(userInput);
                Assistant.addTask(new ToDo(input));
                break;
            default:
                System.out.println(INVALID_COMMAND);
                break;
            }
            userInput = in.nextLine();
            inputCommand = getCommand(userInput);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }


}
