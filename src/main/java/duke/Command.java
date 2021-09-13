package duke;

import duke.exception.DukeInvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;


public class Command {
    public static void executeList(ArrayList<Task> taskList) {
        Messages.printDivider();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
        Messages.printDivider();
    }

    public static void executeDone(String input, ArrayList<Task> taskList) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
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
            Messages.printDivider();
        }
    }

    public static void executeAdd(String input, ArrayList<Task> taskList) throws DukeInvalidInputException {
        String taskType = input.split(" ")[0];
        int commandIndex = input.indexOf(' ');
        int timeIndex = input.indexOf('/');
        if(commandIndex == -1)
            throw new DukeInvalidInputException();
        switch (taskType) {
            case "todo":
                taskList.add(new ToDo(input.substring(commandIndex + 1)));
                break;
            case "deadline":
                taskList.add(new Deadline(input.substring(commandIndex + 1 , timeIndex), input.split("/")[1]));
                break;
            case "event":
                taskList.add(new Event(input.substring(commandIndex + 1 , timeIndex), input.split("/")[1]));
                break;
            default:
        }
        int taskCount = taskList.size();
        DataFile.write(taskList.get(taskCount - 1).toString());
        Messages.printDivider();
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskList.get(taskCount - 1));
        System.out.println("You now have " + taskCount + " items in the list.");
        Messages.printDivider();
    }

    public static void executeDelete(String input, ArrayList<Task> taskList) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
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
            Messages.printDivider();
        }
    }

}
