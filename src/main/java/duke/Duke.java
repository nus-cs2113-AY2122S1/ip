package duke;

/**
 * This is the main class of the chat-bot app that helps user remember their different
 * types of task and "parrot" the task back to them when they request.
 *
 * @author YEOWEIHNGWHYELAB
 */

import duke.commandHandler.DukeCommandHandling;
import duke.exceptionHandler.DukeException;
import duke.printTextFile.PrintTextFile;
import duke.save.SaveTaskListToText;
import duke.taskType.Deadline;
import duke.taskType.Event;
import duke.taskType.Task;
import duke.taskType.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    /* Task Counter */
    public static int numberOfTasks = 0;

    /* This is an array of task & Total task quantity should not exceed 100! */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * These are text objects created that can print text file
     * specified into the terminal.
     */
    public static PrintTextFile printHelloText = new PrintTextFile("text-art/HelloText.txt");
    public static PrintTextFile printByeText = new PrintTextFile("text-art/ByeText.txt");
    public static PrintTextFile printParrotText = new PrintTextFile("text-art/ParrotText.txt");
    public static PrintTextFile printDukeyText = new PrintTextFile("text-art/DukeyText.txt");

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Prints the current list of task when the "list" command is
     * entered to the terminal.
     */
    public static void printTaskList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print("     ");
            System.out.print((i + 1) + ".");
            tasks.get(i).printStatus();
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void removeLineFromFile(String file, String lineToRemove) {
        try {

            File inFile = new File(file);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            // Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }

            pw.close();
            br.close();

            // Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteTask(String userInputString) throws DukeException {
        try {
            int taskNumberToDelete = Integer.parseInt(userInputString.split(" ")[1]);
            String textToRemove;

            if (taskNumberToDelete <= numberOfTasks) {
                tasks.get(taskNumberToDelete - 1).deletedSuccessfully(numberOfTasks - 1);

                String typeOfTask = tasks.get(taskNumberToDelete - 1).toString().substring(1,2);
                String taskIsDone = tasks.get(taskNumberToDelete - 1).toString().substring(4,5);
                String rawTaskDescription = tasks.get(taskNumberToDelete - 1).toString().substring(7);

                if (typeOfTask.equals("T") == true) {
                    if (taskIsDone.equals("X")) {
                        textToRemove = "t-/-1-/-" + rawTaskDescription;
                    } else {
                        textToRemove = "t-/-0-/-" + rawTaskDescription;
                    }
                } else if (typeOfTask.equals("E") == true) {
                    String taskDescription = rawTaskDescription.split("at: ")[0];
                    int indexOfDescriptionEnd = taskDescription.length() - 2;
                    taskDescription = taskDescription.substring(0, indexOfDescriptionEnd);

                    String taskAt = rawTaskDescription.split("at: ")[1];
                    int indexOfByEnd = taskAt.length() - 1;
                    taskAt = taskAt.substring(0, indexOfByEnd);

                    if (taskIsDone.equals("X")) {
                        textToRemove = "e-/-1-/-" + taskDescription + " -/-/at" + taskAt;
                    } else {
                        textToRemove = "e-/-0-/-" + taskDescription + " -/-/at" + taskAt;
                    }
                } else {
                    String taskDescription = rawTaskDescription.split("by: ")[0];
                    int indexOfDescriptionEnd = taskDescription.length() - 2;
                    taskDescription = taskDescription.substring(0, indexOfDescriptionEnd);

                    String taskBy = rawTaskDescription.split("by: ")[1];
                    int indexOfByEnd = taskBy.length() - 1;
                    taskBy = taskBy.substring(0, indexOfByEnd);

                    if (taskIsDone.equals("X")) {
                        textToRemove = "d-/-1-/-" + taskDescription + " -/-/by" + taskBy;
                    } else {
                        textToRemove = "d-/-0-/-" + taskDescription + " -/-/by" + taskBy;
                   }
                }

                removeLineFromFile("./data/duke.txt", textToRemove);

                tasks.remove(taskNumberToDelete - 1);
                numberOfTasks -= 1;
            } else {
                throw new DukeException("Please Enter the Legit Task Number to Delete... Or I won't talk to you!");
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("Please Enter the Legit Task Number to Delete... Or I won't talk to you!");
        }

    }

    /**
     * Adds a new todo task with the description of task provided. Format is
     * strictly: todo <description>
     *
     * @param userInputString which contains the "todo" command along with
     *                        the description of the todo task. No time
     *                        details is needed here.
     */
    public static void addToDo(String userInputString, SaveTaskListToText dukeTaskText) throws DukeException {
        try {
            String taskName = userInputString.substring(5);

            if (taskName.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            tasks.add(new ToDo(taskName));
            numberOfTasks += 1;

            tasks.get(numberOfTasks - 1).printAddingStatus(numberOfTasks - 1);
            dukeTaskText.saveToDo(taskName);

        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a new deadline task with the description of deadline task provided
     * along with the time which is due "by". The format of command is quite strict:
     * deadline <description> /by <time>
     *
     * Trail and Leading space are compulsory for /by separator
     *
     * @param userInputString which contains the "deadline" command along with
     *                        the description of the deadline task and followed
     *                        by a "/" to separate the due time "by".
     */
    public static void addDeadline(String userInputString, SaveTaskListToText dukeTaskText) throws DukeException {
        try {
            if (userInputString.indexOf(" /by ") == -1) {
                throw new DukeException("Don't forget to add /by (must have trail and lead whitespace) to separate description and deadline");
            }
            else {
                String taskName = userInputString.substring(9).split("/")[0];
                String by = userInputString.substring(9).split("/")[1];

                if (taskName.equals("") || by.equals("by ")) {
                    throw new DukeException("The description and event time info of event cannot be empty.");
                } else {
                    tasks.add(new Deadline(taskName, by));
                    numberOfTasks += 1;

                    tasks.get(numberOfTasks - 1).printAddingStatus(numberOfTasks - 1);
                }

                dukeTaskText.saveDeadline(taskName, by);
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description and deadline info of deadline cannot be empty!");
        }
    }

    /**
     * Adds a new event task with the description of task provided and time
     * which it is happening "at". The format of command is quite strict:
     * event <description> /at <time>
     *
     * Trail and Leading space are compulsory for /at separator
     *
     * @param userInputString which contains the "event" command along with
     *                        the description of the event task followed by
     *                        a "/" to separate the happening time "at".
     */
    public static void addEvent(String userInputString, SaveTaskListToText dukeTaskText) throws DukeException {
        try {
            if (userInputString.indexOf(" /at ") == -1) {
                throw new DukeException("Don't forget to add /at (must have trail and lead whitespace) to separate description and event time");
            } else {
                String taskName = userInputString.substring(6).split("/")[0];
                String at = userInputString.substring(6).split("/")[1];

                if (taskName.equals("") || at.equals("at ")) {
                    throw new DukeException("The description and event time info of event cannot be empty.");
                } else {
                    tasks.add(new Event(taskName, at));
                    numberOfTasks += 1;

                    tasks.get(numberOfTasks - 1).printAddingStatus(numberOfTasks - 1);
                }

                dukeTaskText.saveEvent(taskName, at);
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description and event time info of event cannot be empty.");
        }
    }

    /**
     * Marks a certain task based on task number as finished.
     *
     * @param userInputString which contains the "done" command along with the
     *                        finished task number.
     */
    public static void finishTask(String userInputString, SaveTaskListToText dukeTaskText) throws DukeException, IOException {
        int taskNumber = Integer.parseInt(userInputString.split(" ")[1]);

        if (taskNumber <= numberOfTasks) {
            tasks.get(taskNumber - 1).markAsDone();
        } else {
            throw new DukeException("Please Enter the Legit Task Number... Or I won't talk to you!");
        }

        dukeTaskText.saveFinishedTask(tasks.get(taskNumber - 1).toString());
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
     * Main method of the chat-bot app.
     */
    public static void main(String[] args) throws IOException, DukeException {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        /**
         * Prints a "Dukey", "Parrot", "Hello" message sequentially
         * when the user first initialize the chat-bot.
         */
        printDukeyText.printText();
        wait(500);
        printParrotText.printText();
        wait(500);
        printHelloText.printText();

        SaveTaskListToText dukeTaskText = new SaveTaskListToText();
        numberOfTasks = dukeTaskText.loadTask(tasks);

        /**
         * Main while loop of the main() method. It waits for user command
         * and determines what command the user entered
         */
        while (true) {
            userInputString = userInput.nextLine();
            DukeCommandHandling commandHandle = new DukeCommandHandling(userInputString);
            try {
                if (commandHandle.isBye()) {
                    break;
                } else if (commandHandle.isList()) {
                    printTaskList();
                    continue;
                } else if (commandHandle.isDone()) {
                    finishTask(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isDelete()) {
                    deleteTask(userInputString);
                } else if (commandHandle.isToDo()) {
                    addToDo(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isDeadline()) {
                    addDeadline(userInputString, dukeTaskText);
                    continue;
                } else if (commandHandle.isEvent()) {
                    addEvent(userInputString, dukeTaskText);
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
