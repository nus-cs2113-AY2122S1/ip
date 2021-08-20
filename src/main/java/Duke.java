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

    public static void echoInput() {
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        while (!userInputString.equals("bye")) {
            printDividingLine();
            System.out.println(userInputString);
            printDividingLine();
            userInputString = userInput.nextLine();
        }
    }

    public static void main(String[] args) {
        printGreeting();
        echoInput();
        printFarewell();
    }
}
