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
import java.util.stream.Collectors;

/**
 * The class <code>UiHandler</code> includes methods that handle incoming User Inputs from class <code>Ui</code>.
 */
public class UiHandler {
    /**
     * Manages all incoming input and delegates to respective methods.
     * Full list of available commands:
     * bye: Exits Kitty.
     * list: Prints out all tasks at hand.
     * done: Marks respective task as done.
     * todo: Adds a task of Todo type.
     * deadline: Adds a task of Deadline type.
     * event: Adds a task of Event type.
     * delete: Deletes respective task from tasks at hand.
     * find: Searches and displays all tasks which correspond to keyword given.
     */
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

    /**
     * Displays to user all the currents tasks in ArrayList <code>tasks</code>.
     * @param tasks ArrayList containing all tasks at hand.
     * @throws KittyException If there are no tasks at hand.
     */
    public static void printList(ArrayList<Task> tasks) throws KittyException{
        // Throw exception if user list with no tasks
        if (tasks.size() == 0) {
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

    /**
     * Marks corresponding task as done from list of tasks at hand.
     * @throws KittyException If task number from input is invalid, i.e. "first"
     */
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

    /**
     * Adds the task into the list of tasks at hand based on the command type given.
     * @param type Represents the command type, between Todo, Deadline and Event.
     * @throws KittyException If the input formatting does not match the respective command type.
     */
    public static void addToList(String type) throws KittyException{
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
    }

    /**
     * Removes the corresponding task from the list of tasks at hand.
     * @throws KittyException If the task number provided is invalid. i.e. "last".
     */
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

    /**
     * Finds and displays the tasks corresponding to the keyword provided.
     * @throws KittyException If none of the tasks at hand matched the keyword provided.
     */
    public static void findFromList() throws KittyException {
        String keyword = Parser.getKeyword(Ui.userInput);
        ArrayList<Task> filteredTasks = (ArrayList<Task>) Kitty.tasks.stream()
                .filter((t) -> (t.getTaskName().contains(keyword)))
                .collect(Collectors.toList());

        if (filteredTasks.size() == 0) {
            throw new KittyException("There are no tasks that matched the keyword \"" + keyword + "\"");
        } else {
            System.out.println();
            System.out.println("Here are the tasks I found that matched the keyword \"" + keyword + "\"");
            filteredTasks.forEach(System.out::println);
            System.out.println();
            System.out.println(Ui.CAT_5);
        }
    }
}
