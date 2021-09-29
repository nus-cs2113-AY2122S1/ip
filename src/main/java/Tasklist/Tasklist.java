package Tasklist;

import Task.Deadline;
import Task.Event;
import Task.ToDo;
import Ui.Ui;
import Task.Task;
import Exception.TaskNotFoundException;

import java.util.ArrayList;

public class Tasklist {

    public static void addToDo(String inputTask, ArrayList<Task> tasks) {
        try {
            String taskToDo = inputTask.substring(5);
            tasks.add(new ToDo(taskToDo));

            System.out.println("Got it. I've added this task:\n" + "[T][]" + taskToDo + "\n" + "Now you have " + tasks.size() + " tasks in this list.");
            Ui.horizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            Ui.horizontalLine();
        }
    }

    public static void addDeadline(String inputTask, ArrayList<Task> tasks) {
        try {
            String description = inputTask.substring(9);
            int index = description.indexOf("/by");
            String deadlineDescription = description.substring(index + 4);
            String taskDescription = description.substring(0, index);
            tasks.add(new Deadline(taskDescription, deadlineDescription));

            System.out.println("Got it. I've added this task:\n" + "[D][]" + taskDescription + " (by: " + deadlineDescription + ")\n" + "Now you have " + tasks.size() + " tasks in this list.");
            Ui.horizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            Ui.horizontalLine();
        }
    }

    public static void addEvent(String inputTask, ArrayList<Task> tasks) {
        try {
            String description = inputTask.substring(6);
            int index = description.indexOf("/at");
            String eventDescription = description.substring(index + 4);
            String taskDescription = description.substring(0, index);
            tasks.add(new Event(taskDescription, eventDescription));

            System.out.println("Got it. I've added this task:\n" + "[E][]" + taskDescription + " (by: " + eventDescription + ")\n" + "Now you have " + tasks.size() + " tasks in this list.");
            Ui.horizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            Ui.horizontalLine();
        }
    }

    public static void deleteTask(String inputTask, ArrayList<Task> tasks) {
        int sep = inputTask.indexOf(" ");
        int number = Integer.parseInt(inputTask.substring(7));

        System.out.println("Noted. I've removed this task: \n" + tasks.get(number-1) + "\n");
        tasks.remove(number-1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        Ui.horizontalLine();
    }

    public static void findTask(String inputTask, ArrayList<Task> tasks) throws TaskNotFoundException {
        try {
            int temp = 0;
            String keywordToFind = inputTask.substring(5);
            System.out.println("Here are the matching tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                String taskInList = tasks.get(i).toString();
                if (taskInList.contains(keywordToFind)) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    temp++;
                }
                if (temp == 0) {
                    throw new TaskNotFoundException();
                }
            }
        } catch(TaskNotFoundException e) {
            System.out.println("Oops! Task not found in the list!\n");
        }

        Ui.horizontalLine();
    }

    public static void markAsDone(int number, ArrayList<Task> tasks) {
        tasks.get(number-1).setDone();
        Ui.horizontalLine();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(tasks.get(number-1).toString());
    }
}
