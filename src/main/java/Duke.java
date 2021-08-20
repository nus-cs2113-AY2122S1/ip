import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        printDividingLine();
        System.out.println("Greetings, human! I'm Duke. \nWhat can I do for you?");
        printDividingLine();
    }

    public static void printDividingLine() {
        System.out.println("____________________________________");
    }

    public static void printFarewell() {
        printDividingLine();
        System.out.println("Closing Duke. Have a nice day!");
        printDividingLine();
    }

    public static void storeTasks() {
        String[] userTasks = new String[100];
        int stringIndex = 0;
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        while (!userInputString.equals("bye")) {
            printDividingLine();
            if (userInputString.equals("list")) {
                for (int i = 0; i < stringIndex; i++) {
                    System.out.println((i + 1) + ". " + userTasks[i]);
                }
            } else {
                userTasks[stringIndex] = userInputString;
                stringIndex++;
                System.out.println("Added task: " + userInputString);
            }
            printDividingLine();
            userInputString = userInput.nextLine();
        }
    }

    public static void main(String[] args) {
        printGreeting();
        storeTasks();
        printFarewell();
    }
}
