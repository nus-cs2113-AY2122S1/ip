package duke;

import duke.common.Messages;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import static duke.parser.Parser.parseCommand;
import static duke.ui.TextUi.showToUser;
import static duke.ui.TextUi.showWelcomeMessage;

public class Duke {

    public static final int TASK_DATA_COUNT = 2;
    public static final int TASK_DATA_INDEX_DESCRIPTION = 0;
    public static final int TASK_DATA_INDEX_ADDITIONAL_INFO = 1;

    public static final String NUMBER_DONE = "1";


    public static ArrayList<Task> tasks;

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
            String userCommand = TextUi.getInput();
            parseCommand(userCommand);
            showBottomMessage();
        }
    }

    public static void setTaskAsDone(String isCompleteString) {
        if (isCompleteString.equals(NUMBER_DONE)) {
            tasks.get(tasks.size() - 1).setDone();
        }
    }

    public static String isDoneString(boolean isDone) {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    private static void showBottomMessage() {
        System.out.println(TextUi.DASHES);
    }

    public static void executeDeleteTask(String taskIndexString) throws IndexOutOfBoundsException{
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

    public static void executeExitProgram() {
        showToUser(TextUi.DASHES, Messages.MESSAGE_GOODBYE, TextUi.DASHES);
        System.exit(0);
    }

    public static void executeEventTask(String rawInput) {
        String[] decodedInput = Parser.decodeInput(rawInput);
        String description = Parser.getDescription(decodedInput);
        String at = Parser.getAdditionalInfo(decodedInput);
        createEventTask(description, at);
        Storage.appendEventToFile(description, at);
        showSuccessfulAdd();
    }

    private static void createEventTask(String description, String at) {
        tasks.add(new Event(description,at));
    }

    private static void showSuccessfulAdd() {
        System.out.println("Got it! I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size()+ " tasks in the list.");
    }

    public static void executeDeadlineTask(String rawInput) {
        String[] decodedInput = Parser.decodeInput(rawInput);
        String description = Parser.getDescription(decodedInput);
        String by = Parser.getAdditionalInfo(decodedInput);
        createDeadlineTask(description, by);
        Storage.appendDeadlineToFile(description, by);
        showSuccessfulAdd();

    }

    private static void createDeadlineTask(String description, String by) {
        tasks.add(new Deadline(description,by));
    }

    public static void executeTodoTask(String todoInput) {
        createTodoTask(todoInput);
        Storage.appendTodoToFile(todoInput);
        showSuccessfulAdd();
    }

    private static void createTodoTask(String todoInput) {
        tasks.add(new Todo(todoInput));
    }

    public static void executeCompleteTask(String taskIndexString) throws IndexOutOfBoundsException{
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = tasks.get(taskIndex);
        currentTask.setDone();
        OverwriteListToFile();
        showSuccessfulComplete(currentTask);
    }

    private static void OverwriteListToFile() {
        try {
            Storage.clearFile();
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    Storage.writeToFile("T",task.getDescription(),"",task.isDone());
                } else if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    Storage.writeToFile("D",deadlineTask.getDescription(),deadlineTask.getBy(),deadlineTask.isDone());
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    Storage.writeToFile("E",eventTask.getDescription(),eventTask.getAt(),eventTask.isDone());
                }
            }
        } catch (IOException e) {
            System.out.println("IO Error!");
        }
    }

    private static void showSuccessfulComplete(Task currentTask) {
        System.out.println("Nice! You did the following task:"
                + "\n [" + currentTask.getStatusIcon() + "] "
                + currentTask.getDescription());
    }

    public static void executeListTasks() {
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

    private static void initTaskList() {
        tasks = new ArrayList<>();
        try {
            Storage.appendFileContentsToArrayList();
        } catch (FileNotFoundException e) {
            File f = new File("./data/");
            if (f.mkdir()) {
                System.out.println(Messages.MESSAGE_INIT_NEW_STORAGE_FILE + System.lineSeparator());
            } else {
                System.out.println(Messages.MESSAGE_INIT_FAILED);
            }
        }
    }

}
