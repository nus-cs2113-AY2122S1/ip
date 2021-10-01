package input;

import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.InputErrorException;
import exceptions.InputOutOfRangeException;
import parser.Parser;
import tasklist.Task;
import tasklist.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Input {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    /**
     * Handles the inputs given by the user. Extracts the keyword from the input by calling
     * the parseUserInput method from Parser and will handle the remaining input based on the
     * command that has been extracted. The remaining input will be added to the user's list of task.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     */
    public static void inputHandler(String userLine, ArrayList<Task> taskList) {
        String[] splitString = Parser.parseCommand(userLine);
        String taskKeyword = splitString[0];
        try {
            switch (taskKeyword) {
            case COMMAND_LIST:
                TaskList.listTasks(taskList);
                break;
            case COMMAND_DONE:
                TaskList.markTaskDone(userLine, taskList);
                break;
            case COMMAND_TODO:
                TaskList.addTodoTask(userLine, taskList);
                break;
            case COMMAND_DEADLINE:
                TaskList.addDeadlineTask(userLine, taskList);
                break;
            case COMMAND_EVENT:
                TaskList.addEventTask(userLine, taskList);
                break;
            case COMMAND_DELETE:
                TaskList.deleteTask(userLine, taskList);
                break;
            case COMMAND_FIND:
                TaskList.findKeyTasks(userLine, taskList);
                break;
            default:
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundExceptionMessage();
        } catch (InputErrorException e) {
            Ui.printInputErrorExceptionMessage();
        } catch (InputOutOfRangeException e) {
            Ui.printInputOutOfRangeExceptionMessage(taskList.size());
        } catch (DeadlineException e) {
            Ui.printDeadlineExceptionMessage();
        } catch (EventException e) {
            Ui.printEventExceptionMessage();
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseExceptionMessage();
        }
    }
}
