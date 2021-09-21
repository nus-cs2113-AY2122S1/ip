import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
    protected String FIND_COMMAND = "find";

    public int filterTaskNum(String doneTask) throws DukeMissingParamException, NumberFormatException {
        String[] words = doneTask.split(" ");

        // check to see if task number has not been input
        if (words.length > 1) {
            return Integer.parseInt(words[1]);
        }
        else {
            throw new DukeMissingParamException();
        }
    }

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
                catch (DateTimeParseException e) {
                    ui.printDeadlineEventDateParamError();
                }
            } else if (line.contains(EVENT_COMMAND)) {
                try {
                    // adds event and prints success message
                    taskList.addEvent(line, tasks);
                } catch (DukeMissingDescException e) {
                    ui.printEventEmptyError();
                } catch (DukeMissingParamException e) {
                    ui.printEventMissingParamError();
                } catch (DateTimeParseException e) {
                    ui.printDeadlineEventDateParamError();
                }
            } else if (line.contains(FIND_COMMAND)) {
                try {
                    taskList.findTasks(line, tasks);
                } catch (DukeMissingParamException e) {
                    ui.printFindMissingParamError();
                } catch (DukeMultipleParamException e) {
                    ui.printFindMultipleParamError();
                }
            } else {
                // throw error when no commands are found in input
                ui.printUnknownCommand();
            }
            line = in.nextLine();
        }
    }
}
