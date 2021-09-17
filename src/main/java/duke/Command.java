package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;


public class Command {
    public static void executeList() {
        ArrayList<Task> taskList = TaskCollection.getTaskList();
        Messages.printDivider();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
        Messages.printDivider();
    }

    public static void executeDone(ArrayList<String> arguments) {
        try {
            ArrayList<Task> taskList = TaskCollection.getTaskList();
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            taskList.get(taskIndex).setDone();
            Messages.printDivider();
            System.out.println("Nice I've marked this task as done:");
            System.out.println(taskList.get(taskIndex));
            Messages.printDivider();
        } catch (IndexOutOfBoundsException e) {
            Messages.printDivider();
            System.out.println("No such task exists");
            Messages.printDivider();
        } catch (NumberFormatException e) {
            Messages.printDivider();
            System.out.println("Invalid command");
            System.out.println("For information on how to use me try using the help command!");
            Messages.printDivider();
        }
    }

    public static void executeAdd(ArrayList<String> arguments) {
        ArrayList<Task> taskList = TaskCollection.getTaskList();
        try {
            switch (arguments.get(0)) {
                case "todo":
                    taskList.add(new ToDo(arguments.get(1)));
                    break;
                case "deadline":
                    taskList.add(new Deadline(arguments.get(1), arguments.get(2)));
                    break;
                case "event":
                    taskList.add(new Event(arguments.get(1), arguments.get(2)));
                    break;
                default:
            }
            int taskCount = taskList.size();
            Messages.printDivider();
            System.out.println("Got it. I've added this task: ");
            System.out.println(taskList.get(taskCount - 1));
            System.out.println("You now have " + taskCount + " items in the list.");
            Messages.printDivider();

        } catch (IndexOutOfBoundsException e) {
            Messages.printDivider();
            System.out.println("Missing parameters");
            System.out.println("For information on how to use me try using the help command!");
            Messages.printDivider();
        }
    }

    public static void executeAdd(String input) {
        String[] arguments = input.split("\\|");
        ArrayList<Task> taskList = TaskCollection.getTaskList();
        switch (arguments[0]) {
            case "T":
                if(arguments[1].equals("false")) {
                    taskList.add(new ToDo(arguments[2]));
                } else {
                    taskList.add(new ToDo(arguments[2], true));
                }
                break;
            case "D":
                if(arguments[1].equals("false")) {
                    taskList.add(new Deadline(arguments[2], arguments[3]));
                } else {
                    taskList.add(new Deadline(arguments[2], arguments[3], true));
                }
                break;
            case "E":
                if(arguments[1].equals("false")) {
                    taskList.add(new Event(arguments[2], arguments[3]));
                } else {
                    taskList.add(new Event(arguments[2], arguments[3], true));
                }
                break;
            default:
        }

    }

    public static void executeDelete(ArrayList<String> arguments) {
        try {
            ArrayList<Task> taskList = TaskCollection.getTaskList();
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            taskList.get(taskIndex); //Throw exception early
            Messages.printDivider();
            System.out.println("Noted. I've removed this task: ");
            System.out.println(taskList.get(taskIndex));
            taskList.remove(taskIndex);
            System.out.println("You now have " + taskList.size() + " items in the list.");
            Messages.printDivider();
        } catch (IndexOutOfBoundsException e) {
            Messages.printDivider();
            System.out.println("No such task exists");
            Messages.printDivider();
        } catch (NumberFormatException e) {
            Messages.printDivider();
            System.out.println("Invalid command");
            System.out.println("For information on how to use me try using the help command!");
            Messages.printDivider();
        }
    }

}
