package alfred.manager;

import alfred.exception.EmptyDescriptionException;
import alfred.exception.InvalidDateException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.Todo;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        DataManager.populateTasks(tasks);
        MessageManager.initMessage();
    }

    public void processInput(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        try {
            switch (destructuredInputs[0]) {
            case "list":
                listTasks();
                break;
            case "done":
                completeTask(userInput);
                break;
            case "todo":
                // Fallthrough
            case "event":
                // Fallthrough
            case "deadline":
                addTask(userInput);
                break;
            case "delete":
                deleteTask(userInput);
                break;
            default:
                MessageManager.invalidCommandMessage();
            }
        } catch (NumberFormatException e) {
            MessageManager.invalidIndexMessage();
        } catch (IndexOutOfBoundsException e) {
            MessageManager.uninitialisedTaskIndexMessage(tasks.size());
        }
    }

    private void addTask(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        try {
            switch (destructuredInputs[0]) {
            case "todo":
                addTodo(userInput);
                break;
            case "event":
                addEvent(userInput);
                break;
            case "deadline":
                addDeadline(userInput);
                break;
            default:
                MessageManager.invalidCommandMessage();
            }
        } catch (EmptyDescriptionException e) {
            MessageManager.emptyDescriptionMessage();
        } catch (InvalidDateException e) {
            MessageManager.invalidDateMessage();
        }
    }

    private void listTasks() {
        System.out.print(MessageManager.LINE);
        if (tasks.size() == 0) {
            System.out.println(" Your schedule is clear, Master Wayne.");
        } else {
            System.out.println(" Your tasks, sir:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        System.out.println(MessageManager.LINE);
    }

    private void completeTask(String userInput) throws NumberFormatException, IndexOutOfBoundsException {
        String[] destructuredInputs = userInput.split(" ");
        int index = Integer.parseInt(destructuredInputs[1]) - 1;
        tasks.get(index).setTaskDone();
        MessageManager.completeTaskMessage(index + 1, tasks.get(index).toString());
        DataManager.saveAllTasks(tasks);
    }

    private void addTodo(String userInput) throws EmptyDescriptionException {
        String[] destructuredInputs = userInput.split(" ", 2);
        if (destructuredInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String todoName = destructuredInputs[1];
        Todo t = new Todo(todoName);
        tasks.add(t);
        MessageManager.addTaskMessage(t, tasks.size());
        DataManager.saveAllTasks(tasks);
    }

    private void addEvent(String userInput) throws EmptyDescriptionException, InvalidDateException {
        String[] destructuredInputs = userInput.split(" ", 2);
        if (destructuredInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] destructuredArguments = destructuredInputs[1].split(" /at ", 2);
        if (destructuredArguments.length < 2) {
            throw new InvalidDateException();
        }
        Event e = new Event(destructuredArguments[0], destructuredArguments[1]);
        tasks.add(e);
        MessageManager.addTaskMessage(e, tasks.size());
        DataManager.saveAllTasks(tasks);
    }

    private void addDeadline(String userInput) throws EmptyDescriptionException, InvalidDateException {
        String[] destructuredInputs = userInput.split(" ", 2);
        if (destructuredInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] destructuredArguments = destructuredInputs[1].split(" /by ", 2);
        if (destructuredArguments.length < 2) {
            throw new InvalidDateException();
        }
        Deadline d = new Deadline(destructuredArguments[0], destructuredArguments[1]);
        tasks.add(d);
        MessageManager.addTaskMessage(d, tasks.size());
        DataManager.saveAllTasks(tasks);
    }

    private void deleteTask(String userInput) throws NumberFormatException, IndexOutOfBoundsException {
        String[] destructuredInputs = userInput.split(" ");
        int index = Integer.parseInt(destructuredInputs[1]) - 1;
        Task removedTask = tasks.get(index);
        tasks.remove(tasks.get(index));
        MessageManager.deleteTaskMessage(removedTask, tasks.size());
        DataManager.saveAllTasks(tasks);
    }
}
