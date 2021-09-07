package kitty.userinterface;

import kitty.Kitty;
import kitty.task.Task;
import kitty.task.Todo;
import kitty.task.Deadline;
import kitty.task.Event;

public class UiHandler {

    public static void startApp() {
        while (true) {
            Ui.getUserInput();
            switch (Ui.command) {
            case "bye":
                Ui.exit();
                break;
            case "list":
                printList();
                break;
            case "done":
                markTaskAsDone();
                break;
            case "todo":
                addToList(Ui.userInput, "T");
                break;
            case "deadline":
                addToList(Ui.userInput, "D");
                break;
            case "event":
                addToList(Ui.userInput, "E");
                break;
            default:
                Ui.printErrorMessage();
                break;
            }
        }
    }

    public static void printList() {
        System.out.println();
        System.out.println("Here are the tasks you have!");
        for (int i = 0; i < Task.totalTasksCount; i++) {
            System.out.print(i + 1 + ".");
            System.out.println(Kitty.tasks[i]);
        }
        System.out.println();
        Ui.printCat1();
        Ui.printBarLine();
    }

    public static void markTaskAsDone() {
        if (!isTaskNumValid(Ui.userInput)) {
            Ui.printErrorMessage();
            return;
        }
        int taskNum = Integer.parseInt(Ui.userInput.split(" ")[1]);
        Kitty.tasks[taskNum-1].setDone();
        System.out.println();
        System.out.println("Good Job!! One more thing off your list!!");
        System.out.println(Kitty.tasks[taskNum-1]);
        Ui.printCat3();
        Ui.printBarLine();
    }

    public static void addToList(String line, String type) {
        switch (type) {
        case "T":
            Todo.addTodoTask(Kitty.tasks, line);
            break;
        case "D":
            Deadline.addDeadlineTask(Kitty.tasks, line);
            break;
        case "E":
            Event.addEventTask(Kitty.tasks, line);
            break;
        }
        Task.totalTasksCount++;
        System.out.println();
        System.out.println("Added: " + Kitty.tasks[Task.totalTasksCount - 1]);
        System.out.println();
        Ui.printCat2();
        Ui.printBarLine();
    }

    private static boolean isTaskNumValid(String line) {
        String taskNum = line.split(" ")[1];
        boolean isAllNumbers = taskNum.matches("[0-9]+");
        if (isAllNumbers) {
            int numericTaskNum = Integer.parseInt(taskNum);
            boolean isTaskNumPositive = numericTaskNum > 0;
            boolean isTaskNumInRange = numericTaskNum <= Task.totalTasksCount;
            return isTaskNumPositive && isTaskNumInRange;
        } else {
            return false;
        }
    }
}
