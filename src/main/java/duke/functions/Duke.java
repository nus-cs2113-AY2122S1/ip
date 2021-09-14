package duke.functions;

import duke.exceptions.EmptyArgException;
import duke.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Boolean isFinished = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();

        printIntro();

        while (!isFinished) {
            String userInput = sc.nextLine();
            String command = userInput.split(" ")[0];

            switch (command) {
            case "bye":
                isFinished = true;
                break;
            case "list":
                printTaskList(items);
                break;
            case "done": {
                try {
                    handleDone(items, userInput);
                } catch (NumberFormatException e) {
                    System.out.println("\tInvalid argument, please enter a valid task number!");
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println("\tNo such task!");
                } catch (EmptyArgException e) {
                    System.out.println("\tWhich task is done?");
                }
                break;
            }
            case "delete":{
                try {
                    handleDelete(items, userInput);
                } catch (NumberFormatException e) {
                    System.out.println("\tInvalid argument, please enter a valid task number!");
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println("\tNo such task!");
                } catch (EmptyArgException e) {
                    System.out.println("\tWhich task to delete?");
                }
                break;
            }
            case "todo": {
                try {
                    handleTodo(items, userInput);
                } catch (EmptyArgException e) {
                    System.out.println("\tDescription of todo cannot be empty!");
                }
                break;
            }
            case "deadline": {
                try {
                    handleDeadline(items, userInput);
                } catch (EmptyArgException e) {
                    System.out.println("\tDescription for deadline cannot be empty!");
                } catch (WrongFormatException e) {
                    System.out.println("\tWrong format! Try \"Deadline [description] \\by [due date]\"");
                }
                break;
            }
            case "event": {
                try {
                    handleEvent(items, userInput);
                } catch (EmptyArgException e) {
                    System.out.println("\tDescription for deadline cannot be empty!");
                } catch (WrongFormatException e) {
                    System.out.println("\tWrong format! Try \"Event [description] \\by [due date]\"");
                }
                break;
            }
            default:
                System.out.println("\tI don't know what that means");
                break;
            }
        }

        printBye();
    }

    private static void handleDelete(ArrayList<Task> items, String userInput) throws EmptyArgException {
        String[] arg = userInput.split(" ");
        if (arg.length < 2) {
            throw new EmptyArgException();
        }
        int indexToDelete = Integer.parseInt(arg[1]) - 1;
        Task removedItem = items.get(indexToDelete);
        items.remove(indexToDelete);
        System.out.println("\tItem deleted:");
        System.out.println("\t\t" + removedItem);
    }

    private static void handleDone(ArrayList<Task> items, String userInput) throws EmptyArgException {
        String[] arg = userInput.split(" ");
        if (arg.length < 2) {
            throw new EmptyArgException();
        }
        int indexToMark = Integer.parseInt(arg[1]) - 1;
        items.get(indexToMark).markAsDone();
        System.out.println("\tNice! I have marked this task as done:");
        System.out.println("\t\t" + items.get(indexToMark));
    }

    private static void handleEvent(ArrayList<Task> items, String userInput) throws EmptyArgException, WrongFormatException {
        String[] arg = userInput.split(" ", 2);
        if (arg.length < 2) {
            throw new EmptyArgException();
        }
        String[] splitArg = arg[1].split("/", 2);
        if (splitArg.length < 2) {
            throw new WrongFormatException();
        }
        String description = splitArg[0].trim();
        String at = splitArg[1].substring(3);

        items.add(new Event(description, at));
        printTaskAdded(items);
    }

    private static void handleDeadline(ArrayList<Task> items, String userInput) throws EmptyArgException, WrongFormatException {
        String[] arg = userInput.split(" ", 2);
        if (arg.length < 2) {
            throw new EmptyArgException();
        }
        String[] splitArg = arg[1].split("/", 2);
        if (splitArg.length < 2) {
            throw new WrongFormatException();
        }
        String description = splitArg[0].trim();
        String by = splitArg[1].substring(3);

        items.add(new Deadline(description, by));
        printTaskAdded(items);
    }

    private static void handleTodo(ArrayList<Task> items, String userInput) throws EmptyArgException {
        String[] arg = userInput.split(" ", 2);
        if (arg.length < 2) {
            throw new EmptyArgException();
        }
        items.add(new Todo(arg[1]));
        printTaskAdded(items);
    }

    private static void printTaskList(ArrayList<Task> items) {
        drawLine();
        System.out.println("\tHere is your task list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.print("\t\t" + (i + 1) + ". ");
            System.out.println(items.get(i));
        }
        drawLine();
    }

    private static void printTaskAdded(ArrayList<Task> items) {
        int indexOfLastItem = items.size() - 1;
        System.out.println("\tTask Added:");
        System.out.println("\t\t" + items.get(indexOfLastItem));
        System.out.println("\tYou now have " + items.size() + " tasks");
    }

    private static void printIntro() {
        System.out.println(
                "                              _     _\n" +
                "                             ( \\---/ )\n" +
                "                              ) . . (\n" +
                "________________________,--._(___Y___)_,--._______________________ \n" +
                "                        `--'           `--'");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
    }

    private static void printBye() {
        drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void drawLine() {
        System.out.println("____________________________________________________________");
    }
}
