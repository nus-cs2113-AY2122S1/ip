package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * To create an ArrayList to represent the user's tasks and to perform operations on this list.
 */
public class TaskList {

    protected ArrayList<Task> commands;
    protected int positionCheck;
    protected int loadFlag;
    protected int taskNumber;
    protected Storage storage;


    public TaskList(ArrayList<String> loadedTasks, Storage storage) {
        this.commands = new ArrayList<>();
        this.positionCheck = 0;
        this.loadFlag = 0;
        this.taskNumber = 0;
        this.storage = storage;
        String[] taskFromFile;
        String[] taskInput;
        try {
            for (String task : loadedTasks) {
                taskFromFile = task.split(" \\| ");
                taskInput = (taskFromFile[0]).split(" ");
                switch (taskInput[0]) {
                case "deadline":
                    addDeadline(taskInput, taskInput.length);
                    break;
                case "event":
                    addEvent(taskInput, taskInput.length);
                    break;
                case "todo":
                    addTodo(taskInput, taskInput.length);
                default:
                }
                if (taskFromFile[1].equals("1")) {
                    markDone(taskNumber);
                }
                this.taskNumber += 1;
            }
            this.loadFlag = 1;
        } catch (DukeException e) {
            //Catching DukeException errors
        } catch (IOException e) {
            System.out.print("There is an error in your input master! Please check it out!");
        } catch (DateTimeParseException e) {
            System.out.println("Correct date format please!");
        }
    }

    /**
     * To add a deadline task to the ArrayList of tasks.
     *
     * @param input The array of strings that represent the user's command
     * @param length The length of input
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     * @throws DateTimeParseException if the date or time is not written in the proper format
     */
    public void addDeadline(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
        String description;
        String by;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/by")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                by = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    by += (" " + input[k]);
                }
                commands.add(new Deadline(description,by));
                if (loadFlag == 1) {
                    Ui.sayTaskAdded();
                    System.out.println(commands.get(positionCheck));
                    storage.saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(Ui.DEADLINE_ERROR);
    }

    /**
     * To add an event task to the ArrayList of tasks.
     *
     * @param input The array of strings that represent the user's command
     * @param length The length of input
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     * @throws DateTimeParseException if the date or time is not written in the proper format
     */
    public void addEvent(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
        String description;
        String at;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/at")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                at = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    at += (" " + input[k]);
                }
                commands.add( new Event(description,at) );
                if (loadFlag == 1) {
                    Ui.sayTaskAdded();
                    System.out.println(commands.get(positionCheck));
                    storage.saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(Ui.EVENT_ERROR);
    }

    /**
     * To add a todo task to the ArrayList of tasks.
     *
     * @param input The array of strings that represent the user's command
     * @param length The length of input
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     */
    public void addTodo(String[] input, int length) throws DukeException, IOException {
        if (length == 1) {
            throw new DukeException(Ui.TODO_ERROR);
        } else {
            String description = input[1];
            for (int i = 2 ; i < length ; i++) {
                description += (" " + input[i]);
            }
            commands.add( new Todo(description) );
            if (loadFlag == 1) {
                Ui.sayTaskAdded();
                System.out.println(commands.get(positionCheck));
                storage.saveNewTask(input);
            }
            positionCheck += 1;
        }
    }

    /** Prints all tasks in the ArrayList of tasks when the user keys in "list" */
    public void printList() {
        Ui.sayLoadingList();
        int i = 1;
        for (Task num : commands) {
            System.out.println(i + ". " + num);
            i += 1;
        }
    }

    /**
     * Prints all events and deadlines in the ArrayList of tasks that occur on a specified date.
     *
     * @param dateString The specified date
     */
    public void printListForFindingDate(String dateString) {
        Ui.sayLoadingList();
        System.out.println("Generating all the tasks that contain \"" + dateString + "\"...");
        int i = 1;
        LocalDate date = LocalDate.parse(dateString);
        for (Task num : commands) {
            if (num instanceof Event) {
                if (((Event) num).at.contains(dateString)) {
                    System.out.println(i + ". " + num);
                    i += 1;
                }
            } else if (num instanceof Deadline) {
                if (((Deadline) num).by.contains(dateString)) {
                    System.out.println(i + ". " + num);
                    i += 1;
                }
            }
        }
        if (i == 1) {
            System.out.println("There are no tasks that occur on \"" + dateString + "\" master. My apologies!");
        }
    }

    /**
     * Prints all tasks in the ArrayList of tasks that contain a keyword
     *
     * @param keyword The keyword to use filter out tasks
     */
    public void printListForFindingTask(String keyword) {
        Ui.sayLoadingList();
        System.out.println("Generating all the tasks that contain \"" + keyword + "\"...");
        int i = 1;
        for (Task num : commands) {
            if ((num.description).contains(keyword)) {
                System.out.println(i + ". " + num);
                i += 1;
            }
        }
        if (i == 1) {
            System.out.println("Unfortunately, there are no tasks that contain \"" + keyword + "\" master. My apologies!");
        }
    }

    /**
     * To mark a task as done.
     *
     * @param doneTaskNumber The position in the ArrayList of tasks of the task to mark as done
     * @throws IOException if there is any issue with the input or output
     */
    public void markDone(int doneTaskNumber) throws IOException {
        (commands.get(doneTaskNumber)).markAsDone();
        if (loadFlag == 1) {
            Ui.sayMarkedDone();
            System.out.println((doneTaskNumber + 1) + ". " + commands.get(doneTaskNumber));
            storage.saveAllTasks(commands);
        }
    }

    /**
     * To delete a task.
     *
     * @param doneTaskNumber The position in the ArrayList of tasks of the task to delete
     * @throws IOException if there is any issue with the input or output
     */
    public void deleteTask(int doneTaskNumber) throws IOException {
        Ui.sayGoodbyeTask();
        System.out.println((doneTaskNumber+1) + ". " + commands.get(doneTaskNumber));
        commands.remove(commands.get(doneTaskNumber));
        positionCheck -= 1;
        System.out.println("Goodbye Task, may the force be with you. You have " + positionCheck + " task(s) left Master");
        storage.saveAllTasks(commands);
    }
}
