import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This represents the Duke Chat bot.
 */
public class Duke {

    private static boolean isDukeDone = false;
    private static ArrayList<Task> list = new ArrayList<>();

    private final static String LINES = "    ____________________________________________________________";
    private final static String TAB = "    ";
    private final static String GREETING = TAB + "Hello... I'm Sadge Duke\n    What can I do for you? :(";
    private final static String GOODBYE = TAB + "Please don't go... I'll miss you...";
    private final static String INVALID_GENERAL = TAB + "☹ I'm really sorry... This is an invalid input...";
    private final static String INVALID_DONE = TAB + "I'm sorry... This is an incorrect done input...\n"
            + TAB + "Please use 'list' to see what number can be used...";
    private final static String INVALID_DELETE = TAB + "Why would you delete a task... Your delete statement is an "
            + "incorrect input too...";
    private final static String INVALID_TASK = "Your task is really weird... I don't think I like it...";
    private final static String TODO = "todo";
    private final static String DEADLINE = "deadline";
    private final static String EVENT = "event";
    private final static String FILE_PATH = "./data/tasks.txt";

    private final static int DESCRIPTION = 0;
    private final static int DATETIME = 1;
    private final static int TASK_TYPE_INDEX = 0;
    private final static int IS_DONE_INDEX = 1;
    private final static int TASK_INDEX = 2;
    private final static int BY_AT_INDEX = 3;


    /**
     * Main function that is called upon program execution.
     *
     * @param args System Arguments added to program.
     */
    public static void main(String[] args) {
        printWelcomeBanner();
        readSavedTasks();

        Scanner in = new Scanner(System.in);
        while (!isDukeDone) {
            System.out.println(LINES);
            String input = in.nextLine();
            try {
                parseInput(input);
            } catch (InvalidInputException e) {
                System.out.println(INVALID_GENERAL);
            }
            System.out.println(LINES);
        }
    }

    /**
     * This function parses the input given by user and prints the appropriate response.
     *
     * @param input input given by the user.
     */
    private static void parseInput(String input) throws InvalidInputException {
        if (input.equalsIgnoreCase("Bye")) {
            System.out.println(GOODBYE);
            setDukeDone();
        } else if (input.equalsIgnoreCase("List")) {
            printList();
        } else if (isValidDoneInput(input)) {
            handleDoneInput(input);
        } else if (isValidTodoInput(input)) {
            addToList(input, TODO);
        } else if (isValidDeadlineInput(input)) {
            addToList(input, DEADLINE);
        } else if (isValidEventInput(input)) {
            addToList(input, EVENT);
        } else if (isValidDeleteInput(input)) {
            handleDeleteInput(input);
        } else {
            throw new InvalidInputException();
        }
    }


    /**
     * This function adds the input description into list accordingly, based on its task type.
     *
     * @param input    input given by the user.
     * @param taskType task type identified beforehand (DEADLINE/EVENT/TODO).
     */
    private static void addToList(String input, String taskType) {
        String[] parameters = new String[2];
        //getParameters(parameters, input, taskType);
        try {
            switch (taskType) {
            case TODO:
                getParameters(parameters, input, TODO);
                Todo todo = new Todo(parameters[DESCRIPTION]);
                list.add(todo);
                saveTaskInFile(todo);
                break;
            case DEADLINE:
                getParameters(parameters, input, DEADLINE);
                Deadline deadline = new Deadline(parameters[DESCRIPTION], parameters[DATETIME]);
                list.add(deadline);
                saveTaskInFile(deadline);
                break;
            case EVENT:
                getParameters(parameters, input, EVENT);
                Event event = new Event(parameters[DESCRIPTION], parameters[DATETIME]);
                list.add(event);
                saveTaskInFile(event);
                break;
            }
            printAddedMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(INVALID_TASK);
        } catch (InvalidTaskException e) {
            System.out.println(INVALID_TASK);
        }
    }

    /**
     * This function prints the message when task is added to list.
     */
    private static void printAddedMessage() {
        System.out.println(TAB + "Okay... I guess I'll add this task... ");
        System.out.println(TAB + TAB + list.get(list.size() - 1));
        System.out.println(TAB + String.format("Now you have %d tasks in the list... ☹", list.size()));
    }

    /**
     * This function handles the done input by marking task as done.
     *
     * @param input input given by the user.
     */
    private static void handleDoneInput(String input) {
        int index = getIndex(input);
        try {
            list.get(index).markAsDone();
            System.out.print(TAB + "Nice... I guess I will mark this task as done...:"
                    + System.lineSeparator() + TAB + TAB);
            System.out.println(list.get(index));
            refreshFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INVALID_DONE);
        }
    }

    /**
     * This function handles the delete input by deleting it.
     *
     * @param input input given by the user.
     */
    private static void handleDeleteInput(String input) {
        int index = getIndex(input);
        try {
            Task temp = list.get(index);
            list.remove(index);
            System.out.print(TAB + "Are you sure about this? I'll just delete this anyway..."
                    + System.lineSeparator() + TAB + TAB);
            System.out.println(temp);
            System.out.println(TAB + String.format("Now you have %d tasks in the list... ☹", list.size()));
            refreshFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INVALID_DELETE);
        }
    }

    /**
     * This function prints the individual elements in list.
     */
    private static void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.format(TAB + "% 3d.", i + 1);
            System.out.println(list.get(i));
        }
    }

    /**
     * This function sets isDukeDone to true, stopping while loop and signifying the end of program.
     */
    private static void setDukeDone() {
        isDukeDone = true;
    }

    /**
     * This function check if done statement and index in done statement is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the done input.
     */
    private static boolean isValidDoneInput(String input) {
        return Pattern.matches("^done \\d+$", input.toLowerCase());
    }

    /**
     * This function check if done statement and index in delete statement is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the delete input.
     */
    private static boolean isValidDeleteInput(String input) {
        return Pattern.matches("^delete \\d+$", input.toLowerCase());
    }

    /**
     * This function retrieves the index given in done/delete statement.
     *
     * @param input input given by the user.
     * @return returns the index given in done/delete statement
     */
    private static int getIndex(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * This function uses regex to check if to do statement is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the to do statement.
     */
    private static boolean isValidTodoInput(String input) {
        return Pattern.matches("todo [:a-z0-9\\s]+", input.toLowerCase());
    }

    /**
     * This function uses regex to check if deadline statement is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the deadline statement.
     */
    private static boolean isValidDeadlineInput(String input) {
        return Pattern.matches("deadline [a-z0-9\\s]+\\b /by .+", input.toLowerCase());
    }

    /**
     * This function uses regex to check if event statement is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the event statement.
     */
    private static boolean isValidEventInput(String input) {
        return Pattern.matches("event [a-z0-9\\s]+\\b /at .+", input.toLowerCase());
    }

    /**
     * This function gets the parameters for the Task subclasses by slicing input.
     *
     * @param parameters Array of string of fixed size 2 to store parameters for Task subclasses.
     * @param input      User's input into command line.
     * @param taskType   String that is pre-identified (DEADLINE/EVENT/TODO)
     */
    private static void getParameters(String[] parameters, String input, String taskType) throws InvalidTaskException {
        switch (taskType) {
        case TODO:
            String[] todoParts = input.split("(?i)todo ");
            parameters[DESCRIPTION] = todoParts[1];
            break;
        case DEADLINE:
            String[] initDeadlineParts = input.split("(?i)deadline ");
            String[] deadlineParts = initDeadlineParts[1].split(" /by ");
            if (deadlineParts.length != 2) {
                throw new InvalidTaskException();
            }
            parameters[DESCRIPTION] = deadlineParts[0];
            parameters[DATETIME] = deadlineParts[1];
            break;
        case EVENT:
            String[] initEventParts = input.split("(?i)event ");
            String[] eventParts = initEventParts[1].split(" /at ");
            if (eventParts.length != 2) {
                throw new InvalidTaskException();
            }
            parameters[DESCRIPTION] = eventParts[0];
            parameters[DATETIME] = eventParts[1];
            break;
        }
        if (Objects.equals(parameters[DESCRIPTION], "")) {
            throw new InvalidTaskException();
        }
    }

    /**
     * This function prints the welcome banner.
     */
    private static void printWelcomeBanner() {
        /**
         * ASCII art source: https://www.twitchquotes.com/copypastas/2059
         */
        String sadge = "   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠋⠉⣉⣉⠙⠿⠋⣠⢴⣊⣙⢿⣿⣿\n"
                + "   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠋⠁⠀⢀⠔⡩⠔⠒⠛⠧⣾⠊⢁⣀⣀⣀⡙⣿\n"
                + "   ⣿⣿⣿⣿⣿⣿⣿⠟⠛⠁⠀⠀⠀⠀⠀⡡⠊⠀⠀⣀⣠⣤⣌⣾⣿⠏⠀⡈⢿⡜\n"
                + "   ⣿⣿⣿⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠡⣤⣶⠏⢁⠈⢻⡏⠙⠛⠀⣀⣁⣤⢢\n"
                + "   ⣿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣄⡀⠣⣌⡙⠀⣘⡁⠜⠈⠑⢮⡭⠴⠚⠉⠀\n"
                + "   ⠁⠀⢀⠔⠁⣀⣤⣤⣤⣤⣤⣄⣀⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠁⠀⢀⣠⢠\n"
                + "   ⡀⠀⢸⠀⢼⣿⣿⣶⣭⣭⣭⣟⣛⣛⡿⠷⠶⠶⢶⣶⣤⣤⣤⣶⣶⣾⡿⠿⣫⣾\n"
                + "   ⠇⠀⠀⠀⠈⠉⠉⠉⠉⠉⠙⠛⠛⠻⠿⠿⠿⠷⣶⣶⣶⣶⣶⣶⣶⣶⡾⢗⣿⣿\n"
                + "   ⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣿⣶⣾⣿⣿⣿\n"
                + "   ⣿⣿⣿⣷⣶⣤⣄⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣝⡻⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡹⣿⣿⣿⣿⣿⣿\n";

        /**
         * ASCII art source: https://fsymbols.com/text-art/
         */
        String text = "█▀ ▄▀█ █▀▄ █▀▀ █▀▀   █▀▄ █░█ █▄▀ █▀▀\n"
                + "▄█ █▀█ █▄▀ █▄█ ██▄   █▄▀ █▄█ █░█ ██▄";
        System.out.println("Hello from\n" + sadge + text);
        System.out.println(LINES);
        System.out.println(GREETING);
        System.out.println(LINES);
    }

    /**
     * This function adds saved tasks to list.
     */
    private static void readSavedTasks() {
        File dataFile = new File(FILE_PATH);
        try {
            Scanner lineScanner = new Scanner(dataFile);
            while (lineScanner.hasNext()) {
                AddLineTask(lineScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            createFile();
        }

    }

    private static void AddLineTask(String line) {
        String[] lineContents = line.split(" \\| ");
        switch (lineContents[TASK_TYPE_INDEX]) {
        case "T":
            Todo todo = new Todo(lineContents[TASK_INDEX]);
            list.add(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(lineContents[TASK_INDEX], lineContents[BY_AT_INDEX]);
            list.add(deadline);
            break;
        case "E":
            Event event = new Event(lineContents[TASK_INDEX], lineContents[BY_AT_INDEX]);
            list.add(event);
            break;
        }
        if (lineContents[IS_DONE_INDEX].equals("1")) {
            list.get(list.size() - 1).markAsDone();
        }
    }

    private static void refreshFile() {
        try {
            FileWriter file = new FileWriter(FILE_PATH);
            for (Task task : list) {
                file.write(task.toFile());
            }
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to write to file...");
        }
    }

    private static void createFile() {
        try {
            File newFile = new File(FILE_PATH);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println("Unable to create file...");
        }
    }

    private static void saveTaskInFile(Task task) {
        try {
            FileWriter file = new FileWriter(FILE_PATH, true);
            file.write(task.toFile());
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to write to file...");
        }
    }
}


