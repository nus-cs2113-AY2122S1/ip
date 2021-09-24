package duke.task;

import duke.ui.Ui;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void printList() {
        ui.printList(tasks);
    }

    public void addTodo(String userCommand) {
        //add exception
        try {
            int contentStart = 5;
            String description = userCommand.substring(contentStart);
            tasks.add(new Todo(description));
            ui.printTotalNumOfTasks(tasks);
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            ui.printSign();
        }
    }

    public void addEvent(String userCommand) {
        //add exception
        try {
            int contentStart = 6;
            int contentEnd = userCommand.indexOf("/at") - 1;
            int atStart = userCommand.indexOf("/at") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String at = userCommand.substring(atStart);
            tasks.add(new Event(description, at));
            ui.printTotalNumOfTasks(tasks);
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            ui.printSign();
        }
    }

    public void addDeadline(String userCommand) {
        //add exception
        try {
            int contentStart = 9;
            int contentEnd = userCommand.indexOf("/by") - 1;
            int byStart = userCommand.indexOf("/by") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String by = userCommand.substring(byStart);
            tasks.add(new Deadline(description, by));
            ui.printTotalNumOfTasks(tasks);
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            ui.printSign();
        }
    }

    public void deleteTask(String userCommand) {
        try {
            int contentStart = 7;
            int id = Integer.parseInt(userCommand.substring(contentStart));
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(id - 1).toString());
            tasks.remove(id - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The index of the task to be deleted cannot be empty.");
            ui.printSign();
        }
    }

    public void taskDone(String userCommand) {
        // Mark the relevant task as "done", and print out a line indicates that the task is marked as done
        try {
            int id = Integer.parseInt(userCommand.substring(5));
            tasks.get(id - 1).markAsDone();
            ui.printSign();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(id - 1).toString());
            ui.printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The index of the task to be marked as done cannot be empty.");
            ui.printSign();
        }
    }
}
