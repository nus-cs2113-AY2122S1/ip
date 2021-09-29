package duke.data;

import duke.type.Deadline;
import duke.type.Event;
import duke.type.Task;

import duke.startup.Parser;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;


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
        taskList = new ArrayList<>();
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
        Ui.printDivider();
        for (Task item : taskList) {
            if (item != null) {
                String tick = (item.isDone()) ? "✓" : " ";
                System.out.println(in + ". " + "[" + item.getType() + "] " + "[" + tick + "]" + " " + item.getDescription() + printTask(item));
                in++;
            }
        }
        Ui.printDivider();
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
            Ui.printNotInRange(lastIndex);
        } catch (NumberFormatException e) {
            Ui.printIntegerOnly();
        }
    }

    /**
     *  Delete tasks without user input
     * @param toDeleteIndex indexes to delete
     */
    public void deleteTasks(int... toDeleteIndex) {
        int lastIndex = 0;
        try {
            for (int i : toDeleteIndex) {
                System.out.println("remove " + (i + 1) + ": " + taskList.get(i).getDescription());
                lastIndex = i;
                taskList.remove(i);
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.printNotInRange(lastIndex);
        }
    }
    /**
     * Marks a set of tasks as completed
     */
    public void markTasksAsDone() {
        int lastIndex = 0;
        try {
            String userInputString = Ui.readLine();
            userInputString = userInputString.trim();
            String[] numberList = userInputString.split(" ");
            System.out.print("done ");
            for (String i : numberList) {
                int index = Integer.parseInt(i) - 1;
                lastIndex = index;
                taskList.get(index).setDone(true);
                System.out.print(taskList.get(index).getDescription() + ", ");
            }
            Ui.printDoneMessage();
        } catch (NumberFormatException e) {
            Ui.printIntegerOnly();
            Ui.printDoneFormat();
        } catch (NullPointerException e) {
            Ui.printNoNull();
        } catch (IndexOutOfBoundsException e) {
            Ui.printNotInRange(lastIndex);
        }
    }


    public void clearTaskList() {
        taskList.clear();
    }

    /**
     * Adds tasks to a list, while checking if a date exists within input
     */
    public void addTasksToList() {
        String userInput = Ui.readLine();
        while (!Ui.isStop(userInput)) {
            addTaskCheckDate(userInput);
            userInput = Ui.readLine();
            }
    }


    /**
     * Checks if a date is found in user input
     *  if found, add to timeOfTask variable in task, ignore otherwise.
     * @param userInput user input
     */
    public void addTaskCheckDate(String userInput) {
            Task taskToAdd = Parser.parseInputAsTask(userInput);
            taskToAdd.setTime(userInput);
            taskList.add(taskToAdd);
    }

    /**
     * Prints valid tasks with date input
     * @param dateGiven date to search tasks with
     */
    public void printTasksWithGivenDate(LocalDate dateGiven) {
        List<Task> tasksFound =
                taskList.stream()
                .filter(t -> t.getTaskDate().equals(dateGiven))
                .collect(toList());
        if (tasksFound.isEmpty()) {
            System.out.println("No task with date " + dateGiven + " found!");
        } else {
            System.out.println("I found the following tasks:");
            taskList.stream()
                    .map(t->t.getDescription())
                    .forEach(t-> System.out.println(t));
            System.out.println("with the given date: " + dateGiven.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
    }
}
