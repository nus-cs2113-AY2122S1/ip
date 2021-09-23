package duke;

import duke.common.Messages;
import duke.data.exception.EmptyTaskException;
import duke.data.exception.InvalidException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String DASHES = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_COMPLETED_WORD = "done";
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_EXIT_WORD = "bye";
    public static final String COMMAND_DELETE_WORD = "delete";

    public static final int TASK_DATA_COUNT = 2;
    public static final int TASK_DATA_INDEX_DESCRIPTION = 0;
    public static final int TASK_DATA_INDEX_ADDITIONAL_INFO = 1;

    public static final String NUMBER_DONE = "1";


    private static ArrayList<Task> tasks;

//    private Storage storage;
//    private TaskList tasks;
//    private Ui ui;
//
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }
//
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }

    public static void main(String[] args) {
        showWelcomeMessage();
        initTaskList();
        while (true) {
            String userCommand = getInput();
            executeCommand(userCommand);
            showBottomMessage();
        }
    }

    private static void appendFileContentsToArrayList() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] commandInput = s.nextLine().split(" \\| ",4);
            String commandWord = commandInput[0];
            String isCompleteString = commandInput[1];
            String taskDescription = commandInput[2];
            String additionalDescription;
            if (commandWord.equals("T")) {
                additionalDescription = "";
            } else {
                additionalDescription = commandInput[3];
            }
            switch (commandWord) {
            case ("T"):
                tasks.add(new Todo(taskDescription));
                setTaskAsDone(isCompleteString);
                break;
            case("D"):
                tasks.add(new Deadline(taskDescription, additionalDescription));
                setTaskAsDone(isCompleteString);
                break;
            case("E"):
                tasks.add(new Event(taskDescription, additionalDescription));
                setTaskAsDone(isCompleteString);
                break;
            }
        }
    }

    private static void setTaskAsDone(String isCompleteString) {
        if (isCompleteString.equals(NUMBER_DONE)) {
            tasks.get(tasks.size() - 1).setDone();
        }
    }

    private static void writeToFile(String taskInstance, String rawText, String additionalText, boolean isDone) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true);
        String additionalTextWithBorders = (additionalText.equals("") ? "" : " | " + additionalText);
        String taskAsText = taskInstance + " | " + isDoneString(isDone) + " | " + rawText + additionalTextWithBorders + System.lineSeparator();
        fw.write(taskAsText);
        fw.close();
    }

    private static String isDoneString(boolean isDone) {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    private static void showBottomMessage() {
        System.out.println(DASHES);
    }

    private static void executeCommand(String userCommand) {
        try {
            final String[] commandTypeAndParams = splitUserCommand(userCommand);
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case COMMAND_LIST_WORD:
                executeListTasks();
                break;
            case COMMAND_DELETE_WORD:
                checkValidArguments(commandArgs);
                executeDeleteTask(commandArgs);
                break;
            case COMMAND_COMPLETED_WORD:
                checkValidArguments(commandArgs);
                executeCompleteTask(commandArgs);
                break;
            case COMMAND_TODO_WORD:
                checkValidArguments(commandArgs);
                executeTodoTask(commandArgs);
                break;
            case COMMAND_DEADLINE_WORD:
                checkValidArguments(commandArgs);
                executeDeadlineTask(commandArgs);
                break;
            case COMMAND_EVENT_WORD:
                checkValidArguments(commandArgs);
                executeEventTask(commandArgs);
                break;
            case COMMAND_EXIT_WORD:
                executeExitProgram();
                break;
            default:
                throw new InvalidException();
            }
        } catch (InvalidException e) {
            System.out.println(Messages.MESSAGE_INVALID_COMMAND);
        } catch (EmptyTaskException e) {
            System.out.println(Messages.MESSAGE_EMPTY_TASK_DESCRIPTION);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
    }

    private static void executeDeleteTask(String taskIndexString) throws IndexOutOfBoundsException{
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = tasks.get(taskIndex);
        tasks.remove(currentTask);
        OverwriteListToFile();
        showSuccessfulDelete(currentTask);
    }

    private static void showSuccessfulDelete(Task currentTask) {
        System.out.println("Got it. I've removed this task for you: \n "
                + currentTask + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void checkValidArguments(String commandArgs) throws EmptyTaskException {
        if (commandArgs.equals("")) {
            throw new EmptyTaskException();
        }
    }

    private static void executeExitProgram() {
        showToUser(DASHES, Messages.MESSAGE_GOODBYE, DASHES);
        System.exit(0);
    }

    private static void executeEventTask(String rawInput) {
        String[] decodedInput = decodeInput(rawInput);
        String description = getDescription(decodedInput);
        String at = getAdditionalInfo(decodedInput);
        createEventTask(description, at);
        appendEventToFile(description, at);
        showSuccessfulAdd();
    }

    private static void appendEventToFile(String description, String at) {
        try {
            writeToFile("E",description,at,false);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    private static void createEventTask(String description, String at) {
        tasks.add(new Event(description,at));
    }

    private static void showSuccessfulAdd() {
        System.out.println("Got it! I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size()+ " tasks in the list.");
    }

    private static String[] decodeInput(String rawInput) {
        String[] decoded = new String[TASK_DATA_COUNT];
        String[] splitByForwardSlash = rawInput.split("/", 2);
        decoded[TASK_DATA_INDEX_DESCRIPTION] = splitByForwardSlash[0];
        String[] splitBySpace = splitByForwardSlash[1].split(" ", 2);
        decoded[TASK_DATA_INDEX_ADDITIONAL_INFO] = splitBySpace[1];
        return decoded;
    }

    private static String getAdditionalInfo(String[] decodedInput) {
        return decodedInput[1];
    }

    private static String getDescription(String[] decodedInput) {
        return decodedInput[0];
    }

    private static void executeDeadlineTask(String rawInput) {
        String[] decodedInput = decodeInput(rawInput);
        String description = getDescription(decodedInput);
        String by = getAdditionalInfo(decodedInput);
        createDeadlineTask(description, by);
        appendDeadlineToFile(description, by);
        showSuccessfulAdd();

    }

    private static void appendDeadlineToFile(String description, String by) {
        try {
            writeToFile("D",description,by,false);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    private static void createDeadlineTask(String description, String by) {
        tasks.add(new Deadline(description,by));
    }

    private static void executeTodoTask(String todoInput) {
        createTodoTask(todoInput);
        appendTodoToFile(todoInput);
        showSuccessfulAdd();
    }

    private static void appendTodoToFile(String todoInput) {
        try {
            writeToFile("T", todoInput,"", false);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    private static void createTodoTask(String todoInput) {
        tasks.add(new Todo(todoInput));
    }

    private static void executeCompleteTask(String taskIndexString) throws IndexOutOfBoundsException{
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = tasks.get(taskIndex);
        currentTask.setDone();
        OverwriteListToFile();
        showSuccessfulComplete(currentTask);
    }

    private static void OverwriteListToFile() {
        try {
            clearFile();
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    writeToFile("T",task.getDescription(),"",task.isDone());
                } else if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    writeToFile("D",deadlineTask.getDescription(),deadlineTask.getBy(),deadlineTask.isDone());
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    writeToFile("E",eventTask.getDescription(),eventTask.getAt(),eventTask.isDone());
                }
            }
        } catch (IOException e) {
            System.out.println("IO Error!");
        }
    }

    private static void clearFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("IO Error!");
        }
    }

    private static void showSuccessfulComplete(Task currentTask) {
        System.out.println("Nice! You did the following task:"
                + "\n [" + currentTask.getStatusIcon() + "] "
                + currentTask.getDescription());
    }

    private static void executeListTasks() {
        if (tasks.size() == 0) {
            System.out.println("No tasks in the list!");
        } else {
            showAllTasks();
        }
    }

    private static void showAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (Task item : tasks) {
                System.out.println(tasks.indexOf(item)+1 + "." + item);
        }
    }

    private static String[] splitUserCommand(String userCommand) {
        final String[] split = userCommand.trim().split(" ", 2);
        if (split.length >= 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }

    private static String getInput() {
        System.out.print("What would you like me to do? ");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    private static void initTaskList() {
        tasks = new ArrayList<>();
        try {
            appendFileContentsToArrayList();
        } catch (FileNotFoundException e) {
            File f = new File("./data/");
            if (f.mkdir()) {
                System.out.println(Messages.MESSAGE_INIT_NEW_STORAGE_FILE + System.lineSeparator());
            } else {
                System.out.println(Messages.MESSAGE_INIT_FAILED);
            }
        }
    }

    private static void showWelcomeMessage() {
        showToUser(DASHES, LOGO, DASHES, Messages.MESSAGE_WELCOME, DASHES);
    }

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
}
