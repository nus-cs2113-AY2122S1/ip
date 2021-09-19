package kitty.userinterface;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;
import kitty.io.IO;
import kitty.task.Task;
import kitty.task.Todo;
import kitty.task.Deadline;
import kitty.task.Event;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class UiHandler {

    public static void beginUi() {
        while (true) {
            try {
                IO.updateData();
                Ui.getUserInput();
                switch (Ui.command) {
                case "bye":
                    Ui.exit();
                    break;
                case "list":
                    printList(Kitty.tasks);
                    break;
                case "done":
                    markTaskAsDone();
                    break;
                case "todo":
                    addToList("T");
                    break;
                case "deadline":
                    addToList("D");
                    break;
                case "event":
                    addToList("E");
                    break;
                case "delete":
                    removeFromList();
                    break;
                case "find":
                    findFromList();
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
        if (Kitty.tasks.size() == 0) {
            throw new KittyException("Sorry you currently have no tasks!");
        } else {
            // Prints list
            int i = 1;
            System.out.println();
            System.out.println("Here are the tasks you have!");

            for (Task task: tasks) {
                System.out.print(i + ". ");
                System.out.println(task);
                i++;
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

    public static void addToList(String type) throws KittyException{
        try {
            switch (type) {
            case "T":
                Todo.addTodoTask(Ui.userInput);
                break;
            case "D":
                Deadline.addDeadlineTask(Ui.userInput);
                break;
            case "E":
                Event.addEventTask(Ui.userInput);
                break;
            }
            Ui.printAddedTask();
        } catch (KittyException e) {
            throw e;
        }
    }

    public static void removeFromList() throws KittyException{
        try {
            int taskNum = Integer.parseInt(Ui.userInput.split(" ")[1]);
            System.out.println();
            System.out.println("I have deleted '" + Kitty.tasks.get(taskNum - 1) + "' from your list!!");
            System.out.println(Ui.CAT_3);

            Kitty.tasks.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new KittyException("Selected an invalid task number!");
        } catch (NumberFormatException e) {
            throw new KittyException("Inputted a non-Integer Task Number!");
        }
    }

    public static void findFromList() throws KittyException {
        String keyword = Parser.getKeyword(Ui.userInput);

        System.out.println();
        System.out.println("Here are the tasks I found that matched the keyword \"" + keyword + "\"");
        Kitty.tasks.stream()
                .filter((t) -> (t.getTaskName().contains(keyword)))
                .forEach(System.out::println);
        System.out.println();
        System.out.println(Ui.CAT_5);
    }
}
