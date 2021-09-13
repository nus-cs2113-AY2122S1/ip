package duke.task;

import duke.parser.Parser;

public class TaskManager {
    protected Task[] tasks;
    protected int taskCount;

    public TaskManager() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void printTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.println(i + ". " + tasks[i].toString());
        }
    }

    public void doneTask(String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]);
            tasks[index].setDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks[index].toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task done");
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskCount);
        }
    }

    public void storageDoneTask(int index) {
        tasks[index].setDone();
    }

    public void addToDoTask(String userInput) {
        String description = Parser.processToDo(userInput);
        if (description == null) {
            return;
        }
        taskCount++;
        tasks[taskCount] = new ToDo(description);
    }

    public void addDeadlineTask(String userInput) {
        String[] information = Parser.processDeadline(userInput);
        if (information[0].equals("") || information[1].equals("")) {
            return;
        }
        taskCount++;
        tasks[taskCount] = new Deadline(information[0], information[1]);
    }

    public void addEventTask(String userInput) {
        String[] information = Parser.processEvent(userInput);
        if (information[0].equals("") || information[1].equals("")) {
            return;
        }
        taskCount++;
        tasks[taskCount] = new Event(information[0], information[1]);
    }

    public void printAddSuccess() {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + tasks[taskCount].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

}
