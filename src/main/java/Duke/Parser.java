package Duke;

import Duke.Task.TaskList;

import java.util.Scanner;

public class Parser {

    static void parseCommand() {

        boolean condition = true;
        Ui message = new Ui();
        Scanner myObj = new Scanner(System.in);

        //The main loop where all the input cases are run through, if there was no specific command given, the statement is added as a task to the list.
        while(condition) {
            String userInput = myObj.nextLine();
            String instruction = userInput.contains(" ") ? userInput.substring(0, userInput.indexOf(" ")) : userInput;
            String instructionTask = userInput.contains(" ") ? userInput.substring(userInput.indexOf(" ") + 1) : " ";

            switch (instruction) {
            case "bye": // When the command bye is given, the program exits the while loop and stops
                System.out.println("widePeepoSad :(");
                message.printLineBreak();
                condition = false;
                break;
            case "list": // Lists out all the current tasks in the order that they were added in
                TaskList.printList();
                break;
            case "done": // Has to be input with an integer value indicating the specific task to be marked as completed
                TaskList.thisDone(instructionTask);
                Storage.saveData();
                break;
            case "todo": // Adds a task to the list that is marked as to be done.
                TaskList.addToDo(instructionTask);
                Storage.saveData();
                break;
            case "deadline": // Adds a task to the list that has a deadline that it needs to be completed by, using "/by" as the input command
                TaskList.addDeadline(instructionTask);
                Storage.saveData();
                break;
            case "event": // Adds a task to the list that will occur in the future by a given date, using "/at" as the input command.
                TaskList.addEvent(instructionTask);
                Storage.saveData();
                break;
            case "help": // Prints a list of commands that the user can read to know what features are available
                message.printHelp();
                message.printLineBreak();
                break;
            case "delete": // Deletes a task from the list
                TaskList.deleteTask(instructionTask);
                Storage.saveData();
                break;
            case "find":
                TaskList.findTask(instructionTask);
                break;
            default: // When no specific command is selected, the input is added automatically to the list of tasks.
                message.printIntructionsUnclear();
                message.printLineBreak();
                break;
            }
        }
    }

}
