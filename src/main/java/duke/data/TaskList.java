package duke.data;

import Type.Deadline;
import Type.Event;
import Type.Task;
import duke.exception.InputCheckAndPrint;
import duke.startup.Parser;
import duke.startup.Ui;

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

    private void deleteTasks() {
        Scanner in = new Scanner(System.in);
        //1. collect data
        List<Integer> toDeleteList = new ArrayList<Integer>();
        String input = in.nextLine();
        String[] inputData = input.split(" ");
        for (String s : inputData) {
            if (InputCheckAndPrint.startsWithSpace(s)) {
                InputCheckAndPrint.inputFailMessage();
                InputCheckAndPrint.printDoneFormat();
                return;
            }
            else if (InputCheckAndPrint.isEmpty(s)) {
                InputCheckAndPrint.inputFailMessage();
                InputCheckAndPrint.printNoNull();
                return;
            }
            else if (!InputCheckAndPrint.isIntegerInput(s)) {
                InputCheckAndPrint.printIntegerOnly();
                return;
            }
            int sData = Integer.parseInt(s) - 1;
            toDeleteList.add(sData);
        }
        //2. sort in decreasing order
        Collections.sort(toDeleteList, Collections.reverseOrder());
        //3. remove from list
        for (int i : toDeleteList) {
            System.out.println("remove " + (i + 1) + ": " + taskList.get(i).getDescription());
            taskList.remove(i);
        }
    }

    protected void markTasksAsDone() {
        Scanner in = new Scanner(System.in);
        InputCheckAndPrint doneCheck = new InputCheckAndPrint("doneCheck");
        try {
            String userInputString = null;
            do {
                userInputString = in.nextLine();
                if (InputCheckAndPrint.startsWithSpace(userInputString)) {
                    InputCheckAndPrint.inputFailMessage();
                    InputCheckAndPrint.printDoneFormat();
                } else if (InputCheckAndPrint.isEmpty(userInputString)) {
                    InputCheckAndPrint.inputFailMessage();
                    InputCheckAndPrint.printNoNull();
                } else if (!InputCheckAndPrint.isIntegerInput(userInputString)) {
                    InputCheckAndPrint.printIntegerOnly();
                }
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

    private static void clearTaskList(ArrayList<Task> taskList) {
        taskList.clear();
    }

    protected void readLineToTask(Scanner in, ArrayList<Task> taskList) {
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
