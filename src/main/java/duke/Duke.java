package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class Duke {
    /*ATTRIBUTES*/
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;



    /*METHODS*/
    public Duke (String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();
    }


    /*MAIN*/

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

                duke.tasks.setDoneTask(duke.tasks.getTask(index - 1), index - 1, duke.tasks);

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

