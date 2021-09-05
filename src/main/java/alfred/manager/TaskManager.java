package alfred.manager;

import alfred.exception.EmptyDescriptionException;
import alfred.exception.InvalidDateException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.Todo;

public class TaskManager {
    private Task[] tasks;
    private int listIndex;
    private final int TASK_LIST_SIZE = 100;

    public TaskManager() {
        tasks = new Task[TASK_LIST_SIZE];
        listIndex = 0;
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
        } catch (NumberFormatException e) {
            MessageManager.invalidIndexMessage();
        } catch (NullPointerException e) {
            MessageManager.uninitialisedTaskIndexMessage(listIndex);
        } catch (IndexOutOfBoundsException e) {
            MessageManager.indexOutOfBoundsMessage();
        } catch (InvalidDateException e) {
            MessageManager.invalidDateMessage();
        }
    }


    private void listTasks() {
        System.out.print(MessageManager.LINE);
        if (listIndex == 0) {
            System.out.println(" Your schedule is clear, Master Wayne.");
        } else {
            System.out.println(" Your tasks, sir:");
            for (int i = 0; i < listIndex; i++) {
                System.out.println(" " + (i + 1) + "." + tasks[i].toString());
            }
        }
        System.out.println(MessageManager.LINE);
    }

    private void completeTask(String userInput) throws NumberFormatException, IndexOutOfBoundsException, NullPointerException {
        String[] destructuredInputs = userInput.split(" ");
        int index = Integer.parseInt(destructuredInputs[1]) - 1;
        tasks[index].setTaskDone();
        MessageManager.completeTaskMessage(index + 1, tasks[index].toString());
    }

    private void addTodo(String userInput) throws EmptyDescriptionException {
        String[] destructuredInputs = userInput.split(" ", 2);
        if (destructuredInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String todoName = destructuredInputs[1];
        Todo t = new Todo(todoName);
        tasks[listIndex] = t;
        listIndex++;
        MessageManager.addTaskMessage(t, listIndex);
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
        tasks[listIndex] = e;
        listIndex++;
        MessageManager.addTaskMessage(e, listIndex);
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
        tasks[listIndex] = d;
        listIndex++;
        MessageManager.addTaskMessage(d, listIndex);
    }

}
