package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    protected Ui userInterface = new Ui();

    protected TaskList taskList = new TaskList();
    public static final String DOTTED_LINE = "____________________________________________________________";

    protected String BYE_COMMAND = "bye";

    protected String LIST_COMMAND = "list";

    protected String HELP_COMMAND = "helpme";

    protected String DONE_COMMAND = "done";

    protected String DELETE_COMMAND = "delete";

    protected String TODO_COMMAND = "todo";

    protected String DEADLINE_COMMAND = "deadline";

    protected String EVENT_COMMAND = "event";

    protected String FIND_COMMAND = "find";

    public static int filterTaskNumber(String taskThatIsDone) throws DukeMissingParamException, NumberFormatException {

        String[] wordsOfTheTask = taskThatIsDone.split(" ");

        if (wordsOfTheTask.length > 1) { //check if there is input after the command done
            return Integer.parseInt(wordsOfTheTask[1]);
        } else {
            throw new DukeMissingParamException();
        }
    }

    public void parseInputs(Scanner in, String line, ArrayList<Task> tasks) {

        while (!line.equals(BYE_COMMAND)) {
            if (line.equals(LIST_COMMAND)) {

                userInterface.printListOfTasks(tasks);

            } else if (line.equals(HELP_COMMAND)) {

                userInterface.printHelpMessage();

            } else if (line.contains(DONE_COMMAND)) {
                try {

                    int taskNum = filterTaskNumber(line);

                    taskList.markTaskAsDone(taskNum, tasks);

                } catch (DukeMissingParamException e) {

                    userInterface.printDoneEmptyError();

                } catch (NumberFormatException e) {

                    userInterface.printDoneWordError();

                } catch (IndexOutOfBoundsException e) {

                    userInterface.printDoneInvalidNumberError();

                }
            } else if (line.contains(DELETE_COMMAND)) {
                try {

                    int taskNum = filterTaskNumber(line);

                    taskList.deleteTask(taskNum, tasks);

                } catch (DukeMissingParamException e) {

                    userInterface.printDeleteEmptyError();

                } catch (NumberFormatException e) {

                    userInterface.printDeleteWordError();

                } catch (IndexOutOfBoundsException e) {

                    userInterface.printDeleteInvalidNumError();
                  
                }
            } else if (line.contains(TODO_COMMAND)) {
                try {

                    taskList.addTodo(line, tasks);


                } catch (DukeMissingDescException e) {

                    userInterface.printTodoEmptyError();

                }
            } else if (line.contains(DEADLINE_COMMAND)) {
                try {

                    taskList.addDeadline(line, tasks);

                } catch (DukeMissingDescException e) {

                    userInterface.printDeadlineEmptyError();

                } catch (DukeMissingParamException e) {

                    userInterface.printDeadlineMissingParameterError();

                } catch (DateTimeParseException e) {

                    userInterface.printDeadlineEventDateParameterError();

                }
            } else if (line.contains(EVENT_COMMAND)) {
                try {

                    taskList.addEvent(line, tasks);

                } catch (DukeMissingDescException e) {

                    userInterface.printEventEmptyError();

                } catch (DukeMissingParamException e) {

                    userInterface.printEventMissingParameterError();

                } catch (DateTimeParseException e) {

                    userInterface.printDeadlineEventDateParameterError();

                }
            } else if (line.contains(FIND_COMMAND)) {
                try {

                    taskList.findTasks(line, tasks);

                } catch (DukeMissingParamException e) {

                    userInterface.printFindMissingParameterError();

                } catch (DukeMultipleParamException e) {

                    userInterface.printFindMultipleParameterError();

                } catch (DateTimeParseException e) {

                    userInterface.printDeadlineEventDateParameterError();

                }
            } else {

                userInterface.printUnknownCommandError();

            }
            line = in.nextLine();
        }
    }
}
