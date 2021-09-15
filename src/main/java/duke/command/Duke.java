package duke.command;

import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    // Constants
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_BOUNDARY = "____________________________________________________________";
    public static final String FILE_NAME = "data/duke.txt";

    // Attributes
    private static final TaskOperation doTask = new TaskOperation();

    // Main function
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        Greet();
        doTask.loadFile(FILE_NAME);
        Echo();
        doTask.saveFile(FILE_NAME);

    }

    public static void Greet(){
        System.out.println("Hello from\n" + LOGO);
        String greet = " Hello! I'm duke.command.Duke\n"
                    + " What can I do for you?\n";
        System.out.println(MESSAGE_BOUNDARY + "\n" + greet + MESSAGE_BOUNDARY);
    }

    public static void Echo() throws DukeException {
        boolean isChat;
        Scanner in = new Scanner(System.in);

        do{
            // Get query from user
            String userInput = in.nextLine();

            // Give response
            isChat = giveResponse(userInput);

        }while(isChat);

        in.close();
    }

    public static boolean giveResponse(String userInput) throws DukeException {
        String command = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;

        try{
            switch (command) {
                case "todo":
                case "deadline":
                case "event":
                    doTask.addTask(command, userInput);
                    break;
                case "done":
                    doTask.doneTask(userInput);
                    break;
                case "delete":
                    doTask.deleteTask(userInput);
                    break;
                case "list":
                    doTask.printList();
                    break;
                case "bye":
                    Exit();
                    return false;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e){
            //Info user that an error occurred
            System.out.println(MESSAGE_BOUNDARY);
            System.out.println("OOPS!!! " + e.getMessage());
            System.out.println(MESSAGE_BOUNDARY);
        }

        return true;
    }

    public static void Exit(){
        String bye = " Bye. Hope to see you again soon!\n";
        System.out.println(MESSAGE_BOUNDARY + "\n" + bye + MESSAGE_BOUNDARY);
    }

}
