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
        System.out.println("Closing Duke. Have a nice day!");
        printDividingLine();
    }

    public static void main(String[] args) {
        printGreeting();
        printFarewell();
    }
}
