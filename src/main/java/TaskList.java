import exceptions.*;
import tasks.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.stream.Collectors;


public class TaskList {
    public static final int SLASH_INDEX_DEADLINE = 8;
    public static final int SLASH_INDEX_EVENT = 5;
    public static final String TODO_VALID = "\t- todo Read book";
    public static final String EVENT_VALID = "\t- event Book club /at 2021/09/20 1400";
    public static final String DEADLINE_VALID = "\t- deadline Return book /by 2021/09/24 1600";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    /**
     * Prints an error message when an invalid command has been entered.
     */
    public static void printInvalid() {
        Duke.printLine();
        System.out.println("\tHey bud, the command you printed is invalid.");
        System.out.println("\tHere's some examples of valid commands:");
        System.out.println(TODO_VALID);
        System.out.println(EVENT_VALID);
        System.out.println(DEADLINE_VALID);
        Duke.printLine();
    }

    /**
     * Trims the entered command to get the description of the todo task, adds the todo task to
     * the list of tasks and prints a message informing the user that the todo task has been
     * added.
     * @param description the line of input given by the parser
     * @throws IOException
     */
    public static void prepareToAddTodo(String description) throws IOException {
        try {
            String todoDescription = trimTodoDescription(description);
            printNewTodo(todoDescription);
            Storage.writeToFile("T | 0 | " + todoDescription.trim() + System.lineSeparator());
        } catch (EmptyTodoException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tA todo can't be empty. Here's a valid example:");
            System.out.println(TODO_VALID);
            Duke.printLine();
        }

    }

    private static String trimTodoDescription(String description) throws EmptyTodoException {
        String todoDescription = description.substring(4).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyTodoException();
        }
        return todoDescription;
    }


    private static void printNewTodo(String todoDescription) {
        addTodo(todoDescription);
        Duke.printLine();
        System.out.println("\tAdded todo: " + todoDescription);
        Duke.printLine();
    }

    /**
     * Adds a todo task with the description todoDescription to the list of tasks.
     * @param todoDescription the description of the todo task.
     */
    public static void addTodo(String todoDescription) {
        tasks.add(new Todo(todoDescription));
        taskCount += 1;
    }

    /**
     * Trims the entered command to get the description and date/time of the deadline task, adds the
     * deadline task to the list of tasks and prints a message informing
     * the user that the deadline task has been added.
     * @param description the line of input given by the parser.
     */
    public static void prepareToAddDeadline(String description) {
        try {
            String[] deadline = trimDeadlineDescription(description);
            printNewDeadline(deadline[0], deadline[1]);
            Storage.writeToFile("D | 0 | " + deadline[0].trim() + " | " +
                    deadline[1].trim() + System.lineSeparator());
        } catch (NoSlashDeadlineException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tDeadline commands require a slash. Here's a valid example:");
            System.out.println(DEADLINE_VALID);
            Duke.printLine();
        } catch (DeadlineEmptyException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tThe description or deadline of a deadline command can't be empty. " +
                    "Here's a valid example: ");
            System.out.println(DEADLINE_VALID);
            Duke.printLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] trimDeadlineDescription(String description) throws NoSlashDeadlineException,
            DeadlineEmptyException {
        int slashIndex = description.indexOf("by");
        String[] deadline = new String[2];
        if (slashIndex > SLASH_INDEX_DEADLINE) {
            deadline[0] = description.substring(SLASH_INDEX_DEADLINE, slashIndex - 1).trim();
            deadline[1] = description.substring(slashIndex + 3).trim();
        } else {
            throw new NoSlashDeadlineException();
        }
        if (deadline[0].isEmpty() || deadline[1].isEmpty()) {
            throw new DeadlineEmptyException();
        } else {
            return deadline;
        }
    }

    private static void printNewDeadline(String deadlineDescription, String deadlineBy) {
        addDeadline(deadlineDescription, deadlineBy);
        Duke.printLine();
        System.out.println("\tAdded deadline: " + deadlineDescription + " (by: " + deadlineBy + ')');
        Duke.printLine();
    }

    /**
     * Adds a deadline task with the description deadlineDescription
     * and the deadline deadlineBy to the list of tasks.
     * @param deadlineDescription the description of the deadline task
     * @param deadlineBy the date/time when the deadline must be completed
     */
    public static void addDeadline(String deadlineDescription, String deadlineBy) {
        tasks.add(new Deadline(deadlineDescription, deadlineBy));
        taskCount += 1;
    }

    /**
     * Trims the entered command to get the description and date/time of the event task, adds the
     * event task to the list of tasks and prints a message informing
     * the user that the event task has been added.
     * @param description the line of input given by the parser
     */
    public static void prepareToAddEvent(String description) {
        try {
            String[] event = trimEventDescription(description);
            printNewEvent(event[0], event[1]);
            Storage.writeToFile("E | 0 | " + event[0].trim() + " | " +
                    event[1] + System.lineSeparator());
        } catch (NoSlashEventException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tEvent commands require a slash. Here's a valid example:");
            System.out.println(EVENT_VALID);
            Duke.printLine();
        } catch (EventEmptyException e) {
            Duke.printLine();
            System.out.println("\tHey bud, the command you printed is invalid.");
            System.out.println("\tThe description or event time of a event command can't be empty. " +
                    "Here's a valid example: ");
            System.out.println(EVENT_VALID);
            Duke.printLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] trimEventDescription(String description) throws NoSlashEventException, EventEmptyException {
        int slashIndex = description.indexOf("/at");
        String[] event = new String[2];
        if (slashIndex > SLASH_INDEX_EVENT) {
            event[0] = description.substring(SLASH_INDEX_EVENT, slashIndex - 1).trim();
            event[1] = description.substring(slashIndex + 3).trim();
        } else {
            throw new NoSlashEventException();
        }
        if (event[0].isEmpty() || event[1].isEmpty()) {
            throw new EventEmptyException();
        } else {
            return event;
        }
    }

    private static void printNewEvent(String eventDescription, String eventAt) {
        addEvent(eventDescription, eventAt);
        Duke.printLine();
        System.out.println("\tAdded event: " + eventDescription + " (at: " + eventAt + ')');
        Duke.printLine();
    }

    /**
     * Adds an event task with the description eventDescription
     * and the event date/time eventAt to the list of tasks.
     * @param eventDescription the description of the event task
     * @param eventAt the date/time at which the event happens
     */
    public static void addEvent(String eventDescription, String eventAt) {
        tasks.add(new Event(eventDescription, eventAt));
        taskCount += 1;
    }

    /**
     * Lists all the tasks currently available.
     */
    public static void listTasks() {
        Duke.printLine();
        if (taskCount > 0) {
            System.out.println("\t Here are the tasks in your list:");
            printTasks(tasks);
        } else {
            System.out.println("\tYou have no tasks.");
        }
        Duke.printLine();
    }

    private static void printTasks(ArrayList<Task> tasks) {
        int i = 1;
        for (Task t : tasks) {
            System.out.print('\t');
            System.out.print(i + ". ");
            System.out.print(t.toString() + System.lineSeparator());
            i += 1;
        }
    }

    /**
     * Marks the task of index index as done and prints a message informing the user that
     * the task has been marked as done
     * @param index index of the task to be marked done
     */
  
    public static void markAsDone(int index) {
        try {
            printMarkedTask(index);
        } catch (IndexOutOfBoundsException | IOException | NumberFormatException e) {
            printTaskNotExist();
        }
    }

    private static void printMarkedTask(int index) throws IOException {
        String taskDescription = tasks.get(index).getDescription();
        markTaskAsDone(index);
        Storage.markEntryDone(index);
        Duke.printLine();
        System.out.println("\tNice! You completed this task:");
        System.out.println("\t  [X] " + taskDescription);
        Duke.printLine();
    }

    public static void markTaskAsDone(int index) {
        tasks.get(index).setAsDone();
    }

    /**
     * Deletes the task of index index, and prints a message to inform the user that
     * the task has been deleted.
     * @param index index of the task to be deleted
     */
    public static void deleteTask(int index) {
        try {
            printDeleteTask(index);
        } catch (IndexOutOfBoundsException | IOException | NumberFormatException e) {
            printTaskNotExist();
        }
    }

    private static void printTaskNotExist() {
        Duke.printLine();
        System.out.println("\tThat task doesn't exist.");
        Duke.printLine();
    }

    private static void printDeleteTask(int index) throws IOException {
        String taskDescription = tasks.get(index).toString();
        Duke.printLine();
        System.out.println("\tSeems like you didn't want this task:");
        System.out.println("\t" + taskDescription);
        Duke.printLine();
        tasks.remove(index);
        Storage.deleteEntry(index);
        taskCount -= 1;
    }

    public static void findTasks(String keyword) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        if (!filteredList.isEmpty()) {
            Duke.printLine();
            System.out.println("\tHere are the matching tasks in your list: ");
            printTasks(filteredList);
            Duke.printLine();
        } else {
            Duke.printLine();
            System.out.println("\t That keyword did not turn up any searches.");
            Duke.printLine();
        }
    }
  
    public static void filterDates(String content) {
        String dateString = content.substring(7);
        LocalDate ld = TimeHandler.getDate(dateString);
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t instanceof Deadline || t instanceof Event)
                .filter((t) -> Task.getDate(t).equals(ld))
                .sorted(Comparator.comparing(Task::getTime))
                .collect(Collectors.toList());

        Duke.printLine();
        if (!filteredList.isEmpty()) {
            System.out.println("\tThese are the tasks for " + ld + ": ");
            int i = 1;
            for (Task t : filteredList) {
                System.out.println("\t" + i + ". " + t.toString());
                i += 1;
            }
        } else {
            System.out.println("\tNo tasks set for " + ld + ".");
        }
        Duke.printLine();
    }
}
