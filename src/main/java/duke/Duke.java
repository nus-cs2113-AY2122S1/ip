package duke;

import duke.actions.Task;
import duke.exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke extends TaskManager {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userInput = "";
        printIntro();
        while (!userInput.startsWith("bye")) {
            userInput = in.nextLine().toLowerCase();
            try {
                if (userInput.contains("help")) {
                    printHelpList();
                } else if (userInput.startsWith("to do ")) {
                    addTaskAsToDo(taskList, userInput);
                } else if (userInput.startsWith("deadline ")) {
                    addTaskAsDeadline(taskList, userInput);
                } else if (userInput.startsWith("event ")) {
                    addTaskAsEvent(taskList, userInput);
                } else if (userInput.startsWith("list")) {
                    printTaskList(taskList);
                } else if (userInput.startsWith("done ")) {
                    markTaskAsDone(taskList, userInput);
                } else if (userInput.startsWith("bye")) {
                    break;
                } else {
                    printErrorForInvalidCommand(userInput);
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        printOutro();
    }
}
