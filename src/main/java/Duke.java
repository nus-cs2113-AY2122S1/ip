import java.util.Scanner;
import java.io.IOException;




public class Duke {

    private static TaskList taskList;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    public static final int BYE = 0;
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int TODO = 4;
    public static final int EVENT = 5;
    public static final int DEADLINE = 6;
    public static final int FIND = 7;
    public static final int NOT_VALID = -1;



    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage();
        taskList = storage.readFileContents("lines.txt");


        Scanner in = new Scanner(System.in);
        ui.welcomeMessage();
        parser = new Parser(in.nextLine());
        int command = parser.command();
        while (command != BYE) {
            switch (command) {
            case LIST:
                ui.showTaskList(taskList);
                break;
            case DONE:
                try {
                    int doneIndex = parser.doneTaskIndex();
                    Task doneTask = taskList.markDone(doneIndex);
                    ui.showDone(doneTask);
                } catch (IllegalDoneException e) {
                    ui.showLoadingError();
                }
                break;
            case DELETE:
                try {
                    int delIndex = parser.deleteTaskIndex();
                    Task delTask = taskList.deleteTask(delIndex);
                    ui.showDeleted(delTask, taskList);
                    ui.showTaskList(taskList);
                } catch (IllegalDoneException e) {
                    ui.showLoadingError();
                }
                break;
            case TODO:
                try {
                    taskList.addTask(parser.getTodo());
                    ui.showRecentTask(taskList);
                } catch (InvalidTask e) {
                    ui.showInvalidTask();
                }
                break;
            case EVENT:
                try {
                    taskList.addTask(parser.getEvent());
                    ui.showRecentTask(taskList);
                } catch (InvalidEventFormat e) {
                    ui.eventFormatError();
                } catch (InvalidTask e) {
                    ui.showInvalidTask();
                }
                break;
            case DEADLINE:
                try {
                    taskList.addTask(parser.getDeadline());
                    ui.showRecentTask(taskList);
                } catch (InvalidDeadlineFormat e) {
                    ui.deadlineFormatError();
                } catch (InvalidTask e) {
                    ui.showInvalidTask();
                }
                break;
            case FIND:
                try {
                    String findTask = parser.getTask();
                    TaskList foundTaskList = taskList.findTasks(findTask);
                    ui.showFoundTasks(foundTaskList);
                } catch (TaskNotFoundException e) {
                    ui.showNoTask();
                }
                break;
            case NOT_VALID:
                ui.showTypingError();
                break;
            }
            parser = new Parser(in.nextLine());
            command = parser.command();
        }

        try {
            storage.writeToFile("lines.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ui.goodbyeMessage();
    }
}
