package duke;

import java.io.IOException;

/**
 * To make sense of the user's commands by checking if the command is legal or not.
 * If the command is not legal, the respective DukeException will be thrown.
 * If the command is legal, the respective TaskList methods will be called.
 */
public class Parser {

    protected boolean isBye;
    protected TaskList tasks;

    /**
     * Instantiates the Parser object.
     *
     * @param tasks The task object
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.isBye = false;
    }

    /**
     * Checks if the given command is one word or multiple words, calls the respective methods.
     *
     * @param line user's input/command.
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     */
    public void checkCommand(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        if (input.length == 1) {
            checkSingleCommand(line);
        } else {
            checkMultiCommand(input);
        }
    }

    /**
     * Determines the type of command given by the user, calls the respective TaskList methods to take action.
     * If the command is not legal, the respective DukeException will be thrown.
     *
     * @param line The first word of the command given by the user
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     */
    public void checkSingleCommand(String line) throws DukeException {
        switch (line) {
        case "bye":
            isBye = true;
            break;
        case "list":
            if (tasks.positionCheck == 0) {
                throw new DukeException(Ui.EMPTY);
            } else {
                tasks.printList();
                break;
            }
        case "done":
            throw new DukeException(Ui.UNSPECIFIED_DONE);
        case "delete":
            throw new DukeException(Ui.UNSPECIFIED_DELETE);
        case "find":
            throw new DukeException(Ui.UNSPECIFIED_FIND);
        case "date":
            throw new DukeException(Ui.UNSPECIFIED_DATE);
        default:
            throw new DukeException(Ui.UNSPECIFIED_TASK);
        }
    }

    /**
     * Determines the type of command given by the user, calls the respective TaskList methods to take action.
     * If none of the cases are met, checkTypeOfTask will be called.
     * If the command is not legal, the respective DukeException will be thrown.
     *
     * @param input The array of strings that represent the user's command
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     */
    public void checkMultiCommand(String[] input) throws DukeException, IOException {
        String firstWord = input[0];
        String secondWord = input[1];
        switch (firstWord) {
        case "done":
        case "delete":
            int taskNumber = Integer.parseInt(secondWord);
            int taskIndex = taskNumber - 1;
            if (tasks.positionCheck <= 0) {
                throw new DukeException(Ui.EMPTY);
            } else if ((taskNumber > tasks.positionCheck) || (taskNumber <= 0)) {
                throw new DukeException(Ui.INVALID);
            } else if (firstWord.equals("done")) {
                tasks.markDone(taskIndex);
                break;
            } else {
                tasks.deleteTask(taskIndex);
                break;
            }
        case "find":
            String keyword = secondWord;
            for (int i = 2; i < input.length; i++) {
                keyword += " " + input[i];
            }
            tasks.printListForFindingTask(keyword);
            break;
        case "date":
            tasks.printListForFindingDate(secondWord);
            break;
        default:
            checkTypeOfTask(input);
            break;
        }
    }

    /**
     * Determines the type of task the user wishes to add, calls the respective TaskList methods to take action.
     * If the command is not legal, the respective DukeException will be thrown.
     *
     * @param input The array of strings that represent the user's command
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     */
    public void checkTypeOfTask(String[] input) throws DukeException, IOException {
        int length = input.length;
        String firstWord = input[0];
        switch (firstWord) {
        case "deadline":
            tasks.addDeadline(input,length);
            break;
        case "event":
            tasks.addEvent(input,length);
            break;
        case "todo":
            tasks.addTodo(input,length);
            break;
        default:
            throw new DukeException(Ui.UNSPECIFIED_TASK);
        }
    }

}
