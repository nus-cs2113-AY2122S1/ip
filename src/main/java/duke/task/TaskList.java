package duke.task;

import duke.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * A tasklist for all the tasks in the application.
 * Manages all the tasks in the application through its various functions.
 */
public class TaskList {
    static final int INDEX_OF_COMMAND_WORD = 0;
    static final int INDEX_OF_DESCRIPTION = 1;
    static final int INDEX_OF_TIME = 2;

    private ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;
    private int numberOfTasks;

    /**
     * Constructor to make a new TaskList from the data in the file
     * in the specified filepath.
     *
     * @param data The data from the file in the specified filepath.
     * @param storage To initialise the same storage of the application in the class.
     */
    public TaskList(ArrayList<String> data, Storage storage) {
        tasks = new ArrayList<>();
        ui = new Ui();
        numberOfTasks = 0;
        this.storage = storage;
        int i = 0;
        while (i < data.size()) {
            numberOfTasks++;
            switch (data.get(i)) {
            case "T":
                tasks.add(new Todo(data.get(i + 2)));
                if (data.get(i + 1).equals("true")) {
                    tasks.get(numberOfTasks - 1).setDone(); //else leave isDone as false
                }
                i += 3;
                break;
            case "D":
                tasks.add(new Deadline(data.get(i + 2), data.get(i + 3)));
                if (data.get(i + 1).equals("true")) {
                    tasks.get(numberOfTasks - 1).setDone(); //else leave isDone as false
                }
                i += 4;
                break;
            case "E":
                tasks.add(new Event(data.get(i + 2), data.get(i + 3)));
                if (data.get(i + 1).equals("true")) {
                    tasks.get(numberOfTasks - 1).setDone(); //else leave isDone as false
                }
                i += 4;
                break;
            //no default case here because the file follows a certain structure which will
            //go into all the 3 cases above
            }
        }
    }

    /**
     * Adds a new task into the tasklist and prints it out.
     * Saves the edited tasklist into the file in the specified filepath.
     *
     * @param parsedFullCommand String array containing the parsed command in the correct format
     *                          after going through the Parser.
     */
    public void addTask(String[] parsedFullCommand) {
        if (parsedFullCommand[INDEX_OF_COMMAND_WORD].equals("todo")) {
            tasks.add(new Todo(parsedFullCommand[1]));
        } else if (parsedFullCommand[INDEX_OF_COMMAND_WORD].equals("deadline")) {
            tasks.add(new Deadline(parsedFullCommand[INDEX_OF_DESCRIPTION],
                    parsedFullCommand[INDEX_OF_TIME]));
        } else if (parsedFullCommand[INDEX_OF_COMMAND_WORD].equalsIgnoreCase("event")) {
            tasks.add(new Event(parsedFullCommand[INDEX_OF_DESCRIPTION],
                    parsedFullCommand[INDEX_OF_TIME]));
        }
        numberOfTasks++;
        ui.printAddedTasked(tasks, numberOfTasks);
        storage.save(tasks, numberOfTasks);
    }

    /**
     * Intermediate function to call the listAllTasks function in the Ui class.
     */
    public void listAllTasks() {
        ui.listAllTasks(tasks, numberOfTasks);
    }

    /**
     * Marks the task at the index specified by the user as done.
     * Saves the edited tasklist into the file in the specified filepath.
     *
     * @param index Index of the task specified by the user to be marked as done.
     */
    public void markAsDone(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            tasks.get(indexInteger - 1).setDone();
            ui.printMarkAsDone(tasks, indexInteger);
        } catch (NumberFormatException e) {
            ui.showLoadingError(e);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.showLoadingError(e);
        }
        storage.save(tasks, numberOfTasks);
    }

    /**
     * Deletes the task at the index specified by the user.
     * Saves the edited tasklist into the file in the specified filepath.
     *
     * @param index Index of the task specified by the user to be deleted.
     */
    public void deleteTask(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            String description = tasks.get(indexInteger - 1).toString();
            tasks.remove(indexInteger - 1);
            numberOfTasks--;
            ui.showDeletedTask(description, numberOfTasks);
        } catch (NumberFormatException e) {
            ui.showLoadingError(e);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.showLoadingError(e);
        }
        storage.save(tasks, numberOfTasks);
    }

    /**
     * Filters out the tasks with the keyword in their description
     * specified by the user and lists them out.
     *
     * @param filterWord The keyword specified by the user to filter
     *                   the description of the tasks.
     */
    public void filterTasksByString(String filterWord) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().toLowerCase().contains(filterWord.toLowerCase()))
                .collect(Collectors.toList());
        ui.listMatchingTasks(filteredList);
    }
}
