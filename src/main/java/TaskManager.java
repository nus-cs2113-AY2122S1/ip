import java.util.ArrayList;
import java.util.stream.Collectors;

import exceptions.DukeException;
import exceptions.EmptyTimeException;
import exceptions.InvalidCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class TaskManager {
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Marks the task at index as done
     * Index is extracted from userInput using Parser.parseExtractIndex
     *
     * @param userInput the String entered by user, in this case "done {position of task}"
     * @throws DukeException the exception thrown when user did not include task number
     * @throws IndexOutOfBoundsException the exception thrown when user enter a number
     * that is out of bound of ArrayList<Task> tasks
     */
    private void taskDone(String userInput) throws DukeException, IndexOutOfBoundsException {
        int index = Parser.parseExtractIndex(userInput);
        tasks.get(index).markAsDone();
        Ui.printMarkAsDoneMessage(index);
        Ui.printDivider();
    }

    /**
     * Delete a task from ArrayList<Task> tasks
     * Index is extracted from userInput using Parser.parseExtractIndex
     *
     * @param userInput the String entered by user, in this case "delete {position of task}"
     * @throws DukeException the exception thrown when user did not include task number
     * @throws IndexOutOfBoundsException the exception thrown when user enter a number
     * that is out of bound of ArrayList<Task> tasks
     */
    private void deleteTask(String userInput) throws DukeException, IndexOutOfBoundsException {
        int index = Parser.parseExtractIndex(userInput);
        Ui.printDeleteTaskMessage(index);
        tasks.remove(index);
    }

    /**
     * Mark the last task that is added as done
     * Primarily used when reading data from local storage
     */
    public static void taskDoneLatest() {
        tasks.get(tasks.size() - 1).markAsDone();
    }

    public static void addTodo(String userInput) {
        tasks.add(new Todo(userInput));
    }

    public static void addDeadline(String description, String time) {
        tasks.add(new Deadline(description, time));
    }

    public static void addEvent(String description, String time) {
        tasks.add(new Event(description, time));
    }

    /**
     * Iterate through ArrayList<Task> tasks and return tasks that contains filterString, using Java Streams
     *
     * @param tasks an ArrayList that stores user's tasks
     * @param filterString a String query by user
     * @return a list of tasks containing filteredString
     */
    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredList;
    }

    public void findTask(String userInput) throws DukeException, EmptyTimeException {
        String userQuery = Parser.parseExtractString(userInput);
        ArrayList<Task> filteredList = filterTasksByString(tasks, userQuery);
        Ui.printMatchingString(filteredList, userInput);
    }

    /**
     * Adds a new task to ArrayList<Task> tasks
     *
     * @param userInput the String entered by user, including command
     * @throws DukeException the exception thrown when user did not include description of task
     * @throws EmptyTimeException the exception thrown when user did not include date/time for deadline or event
     */
    private void addTask(String userInput) throws DukeException, EmptyTimeException{
        String command = Parser.parseExtractCommand(userInput);
        String description = Parser.parseExtractString(userInput);

        if (command.equals("todo")) {
            addTodo(description);
            Ui.echoLastTask();
            return;
        }

        //timed tasks
        String[] InfoTimeArray = Parser.parseExtractInfoAndTime(description);
        description = InfoTimeArray[0];
        String time = InfoTimeArray[1];
        if (command.equals("deadline")) {
            addDeadline(description, time);
        } else if (command.equals("event")) {
            addEvent(description, time);
        }
        Ui.echoLastTask();
    }

    /**
     * Handles user requests: {list, todo, deadline, event, find, done, delete}
     * and call its corresponding methods
     * An invalid command returns an error message
     *
     * @param userInput the String entered by user
     */

    public void handleRequest(String userInput) {
        try {
            String command = Parser.parseExtractCommand(userInput);
            switch (command.toUpperCase()) {
            case "LIST":
                Ui.printTasks();
                break;
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                addTask(userInput);
                break;
            //fallthrough
            case "FIND":
                findTask(userInput);
                break;
            case "DONE":
                taskDone(userInput);
                break;
            case "DELETE":
                deleteTask(userInput);
                break;
            default:
                throw new InvalidCommandException("☹ OOPS!!! I do not understand what that means!"); //if user input is not any of the commands
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printOutOfBoundsMessage();
        } catch (EmptyTimeException e) {
            Ui.printEmptyTimeExceptionMEssage();
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printNumberFormatExceptionMessage();
        }
        catch (DukeException e) {
            Ui.printDukeExceptionMessage(userInput);
        }
    }
}
