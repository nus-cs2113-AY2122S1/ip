import java.util.Scanner;

public class xRoss {

    // prints a divider line and new line to command line output
    public static void printDividerLine(){
        System.out.println("..........................................................................................");
    }

    // prints welcome message
    public static void printWelcomeMessage(){
        String logo = "        ____          \n"
                + "       | __ \\  _  __  __\n"
                + " _  _  |  __/ / \\|  \\|  \\\n"
                + "\\ \\/ / | |\\ \\| | |\\ \\\\ \\\n"
                + "/_/\\_\\ |_| \\_\\\\_/\\__|\\__|\n";
        System.out.println("Hello from\n" + logo);

        printDividerLine();
        System.out.println("\tHello! I'm xRoss, your personal chat bot assistant!\n\tWhat can I do for you today?\n");
        printDividerLine();
    }

    // prints exit message
    public static void printExitMessage(){
        printDividerLine();
        System.out.println("\tBye!\n\tHave a nice day and I hope to see you again soon!\n");
        printDividerLine();
    }

    public static void printEcho(String inputLine){
        printDividerLine();
        System.out.println("\t" + inputLine);
        System.out.println("\tThere was no command given, so I'll just echo your input.\n");
        printDividerLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        // TaskManager instance to keep track of all tasks
        TaskManager taskManager = new TaskManager();

        // setting up variable and scanner for user input
        String inputLine;
        Scanner in = new Scanner(System.in);

        // boolean value on whether
        boolean continueLoop = true;

        while (continueLoop){
            inputLine = in.nextLine();
            if (inputLine.equals("bye")){
                continueLoop = false;
            } else if (inputLine.equals("list")){
                printDividerLine();
                taskManager.printTasks();
                printDividerLine();
            } else if (inputLine.startsWith("done ")){
                printDividerLine();
                taskManager.markAsDone(Integer.parseInt(inputLine.substring(5)));
                printDividerLine();
            } else if (inputLine.startsWith("todo ")) {
                Todo newTodo = new Todo(inputLine.substring(5));

                printDividerLine();
                taskManager.addTask(newTodo);
                printDividerLine();
            } else if (inputLine.startsWith("deadline ")){
                String[] newDeadline = inputLine.split(" /by ");

                printDividerLine();
                taskManager.addTask(new Deadline(newDeadline[0].substring(9), newDeadline[1]));
                printDividerLine();
            } else if (inputLine.startsWith("event ")){
                String[] newEvent = inputLine.split(" /at ");

                printDividerLine();
                taskManager.addTask(new Event(newEvent[0].substring(6), newEvent[1]));
                printDividerLine();
            } else {
                printEcho(inputLine);
            }
        }

        printExitMessage();
    }
}
