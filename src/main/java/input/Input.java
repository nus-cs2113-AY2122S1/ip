package input;

import exceptions.DeadlineException;
import exceptions.EventException;
import parser.Parser;
import tasklist.Task;
import tasklist.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class Input {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    /**
     * This method handles the inputs given by the user. It extracts the keyword from the input
     * by calling the parseUserInput method from Parser and will handle the remaining input
     * based on the command that has been extracted. The remaining input will be added to the
     * user's list of task.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     */
    public static void inputHandler(String userLine, ArrayList<Task> taskList) {
        String[] splitString = Parser.parseUserInput(userLine);
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
                Ui.printAddedTask(taskList.get(taskList.size() - 1), taskList);
                break;
            case COMMAND_DEADLINE:
                TaskList.addDeadlineTask(userLine, taskList);
                Ui.printAddedTask(taskList.get(taskList.size() - 1), taskList);
                break;
            case COMMAND_EVENT:
                TaskList.addEventTask(userLine, taskList);
                Ui.printAddedTask(taskList.get(taskList.size() - 1), taskList);
                break;
            case COMMAND_DELETE:
                TaskList.deleteTask(userLine, taskList);
                break;
            default:
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundExceptionMessage();
        } catch (NumberFormatException e) {
            Ui.printNumberFormatExceptionMessage();
        } catch (ArithmeticException e) {
            Ui.printArithmeticExceptionMessage(taskList.size());
        } catch (DeadlineException e) {
            Ui.printDeadlineExceptionMessage();
        } catch (EventException e) {
            Ui.printEventExceptionMessage();
        }
    }
}
