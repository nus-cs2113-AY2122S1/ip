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
            switch (inputLine){
            case "bye": // exit command for chatbot
                continueLoop = false;
                break;
            case "list": // command to print to-do list
                printDividerLine();
                taskManager.printTasks();
                printDividerLine();
                break;
            default:
                Task newTask = new Task(inputLine);
                printDividerLine();
                taskManager.addTask(newTask);
                printDividerLine();
            }
        }

        printExitMessage();
    }
}
