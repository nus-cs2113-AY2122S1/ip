package Ui;
import java.util.Scanner;

public class UserInterface {
    Scanner userInput = new Scanner(System.in);
    static String horizontal = " __________________________________________________\n";
    static String introduction = horizontal + "   Hello! I'm Duke\n" + "   What can I do for you?\n" + horizontal;
    static String farewell = horizontal + "   Bye! Hope to see you again soon!\n" + horizontal;

    public void UserInterface() {
        System.out.println(introduction);
    }

    public void printFarewell() {
        System.out.println(farewell);
    }

    public String getUserInput() {
        return userInput.nextLine().trim();
    }

    public void printOutput(String output) {
        System.out.println(horizontal + output + horizontal);
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
