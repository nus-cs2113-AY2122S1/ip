package duke;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class Duke {
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;
    //private Ui ui;
    private Storage storage;
    //private TaskList tasks;

    public Duke (String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser();
    }



    /*ATTRIBUTES*/

    //private TaskList tasks = new TaskList();
    //private static ArrayList<Task> tasks = new ArrayList<>();
    //private static String filePath = "data/duke.txt";
    //private static File taskText = new File(filePath);


    /*METHODS*/


    /*MAIN*/

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke ("data/duke.txt");

        duke.ui.printStartMessage();

        try {
            tasks = duke.storage.loadTasksFromFile();
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
                tasks.printTaskList(); //print duke.task list

            } else if (input.startsWith("done ")) {
                int index = parser.getTaskNumber(input);

                tasks.setDoneTask(tasks.getTask(index - 1), index - 1, tasks);

            } else if (input.startsWith("delete ")){
                int index = parser.getTaskNumber(input);

                ui.deleteTaskFromList(tasks.getTask(index - 1), tasks);
                //tasks.setDoneTask(tasks.getTask(index - 1), index - 1);

            } else if (input.startsWith("todo ")) { //duke.task is a todo
                String description = input.substring(4).trim();

                if (description.isEmpty()) {
                    error = "toDoDescriptionEmptyException";
                    throw new DukeException(error);

                } else {
                    t = new Todo(description);
                    ui.addTaskToList(t, tasks);
                }

            } else if (input.startsWith("deadline ")) { //duke.task is a deadline
                int slash = input.indexOf("/");
                String description = input.substring(9, slash);
                String by = input.substring(slash + 4);
                t = new Deadline(description, by);

                ui.addTaskToList(t, tasks);

            } else if (input.startsWith("event ")) { //duke.task is an event
                int slash = input.indexOf("/");
                String description = input.substring(6, slash);
                String at = input.substring(slash + 4);
                t = new Event(description, at);

                ui.addTaskToList(t, tasks);

            } else if (input.startsWith("find ")){
                String key = input.substring(5);
                tasks.searchList(tasks, key);
            }

            else {//basic duke.task
//                t = new duke.task.Task(input);
//                addTask(t);

                error = "unrecognisedTask";
                throw new DukeException(error);
            }
        }
    }
}

