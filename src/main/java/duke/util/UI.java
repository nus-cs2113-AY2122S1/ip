package duke.util;

public class UI {
    public static final String DIVIDING_LINE = "________________________________________";

    public void printGreeting() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Greetings, human! I'm Duke. \nWhat can I do for you?");
        System.out.println(DIVIDING_LINE);
    }

    public void printFarewell() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Closing Duke. Have a nice day!");
        System.out.println(DIVIDING_LINE);
    }

    public void printDivLine() {
        System.out.println(DIVIDING_LINE);
    }

    public void showError(String error) {
        System.out.println("DukeException: " + error);
    }
}
