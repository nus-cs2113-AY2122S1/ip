package duke;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.*;

public class Duke {
    private static final String LOGO =
            " _______  __   __  ______   _______  _______  _______  __   __  _______\n" +
                    "|       ||  | |  ||      | |       ||       ||   _   ||  |_|  ||   _   |\n" +
                    "|    ___||  | |  ||  _    ||    ___||_     _||  |_|  ||       ||  |_|  |\n" +
                    "|   | __ |  |_|  || | |   ||   |___   |   |  |       ||       ||       |\n" +
                    "|   ||  ||       || |_|   ||    ___|  |   |  |       ||       ||       |\n" +
                    "|   |_| ||       ||       ||   |___   |   |  |   _   || ||_|| ||   _   |\n" +
                    "|_______||_______||______| |_______|  |___|  |__| |__||_|   |_||__| |__|\n";
    private static final String SEPARATOR = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\n";
    private static final int MAX_TASK = 100;
    private static Task[] userInputs = new Task[MAX_TASK];
    private static int userInputCount = 0;

    /**
     * Initiate the program and print the various messages
     */
    public static void initiateDuke() {
        System.out.println(SEPARATOR + "Hi... from GUDETAMA... so sleepy\n" + LOGO);
        System.out.println("Give me five more minutes..... What can I do for you?\n" + SEPARATOR);

        try {
            loadSave();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + System.lineSeparator() + "Empty file created");
            System.out.println(SEPARATOR);
        }
    }

    public static void loadSave() throws FileNotFoundException {
        final String LOAD_DELIMITER = "--";
        final int TASK_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int BY_AT_INDEX = 3;
        final String TODO_CODE = "T";
        final String DEADLINE_CODE = "D";
        final String EVENT_CODE = "E";
        String line;
        File dir = new File("data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            line = s.nextLine();
            String[] lineData = line.split(LOAD_DELIMITER);

            if (lineData[TASK_INDEX].equals(TODO_CODE)) {
                userInputs[userInputCount] = new Todo(lineData[DESCRIPTION_INDEX]);
            } else if (lineData[TASK_INDEX].equals(DEADLINE_CODE)) {
                userInputs[userInputCount] = new Deadline(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]);
            } else if (lineData[TASK_INDEX].equals(EVENT_CODE)) {
                userInputs[userInputCount] = new Event(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]);
            }

            if (lineData[DONE_INDEX].equals("1")) {
                userInputs[userInputCount].markAsDone();
            }
            userInputCount++;
        }
    }

    /**
     * Prompt for user inputs
     */
    public static void promptInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            processInput(line);
            line = in.nextLine();
        }

        try {
            printEnd();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Process user input
     * @param input the user input
     */
    public static void processInput(String input) {
        input = input.trim();

        try {
            if (input.startsWith("list")) {
                listTasks(input);
            } else if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("todo")) {
                addTodo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            } else {
                handleInvalid();
            }
        } catch (DukeException e) {
            System.out.println(e);
            System.out.println(SEPARATOR);
        }
    }

    /**
     * Prints the ending messages before ending the program
     */
    public static void printEnd() throws IOException{
        final String DELIMITER = "--";
        int doneValue;
        FileWriter beginWrite = new FileWriter("data/duke.txt");

        beginWrite.write("");
        beginWrite.close();

        FileWriter fw = new FileWriter("data/duke.txt", true);

        for (int i = 0; i < userInputCount; i++) {
            if (userInputs[i].getCode().equals("T")) {
                String lineToAppend = userInputs[i].getCode() + DELIMITER + userInputs[i].getDoneValue() +
                        DELIMITER + userInputs[i].getDescription();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (userInputs[i].getCode().equals("D")) {
                String lineToAppend = userInputs[i].getCode() + DELIMITER + userInputs[i].getDoneValue() +
                        DELIMITER + userInputs[i].getDescription() + DELIMITER + userInputs[i].getBy();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (userInputs[i].getCode().equals("E")) {
                String lineToAppend = userInputs[i].getCode() + DELIMITER + userInputs[i].getDoneValue() +
                        DELIMITER + userInputs[i].getDescription() + DELIMITER + userInputs[i].getAt();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            }
        }
        fw.close();

        System.out.println(SEPARATOR + "\nBye. I'm going back to sleep... zzz");
    }

    /**
     * List out the tasks in the array userInputs with numbering
     * @param line the user input
     * @throws DukeException command list is invalid
     */
    public static void listTasks(String line) throws DukeException {
        if (!line.equals("list")) {
            throw new DukeException("command list does not take additional parameters");
        }

        System.out.println(SEPARATOR + "\n\tTasks to do... so lazy:");

        for (int i = 0; i < userInputCount; i++) {
            System.out.println("\t" + (i + 1) + "." + userInputs[i]);
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Marks a particular task in userInputs as done
     * @param line the user input
     * @throws DukeException command done has invalid parameters
     */
    public static void markDone(String line) throws DukeException {
        String[] taskInputs = line.split(" ");

        if (taskInputs.length != 2) {
            throw new DukeException("incorrect number of parameters for command done");
        }

        final int INDEX_DONE = 1;
        int taskIndexDone = Integer.parseInt(taskInputs[INDEX_DONE]) - 1;

        userInputs[taskIndexDone].markAsDone();

        System.out.println(
                SEPARATOR + "\n\tfinished this task... I need a break:\n\t\t" +
                userInputs[taskIndexDone]);
        System.out.println(SEPARATOR);
    }

    /**
     * Adds a task todo into userInputs and increase the counter
     * @param line the user input
     * @throws DukeException when description is missing
     */
    public static void addTodo(String line) throws DukeException {
        final int START_INDEX = 5;
        String[] todoInputs = line.split(" ");

        if (todoInputs.length == 1) {
            throw new DukeException("command todo description missing");
        }

        userInputs[userInputCount] = new Todo(line.substring(START_INDEX));

        System.out.println(SEPARATOR + "\n\tadded: " + userInputs[userInputCount] + "\n" + SEPARATOR);
        userInputCount++;
    }

    /**
     * Adds a task with deadline into userInputs and increase the counter
     * @param line the user input
     * @throws DukeException when deadline is missing
     */
    public static void addDeadline(String line) throws DukeException {
        if (!line.contains("/by")) {
            throw new DukeException("wrong input format for command deadline");
        }

        final int DESCRIPTION_INDEX = 0;
        final int BY_INDEX = 1;
        final int START_INDEX = 9;

        String[] deadlineInputs = line.substring(START_INDEX).split("/by");
        userInputs[userInputCount] = new Deadline(deadlineInputs[DESCRIPTION_INDEX].trim(),
                deadlineInputs[BY_INDEX].trim());

        System.out.println(SEPARATOR + "\n\tadded: " + userInputs[userInputCount] + "\n" + SEPARATOR);
        userInputCount++;
    }

    /**
     * dds an event with a specific start and end time into userInputs and increase the counter
     * @param line the user input
     * @throws DukeException when start and end time is missing
     */
    public static void addEvent(String line) throws DukeException {
        if (!line.contains("/at")) {
            throw new DukeException("wrong input format for command event");
        }

        final int DESCRIPTION_INDEX = 0;
        final int AT_INDEX = 1;
        final int START_INDEX = 6;

        String[] eventInputs = line.substring(START_INDEX).split("/at");
        userInputs[userInputCount] = new Event(eventInputs[DESCRIPTION_INDEX].trim(),
                eventInputs[AT_INDEX].trim());

        System.out.println(SEPARATOR + "\n\tadded: " + userInputs[userInputCount] + "\n" + SEPARATOR);
        userInputCount++;
    }

    /**
     * handle invalid user input
     * @throws DukeException when input command does not exist
     */
    public static void handleInvalid() throws DukeException {
        throw new DukeException("invalid command");
    }

    public static void main(String[] args) {
        initiateDuke();
        promptInput();
    }
}
