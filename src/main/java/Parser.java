import tasks.Storage;
import tasks.TaskList;
import tasks.Tasks;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the processing and understanding of the user's input
 */
public class Parser {
    static boolean isContinue = true;

    public static boolean getIsContinue(){
        return isContinue;
    }

    /**
     * Checks if the user input is valid then executes the commands based on what the user has entered.
     * To exit the current program the user is in, they can either key in 'bye' to complete exit the application or
     * 'change' to select between echo and tasks again.
     * @param userChoice is the commands entered by the user which can be either: echo or tasks
     * @param tasksAL is the ArrayList container that stores all the tasks that the user enters
     */
    public static void executeUserChoice(String userChoice, ArrayList<Tasks> tasksAL) {
        Scanner scannerObj = new Scanner(System.in);
        while (!checkValidInput(userChoice)) {
            System.out.println("Invalid input, please try again. You may choose: Echo, Tasks");
            userChoice = scannerObj.nextLine();
        }

        switch (userChoice) {
        case "echo": //Echo what is said to user
            System.out.println("I'm a copy cat!");
            while (true) {
                String userInput = scannerObj.nextLine();
                if (userInput.equalsIgnoreCase("bye")) {
                    isContinue = false;
                    break;
                } else if (userInput.equalsIgnoreCase("change")) {
                    break;
                } else {
                    System.out.println(userInput);
                }
            }
            break;

        case "tasks": //Activate list actions
            Storage.loadSaves(tasksAL);
            while (true) {
                System.out.println("To view your list, enter 'list'. " +
                        "To add to your list type 'todo', 'event' or 'deadline' then your task."
                            + System.lineSeparator() + "Date and time format: ddmmyyyy time(in 24hr), " +
                                "e.g. 02122021 1231");
                String userInput = scannerObj.nextLine();
                if (userInput.equalsIgnoreCase("bye")) {
                    isContinue = false;
                    break;
                } else if (userInput.equalsIgnoreCase("change")) {
                    break;
                } else {
                    TaskList.manageTasks(userInput, tasksAL);
                }
            }
            break;

        default:
            break;
        }
    }


    private static boolean checkValidInput(String input){
        String[] validInputs = {"echo", "tasks"};
        for (String validInput: validInputs){
            if (input.equalsIgnoreCase(validInput)){
                return true;
            }
        }
        return false;
    }
}
