package command;

import exceptions.DeadlineException;
import exceptions.EventException;
import parser.Parser;
import tasklist.Task;
import tasklist.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Command {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    public static void commandHandler(String userLine, ArrayList<Task> taskList) {
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
            case COMMAND_FIND:
                TaskList.findKeyTasks(userLine, taskList);
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
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseExceptionMessage();
        }
    }
}
