package duke.processes.commands;

import duke.Duke;
import duke.exceptions.TaskException;
import duke.exceptions.TimeException;
import duke.processes.tasks.Task;
import duke.processes.tasks.Deadlines;
import duke.processes.tasks.Event;
import duke.processes.tasks.ToDo;

public class AddCommand extends Command {
    public static final int TIME_COMMAND = 4;
    public static final int DEADLINE_LENGTH = 13;
    public static final int EVENT_LENGTH = 10;
    public static final int TODO_LENGTH = 9;

    public static String taskDescription;
    public static String taskTime;
    public static String taskType;
    public static int error;

    public AddCommand(String[] command, String response) {
        error = 0;
        try {
            if ((command.length == 1) || (command[1].isEmpty())) {
                throw new TaskException("please specify what to add");
            }
            taskType = command[1];
            if (!(taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event")
                    || taskType.equalsIgnoreCase("todo"))) {
                throw new TaskException("Invalid Task entered please only add (deadline, event, todo)");
            }
            if ((command.length == 2) || (command[2].isEmpty())) {
                throw new TaskException("please specify task to add");
            }
            if (command[1].equalsIgnoreCase("todo")) {
                prepareTodo(response);
            } else {
                if (command[1].equalsIgnoreCase("deadline")) {
                    prepareDeadline(response);
                } else if (command[1].equalsIgnoreCase("event")){
                    prepareEvent(response);
                }
            }
        } catch (TaskException | TimeException e ) {
            System.out.println(e.getMessage());
            error = 1;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("please check format. Use help function for guide!");
            error = 1;
        }
    }

    private void prepareTodo(String response) {
        taskTime = " ";
        taskDescription = response.substring(TODO_LENGTH);
    }

    private void prepareEvent(String response) throws TimeException {
        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? " +
                    "[indicate by adding: /at-your_timing]");
        }
        taskDescription = response.substring(EVENT_LENGTH, response.indexOf("/") - 1);
        taskTime = " (at: " + response.substring(response.indexOf("/") + TIME_COMMAND) + ")";
    }

    private void prepareDeadline(String response) throws TimeException {
        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? " +
                    "[indicate by adding: /by-your_timing]");
        }
        taskDescription = response.substring(DEADLINE_LENGTH, response.indexOf("/") - 1);
        taskTime = " (by: " + response.substring(response.indexOf("/") + TIME_COMMAND) + ")";
    }

    private static void addToList(Task task) {
        Duke.taskList.add(task);
    }

    public CommandResult execute() {
        if (error == 0) {
            if (taskType.equalsIgnoreCase("event")) {
                Event event = new Event(taskDescription, taskTime);
                addToList(event);
            } else if (taskType.equalsIgnoreCase("todo")) {
                ToDo task = new ToDo(taskDescription);
                addToList(task);
            } else if (taskType.equalsIgnoreCase("deadline")) {
                Deadlines work = new Deadlines(taskDescription, taskTime);
                addToList(work);
            }

            return new CommandResult("Task added:" + taskDescription +
                    System.lineSeparator() + "Total no. of Tasks = " + (Duke.taskList.size()));
        } else {
            return new CommandResult("No task added");
        }
    }
}
