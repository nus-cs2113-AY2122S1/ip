package alfred.manager;

import alfred.exception.EmptyDescriptionException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.Todo;

public class TaskManager {
    private Task[] tasks;
    private int listIndex;
    private final String LINE = "____________________________________________________________\n";
    private final int TASK_LIST_SIZE = 100;
    private final String LOGO =
            " **********************************\n" +
            " *     _    _  __              _  *\n" +
            " *    / \\  | |/ _|_ __ ___  __| | *\n" +
            " *   / _ \\ | | |_| '__/ _ \\/ _` | *\n" +
            " *  / ___ \\| |  _| | |  __/ (_| | *\n" +
            " * /_/   \\_\\_|_| |_|  \\___|\\__,_| *\n" +
            " **********************************\n";


    public TaskManager() {
        tasks = new Task[TASK_LIST_SIZE];
        listIndex = 0;
        initAlfred();
    }

    private void initAlfred() {
        System.out.println(
                LOGO + "\n" + LINE +
                " Welcome back, Master Wayne.\n" +
                " How may I be of service to you?\n" +
                LINE
        );
    }

    public void shutdownMessage() {
        System.out.println(
                LINE +
                " Very well sir, I shall leave you to your own devices.\n" +
                LINE
        );
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
                ErrorManager.invalidCommandMessage();
            }
        } catch (EmptyDescriptionException e) {
            ErrorManager.emptyDescriptionMessage();
        }
    }


    private void listTasks() {
        System.out.print(LINE);
        if (listIndex == 0) {
            System.out.println(" Your schedule is clear, Master Wayne.");
        } else {
            System.out.println(" Your tasks, sir:");
            for (int i = 0; i < listIndex; i++) {
                System.out.println(" " + (i + 1) + "." + tasks[i].toString());
            }
        }
        System.out.println(LINE);
    }

    private void completeTask(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        int index = Integer.parseInt(destructuredInputs[1]) - 1;
        tasks[index].setTaskDone();
        completeTaskMessage(index + 1, tasks[index].toString());
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
        addTaskMessage(t);
    }

    private void addEvent(String userInput) throws EmptyDescriptionException {
        String[] destructuredInputs = userInput.split(" ", 2);
        if (destructuredInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] destructuredArguments = destructuredInputs[1].split(" /at ", 2);
        Event e = new Event(destructuredArguments[0], destructuredArguments[1]);
        tasks[listIndex] = e;
        listIndex++;
        addTaskMessage(e);
    }

    private void addDeadline(String userInput) throws EmptyDescriptionException {
        String[] destructuredInputs = userInput.split(" ", 2);
        if (destructuredInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] destructuredArguments = destructuredInputs[1].split(" /by ", 2);
        Deadline d = new Deadline(destructuredArguments[0], destructuredArguments[1]);
        tasks[listIndex] = d;
        listIndex++;
        addTaskMessage(d);
    }

    private void completeTaskMessage(int index, String taskDescription) {
        System.out.println(
                LINE +
                " Duly noted on completion of task, sir.\n" +
                "    " + index + "." + taskDescription + "\n" +
                LINE
        );
    }

    private void addTaskMessage(Task t) {
        System.out.println(
                LINE +
                " I shall put this in your schedule, Master Wayne: \n    " + t.toString() + "\n" +
                " Sir, the number of Tasks you have scheduled currently amounts to " + listIndex + "." + "\n" +
                LINE
        );
    }
}
