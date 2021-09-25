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

    private void taskDone(String userInput) throws DukeException, IndexOutOfBoundsException {
        int index = Parser.parseExtractIndex(userInput);
        tasks.get(index).markAsDone();
        Ui.printMarkAsDoneMessage(index);
        Ui.printDivider();
    }

    private void deleteTask(String userInput) throws DukeException, IndexOutOfBoundsException {
        int index = Parser.parseExtractIndex(userInput);
        Ui.printDeleteTaskMessage(index);
        tasks.remove(index);
    }

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

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredList;
    }

    public void findTask(String userInput) throws DukeException, EmptyTimeException {
        String userQuery = Parser.parseExtractString(userInput);
        ArrayList<Task> filteredList = filterTasksByString(tasks, userQuery);
        Ui.printData(filteredList, userInput);
    }

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
        } catch (DukeException e) {
            Ui.printDukeExceptionMessage(userInput);
        }
    }
}
