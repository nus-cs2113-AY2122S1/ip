import java.util.Scanner;

public class Duke {
    public static int count = 0;
    public static Task[] lists = new Task[100];

    public static void main(String[] args) {
        //logo
        String logo =
                " _____  ________    _        ______  ___  ___  ______\n"
                        + "|_   _||  _   _ |  / \\    .' ___  | |_  ||  _||__   _|\n"
                        + "  | |     | |     / _ \\  / .'   \\_   | |__| |    | |\n"
                        + "  | |     | |    / ___ \\ | |         |  __  |    | |\n"
                        + " _| |_   _| |_ _/ /     \\ \\ `.___.'\\_| |  | |   _| |_\n"
                        + "|_____| |_____|____| |____ `.____ .|____||____||_____|\n";

        System.out.println("THE GREATEST SHINOBI\n" + logo
                + "\nWelcome! You have entered my illusion where I will be your partner\n"
                + "and I will make you productive in order for you to reach your goals so that\n"
                + "I can fulfill my dream of making someone great.\n"
                + "Go ahead, give your command\n");

        printWelcomeMessage();
        userCommands();
    }

    private static void userCommands() {
        boolean isOver = false;
        while (!isOver) {
            String input = getUserInput();
            String command = getFirstWord(input);
            if (command.equals("bye")) {
                printByeMessage();
                isOver = true;
            } else if (command.equals("list")) {
                printLineSeparator();
                System.out.println("Here are the tasks in your list:");

                //Lists down all the tasks added along with its status
                for (int i = 0; i < count; i++) {
                    String taskStatus = lists[i].getStatusIcon();
                    System.out.println((i + 1) + ". " + lists[i]);
                }
                printLineSeparator();
            } else if (command.equals("done")) {

                //Extracts the index number from the text and changes status of the task
                int index = getIndex(input);
                lists[index].markAsDone();
                String taskStatus = lists[index].getStatusIcon();

                printLineSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(lists[index]);
                printLineSeparator();
            } else if (command.equals("todo")) {

                //Extracts the description and stores the task in the list
                Task todo = getTodo(input);

                printLineSeparator();
                System.out.println("Got it. I've added this task:\n" + todo + "\nNow you have " + count + " tasks in the list.");
                printLineSeparator();
            } else if (command.equals("deadline")) {

                //Extracts the description and day/date and stores the task in the list
                Task deadline = getDeadline(input);

                printLineSeparator();
                System.out.println("Got it. I've added this task:\n" + deadline + "\nNow you have " + count + " tasks in the list.");
                printLineSeparator();
            } else if (command.equals("event")) {

                //Extracts the description and the time and stores the task in the list
                Task event = getEvent(input);

                printLineSeparator();
                System.out.println("Got it. I've added this task:\n" + event + "\nNow you have " + count + " tasks in the list.");
                printLineSeparator();
            } else {
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

    private static Task getTodo(String input) {
        String todoDescription = input.substring(4).trim();
        Task todo = new Todo(todoDescription);
        lists[count] = todo;
        count++;

        return todo;
    }

    private static Task getDeadline(String input) {
        int endIndex = input.indexOf("/");
        String deadlineDescription = input.substring(8, endIndex);
        String deadlineDate = getDate(input);

        Task deadline = new Deadline(deadlineDescription, deadlineDate);
        lists[count] = deadline;
        count++;

        return deadline;
    }

    private static Task getEvent(String input) {
        int endIndex = input.indexOf("/");
        String eventDescription = input.substring(5, endIndex);
        String eventDate = getDate(input);

        Task event = new Event(eventDescription, eventDate);
        lists[count] = event;
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

    private static String getFirstWord(String input) {
        return input.toLowerCase().split(" ")[0];
    }

    private static String getDate(String input) {
        int startIndex = input.indexOf("/");
        return input.substring(startIndex + 3).trim();
    }
}