package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static final int ARRAYLIST_PRINT_OFFSET = 1;

    public static final int DELETE_OFFSET = 1;

    public static final int DONE_OFFSET = 1;

    public static final int DEADLINE_DESCRIPTION_OFFSET = 9;

    public static final int DEADLINE_BY_OFFSET = 4;

    public static final int EVENT_DESCRIPTION_OFFSET = 6;

    public static final int EVENT_AT_OFFSET = 4;

    public static final int TASK_INITIALISE_OFFSET = 1;

    public static final String DOTTED_LINE = "____________________________________________________________";

    public static final String BYE_MESSAGE = DOTTED_LINE + System.lineSeparator()
            + "Goodbye! See you again!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String UNKNOWN_COMMAND = DOTTED_LINE + System.lineSeparator()
            + "Unknown command! Try again, or type helpme for the list of commands." + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DONE_EMPTY = DOTTED_LINE + System.lineSeparator()
            + "You didn't choose a task to mark as done!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DONE_WORD = DOTTED_LINE + System.lineSeparator()
            + "Enter the task NUMBER to be marked as done!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DONE_INVALID_NUMBER = DOTTED_LINE + System.lineSeparator()
            + "Task number is invalid! Enter the correct task number!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DELETE_EMPTY = DOTTED_LINE + System.lineSeparator()
            + "You didn't choose a task to delete!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DELETE_WORD = DOTTED_LINE + System.lineSeparator()
            + "Enter the task NUMBER to be deleted!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DELETE_INVALID_NUMBER = DOTTED_LINE + System.lineSeparator()
            + "Task number is invalid! Enter the correct task number!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String TODO_EMPTY = DOTTED_LINE + System.lineSeparator()
            + "Empty todo description! Enter your todo description!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DEADLINE_EMPTY = DOTTED_LINE + System.lineSeparator()
            + "Empty deadline description! Enter your deadline description!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DEADLINE_MISSING_PARAMETER = DOTTED_LINE + System.lineSeparator()
            + "Oops! You forgot the /by parameter!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String EVENT_EMPTY = DOTTED_LINE + System.lineSeparator()
            + "Empty event description! Enter your deadline description!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String EVENT_MISSING_PARAMETER = DOTTED_LINE + System.lineSeparator()
            + "Oops! You forgot the /at parameter!" + System.lineSeparator()
            + DOTTED_LINE;

    public static final String DIRECTORY_NAME = "data";

    public static final String FILE_PATH = "data/duke.txt";

    public static void printWelcomeMessage() {
        String welcomeMessage = DOTTED_LINE + System.lineSeparator()
                + "Hello! Welcome to Task_Tracker!" + System.lineSeparator()
                + "Type helpme for a list of commands!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(welcomeMessage);

    }

    public static void printHelpMessage() {
        String helpMessage = DOTTED_LINE + System.lineSeparator()
                + "todo <task> - Adds a todo task!" + System.lineSeparator()
                + "deadline <task> /by <due date> - Adds a deadline task!" + System.lineSeparator()
                + "event <task> /at <when> - Adds an event task!" + System.lineSeparator()
                + "list - Lists all tasks tracked!" + System.lineSeparator()
                + "done <task number> - Marks selected task number as done with X ! " + System.lineSeparator()
                + "bye - Exits Task_Tracker!" + System.lineSeparator()
                + "helpme - Get help!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(helpMessage);
    }

    public static void printAddTodoMessage(String todoDescription) {
        String addTodoMessage = DOTTED_LINE + System.lineSeparator()
                + "Great! This todo task has been added!!" + System.lineSeparator()
                + "[T]" + "[ " + "] " + todoDescription + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(addTodoMessage);
    }

    public static void printAddDeadlineMessage(String deadlineDescription, String deadlineBy) {
        String addDeadlineMessage = DOTTED_LINE + System.lineSeparator()
                + "Great! This deadline task has been added!" + System.lineSeparator()
                + "[D]" + "[ " + "] " + deadlineDescription + "(by: " + deadlineBy + ")" + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(addDeadlineMessage);
    }

    public static void printAddEventMessage(String eventDescription, String eventAt) {
        String addEventMessage = DOTTED_LINE + System.lineSeparator()
                + "Great! This event task has been added!" + System.lineSeparator()
                + "[E]" + "[ " + "] " + eventDescription + "(at: " + eventAt + ")" + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(addEventMessage);
    }

    public static void printListOfTasks() {
        System.out.println(DOTTED_LINE + System.lineSeparator() + "Here are the tasks in your list!");

        if (tasks.size() == 0) {
            System.out.println("No tasks being tracked!");
        } else {

            for (Task element : tasks) {
                System.out.println((tasks.indexOf(element)
                        + ARRAYLIST_PRINT_OFFSET)
                        + "." + element.toString());
            }
            System.out.println(DOTTED_LINE);
        }
    }

    public static int filterTaskNumber(String taskThatIsDone) throws DukeMissingParamException, NumberFormatException {
        String[] wordsOfTheTask = taskThatIsDone.split(" ");

        if (wordsOfTheTask.length > 1) { //check if there is input after the command done
            return Integer.parseInt(wordsOfTheTask[1]);
        } else {
            throw new DukeMissingParamException();
        }
    }

    public static void markTaskAsDone(int taskNumberToMark) {
        if ((taskNumberToMark - DONE_OFFSET >= 0) && (tasks.get(taskNumberToMark - DONE_OFFSET) != null)) {
            tasks.get(taskNumberToMark - DONE_OFFSET).setAsDone();

            System.out.println(DOTTED_LINE + System.lineSeparator()
                    + "Great! This task has been marked as done!!" + System.lineSeparator()
                    + "[" + tasks.get(taskNumberToMark - DONE_OFFSET).getType() + "]" + "[" + tasks.get(taskNumberToMark - DONE_OFFSET).getStatusIcon() + "] "
                    + tasks.get(taskNumberToMark - DONE_OFFSET).description + System.lineSeparator()
                    + DOTTED_LINE);
        } else {
            System.out.println(DONE_INVALID_NUMBER);
        }
    }

    public static void addTask(Task t) {
        tasks.add(t);
    }

    public static void deleteTask(int taskNumberToRemove) throws DukeMissingParamException, NumberFormatException, IndexOutOfBoundsException {
        if ((taskNumberToRemove - DELETE_OFFSET >= 0) && (tasks.get(taskNumberToRemove - DELETE_OFFSET) != null)) {
            String byOrAt;
            if (tasks.get(taskNumberToRemove - DELETE_OFFSET).getType().equals("D")) {
                byOrAt = "by: ";
            } else if (tasks.get(taskNumberToRemove - DELETE_OFFSET).getType().equals("E")) {
                byOrAt = "at: ";
            } else {
                byOrAt = "";
            }

            if (byOrAt.equals("")) {
                System.out.println(DOTTED_LINE + System.lineSeparator()
                        + "This task has been removed!" + System.lineSeparator()
                        + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getType() + "]" + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getStatusIcon() + "] "
                        + tasks.get(taskNumberToRemove - DELETE_OFFSET).description + System.lineSeparator()
                        + "You now have " + (tasks.size() - DELETE_OFFSET) + " tasks in the list!" + System.lineSeparator()
                        + DOTTED_LINE);

            } else {
                System.out.println(DOTTED_LINE + System.lineSeparator()
                        + "This task has been removed!" + System.lineSeparator()
                        + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getType() + "]" + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getStatusIcon() + "] "
                        + tasks.get(taskNumberToRemove - DELETE_OFFSET).description + "(" + byOrAt + tasks.get(taskNumberToRemove - DELETE_OFFSET).getWhen() + ")" + System.lineSeparator()
                        + "You now have " + (tasks.size() - DELETE_OFFSET) + " tasks in the list!" + System.lineSeparator()
                        + DOTTED_LINE);
            }
        }
        tasks.remove(tasks.get(taskNumberToRemove - DELETE_OFFSET));
    }

    public static void addTodo(String line) throws DukeMissingDescException {
        if (line.length() == 4 || line.substring(5).isBlank()) { //check that there is text after todo
            throw new DukeMissingDescException();
        }
        String todoDescription = line.substring(5);

        addTask(new Todo(todoDescription));

        printAddTodoMessage(todoDescription);
    }

    public static void addDeadline(String line) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == 8 || line.substring(9).isBlank()) {
            throw new DukeMissingDescException();
        }

        int positionOfBy = line.indexOf("/by");

        if (positionOfBy == -1) { //missing by, throw exception
            throw new DukeMissingParamException();
        }

        int positionOfLastCharacter = line.length();

        String deadlineDescription = line.substring(DEADLINE_DESCRIPTION_OFFSET, positionOfBy); //description of deadline

        String deadlineBy = line.substring(positionOfBy + DEADLINE_BY_OFFSET, positionOfLastCharacter); //date of deadline

        addTask(new Deadline(deadlineDescription, deadlineBy));

        printAddDeadlineMessage(deadlineDescription, deadlineBy);
    }

    public static void addEvent(String line) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == 5 || line.substring(6).isBlank()) {
            throw new DukeMissingDescException();
        }

        int positionOfAt = line.indexOf("/at");

        if (positionOfAt == -1) { //missing at, throw exception
            throw new DukeMissingParamException();
        }

        int positionOfLastCharacter = line.length();

        String eventDescription = line.substring(EVENT_DESCRIPTION_OFFSET, positionOfAt); //description of event

        String eventAt = line.substring(positionOfAt + EVENT_AT_OFFSET, positionOfLastCharacter); //date of event

        addTask(new Event(eventDescription, eventAt));

        printAddEventMessage(eventDescription, eventAt);
    }


    public static void folderInitialise() {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Missing directory! Creating new directory!");
        }
    }


    public static void saveFileInitialise() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner saveFile = new Scanner(f);
        int addCounter = 0;
        while (saveFile.hasNextLine()) {
            String line = saveFile.nextLine();
            String[] processWordsArray = line.split(("[|]"));
            if (line.charAt(0) == 'T') {
                addTask(new Todo(processWordsArray[2].substring(TASK_INITIALISE_OFFSET)));
            } else if (line.charAt(0) == 'D') {
                addTask(new Deadline(processWordsArray[2].substring(TASK_INITIALISE_OFFSET), processWordsArray[3].substring(TASK_INITIALISE_OFFSET)));
            } else if (line.charAt(0) == 'E') {
                addTask(new Event(processWordsArray[2].substring(TASK_INITIALISE_OFFSET), processWordsArray[3].substring(TASK_INITIALISE_OFFSET)));
            }
            if (processWordsArray[1].charAt(1) == '1') {
                tasks.get(addCounter).setAsDone();
            }
            addCounter++;
        }
    }


    public static void writeToSave() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {

            String doneNumber = "0";
            if (tasks.get(taskIndex).isDone) {
                doneNumber = "1";
            }

            String textToAdd;
            if (tasks.get(taskIndex).getType().equals("T")) {
                textToAdd = tasks.get(taskIndex).getType() + " | "
                        + doneNumber + " | "
                        + tasks.get(taskIndex).description + System.lineSeparator();
            } else {
                textToAdd = tasks.get(taskIndex).getType() + " | "
                        + doneNumber + " | "
                        + tasks.get(taskIndex).description + "| " + tasks.get(taskIndex).getWhen() + System.lineSeparator();
            }
            fileWriter.write(textToAdd);
        }
        fileWriter.close();
    }


    public static void processInputs(Scanner in, String line) {
        //keep taking inputs unless user types bye
        while (!line.equals("bye")) {
            if (line.equals("list")) { //user types list, prints the list of tasks
                printListOfTasks();
            } else if (line.equals("helpme")) { //user types help, prints the help message
                printHelpMessage();
            } else if (line.contains("done")) { //user types done, processes the line in the done scenario
                try {
                    int taskNumber = filterTaskNumber(line);
                    markTaskAsDone(taskNumber);
                } catch (DukeMissingParamException e) {
                    System.out.println(DONE_EMPTY);
                } catch (NumberFormatException e) {
                    System.out.println(DONE_WORD);
                }
            } else if (line.contains("delete")) {
                try {
                    int taskNumber = filterTaskNumber(line);
                    deleteTask(taskNumber);
                } catch (DukeMissingParamException e) {
                    System.out.println(DELETE_EMPTY);
                } catch (NumberFormatException e) {
                    System.out.println(DELETE_WORD);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(DELETE_INVALID_NUMBER);
                }
            } else if (line.contains("todo")) { //user types todo, processes the line in the todo scenario
                try {
                    addTodo(line); //adds the todo to the list of tasks & notifies user with message
                } catch (DukeMissingDescException e) {
                    System.out.println(TODO_EMPTY);
                }
            } else if (line.contains("deadline")) { //user types deadline, processes the line in the deadline scenario
                try {
                    addDeadline(line); //adds the deadline to the list of tasks & notifies user with message
                } catch (DukeMissingDescException e) {
                    System.out.println(DEADLINE_EMPTY);
                } catch (DukeMissingParamException e) {
                    System.out.println(DEADLINE_MISSING_PARAMETER);
                }
            } else if (line.contains("event")) { //user types event, processes the line in the event scenario
                try {
                    addEvent(line); //adds the event to the list of tasks & notifies the user with message
                } catch (DukeMissingDescException e) {
                    System.out.println(EVENT_EMPTY);
                } catch (DukeMissingParamException e) {
                    System.out.println(EVENT_MISSING_PARAMETER);
                }
            } else { //no relevant commands, throw exception
                System.out.println(UNKNOWN_COMMAND);
            }
            line = in.nextLine();
        }

    }

    public static void main(String[] args) throws IOException {

        folderInitialise();
        try {
            saveFileInitialise();
        } catch (FileNotFoundException e) {
            File f = new File(FILE_PATH);
            f.createNewFile();
            System.out.println("Missing duke.txt! Creating new file!");
        }

        Scanner in = new Scanner(System.in);

        String line;

        printWelcomeMessage();

        line = in.nextLine();

        processInputs(in, line);

        try {
            writeToSave();
        } catch (IOException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }

        System.out.println(BYE_MESSAGE);
    }
}
