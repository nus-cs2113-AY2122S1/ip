package duke.command;

import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    // Constants
    private static final String MESSAGE_BOUNDARY = "____________________________________________________________";

    // Attributes
    private static TaskOperation doTask = new TaskOperation();

    // Constructor
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        Echo();
    }

    public static void Greet(){
        String lines = "____________________________________________________________";
        String greet = " Hello! I'm duke.command.Duke\n"
                    + " What can I do for you?\n";
        System.out.println(MESSAGE_BOUNDARY + "\n" + greet + MESSAGE_BOUNDARY);
    }

    public static void Echo() throws DukeException {
        boolean continueChat;
        Scanner in = new Scanner(System.in);

        do{
            // Get query from user
            String userInput = in.nextLine();

            // Give response
            continueChat = giveResponse(userInput);

        }while(continueChat);

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
