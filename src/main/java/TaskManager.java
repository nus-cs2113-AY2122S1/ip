import exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;

public class TaskManager {
    public static final int MAX_TASKS = 100;
    public static final int SLASH_INDEX_DEADLINE = 8;
    public static final int SLASH_INDEX_EVENT = 5;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public void printInvalid() {
        Duke.printLine();
        System.out.println("\tHey bud, the command you printed is invalid.");
        System.out.println("\tHere's some examples of valid commands:");
        System.out.println("\t- todo Read book");
        System.out.println("\t- event Book club /at Monday 2pm");
        System.out.println("\t- deadline Return book /by Friday");
        Duke.printLine();
    }

    public void addTodo(String description) throws IOException {
        try {
            String todoDescription = trimTodoDescription(description);
            printNewTodo(todoDescription);
            FileWriting.writeToFile("T | 0 | " + todoDescription + System.lineSeparator());
        } catch (EmptyTodoException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tA todo can't be empty. Here's a valid example:");
            System.out.println("\t- todo Read book");
            Duke.printLine();
        }

    }

    public String trimTodoDescription(String description) throws EmptyTodoException {
        String todoDescription = description.substring(4, description.length()).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyTodoException();
        }
        return todoDescription;
    }

    private void printNewTodo(String todoDescription) throws IOException {
        addTodoTask(todoDescription);
        Duke.printLine();
        System.out.println("\tAdded todo: " + todoDescription);
        Duke.printLine();
    }

    public void addTodoTask(String todoDescription) throws IOException {
        tasks[taskCount] = new Todo(todoDescription);
        taskCount += 1;
    }

    public void addDeadline(String description) {
        try {
            String[] deadline = trimDeadlineDescription(description);
            printNewDeadline(deadline[0], deadline[1]);
            FileWriting.writeToFile("D | 0 | " + deadline[0] + " | " +
                    deadline[1] + System.lineSeparator());
        } catch (NoSlashDeadlineException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tDeadline commands require a slash. Here's a valid example:");
            System.out.println("\t- deadline Return book /by Friday");
            Duke.printLine();
        } catch (DeadlineEmptyException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tThe description or deadline of a deadline command can't be empty. " +
                    "Here's a valid example: ");
            System.out.println("\t- deadline Return book /by Friday");
            Duke.printLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] trimDeadlineDescription(String description) throws NoSlashDeadlineException,
            DeadlineEmptyException {
        int slashIndex = description.indexOf('/');
        String[] deadline = new String[2];
        if (slashIndex > SLASH_INDEX_DEADLINE) {
            deadline[0] = description.substring(SLASH_INDEX_DEADLINE, slashIndex).trim();
            deadline[1] = description.substring(slashIndex + 3, description.length()).trim();
        } else {
            throw new NoSlashDeadlineException();
        }
        if (deadline[0].isEmpty() || deadline[1].isEmpty()) {
            throw new DeadlineEmptyException();
        } else {
            return deadline;
        }
    }

    private void printNewDeadline(String deadlineDescription, String deadlineBy) {
        addDeadlineTask(deadlineDescription, deadlineBy);
        Duke.printLine();
        System.out.println("\tAdded deadline: " + deadlineDescription + " (by: " + deadlineBy + ')');
        Duke.printLine();
    }

    public void addDeadlineTask(String deadlineDescription, String deadlineBy) {
        tasks[taskCount] = new Deadline(deadlineDescription, deadlineBy);
        taskCount += 1;
    }

    public void addEvent(String description) {
        try {
            String[] event = trimEventDescription(description);
            printNewEvent(event[0], event[1]);
            FileWriting.writeToFile("E | 0 | " + event[0] + " | " +
                    event[1] + System.lineSeparator());
        } catch (NoSlashEventException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tEvent commands require a slash. Here's a valid example:");
            System.out.println("\t- deadline Return book /by Friday");
            Duke.printLine();
        } catch (EventEmptyException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tThe description or event time of a event command can't be empty. " +
                    "Here's a valid example: ");
            System.out.println("\t- deadline Return book /by Friday");
            Duke.printLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] trimEventDescription(String description) throws NoSlashEventException, EventEmptyException {
        int slashIndex = description.indexOf('/');
        String[] event = new String[2];
        if (slashIndex > SLASH_INDEX_EVENT) {
            event[0] = description.substring(SLASH_INDEX_EVENT, slashIndex).trim();
            event[1] = description.substring(slashIndex + 3, description.length()).trim();
        } else {
            throw new NoSlashEventException();
        }
        if (event[0].isEmpty() || event[1].isEmpty()) {
            throw new EventEmptyException();
        } else {
            return event;
        }
    }

    private void printNewEvent(String eventDescription, String eventAt) {
        addEventTask(eventDescription, eventAt);
        Duke.printLine();
        System.out.println("\tAdded event: " + eventDescription + " (at: " + eventAt + ')');
        Duke.printLine();
    }

    public void addEventTask(String eventDescription, String eventAt) {
        tasks[taskCount] = new Event(eventDescription, eventAt);
        taskCount += 1;
    }

    public void listTasks() {
        Duke.printLine();
        if (taskCount > 0) {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i += 1) {
                System.out.print('\t');
                System.out.print(i + 1 + ". ");
                System.out.print(tasks[i].toString() + System.lineSeparator());
            }
        } else {
            System.out.println("\tYou have no tasks.");
        }
        Duke.printLine();
    }

    public void markAsDone(int index) {
        tasks[index].setAsDone();
        Duke.printLine();
        System.out.println("\tNice! You completed this task:");
        System.out.println("\t  [X] " + tasks[index].getDescription());
        Duke.printLine();
    }
}
