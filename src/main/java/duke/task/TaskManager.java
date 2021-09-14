package duke.task;

import duke.parser.Parser;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void printTask() {
        System.out.println("Here are the tasks in your list:");
        int currentIndex = 1;
        for (Task task : tasks) {
            System.out.println(currentIndex + ". " + task.toString());
            currentIndex++;
        }
    }

    public void doneTask(String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            tasks.get(index).setDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task done");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + tasks.size());
        }
    }

    public void deleteTask(String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n  " + removedTask.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task to be removed");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + tasks.size());
        }
    }

    public void addToDoTask(String userInput) {
        String description = Parser.processToDo(userInput);
        if (description == null) {
            return;
        }
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        printAddSuccess(todo.toString());
    }

    public void addDeadlineTask(String userInput) {
        String[] information = Parser.processDeadline(userInput);
        if (information[0].equals("") || information[1].equals("")) {
            return;
        }
        Deadline deadline = new Deadline(information[0], information[1]);
        tasks.add(deadline);
        printAddSuccess(deadline.toString());
    }

    public void addEventTask(String userInput) {
        String[] information = Parser.processEvent(userInput);
        if (information[0].equals("") || information[1].equals("")) {
            return;
        }
        Event event = new Event(information[0], information[1]);
        tasks.add(event);
        printAddSuccess(event.toString());
    }

    private void printAddSuccess(String addedTask) {
        System.out.println("Got it. I've added this task:\n  " + addedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

}
