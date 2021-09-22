package herrekt;

import herrekt.exceptions.InvalidInputException;
import herrekt.exceptions.InvalidTaskException;
import herrekt.taskmanager.Task;
import herrekt.taskmanager.TaskList;

public class Command {
    private final Ui ui;
    private final Parser parser;
    private final static String[] commands = {"todo", "event", "deadline", "list", "done", "find", "delete", "bye"};

    public Command() {
        this.ui = new Ui();
        this.parser = new Parser();
    }


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

    private boolean isValidTodo(String phrase) throws InvalidTaskException {
        String[] splitPhrase = phrase.split(" ");
        boolean validInput = splitPhrase.length > 1;
        if (!validInput) {
            throw new InvalidTaskException("ERROR! Please add a description after 'todo'!" + "\n"
            + "Your input: " + phrase);
        } else {
            return true;
        }
    }

    private boolean isValidDeadline(String phrase) throws InvalidTaskException {
        String[] splitPhrase = phrase.split(" ");
        boolean validInput = splitPhrase.length > 1;
        boolean gotDeadline = phrase.contains("/by") && splitPhrase[splitPhrase.length - 1].equals("/by");
        if (!(validInput && gotDeadline)) {
            throw new InvalidTaskException("ERROR! Please add a description after 'deadline' " +
                    "and more information about the time/date after '/by'" + "\n"
                    + "Your input: " + phrase);
        } else {
            return true;
        }
    }

    private boolean isValidEvent(String phrase) throws InvalidTaskException {
        String[] splitPhrase = phrase.split(" ");
        boolean validInput = splitPhrase.length > 1;
        boolean gotEvent = phrase.contains("/at") && !splitPhrase[splitPhrase.length - 1].equals("/at");
        if (!(validInput && gotEvent)) {
            throw new InvalidTaskException("ERROR! Please add a description after 'event' " +
                    "and more information about the time/date after '/at'" + "\n"
                    + "Your input: " + phrase);
        } else {
            return true;
        }
    }

    public void runDoneCommand(String phrase, TaskList tasks) {
        int taskNumber = parser.parseDoneInputToInt(phrase);
        if (tasks.markAsDone(taskNumber)) {
            ui.printMarkTaskAsDone(taskNumber, tasks);
        } else {
            ui.printTaskAlreadyMarkedAsDone(taskNumber, tasks);
        }

    }

    public void runDeleteCommand(String phrase, TaskList tasks) {
        int taskNumber = parser.parseDeleteInputToInt(phrase);
        ui.printTaskDeleted(taskNumber, tasks);
        tasks.delete(taskNumber);
    }

    public void runFindCommand(String phrase, TaskList tasks) {
        String phraseToSearch = parser.parseSearchInputToString(phrase);
        ui.printMatchingTaskList(new TaskList(tasks.search(phraseToSearch)));
    }

    public void runTaskCommand(String phrase, TaskList tasks) throws InvalidInputException {
        Task task = parser.parsePhraseToTask(phrase);
        tasks.add(task);
        ui.printNumberOfTasks(tasks);
    }
}
