package duke.processes;

import duke.exceptions.DoneUndoException;
import duke.exceptions.RemoveException;
import duke.exceptions.TaskException;
import duke.exceptions.TimeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    public static ArrayList<Task> taskList;

    public Manager(ArrayList<Task> taskList) {
        Manager.taskList = taskList;
    }

    public static void help() {
        System.out.println("Below are the list of acceptable commands"
                + System.lineSeparator() + System.lineSeparator()
                + "add todo -> add a todo task to the list [add todo todo_name]"
                + System.lineSeparator()
                + "add event -> adds an event task to the list [add event event_name " +
                "/at event_date"
                + System.lineSeparator()
                + "add deadline -> adds a deadline task to the list " +
                "[add deadline deadline_name /by deadline_date"
                + System.lineSeparator()
                + "remove -> removes a task [remove task_number]"
                + System.lineSeparator()
                + "done -> marks a task as complete [done task_number]"
                + System.lineSeparator()
                + "undo -> marks a task as undone [undo task_number]"
                + System.lineSeparator()
                + "list -> Display the list of tasks"
                + System.lineSeparator()
                + "echo -> echo mode activated, where system echos your input, " +
                "type [exit] to leave mode"
                + System.lineSeparator()
                + "bye -> kill ikaros");
        Interface.lineBreak();
    }

    public static void manageInstructions(String[] command, String response) {
        try {
            if (command[0].equalsIgnoreCase("add")) {
                manageTaskType(command, response);
            } else {
                remove(command);
            }
        } catch (RemoveException e) {
            System.out.println(e.getMessage());
        } finally {
            Interface.lineBreak();
        }
    }

    public static void manageDoUndo(String[] command) {
        try {
            if (command[0].equalsIgnoreCase("done")) {
                ProcessTasks.done(command);
            } else if (command[0].equalsIgnoreCase("undo")) {
                ProcessTasks.undo(command);
            }
        } catch (DoneUndoException e) {
            System.out.println(e.getMessage());
        } finally {
            Interface.lineBreak();
        }
    }

    private static void manageTaskType(String[] command, String response) {
        String TaskType = command[1];
        try {
            if ((command.length == 2) || (command[2].isEmpty())) {
                throw new TaskException();
            } else if (command[1].equalsIgnoreCase("event")) {
                ProcessTasks.Event(response);
            } else if (command[1].equalsIgnoreCase("todo")) {
                ProcessTasks.toDo(response);
            } else if (command[1].equalsIgnoreCase("deadline")) {
                ProcessTasks.deadLine(response);
            }
        } catch (TaskException e) {
            System.out.println("please specify " + TaskType + " to add!");
        } catch (TimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(i + ".[" + task.getTaskID() + "]" +
                    "[" + task.getStatusIcon() +
                    "] " + task.description + task.getDate());
            i++;
        }
        Interface.lineBreak();
    }

    public static void remove(String[] command) throws RemoveException {
        try {
            int i = Integer.parseInt(command[1]);
            if (i > taskList.size()) {
                throw new RemoveException("That task does not exist");
            }
            System.out.println("Understood, I have removed "
                    + taskList.get(i - 1).description);
            taskList.remove(i - 1);
            System.out.println("Tasks remaining = " + taskList.size());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please specify a task [use numbers to indicate the task]");
        }
    }

    public static void echo() {
        System.out.println("Life is a mirror and will reflect back to "
                + "the thinker what\nhe thinks into it, echoing commencing");
        Interface.lineBreak();
        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        Interface.lineBreak();
        while (!response.equals("exit")) {
            System.out.println(response);
            Interface.lineBreak();
            response = in.nextLine();
            Interface.lineBreak();
        }
    }
}
