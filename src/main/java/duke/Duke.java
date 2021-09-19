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

    private Ui ui;

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    /*
    public void run() {
        public void run() {
            ui.showWelcome();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String fullCommand = ui.readCommand();
                    ui.showLine(); // show the divider line ("_______")
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.showLine();
                }
            }
        }

    }
    */

    public static void main(String[] args) throws IOException, DukeException {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        Delay_ms delay = new Delay_ms();
        Ui.printDukeyText.printText();
        delay.wait(500);
        Ui.printParrotText.printText();
        delay.wait(500);
        Ui.printHelloText.printText();

        SaveTaskListToText dukeTaskText = new SaveTaskListToText();
        Duke.numberOfTasks = dukeTaskText.loadTask(Duke.tasks);

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
