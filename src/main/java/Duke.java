import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;




public class Duke {

//    private static ArrayList<Task> taskList = new ArrayList<>();
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

    /**
     *
     * Informs the user what task they will be adding into their task list and the number of tasks
     * in their list.
     * Prints the new task and the new number of tasks in their list.
     *
     * @param toPrint The new task that will be printed
     */


    /**
     * Deletes the task specified by user from the taskList.
     *
     * @param delTask Index of task to be deleted, passed in as String and will be converted to integer.
     * @throws IllegalDoneException Exception thrown when user inputs an incorrect index of the number.
     */
//     TODO :
//    public static void deleteTask(String delTask) throws IllegalDoneException {

    /**
     * Prints all the tasks that the user has in their list.
     */
//    public static void printTaskList() {


    /**
     * Marks the task that is specified by user, by number starting from 1, as done.
     *
     * @param doneTask Task that is to be marked as done.
     * @throws IllegalDoneException If doneIndex >= taskCount, the task that user wants to mark as
     * done does not exist in the list
     */
    // TODO :
//    public static void markDone(String doneTask) throws IllegalDoneException {


    /**
     * Returns the task that is to be added into user's task list.
     * @param t Task that is to be added into user's list.
     * @return Task that is to be added to the user's list.
     * @throws IllegalTaskException Task description does not start with "todo", "deadline" or "event".
     * @throws InvalidDeadlineFormat Deadline description does not contain the correct format of what is
     * to be expected for deadline, does not contain '/by'.
     * @throws InvalidEventFormat Event description does not contain the correct format of what is
     * to be expected for event, does not contain '/at'.
     */
//    public static void typeOfTask(String t) throws IllegalTaskException, InvalidDeadlineFormat, InvalidEventFormat {


    /**
     * Stores taskList to the file specified, tasks will be stored as Todo, Deadline or Event.
     *
     * @param filePath Path to file to be written to
     * @throws IOException Exception is thrown when there is no valid file of the name and path.
     */
//    private static void writeToFile(String filePath) throws IOException {


    /**
     * Reads content of the file and stores the tasks into taskList to be used by the user.
     *
     * @param filePath Path to the file that is read from
     */
    // TODO :
//    private static void readFileContents(String filePath) {
//



    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage();
        taskList = storage.readFileContents("lines.txt");


        Scanner in = new Scanner(System.in);
        ui.welcomeMessage();
//        String t = in.nextLine();
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
                } catch (IllegalTaskException e) {
                    ui.showTypingError();
                }
                break;
            case EVENT:
                try {
                    taskList.addTask(parser.getEvent());
                    ui.showRecentTask(taskList);
                } catch (InvalidEventFormat e) {
                    ui.eventFormatError();
                }
                break;
            case DEADLINE:
                try {
                    taskList.addTask(parser.getDeadline());
                    ui.showRecentTask(taskList);
                } catch (InvalidDeadlineFormat e) {
                    ui.deadlineFormatError();
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
                System.out.println("Not a valid command bby :(");
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
