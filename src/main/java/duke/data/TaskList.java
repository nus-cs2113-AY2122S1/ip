package duke.data;

import Type.Deadline;
import Type.Event;
import Type.Task;

import duke.exceptionhandler.InputCheckAndPrint;
import duke.startup.Parser;
import duke.startup.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a task list. A <code>TaskList</code> object simulates the behaviour of a digital list,
 *  with the ability of data manipulation and reading.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> loadedTaskList) {
        taskList = loadedTaskList;
    }

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static String printTask(Task item) {
        if (item instanceof Deadline) {
            return " (by: " + ((Deadline) item).getByWhen() + ") ";
        } else if (item instanceof Event) {
            return " (at: " + ((Event) item).getAtWhen() + ") ";
        }
        return "";
    }

    /**
     * Displays list of task to user in easy to read format
     */
    public void listTasks() {
        int in = 1;
        System.out.println(" /          / ");
        for (Task item : taskList) {
            if (item != null) {
                String tick = (item.isDone()) ? "✓" : " ";
                System.out.println(in + ". " + "[" + item.getType() + "] " + "[" + tick + "]" + " " + item.getDescription() + printTask(item));
                in++;
            }
        }
        System.out.println(" /          / ");
    }

    /**
     * Deletes a set of specific tasks from a list
     */
    public void deleteTasks() {
        Scanner in = new Scanner(System.in);
        int lastIndex = 0;
        List<Integer> toDeleteList = new ArrayList<Integer>();
        String input = in.nextLine();
        String[] inputData = input.split(" ");
        try {
            for (String s : inputData) {
                toDeleteList.add(Integer.parseInt(s) - 1);
            }
            Collections.sort(toDeleteList, Collections.reverseOrder());
            for (int i : toDeleteList) {
                System.out.println("remove " + (i + 1) + ": " + taskList.get(i).getDescription());
                lastIndex = i;
                taskList.remove(i);
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            InputCheckAndPrint.printNotInRange(lastIndex);
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printIntegerOnly();
        }
    }

    /**
     * Marks a set of tasks as completed
     */
    public void markTasksAsDone() {
        int lastIndex = 0;
        try {
            String userInputString = Ui.readCommand();
            userInputString = userInputString.trim();
            String[] numberList = userInputString.split(" ");
            System.out.print("done ");
            for (String i : numberList) {
                int index = Integer.parseInt(i) - 1;
                lastIndex = index;
                taskList.get(index).setDone(true);
                System.out.print(taskList.get(index).getDescription() + ", ");
            }
            System.out.println("\n / done tasks, good job! / ");
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printIntegerOnly();
            InputCheckAndPrint.printDoneFormat();
        } catch (NullPointerException e) {
            InputCheckAndPrint.printNoNull();
        } catch (IndexOutOfBoundsException e) {
            InputCheckAndPrint.printNotInRange(lastIndex);
        }
    }

    public void clearTaskList() {
        taskList.clear();
    }

    public void addTasksToList() {
        String userInput;
        do {
            userInput = Ui.readCommand();
        } while (!stopAddTasksCheckDate(userInput));
        System.out.println("Finished adding tasks!");
    }

    /**
     * Primarily checks if user enters STOP
     *  if not, add tasks with date and respective attributes
     * @param userInput sentence with attributes of task
     *                  - may include deadlines, date etc.
     * @return true if user stops add
     */
    public boolean stopAddTasksCheckDate(String userInput) {
        if (!userInput.equals("stop")) {
            Task taskToAdd = Parser.parseInputAsTask(userInput);
            taskToAdd.setTime(userInput);
            taskList.add(taskToAdd);
        } else {
            return true;
        }
        return false;
    }

    public void printTasksWithValidDate() {
        for (Task t : taskList) {
            setTimeFromSavedList(t);
            }
    }

    /**
     * Checks if a valid date is found in a task
     * @param t task to check
     */
    private void setTimeFromSavedList(Task t) {
        t.setTime(t.getDescription());
        if (t instanceof Deadline) {
            t.setTime(((Deadline) t).getByWhen());
        } else if (t instanceof Event) {
            t.setTime(((Event) t).getAtWhen());
        }
    }
}
