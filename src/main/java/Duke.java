import java.util.Scanner;

public class Duke {
    private static final String LOGO =
                    " _______  __   __  ______   _______  _______  _______  __   __  _______\n" +
                    "|       ||  | |  ||      | |       ||       ||   _   ||  |_|  ||   _   |\n" +
                    "|    ___||  | |  ||  _    ||    ___||_     _||  |_|  ||       ||  |_|  |\n" +
                    "|   | __ |  |_|  || | |   ||   |___   |   |  |       ||       ||       |\n" +
                    "|   ||  ||       || |_|   ||    ___|  |   |  |       ||       ||       |\n" +
                    "|   |_| ||       ||       ||   |___   |   |  |   _   || ||_|| ||   _   |\n" +
                    "|_______||_______||______| |_______|  |___|  |__| |__||_|   |_||__| |__|\n";
    private static final String SEPARATOR = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\n";
    private static Task[] userInputs = new Task[100];
    private static int userInputCount = 0;

    public static void initiateDuke() {
        System.out.println(SEPARATOR + "Hi... from GUDETAMA... so sleepy\n" + LOGO);
        System.out.println("Give me five more minutes..... What can I do for you?\n" + SEPARATOR);
    }

    public static void processInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks();
            } else if (line.startsWith("done")) {
                markDone(line);
            } else if (line.startsWith("todo")) {
                addTodo(line);
            } else if (line.startsWith("deadline")) {
                addDeadline(line);
            } else if (line.startsWith("event")) {
                addEvent(line);
            } else {
                handleInvalid();
            }
            line = in.nextLine();
        }
        endDuke();
    }

    public static void endDuke() {
        System.out.println(SEPARATOR + "\nBye. I'm going back to sleep... zzz");
    }

    public static void listTasks() {
        System.out.println(SEPARATOR + "\n\tTasks to do... so lazy:");

        for (int i = 0; i < userInputCount; i++) {
            System.out.println("\t" + (i + 1) + "." + userInputs[i]);
        }
        System.out.println(SEPARATOR);
    }

    public static void markDone(String line) {
        int taskIndexDone = Integer.parseInt(line.split(" ")[1]) - 1;

        userInputs[taskIndexDone].markAsDone();
        System.out.println(SEPARATOR + "\n\tfinished this task... I need a break:\n\t\t" +
                            userInputs[taskIndexDone]);
        System.out.println(SEPARATOR);
    }

    public static void addTodo(String line) {
        userInputs[userInputCount] = new Todo(line.substring(5));

        System.out.println(SEPARATOR + "\n\tadded: " + userInputs[userInputCount] + "\n" + SEPARATOR);
        userInputCount++;
    }

    public static void addDeadline(String line) {
        String[] lineArray = line.substring(9).split("/by");
        userInputs[userInputCount] = new Deadline(lineArray[0].trim(), lineArray[1].trim());

        System.out.println(SEPARATOR + "\n\tadded: " + userInputs[userInputCount] + "\n" + SEPARATOR);
        userInputCount++;
    }

    public static void addEvent(String line) {
        String[] lineArray = line.substring(6).split("/at");
        userInputs[userInputCount] = new Event(lineArray[0].trim(), lineArray[1].trim());

        System.out.println(SEPARATOR + "\n\tadded: " + userInputs[userInputCount] + "\n" + SEPARATOR);
        userInputCount++;
    }

    public static void handleInvalid() {
        System.out.println(SEPARATOR + "Invalid function\n" + SEPARATOR);
    }

    public static void main(String[] args) {
        initiateDuke();
        processInput();
    }
}
