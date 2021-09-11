package kitty.userinterface;

import kitty.Kitty;
import kitty.KittyException;
import kitty.task.Task;
import kitty.task.Todo;
import kitty.task.Deadline;
import kitty.task.Event;

import java.util.Optional;

public class UiHandler {

    public static void startApp() {
        while (true) {
            try {
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
                case "delete":
                    removeFromList(Ui.userInput);
                    break;
                default:
                    throw new KittyException("No such command found");
                }
            } catch (KittyException e) {
                Ui.printErrorMessage();
            }
        }
    }

    public static void printList() throws KittyException{
        // Throw exception if user list with no tasks
        if (Task.totalTasksCount < 1) {
            throw new KittyException("Sorry you currently have no tasks!");
        } else {
            // Prints list
            System.out.println();
            System.out.println("Here are the tasks you have!");
            for (int i = 0; i < Task.totalTasksCount; i++) {
                System.out.print(i + 1 + ".");
//                System.out.println(Kitty.tasks[i]);
                System.out.println(Kitty.tasks.get(i));
            }
            System.out.println();
            System.out.println(Ui.CAT_1);
        }
    }

    public static void markTaskAsDone() throws KittyException{
        try {
            int taskNum = Integer.parseInt(Ui.userInput.split(" ")[1]);
            Kitty.tasks.get(taskNum - 1).setDone();
            System.out.println();
            System.out.println("Good Job!! One more thing off your list!!");
            System.out.println(Kitty.tasks.get(taskNum - 1));
            System.out.println(Ui.CAT_3);
        } catch (IndexOutOfBoundsException e) {
            throw new KittyException("Selected an invalid task number!");
        } catch (NumberFormatException e) {
            throw new KittyException("Inputted a non-Integer Task Number!");
        }
    }

    public static void addToList(String line, String type) throws KittyException{
        try {
            switch (type) {
            case "T":
                Todo.addTodoTask(line);
                break;
            case "D":
                Deadline.addDeadlineTask(line);
                break;
            case "E":
                Event.addEventTask(line);
                break;
            }
            Task.totalTasksCount++;
            Ui.printAddedTask();
        } catch (KittyException e) {
            throw e;
        }
    }

    public static void removeFromList(String line) {

    }
}
