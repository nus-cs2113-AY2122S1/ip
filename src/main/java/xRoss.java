import java.util.Scanner;

public class xRoss {

    // prints a divider line and new line to command line output
    public static void printDividerLine(){
        System.out.println(".....................................................");
    }

    // prints message and new line to command line output
    public static void printMessage(String message, boolean extra_line){
        System.out.println("\t" + message);
        if (extra_line) System.out.println();
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
        printMessage("Hello! I'm xRoss", false);
        printMessage("What can I do for you today?", true);
        printDividerLine();
    }

    // prints exit message
    public static void printExitMessage(){
        printDividerLine();
        printMessage("Bye. Hope to see you again soon!", true);
        printDividerLine();
    }

    // prints echo
    public static void printEcho(String message){
        printDividerLine();
        printMessage(message, true);
        printDividerLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

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
                printExitMessage();
                break;
            default:
                printEcho(inputLine);
            }
        }
    }
}
