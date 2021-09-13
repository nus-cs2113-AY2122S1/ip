import FridayExceptions.EmptyListException;
import FridayExceptions.MissingKeyWordException;
import FridayExceptions.MissingDateException;
import FridayExceptions.EmptyTaskNameException;
import FridayExceptions.InvalidTaskIndexException;
import FridayExceptions.IncompleteCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import enums.Commands;
import enums.Errors;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {
    // array storing all task
    // change to array list
    // private static Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    // task counter to enumerate through task array
    // private static int tasksCounter = 0;

    private static final String FILEPATH = "data/friday.txt";
    /**
     * Main function managing all user inputs until program is terminated
     * via the "bye" command
     */
    public static void manageTasks() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();

            /*
            while (userInput.trim().isEmpty()) {
                userInput = in.nextLine();
            }
             */
            // change to switch case based on enum
            Commands command = InputParser.getCommand(userInput);

            // if bye, exit straight
            if (command == Commands.BYE) {
                return;
            }
            try {
                switch (command) {
                case INVALID:
                    MessagePrinter.invalidCommand();
                    break;
                case LIST:
                    getList();
                    break;
                case TODO:
                    updateList(addToDo(userInput, false));
                    break;
                case DEADLINE:
                    updateList(addDeadline(userInput, false));
                    break;
                case EVENT:
                    updateList(addEvent(userInput, false));
                    break;
                case DELETE:
                    removeTask(userInput);
                    break;
                case DONE:
                    markAsDone(userInput);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                MessagePrinter.outOfBoundsTaskIndex();
            } catch (InvalidTaskIndexException e) {
                MessagePrinter.invalidTaskIndex();
            } catch (EmptyTaskNameException e) {
                MessagePrinter.emptyTaskName();
            } catch (EmptyListException e) {
                MessagePrinter.emptyListMessage();
            } catch (IncompleteCommandException e) {
                MessagePrinter.incompleteCommand();
            } catch (MissingKeyWordException e) {
                MessagePrinter.missingKeyWord(e.getKeyword());
            } catch (MissingDateException e) {
                MessagePrinter.missingDate(e.getType());
            }
        }
    }


    /**
     * Function to scan data from saved file upon initiating Friday
     * Check if fle exists; if it doesn't, create new
     * Works in loading from saved file
     */
    public static void loadData() throws FileNotFoundException {
        // read from file using Scanner
        File data = new File(FILEPATH);
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            // read and parse data into Task array.
            // Data stored in format type | isDone | taskname | date (if exists)
            String[] splitString = s.nextLine().split("\\|");
            boolean isDone = false;
            if (splitString[1].trim().equals("X")) {
                isDone = true;
            }
            try {
                // if splitString only has 3 items; means its a todo
                if (splitString.length == 3) {
                    addToDo(splitString[0] + " " + splitString[2], isDone);
                } else { // means it's a deadline or event
                    if (splitString[0].trim().equals("deadline")) {
                        addDeadline(splitString[0] + " " + splitString[2] + " /by " + splitString[3], isDone);
                    } else {
                        addEvent(splitString[0] + " " + splitString[2] + " /at " + splitString[3], isDone);
                    }
                }
            } catch (EmptyTaskNameException e) {
                MessagePrinter.emptyTaskName();
            } catch (IncompleteCommandException e) {
                MessagePrinter.incompleteCommand();
            } catch (MissingKeyWordException e) {
                MessagePrinter.missingKeyWord(e.getKeyword());
            } catch (MissingDateException e) {
                MessagePrinter.missingDate(e.getType());
            }
        }
    }

    /**
     * @throws IOException
     * Goes through changed list and writes to file (Creates new file each time)
     * DOesnt work; refer to Irvin method
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void updateList(Task currTask) {
        String taskName = currTask.getTaskName();
        String isDoneSymbol = currTask.isDone() ? "X" : "O";
        // check prefix
        if (currTask.getPrefix().equals("[T]")) {
            try {
                appendToFile("todo | " + isDoneSymbol + " | " + taskName + System.lineSeparator());
            } catch (IOException e) {
                e.getMessage();
            }
        } else if (currTask.getPrefix().equals("[D]")) {
            try {
                Deadline deadlineTask = (Deadline) currTask;
                String deadline = deadlineTask.getDeadline();
                appendToFile("deadline | " + isDoneSymbol + " | " + taskName + " | " + deadline + System.lineSeparator());
            } catch (IOException e) {
                e.getMessage();
            }
        } else {
            try {
                Event eventTask = (Event) currTask;
                String eventDate = eventTask.getEventDate();
                appendToFile("event | " + isDoneSymbol + " | " + taskName + " | " + eventDate + System.lineSeparator());
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Rewrite list; Delete old list and update based on current Task array
     */
    private static void rewriteList() {
        // Clear list
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }

        // go through current task list and update
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            updateList(task);
        }
    }

    /**
     *
     * @throws EmptyListException
     */

    // function to update Task array on adding and deleting task
    private static void getList() throws EmptyListException{
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        // Message Printer
        MessagePrinter.printList(tasks);
    }

    private static void addTask(Task newTask, String taskName) {
        // add to array list
        tasks.add(newTask);
        // Message Printer
        MessagePrinter.addedTask(taskName);
    }

    // catch exception for not enough parameters
    private static Task addToDo(String userInput, boolean isDone) throws EmptyTaskNameException {
        String[] splitString = userInput.split("\\s");
        if (splitString.length <= 1) {
            throw new EmptyTaskNameException();
        }
        // get taskName
        String taskName = userInput.substring(userInput.indexOf(" ")).trim();
        Todo newTodo = new Todo(isDone, taskName);
        addTask(newTodo, taskName);
        return newTodo;
    }

    private static Task addDeadline(String userInput, boolean isDone) throws
            IncompleteCommandException,
            EmptyTaskNameException,
            MissingKeyWordException,
            MissingDateException {
        // check validity of deadline string
        Errors checkDeadline = InputParser.checkDeadlineCommand(userInput);
        switch (checkDeadline) {
        case INCOMPLETE_COMMAND:
            throw new IncompleteCommandException();
        case MISSING_BY:
            throw new MissingKeyWordException("by");
        case MISSING_TASK:
            throw new EmptyTaskNameException();
        case MISSING_DEADLINE:
            throw new MissingDateException("deadline");
        }
        // get taskName
        String taskName = InputParser.getTaskName(userInput);
        // get deadline; catch exception for no deadline
        String deadline = InputParser.getDate(userInput);
        Deadline newDeadline = new Deadline(isDone, taskName, deadline);
        addTask(newDeadline, taskName);
        return newDeadline;
    }

    private static Task addEvent(String userInput, boolean isDone) throws
            IncompleteCommandException,
            EmptyTaskNameException,
            MissingKeyWordException,
            MissingDateException {
        // check validity of event string
        Errors checkEvent = InputParser.checkEventCommand(userInput);
        switch (checkEvent) {
        case INCOMPLETE_COMMAND:
            throw new IncompleteCommandException();
        case MISSING_AT:
            throw new MissingKeyWordException("at");
        case MISSING_TASK:
            throw new EmptyTaskNameException();
        case MISSING_EVENT:
            throw new MissingDateException("event");
        }
        // get taskName
        String taskName = InputParser.getTaskName(userInput);
        // get event date; catch exception for no event date
        String eventDate = InputParser.getDate(userInput);
        Event newEvent = new Event(isDone, taskName, eventDate);
        addTask(newEvent, taskName);
        return newEvent;
    }

    // function to remove a task from the list of tasks
    private static void removeTask(String userInput) throws IndexOutOfBoundsException, InvalidTaskIndexException {
        int taskIndex = InputParser.getTaskIndex(userInput);

        // catch exception for task being out of bounds
        if (taskIndex < 0 || taskIndex > 99) {
            throw new IndexOutOfBoundsException();
        }

        // change task to done
        Task currTask = tasks.get(taskIndex);
        if (currTask == null) {
            throw new InvalidTaskIndexException();
        }
        String taskName = currTask.getTaskName();
        tasks.remove(currTask);
        MessagePrinter.removeTask(taskName, tasks.size());

    }

    private static void markAsDone(String userInput) throws IndexOutOfBoundsException, InvalidTaskIndexException {
        // get index of task to chang
        int taskIndex = InputParser.getTaskIndex(userInput);

        // catch exception for task being out of bounds
        if (taskIndex < 0 || taskIndex > 99) {
            throw new IndexOutOfBoundsException();
        }

        // change task to done
        Task currTask = tasks.get(taskIndex);
        if (currTask == null) {
            throw new InvalidTaskIndexException();
        }
        currTask.setDone(true);
        MessagePrinter.taskMarkedAsDone(currTask);
        rewriteList();
    }

}
