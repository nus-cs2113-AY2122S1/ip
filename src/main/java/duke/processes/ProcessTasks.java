package duke.processes;

import duke.exceptions.DoneUndoException;
import duke.exceptions.TimeException;
import duke.processes.tasks.Deadlines;
import duke.processes.tasks.Event;
import duke.processes.tasks.ToDo;

public class ProcessTasks {
    public static final int TIME_COMMAND = 4;
    public static final int DEADLINE_LENGTH = 13;
    public static final int EVENT_LENGTH = 10;
    public static final int TODO_LENGTH = 9;

    protected static void Event(String response)
            throws TimeException {
        String timing = " (at: " + response.substring(response.indexOf("/") + TIME_COMMAND)
                + ")";

        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? " +
                    "[indicate by adding: /at your_timing]");
        }
        Event event = new Event(response.substring
                (EVENT_LENGTH, response.indexOf("/") - 1), timing);
        addToList(event);

    }

    protected static void deadLine(String response)
            throws TimeException {
        String timing = " (by: " + response.substring(response.indexOf("/") + TIME_COMMAND)
                + ")";

        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it due? " +
                    "[indicate by adding: /by your_timing]");
        }
        Deadlines work = new Deadlines(response.substring
                (DEADLINE_LENGTH, response.indexOf("/") - 1),
                timing);
        addToList(work);
    }

    protected static void toDo(String response) {
        ToDo task = new ToDo(response.substring(TODO_LENGTH));
        addToList(task);
    }

    protected static void undo(String[] command) throws DoneUndoException {
        try {
            String[] tasks = command[1].split(",");
            int i;
            for (String j : tasks) {
                i = Integer.parseInt(j) - 1;
                if (!Manager.taskList.get(i).isDone) {
                    throw new DoneUndoException("Task is currently incomplete");
                }
                Manager.taskList.get(i).undo();
                System.out.println("Ok i have changed the status for the specific task:\n ["
                        + Manager.taskList.get(i).getStatusIcon() + "] "
                        + Manager.taskList.get(i).getDescription());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That task does not exist");
        }
    }

    protected static void done(String[] command) throws DoneUndoException {
        try {
            String[] tasks = command[1].split(",");
            int j;
            for (String i : tasks) {
                j = Integer.parseInt(i) - 1;
                if (Manager.taskList.get(j).isDone) {
                    throw new DoneUndoException("Task is already completed");
                }
                Manager.taskList.get(j).markAsDone();
                System.out.println("Nice! i have marked this task as done:\n ["
                        + Manager.taskList.get(j).getStatusIcon() + "] "
                        + Manager.taskList.get(j).getDescription());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That task does not exist");
        }


    }

    private static void addToList(Task task) {
        Manager.taskList.add(task);
        System.out.println("Task added: " + task);
        System.out.println("Total no. of Tasks = " + (Manager.taskList.size()));
    }
}
