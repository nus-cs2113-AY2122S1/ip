package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ui {

    public static final int ARRAYLIST_PRINT_OFFSET = 1;

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

    protected String DEADLINE_EVENT_DATEPARAM = DOTTED_LINE + System.lineSeparator()
            + "Oops! Please enter a valid date! YYYY-MM-DD (e.g. 2021-12-31)" + System.lineSeparator()
            + DOTTED_LINE;

    protected String FIND_MISSING_PARAMETER = DOTTED_LINE + System.lineSeparator()
            + "Oops! Please include search keyword!" + System.lineSeparator()
            + DOTTED_LINE;
    protected String FIND_MULTIPLE_PARAMETER = DOTTED_LINE + System.lineSeparator()
            + "Oops! Only one search keyword is allowed!" + System.lineSeparator()
            + DOTTED_LINE;


    public void printWelcomeMessage() {
        String welcomeMessage = DOTTED_LINE + System.lineSeparator()
                + "Hello! Welcome to Task_Tracker!" + System.lineSeparator()
                + "Type helpme for a list of commands!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(welcomeMessage);

    }

    public void printHelpMessage() {
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

    public void printAddTodoMessage(String todoDescription, ArrayList<Task> tasks) {
        String addTodoMessage = DOTTED_LINE + System.lineSeparator()
                + "Great! This todo task has been added!!" + System.lineSeparator()
                + "[T]" + "[ " + "] " + todoDescription + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(addTodoMessage);
    }

    public void printAddDeadlineMessage(String deadlineDescription, LocalDate deadlineBy, ArrayList<Task> tasks) {
        String addDeadlineMessage = DOTTED_LINE + System.lineSeparator()
                + "Great! This deadline task has been added!" + System.lineSeparator()
                + "[D]" + "[ " + "] " + deadlineDescription + "(by: " + deadlineBy + ")" + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(addDeadlineMessage);
    }

    public void printAddEventMessage(String eventDescription, LocalDate eventAt, ArrayList<Task> tasks) {
        String addEventMessage = DOTTED_LINE + System.lineSeparator()
                + "Great! This event task has been added!" + System.lineSeparator()
                + "[E]" + "[ " + "] " + eventDescription + "(at: " + eventAt + ")" + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE;
        System.out.println(addEventMessage);
    }

    public void printListOfTasks(ArrayList<Task> tasks) {
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

    public void printDottedLine() {
        System.out.println(DOTTED_LINE);
    }

    public void printBye() {
        System.out.println(BYE_MESSAGE);
    }

    public void printUnknownCommandError() {
        System.out.println(UNKNOWN_COMMAND);
    }

    public void printDoneEmptyError() {
        System.out.println(DONE_EMPTY);
    }

    public void printDoneWordError() {
        System.out.println(DONE_WORD);
    }

    public void printDoneInvalidNumberError() {
        System.out.println(DONE_INVALID_NUMBER);
    }

    public void printDeleteEmptyError() {
        System.out.println(DELETE_EMPTY);
    }

    public void printDeleteWordError() {
        System.out.println(DELETE_WORD);
    }

    public void printDeleteInvalidNumError() {
        System.out.println(DELETE_INVALID_NUMBER);
    }

    public void printTodoEmptyError() {
        System.out.println(TODO_EMPTY);
    }

    public void printDeadlineEmptyError() {
        System.out.println(DEADLINE_EMPTY);
    }

    public void printDeadlineMissingParameterError() {
        System.out.println(DEADLINE_MISSING_PARAMETER);
    }

    public void printDeadlineEventDateParameterError() {
        System.out.println(DEADLINE_EVENT_DATEPARAM);
    }

    public void printEventEmptyError() {
        System.out.println(EVENT_EMPTY);
    }

    public void printEventMissingParameterError() {
        System.out.println(EVENT_MISSING_PARAMETER);
    }

    public void printTaskAsMarked(int taskNumberToMark, ArrayList<Task> tasks, int DONE_OFFSET) {
        System.out.println(DOTTED_LINE + System.lineSeparator()
                + "Great! This task has been marked as done!!" + System.lineSeparator()
                + "[" + tasks.get(taskNumberToMark - DONE_OFFSET).getType() + "]" + "[" + tasks.get(taskNumberToMark - DONE_OFFSET).getStatusIcon() + "] "
                + tasks.get(taskNumberToMark - DONE_OFFSET).description + System.lineSeparator()
                + DOTTED_LINE);
    }

    public void printWithoutTaskWordString(int taskNumberToRemove, int DELETE_OFFSET, ArrayList<Task> tasks) {
        System.out.println(DOTTED_LINE + System.lineSeparator()
                + "This task has been removed!" + System.lineSeparator()
                + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getType() + "]" + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getStatusIcon() + "] "
                + tasks.get(taskNumberToRemove - DELETE_OFFSET).description + System.lineSeparator()
                + "You now have " + (tasks.size() - DELETE_OFFSET) + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE);
    }

    public void printWithTaskWordString(int taskNumberToRemove, int DELETE_OFFSET, String taskWordString, ArrayList<Task> tasks) {
        System.out.println(DOTTED_LINE + System.lineSeparator()
                + "This task has been removed!" + System.lineSeparator()
                + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getType() + "]" + "[" + tasks.get(taskNumberToRemove - DELETE_OFFSET).getStatusIcon() + "] "
                + tasks.get(taskNumberToRemove - DELETE_OFFSET).description + "(" + taskWordString + tasks.get(taskNumberToRemove - DELETE_OFFSET).getWhen() + ")" + System.lineSeparator()
                + "You now have " + (tasks.size() - DELETE_OFFSET) + " tasks in the list!" + System.lineSeparator()
                + DOTTED_LINE);
    }

    public void printTasksThatMatch(ArrayList<Task> matchedKeys, ArrayList<Task> tasks)  {
        System.out.println(DOTTED_LINE + System.lineSeparator()
                + "Here are the tasks in your list!");
        if (tasks.size() == 0) {
            System.out.println("Oops! No tasks recorded!");
        } else if (matchedKeys.size() == 0) {
            System.out.println("Oops! Keyword not found!");
        } else {
            int taskCounter = 0;
            for (Task element : matchedKeys) {
                System.out.println(taskCounter + ARRAYLIST_PRINT_OFFSET + ". " + element.toString());
                taskCounter++;
            }
        }
        System.out.println(DOTTED_LINE);
    }

    public void printFindMissingParameterError() {
        System.out.println(FIND_MISSING_PARAMETER);
    }

    public void printFindMultipleParameterError() {
        System.out.println(FIND_MULTIPLE_PARAMETER);
    }

}
