package duke.data;

import Type.Deadline;
import Type.Event;
import Type.Task;
import duke.exception.InputCheckAndPrint;
import duke.startup.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

    public void deleteTasks() {
        Scanner in = new Scanner(System.in);
        //1. collect data
        int lastIndex = 0;
        List<Integer> toDeleteList = new ArrayList<Integer>();
        String input = in.nextLine();
        String[] inputData = input.split(" ");
        try {
            for (String s : inputData) {
                toDeleteList.add(Integer.parseInt(s) - 1);
            }
            //2. sort in decreasing order
            Collections.sort(toDeleteList, Collections.reverseOrder());
            //3. remove from list
            for (int i : toDeleteList) {
                System.out.println("remove " + (i + 1) + ": " + taskList.get(i).getDescription());
                lastIndex = i;
                taskList.remove(i);
            }
        } catch (NullPointerException e) {
            InputCheckAndPrint.printNotInRange(lastIndex);
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printIntegerOnly();
        } catch (IndexOutOfBoundsException e) {
            InputCheckAndPrint.printNotInRange(lastIndex);
        }
    }

    public void markTasksAsDone() {
        Scanner in = new Scanner(System.in);
        try {
            String userInputString = null;
            do {
                userInputString = in.nextLine();
            } while (InputCheckAndPrint.startsWithSpace(userInputString)
                    || InputCheckAndPrint.isEmpty(userInputString)
                    || !InputCheckAndPrint.isIntegerInput(userInputString)
            );
            userInputString = userInputString.trim();
            String[] numberList = userInputString.split(" ");
            System.out.print("done ");
            for (String i : numberList) {
                int index = Integer.parseInt(i) - 1;
                if (index >= taskList.size()) {
                    InputCheckAndPrint.printNotInRange(index);
                    break;
                }
                taskList.get(index).setDone(true);
                System.out.print(taskList.get(index).getDescription() + ", ");
            }
            System.out.println("\n / done tasks, good job! / ");
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printIntegerOnly();
        } catch (NullPointerException e) {
            InputCheckAndPrint.printNoNull();
        }
    }

    public void clearTaskList() {
        taskList.clear();
    }

    public void addTasksToList() {
        Scanner in = new Scanner(System.in);
        String userInput;
        do {
            userInput = in.nextLine();
        } while (!userStopAdd(userInput));
        System.out.println("Finished adding tasks!");
    }

    public boolean userStopAdd(String userInput) {
        if (!userInput.equals("stop")) {
            Task taskToAdd = Parser.parseInputAsTask(userInput);
            taskList.add(taskToAdd);
        } else {
            return true;
        }
        return false;
    }
}
