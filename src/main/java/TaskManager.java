import java.util.ArrayList;
import java.util.stream.Collectors;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class TaskManager {
    public static ArrayList<Task> tasks = new ArrayList<>();

    private void taskDone(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        String position = params[1];
        int index = Integer.parseInt(position) - 1;
        tasks.get(index).markAsDone();
        Ui.printMarkAsDoneMessage(index);
        Ui.printDivider();
    }

    private void deleteTask(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        String position = params[1];
        int index = Integer.parseInt(position) - 1;
        Ui.printDeleteTaskMessage(index);
        tasks.remove(index);
    }

    public static void taskDoneLatest() {
        tasks.get(tasks.size() - 1).markAsDone();
    }


    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s" + System.lineSeparator(), i + 1, tasks.get(i));
        }
        Ui.printDivider();
    }

    private void echoTask(int index) {
        Ui.printNewlyAddedTask(index);
        Ui.printTaskCount();
        Ui.printDivider();
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

    public void findTask(String userInput) {
        ArrayList<Task> filteredList = filterTasksByString(tasks, userInput);
        Ui.printData(filteredList, userInput);
    }

    private void addTask(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        String command = params[0];
        if (params.length < 2) {
            throw new DukeException();
        }

        String description = params[1];
        String[] separateTime;
        String[] separatePreposition;
        String time;

        if (command.equals("todo")) {
            addTodo(description);
        }
        else { //timed tasks
            separateTime = params[1].split("/", 2);
            description = separateTime[0];
            separatePreposition = separateTime[1].split(" ", 2);
            time = separatePreposition[1];
            if (command.equals("deadline")){
                addDeadline(description, time);
            }
            else {
                addEvent(description, time);
            }
        }

    }

    public void handleRequest(String userInput) {
        try {
            String[] params = userInput.split(" ", 2);
            String command = params[0];

            switch (command.toUpperCase()) {
            case "LIST":
                listTasks();
                break;
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                addTask(userInput);
                echoTask(tasks.size() - 1);
                break;
            //fallthrough
            case "FIND":
                findTask(params[1]);
                break;
            case "DONE":
                taskDone(userInput);
                break;
            case "DELETE":
                deleteTask(userInput);
                break;
            default:
                throw new InvalidCommandException(); //if user input is not any of the commands
            }
        } catch (InvalidCommandException e) {
            Ui.printInvalidCommandMessage();
        } catch (DukeException e) {
            Ui.printDukeExceptionMessage(userInput);
        }
    }
}
