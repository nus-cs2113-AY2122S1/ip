import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handling the user's command inputs.
 */
public class Parser {
    protected Ui ui = new Ui();
    protected TaskList taskList = new TaskList();
    protected String LINE_DIVIDER = "____________________________________________________________";
    protected String EXIT_COMMAND = "exit";
    protected String LIST_COMMAND = "list";
    protected String HELP_COMMAND = "@help";
    protected String DONE_COMMAND = "done";
    protected String DELETE_COMMAND = "delete";
    protected String TODO_COMMAND = "todo";
    protected String DEADLINE_COMMAND = "deadline";
    protected String EVENT_COMMAND = "event";

    /**
     * Filters the task number for use when marking tasks as done or deleting tasks.
     * The task number is expected to be an integer.
     *
     * @param userInput the string of input to be filtered.
     * @return the task number to marked as done/deleted.
     * @throws DukeMissingParamException when the task number has not been input.
     * @throws NumberFormatException when a non-integer/string has been input where expected.
     */
    public int filterTaskNum(String userInput) throws DukeMissingParamException, NumberFormatException {
        String[] words = userInput.split(" ");

        // check to see if task number has not been input
        if (words.length > 1) {
            return Integer.parseInt(words[1]);
        }
        else {
            throw new DukeMissingParamException();
        }
    }

    /**
     * Processes the commands input by the user and the errors caused by incorrect input.
     * Command List: EXIT, LIST, HELP, DONE, DELETE, TODO, DEADLINE, EVENT.
     *
     * @param in the system scanner, required to move to next line.
     * @param line the input of the user.
     * @param tasks the ArrayList of Tasks to operate the commands on.
     */
    public void parseInputs(Scanner in, String line, ArrayList<Task> tasks) {
        // while input is not "exit", keep taking inputs.
        while (!line.equals(EXIT_COMMAND)) {
            if (line.equals(LIST_COMMAND)) {
                ui.printList(tasks);
            } else if (line.equals(HELP_COMMAND)) {
                ui.printHelp();
            } else if (line.contains(DONE_COMMAND)) {
                try {
                    int taskNum = filterTaskNum(line);
                    taskList.markTaskDone(taskNum, tasks);
                }
                catch (DukeMissingParamException e) {
                    ui.printDoneEmptyError();
                }
                catch (NumberFormatException e) {
                    ui.printDoneWordError();
                }
                catch (IndexOutOfBoundsException e) {
                    ui.printDoneInvalidNumError();
                }
            } else if (line.contains(DELETE_COMMAND)) {
                try {
                    int taskNum = filterTaskNum(line);
                    taskList.deleteTask(taskNum, tasks);
                }
                catch (DukeMissingParamException e) {
                    ui.printDeleteEmptyError();
                }
                catch (NumberFormatException e) {
                    ui.printDeleteWordError();
                }
                catch (IndexOutOfBoundsException e) {
                    ui.printDeleteInvalidNumError();
                }
            } else if (line.contains(TODO_COMMAND)) {
                try {
                    // adds todo and prints success message
                    taskList.addTodo(line, tasks);
                }
                catch (DukeMissingDescException e) {
                    ui.printTodoEmptyError();
                }
            } else if (line.contains(DEADLINE_COMMAND)) {
                try {
                    // adds deadline and prints success message
                    taskList.addDeadline(line, tasks);
                }
                catch (DukeMissingDescException e) {
                    ui.printDeadlineEmptyError();
                }
                catch (DukeMissingParamException e) {
                    ui.printDeadlineMissingParamError();
                }
            } else if (line.contains(EVENT_COMMAND)) {
                try {
                    // adds event and prints success message
                    taskList.addEvent(line, tasks);
                }
                catch (DukeMissingDescException e) {
                    ui.printEventEmptyError();
                }
                catch (DukeMissingParamException e) {
                    ui.printEventMissingParamError();
                }
            } else {
                // throw error when no commands are found in input
                ui.printUnknownCommand();
            }
            line = in.nextLine();
        }
    }
}
