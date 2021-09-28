package duke.tasklist;

import duke.Ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.stream.Collectors;
import java.util.ArrayList;

public class TaskList {

    public static void deleteTask(String inputCommand, ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        try {
            int position = inputCommand.indexOf(" ");
            int taskToDelete = Integer.parseInt(inputCommand.trim().substring(position + 1)) - 1;
            Task removeTask = tasks.get(taskToDelete);
            tasks.remove(taskToDelete);
            System.out.println("Noted! I've remove this task:");
            System.out.println(removeTask.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please enter a number after delete!");
        }
        Ui.printHorizontalLine();
    }


    public static void addEvent(String inputCommand, ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        try {
            String description = inputCommand.substring(6);
            String[] parts = description.split("/at");
            String eventDescription = parts[0];
            String timingDescription = parts[1];
            Event newEvent = new Event(eventDescription, timingDescription);
            tasks.add(newEvent);
            Ui.gotItMessage(newEvent, tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        }
        Ui.printHorizontalLine();
    }

    public static void addDeadline(String inputCommand, ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        try {
            String description = inputCommand.substring(9);
            String[] parts = description.split("/by");
            String taskDescription = parts[0];
            String deadlineDescription = parts[1];
            Deadline newDeadline = new Deadline(taskDescription, deadlineDescription);
            tasks.add(newDeadline);
            Ui.gotItMessage(newDeadline, tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Ui.printHorizontalLine();
    }

    public static void addToDo(String inputCommand, ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        try {
            String taskToDo = inputCommand.substring(5);
            ToDo newToDo = new ToDo(taskToDo);
            tasks.add(newToDo);
            Ui.gotItMessage(newToDo, tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Ui.printHorizontalLine();
    }

    public static void markAsDone(String inputCommand, ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        try {
            int position = inputCommand.indexOf(" ");
            int taskNum = Integer.parseInt(inputCommand.trim().substring(position + 1));
            tasks.get(taskNum - 1).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNum - 1).getDescription().trim());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please enter a number after done.");
        }
        Ui.printHorizontalLine();
    }

    public static void showList(ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        Ui.printHorizontalLine();
    }

    public static void findTask(String inputCommand, ArrayList<Task> tasks){
        String taskDescriptionToFind = inputCommand.substring(5);
        ArrayList<Task> filteredTask = (ArrayList<Task>) tasks.stream()
                .filter((task) -> task.getDescription().toLowerCase().contains(taskDescriptionToFind))
                .collect(Collectors.toList());
        if(filteredTask.isEmpty()){
            System.out.println("I cannot find any matching tasks");
        }
        Ui.printFilteredList(filteredTask);
    }

}
