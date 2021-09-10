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

import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {
    // array storing all task
    // change to array list
    // private static Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    // task counter to enumerate through task array
    // private static int tasksCounter = 0;

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
                    addToDo(userInput);
                    break;
                case DEADLINE:
                    addDeadline(userInput);
                    break;
                case EVENT:
                    addEvent(userInput);
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
                MessagePrinter.missingKeyWord(e.getkeyWord());
            } catch (MissingDateException e) {
                MessagePrinter.missingDate(e.getType());
            }
        }
    }

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
    private static void addToDo(String userInput) throws EmptyTaskNameException {
        String[] splitString = userInput.split("\\s");
        if (splitString.length <= 1) {
            throw new EmptyTaskNameException();
        }
        // get taskName
        String taskName = userInput.substring(userInput.indexOf(" ")).trim();
        Todo newTodo = new Todo(false, taskName);
        addTask(newTodo, taskName);
    }

    private static void addDeadline(String userInput) throws
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
        Deadline newDeadline = new Deadline(false, taskName, deadline);
        addTask(newDeadline, taskName);
    }

    private static void addEvent(String userInput) throws
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
        Event newEvent = new Event(false, taskName, eventDate);
        addTask(newEvent, taskName);
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
    }

}
