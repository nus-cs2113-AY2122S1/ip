package herrekt.command;

import herrekt.exceptions.*;
import herrekt.taskmanager.Task;
import herrekt.taskmanager.TaskList;

public class Command {
    private final CommandUi ui;
    private final Parser parser;
    private final static String[] commands = {"todo", "event", "deadline", "list", "done", "find", "delete", "bye", "help"};

    public Command() {
        this.ui = new CommandUi();
        this.parser = new Parser();
    }

    /**
     * Check if the input is valid to be processed.
     *
     * @param phrase The user's input.
     * @throws InvalidTaskException If the input is recognized as a Task but is of an invalid format.
     * @throws InvalidInputException If the input is invalid.
     */
    public void isInputValid(String phrase) throws InvalidInputException, InvalidTaskException {
        boolean isValid = false;
        for (String command : commands) {
            if (phrase.startsWith(command)) {
                isValid = true;
                break;
            }
        }
        if (isValid) {
            if (phrase.startsWith("todo") || phrase.startsWith("deadline")|| phrase.startsWith("event")) {
                isValid = isValidTaskCommand(phrase);
            }
        }
        if (!isValid) {
            throw new InvalidInputException("ERROR! Please follow format in README.md" + "\n"
                    + "Your input: " + phrase);
        }
    }

    private boolean isValidTaskCommand(String phrase) throws InvalidTaskException {
        if (phrase.startsWith("todo")) {
            return isValidTodo(phrase);
        } else if (phrase.startsWith("deadline")) {
            return isValidDeadline(phrase);
        } else if (phrase.startsWith("event")) {
            return isValidEvent(phrase);
        } else {
            return false;
        }
    }

    private boolean isValidTodo(String phrase) throws InvalidTodoException {
        String[] splitPhrase = phrase.split(" ");
        boolean validInput = splitPhrase.length > 1;
        if (!validInput) {
            throw new InvalidTodoException("ERROR! Please add a description after 'todo'!" + "\n"
            + "Your input: " + phrase);
        } else {
            return true;
        }
    }

    private boolean isValidDeadline(String phrase) throws InvalidDeadlineException {
        String[] splitPhrase = phrase.split(" ");
        boolean validInput = splitPhrase.length > 1;
        boolean gotDeadline = phrase.contains("/by") && !splitPhrase[splitPhrase.length - 1].equals("/by");
        if (!(validInput && gotDeadline)) {
            throw new InvalidDeadlineException("ERROR! Please add a description after 'deadline' " +
                    "and more information about the time/date after '/by'" + "\n"
                    + "Your input: " + phrase);
        } else {
            return true;
        }
    }

    private boolean isValidEvent(String phrase) throws InvalidEventException {
        String[] splitPhrase = phrase.split(" ");
        boolean validInput = splitPhrase.length > 1;
        boolean gotEvent = phrase.contains("/at") && !splitPhrase[splitPhrase.length - 1].equals("/at");
        if (!(validInput && gotEvent)) {
            throw new InvalidEventException("ERROR! Please add a description after 'event' " +
                    "and more information about the time/date after '/at'" + "\n"
                    + "Your input: " + phrase);
        } else {
            return true;
        }
    }

    /**
     * Marks the user specified task as done.
     * Parse the task's number to be marked as done and set it as done.
     *
     * @param phrase The User's input
     * @param tasks The current TaskList
     */
    public void runDoneCommand(String phrase, TaskList tasks) {
        int taskNumber = parser.parseDoneInputToInt(phrase);
        if (tasks.markAsDone(taskNumber)) {
            ui.printMarkTaskAsDone(taskNumber, tasks);
        } else {
            ui.printTaskAlreadyMarkedAsDone(taskNumber, tasks);
        }

    }

    /**
     * Deletes the user specified task.
     * Parse the task's number from the user input and then delete the specified task.
     *
     * @param phrase The User's input
     * @param tasks The current TaskList
     */
    public void runDeleteCommand(String phrase, TaskList tasks) {
        int taskNumber = parser.parseDeleteInputToInt(phrase);
        ui.printTaskDeleted(taskNumber, tasks);
        tasks.delete(taskNumber);
    }

    /**
     * Finds the task that matches the input's description.
     * Parse the input into a string containing only the details to search for.
     *
     * @param phrase The User's input
     * @param tasks The current TaskList
     */
    public void runFindCommand(String phrase, TaskList tasks) throws InvalidFindException {
        String phraseToSearch = parser.parseSearchInputToString(phrase);
        ui.printMatchingTaskList(tasks.search(phraseToSearch));
    }

    /**
     * Adds the User's input as a task to the current TaskList.
     * Parse the input as the specified Task subclass: Todo/Deadline/Event.
     *
     * @param phrase The User's input
     * @param tasks The current TaskList
     * @throws InvalidTaskException If the phrase does not follow specified task format.
     */
    public void runTaskCommand(String phrase, TaskList tasks) throws InvalidTaskException {
        Task task = parser.parsePhraseToTask(phrase);
        tasks.add(task);
        ui.printTaskAdded(task);
        ui.printNumberOfTasks(tasks);
    }

    public void runHelpCommand() {
        ui.printCommandList();
    }
}
