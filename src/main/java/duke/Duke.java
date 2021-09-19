package duke;

/**
 * This is the main class of the chat-bot app that helps user remember their different
 * types of task and "parrot" the task back to them when they request.
 *
 * @author YEOWEIHNGWHYELAB
 */

import duke.commandHandler.DukeCommandHandling;
import duke.delay.Delay_ms;
import duke.exceptionHandler.DukeException;
import duke.printTextFile.PrintTextFile;
import duke.save.SaveTaskListToText;
import duke.taskType.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static int numberOfTasks = 0; // Task Counter
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void run() throws IOException {
        Ui ui = new Ui();
        ui.showWelcome();

        String userInputString;
        Scanner userInput = new Scanner(System.in);
        SaveTaskListToText dukeTaskText = getSaveTaskListToText();

        boolean isExit = false;
        while (!isExit) {
            userInputString = userInput.nextLine();
            DukeCommandHandling commandHandle = new DukeCommandHandling(userInputString);

            try {
                if (commandHandle.isBye()) {
                    break;
                } else if (commandHandle.isList()) {
                    TaskList.printTaskList();
                    continue;
                } else if (commandHandle.isDone()) {
                    TaskList.finishTask(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isDelete()) {
                    TaskList.deleteTask(userInputString, dukeTaskText);
                } else if (commandHandle.isToDo()) {
                    TaskList.addToDo(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isDeadline()) {
                    TaskList.addDeadline(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isEvent()) {
                    TaskList.addEvent(userInputString, dukeTaskText);
                    continue;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeError) {
                dukeError.printErrorMessage();
            }
        }
        userInput.close();
        Ui.printByeText.printText();
    }

    public static SaveTaskListToText getSaveTaskListToText() throws IOException {
        SaveTaskListToText dukeTaskText = new SaveTaskListToText();
        Duke.numberOfTasks = dukeTaskText.loadTask(Duke.tasks);
        return dukeTaskText;
    }

    public static void main(String[] args) throws IOException, DukeException {
        // run();
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        Delay_ms delay = new Delay_ms();
        Ui.printDukeyText.printText();
        delay.wait(500);
        Ui.printParrotText.printText();
        delay.wait(500);
        Ui.printHelloText.printText();

        SaveTaskListToText dukeTaskText = getSaveTaskListToText();


        while (true) {
            userInputString = userInput.nextLine();
            DukeCommandHandling commandHandle = new DukeCommandHandling(userInputString);
            try {
                if (commandHandle.isBye()) {
                    break;
                } else if (commandHandle.isList()) {
                    TaskList.printTaskList();
                    continue;
                } else if (commandHandle.isDone()) {
                    TaskList.finishTask(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isDelete()) {
                    TaskList.deleteTask(userInputString, dukeTaskText);
                } else if (commandHandle.isToDo()) {
                    TaskList.addToDo(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isDeadline()) {
                    TaskList.addDeadline(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isEvent()) {
                    TaskList.addEvent(userInputString, dukeTaskText);
                    continue;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeError) {
                dukeError.printErrorMessage();
            }
        }

        userInput.close();
        Ui.printByeText.printText();

    }
}
