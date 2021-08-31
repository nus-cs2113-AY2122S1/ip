import java.util.Scanner;

public class Duke {
    public static int count = 0;
    public static Task[] list = new Task[100];

    public static void main(String[] args) {
        //logo
        String logo = " _____  ________    _        ______  ___  ___  ______\n"
                + "|_   _||  _   _ |  / \\    .' ___  | |_  ||  _||__   _|\n"
                + "  | |     | |     / _ \\  / .'   \\_   | |__| |    | |\n"
                + "  | |     | |    / ___ \\ | |         |  __  |    | |\n"
                + " _| |_   _| |_ _/ /     \\ \\ `.___.'\\_| |  | |   _| |_\n"
                + "|_____| |_____|____| |____ `.____ .|____||____||_____|\n";

        System.out.println("THE GREATEST SHINOBI\n" + logo
                + "\nWelcome! You have entered my illusion where I will be your partner\n"
                + "and I will make you productive in order for you to reach your goals\n"
                + "so that I can fulfill my dream of making someone great.\n"
                + "Go ahead, give your command\n");

        printWelcomeMessage();
        userCommands();
    }

    private static void userCommands() {
        boolean isOver = false;

        //Runs until user enters bye
        while (!isOver) {
            String input = getUserInput();
            String command = getFirstWordFromCommand(input);

            switch (command) {
            case "bye":
                printByeMessage();
                isOver = true;
                break;
            case "list":
                printLineSeparator();
                System.out.println("Here are the tasks in your list:");

                //Lists down all the tasks added along with its status
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }

                printLineSeparator();
                break;
            case "done":

                //Extracts the index number from the text and changes status of the task
                int index = getIndex(input);
                list[index].markAsDone();

                printLineSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[index]);
                printLineSeparator();
                break;
            case "todo":

                //Extracts the description, creates a Task object and stores the task in the list
                Task todo = getTodoDetails(input);

                printLineSeparator();
                System.out.println("Got it. I've added this task:\n" + todo + "\nNow you have " + count + " tasks in the list.");
                printLineSeparator();
                break;
            case "deadline":

                //Extracts the description and day/date, creates a Task object and stores the task in the list
                Task deadline = getDeadlineDetails(input);

                printLineSeparator();
                System.out.println("Got it. I've added this task:\n" + deadline + "\nNow you have " + count + " tasks in the list.");
                printLineSeparator();
                break;
            case "event":

                //Extracts the description and the time, creates a Task object and stores the task in the list
                Task event = getEventDetails(input);

                printLineSeparator();
                System.out.println("Got it. I've added this task:\n" + event + "\nNow you have " + count + " tasks in the list.");
                printLineSeparator();
                break;
            default:

                //Shows invalid command incase no matching commands are given
                printLineSeparator();
                System.out.println("Invalid command: " + input);
                System.out.println("Looks like you have to try again");
                printLineSeparator();
            }
        }
    }

    private static String getUserInput() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        return input;
    }

    private static int getIndex(String input) {
        String[] temp = input.split(" ");
        int index = Integer.parseInt(temp[1]);
        index = index - 1;

        return index;
    }

    private static Task getTodoDetails(String input) {
        String todoDescription = input.substring(4).trim();

        Task todo = new Todo(todoDescription);
        list[count] = todo;
        count++;

        return todo;
    }

    private static Task getDeadlineDetails(String input) {
        int endIndex = input.indexOf("/");
        String deadlineDescription = input.substring(8, endIndex);
        String deadlineDate = getDateFromCommand(input);

        Task deadline = new Deadline(deadlineDescription, deadlineDate);
        list[count] = deadline;
        count++;

        return deadline;
    }

    private static Task getEventDetails(String input) {
        int endIndex = input.indexOf("/");
        String eventDescription = input.substring(5, endIndex);
        String eventDate = getDateFromCommand(input);

        Task event = new Event(eventDescription, eventDate);
        list[count] = event;
        count++;

        return event;
    }

    private static void printLineSeparator() {
        String line = "________________________________________________________";
        System.out.println(line);
    }

    private static void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void printByeMessage() {
        printLineSeparator();
        System.out.println("Farewell. Come back when you need more help");
        printLineSeparator();
    }

    private static String getFirstWordFromCommand(String input) {
        return input.toLowerCase().split(" ")[0];
    }

    private static String getDateFromCommand(String input) {
        int startIndex = input.indexOf("/");

        return input.substring(startIndex + 3).trim();
    }
}