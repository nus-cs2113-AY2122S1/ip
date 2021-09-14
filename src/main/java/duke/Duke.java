package duke;

import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke extends DukeMethods {

    public static void main(String[] args) {
        String userCommand; //store user input
        ArrayList<Task> taskList = new ArrayList<>(); //store all the task from user input
        printIntroduction();

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine(); //user input

        Storage.openFile(taskList);
        while (!userCommand.equalsIgnoreCase("bye")) { //exit command is bye
            if (userCommand.equalsIgnoreCase("list")) { //prints list of task
                printList(taskList);
            }
            else if (userCommand.equalsIgnoreCase("help")) {
                printHelpList();
            }
            else if (userCommand.toLowerCase().contains("done ")) { //check user input to see if task completed
                markCompletedTask(userCommand, taskList);
            }
            else if (isValid(userCommand.toLowerCase())){
                addAndPrintTask(userCommand, taskList);
            }
            else if (userCommand.contains("delete ")) {
                deleteAndPrintTask(userCommand, taskList);
            }
            else {
                printUnknownCommandMessage();
            }
            Storage.saveFile(taskList);
            userCommand = in.nextLine();
        }
        printBye();
    }
}