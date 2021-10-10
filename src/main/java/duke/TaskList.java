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
    protected Storage storage;

    /**
     * Instantiates the TaskList object and loads all the tasks from the text file into the ArrayList.
     *
     * @param loadedTasks the ArrayList of tasks from the text file
     * @param storage the storage object
     */
    public TaskList(ArrayList<String> loadedTasks, Storage storage) {
        this.commands = new ArrayList<>();
        this.positionCheck = 0;
        this.loadFlag = 0;
        this.storage = storage;
        String[] taskFromFile;
        String[] taskInput;
        int taskNumber = 0;
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
                taskNumber += 1;
            }
            this.loadFlag = 1;
        } catch (DukeException e) {
            Ui.saySorry();
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        } catch (DateTimeParseException e) {
            Ui.printDateTimeExceptionMessage();
        }
    }

    public TaskList(Storage storage) {
        this.commands = new ArrayList<>();
        this.positionCheck = 0;
        this.loadFlag = 1;
        this.storage = storage;
    }

    /**
     * To add a deadline task to the ArrayList of tasks.
     *
     * @param input the array of strings that represent the user's command
     * @param length the length of input
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     * @throws DateTimeParseException if the date or time is not written in the proper format
     */
    public void addDeadline(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
        String description = "";
        String by = "";
        int byPosition = 0;
        int byLength = 2;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/by")) && (i != 1) && (i != (length - byLength))) {
                description = input[1];
                by = input[i + 1];
                byPosition = i;
                break;
            }
        }
        if (byPosition != 0) {
            for (int j = 2; j < byPosition; j++) {
                description = description.concat(" " + input[j]);
            }
            for (int k = byPosition + 2; k < length; k++) {
                by = by.concat(" " + input[k]);
            }
            commands.add(new Deadline(description, by));
            if (loadFlag == 1) {
                Ui.sayTaskAdded();
                System.out.println(commands.get(positionCheck));
                storage.saveNewTask(input);
            }
            positionCheck += 1;
            return;
        }
        throw new DukeException(Ui.DEADLINE_ERROR);
    }

    /**
     * To add an event task to the ArrayList of tasks.
     *
     * @param input the array of strings that represent the user's command
     * @param length the length of input
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     * @throws DateTimeParseException if the date or time is not written in the proper format
     */
    public void addEvent(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
        String description = "";
        String at = "";
        int atPosition = 0;
        int atLength = 2;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/at")) && (i != 1) && (i != (length - atLength))) {
                description = input[1];
                at = input[i + 1];
                atPosition = i;
            }
        }
        if (atPosition != 0) {
            for (int j = 2; j < atPosition; j++) {
                description = description.concat(" " + input[j]);
            }
            for (int k = atPosition + 2; k < length; k++) {
                at = at.concat(" " + input[k]);
            }
            commands.add(new Event(description, at));
            if (loadFlag == 1) {
                Ui.sayTaskAdded();
                System.out.println(commands.get(positionCheck));
                storage.saveNewTask(input);
            }
            positionCheck += 1;
            return;
        }
        throw new DukeException(Ui.EVENT_ERROR);
    }

    /**
     * To add a todo task to the ArrayList of tasks.
     *
     * @param input the array of strings that represent the user's command
     * @param length the length of input
     * @throws DukeException if the user's command does not fit the requirements to be considered a legal command
     * @throws IOException if there is any issue with the input or output
     */
    public void addTodo(String[] input, int length) throws DukeException, IOException {
        if (length == 1) {
            throw new DukeException(Ui.TODO_ERROR);
        } else {
            String description = input[1];
            for (int i = 2 ; i < length ; i++) {
                description = description.concat(" " + input[i]);
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
        Ui.sayLoading();
        int i = 1;
        for (Task num : commands) {
            System.out.println(i + ". " + num);
            i += 1;
        }
    }

    /**
     * Prints all events and deadlines in the ArrayList of tasks that occur on a specified date.
     *
     * @param dateString the specified date
     */
    public void printListForFindingDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        Ui.sayLoadingForDate(date);
        int i = 1;
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
            Ui.sayCannotFindDate(dateString);
        }
    }

    /**
     * Prints all tasks in the ArrayList of tasks that contain a keyword
     *
     * @param keyword the keyword to use filter out tasks
     */
    public void printListForFindingTask(String keyword) {
        Ui.sayLoadingForKeyword(keyword);
        int i = 1;
        for (Task num : commands) {
            if ((num.description).contains(keyword)) {
                System.out.println(i + ". " + num);
                i += 1;
            }
        }
        if (i == 1) {
            Ui.sayCannotFindKeyword(keyword);
        }
    }

    /**
     * To mark a task as done.
     *
     * @param doneTaskNumber the position in the ArrayList of tasks of the task to mark as done
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
     * @param doneTaskNumber the position in the ArrayList of tasks of the task to delete
     * @throws IOException if there is any issue with the input or output
     */
    public void deleteTask(int doneTaskNumber) throws IOException {
        Ui.sayGoodbyeTask();
        System.out.println((doneTaskNumber+1) + ". " + commands.get(doneTaskNumber));
        commands.remove(commands.get(doneTaskNumber));
        positionCheck -= 1;
        Ui.sayTaskCount(positionCheck);
        storage.saveAllTasks(commands);
    }
}
