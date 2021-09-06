/**
 * This is the main class of the chat bot app that helps user remember their different
 * types of task and "parrot" the task back to them when they request.
 *
 * @author YEOWEIHNGWHYELAB
 */

import java.util.Scanner;

public class Duke {
    /* Task Counter */
    public static int numberOfTasks = 0;

    /* This is an array of task & Total task quantity should not exceed 100! */
    public static Task[] tasks = new Task[100];

    /**
     * These are text objects created that can print text file
     * specified into the terminal.
     */
    public static PrintTextFile printHelloText = new PrintTextFile("text-art/HelloText.txt");
    public static PrintTextFile printByeText = new PrintTextFile("text-art/ByeText.txt");
    public static PrintTextFile printParrotText = new PrintTextFile("text-art/ParrotText.txt");
    public static PrintTextFile printDukeyText = new PrintTextFile("text-art/DukeyText.txt");

    /**
     * Prints the current list of task when the "list" command is
     * entered to the terminal.
     */
    public static void printTaskList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print("     ");
            System.out.print((i + 1) + ".");
            tasks[i].printStatus();
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Adds a new todo task with the description of task provided.
     *
     * @param userInputString which contains the "todo" command along with
     *                        the description of the todo task. No time
     *                        details is needed here.
     */
    public static void addToDo(String userInputString) throws DukeException {
        try {
            String taskName = userInputString.substring(5);

            tasks[numberOfTasks] = new ToDo(taskName);
            numberOfTasks += 1;

            tasks[numberOfTasks - 1].printAddingStatus(numberOfTasks - 1);
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a new deadline task with the description of deadline task provided
     * along with the time which is due "by".
     *
     * @param userInputString which contains the "deadline" command along with
     *                        the description of the deadline task and followed
     *                        by a "/" to separate the due time "by".
     */
    public static void addDeadline(String userInputString) throws DukeException {
        try {
            String taskName = userInputString.substring(9).split("/")[0];
            String by = userInputString.substring(9).split("/")[1];

            tasks[numberOfTasks] = new Deadline(taskName, by);
            numberOfTasks += 1;

            tasks[numberOfTasks - 1].printAddingStatus(numberOfTasks - 1);
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a new event task with the description of task provided and time
     * which it is happening "at".
     *
     * @param userInputString which contains the "event" command along with
     *                        the description of the event task followed by
     *                        a "/" to separate the happening time "at".
     */
    public static void addEvent(String userInputString) throws DukeException {
        String taskName = userInputString.substring(7).split("/")[0];
        String by = userInputString.substring(7).split("/")[1];

        tasks[numberOfTasks] = new Event(taskName, by);
        numberOfTasks += 1;

        tasks[numberOfTasks - 1].printAddingStatus(numberOfTasks - 1);
    }

    /**
     * Marks a certain task based on task number as finished.
     *
     * @param userInputString which contains the "done" command along with the
     *                        finished task number.
     */
    public static void finishTask(String userInputString) {
        int taskNumber = Integer.parseInt(userInputString.split(" ")[1]);

        if (taskNumber <= numberOfTasks) {
            tasks[taskNumber - 1].markAsDone();
        } else {
            System.out.println("    Please Enter the Legit Task Number... Or I won't talk to you!");
        }
    }

    /**
     * Causes a delay in execution of code for specified duration
     * before returning control back to the caller of this method.
     *
     * @param ms The amount of millisecond to delay execution.
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Main method of the chat bot app.
     */
    public static void main(String[] args) {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        /**
         * Prints a "Dukey", "Parrot", "Hello" message sequentially
         * when the user first initialize the chat bot.
         */
        printDukeyText.printText();
        wait(500);
        printParrotText.printText();
        wait(500);
        printHelloText.printText();

        /**
         * Main while loop of the main() method. It waits for user command
         * and determines what command the user entered
         */
        while (true) {
            userInputString = userInput.nextLine();
            CommandHandling commandHandle = new CommandHandling(userInputString);
            try {
                if (commandHandle.isBye()) {
                    break;
                } else if (commandHandle.isList()) {
                    printTaskList();
                    continue;
                } else if (commandHandle.isDone()) {
                    finishTask(userInputString);
                    continue;
                } else if (commandHandle.isToDo()) {
                    addToDo(userInputString);
                    continue;
                } else if (commandHandle.isDeadline()) {
                    addDeadline(userInputString);
                    continue;
                } else if (commandHandle.isEvent()) {
                    addEvent(userInputString);
                    continue;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeError) {
                dukeError.printErrorMessage();
            }
        }

        /**
         * Prints a "Bye" message when the user enters "bye" command.
         */
        printByeText.printText();
    }
}
