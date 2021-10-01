package duke;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * <h1>Duke Task Manager</h1>
 * Implements a task manager which can track 3 types of tasks.
 *
 * It contains a {@code Ui} class to deal with user interactions,
 * a {@code TaskList} class to keep track of all tasks,
 * a {@code Storage} class to save and load the data, and
 * a {@code Parser} class to interpret user input and carry out instructions.
 */

public class Duke {
    /*ATTRIBUTES*/
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;


    /**
     * Constructor for {@code Duke} class
     *
     * @param filePath Indicates the path to a file in the hard disk where tasks are stored to or loaded from.
     */
    public Duke (String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();
    }


    /**
     * Main method where programe is run
     *
     * @param args Unused
     * @throws DukeException Errors specific to Duke
     * @throws IOException Errors regarding external text file
     */
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke ("duke.txt");

        duke.ui.printStartMessage();

        try {
            duke.tasks = duke.storage.loadTasksFromFile();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        String input, error;
        Scanner in = new Scanner(System.in);

        Task t;
        boolean isBye = false;

        while (!isBye) {
            input = in.nextLine();

            if (input.equalsIgnoreCase(("bye"))) { //end program
                isBye = true;
                duke.ui.printByeMessage();

            } else if (input.equalsIgnoreCase("list")) {
                duke.tasks.printTaskList(); //print duke.task list

            } else if (input.startsWith("done ")) {
                int index = duke.parser.getTaskNumber(input);

                duke.tasks.setDoneTask(index - 1, duke.tasks);

            } else if (input.startsWith("delete ")){
                int index = duke.parser.getTaskNumber(input);

                duke.ui.deleteTaskFromList(duke.tasks.getTask(index - 1), duke.tasks);

            } else if (input.startsWith("todo ")) { //duke.task is a todo
                String description = input.substring(4).trim();

                if (description.isEmpty()) {
                    error = "toDoDescriptionEmptyException";
                    throw new DukeException(error);

                } else {
                    t = new Todo(description);
                    duke.ui.addTaskToList(t, duke.tasks);
                }

            } else if (input.startsWith("deadline ")) { //duke.task is a deadline
                String description = duke.parser.getDescription(input, 9);
                String by = duke.parser.getDateTime(input);
                t = new Deadline(description, by);

                duke.ui.addTaskToList(t, duke.tasks);

            } else if (input.startsWith("event ")) { //duke.task is an event
                String description = duke.parser.getDescription(input, 6);
                String at = duke.parser.getDateTime(input);
                t = new Event(description, at);

                duke.ui.addTaskToList(t, duke.tasks);

            } else if (input.startsWith("find ")){
                String key = duke.parser.getSearchKey(input);
                duke.tasks.searchList(duke.tasks, key);
            }

            else {
                error = "unrecognisedTask";
                throw new DukeException(error);
            }
        }
    }
}

