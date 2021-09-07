import java.util.Scanner;

public class Duke {

    private static Boolean isFinished = false;
    private static int itemCount = 0;

    public static void main(String[] args) throws EmptyArgException {
        Scanner sc = new Scanner(System.in);
        Task[] items = new Task[100];

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
                String[] arg = userInput.split(" ");
                try {
                    int indexToMark = Integer.parseInt(arg[1]) - 1;
                    items[indexToMark].markAsDone();
                    System.out.println("\tNice! I have marked this task as done:");
                    System.out.println("\t\t" + items[indexToMark]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid argument, please enter a valid task number!");
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println("No such task!");
                }
                break;
            }
            case "todo": {
                try {
                    handleTodo(items, userInput);
                } catch (EmptyArgException e) {
                    System.out.println("Description of todo cannot be empty!");
                }
                break;
            }
            case "deadline": {
                try {
                    handleDeadline(items, userInput);
                } catch (EmptyArgException e) {
                    System.out.println("Description for deadline cannot be empty");
                } catch (WrongFormatException e) {
                    System.out.println("Wrong format! Try \"Deadline [description] \\by [due date]\"");
                }
                break;
            }
            case "event": {
                try {
                    handleEvent(items, userInput);
                } catch (EmptyArgException e) {
                    System.out.println("Description for deadline cannot be empty");
                } catch (WrongFormatException e) {
                    System.out.println("Wrong format! Try \"Event [description] \\by [due date]\"");
                }
                break;
            }
            default:
                System.out.println("I don't know what that means");
                break;
            }
        }

        printBye();
    }

    private static void handleEvent(Task[] items, String userInput) throws EmptyArgException, WrongFormatException {
        String[] arg = userInput.split(" ", 2);
        if (arg.length < 2){
            throw new EmptyArgException();
        }
        String[] splitArg = arg[1].split("/", 2);
        if (splitArg.length<2){
            throw new WrongFormatException();
        }
        String description = splitArg[0].trim();
        String at = splitArg[1].substring(3);

        items[itemCount] = new Event(description, at);
        incrementItemCount(items[itemCount]);
    }

    private static void handleDeadline(Task[] items, String userInput) throws EmptyArgException, WrongFormatException {
        String[] arg = userInput.split(" ", 2);
        if (arg.length < 2){
            throw new EmptyArgException();
        }
        String[] splitArg = arg[1].split("/", 2);
        if (splitArg.length < 2){
            throw new WrongFormatException();
        }
        String description = splitArg[0].trim();
        String by = splitArg[1].substring(3);

        items[itemCount] = new Deadline(description, by);
        incrementItemCount(items[itemCount]);
    }

    private static void handleTodo(Task[] items, String userInput) throws EmptyArgException {
        String[] arg = userInput.split(" ", 2);
        if (arg.length < 2) {
            throw new EmptyArgException();
        }
        items[itemCount] = new Todo(arg[1]);
        incrementItemCount(items[itemCount]);
    }

    private static void printTaskList(Task[] items) {
        drawLine();
        System.out.println("\tHere is your task list:");
        for (int i = 0; i < itemCount; i++) {
            System.out.print("\t\t" + (i + 1) + ". ");
            System.out.println(items[i]);
        }
        drawLine();
    }


    private static void incrementItemCount(Task item) {
        System.out.println("\tTask Added:");
        System.out.println("\t\t" + item);
        itemCount++;
        System.out.println("\tYou now have " + itemCount + " tasks");
    }

    private static void printIntro() {
        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
        System.out.println();
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
