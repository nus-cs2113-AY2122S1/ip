package DukeUtility;

import TypeOfTasks.Task;
import java.util.Scanner;

public class Ui {
    public static final String GREETING_MESSAGE = "SQUAWK! See you next time! :)";
    public static final String LOGO = " ______   _       _   _\n"
            + "|  __  | | | ___ | | | | \n"
            + "| |  | | | |/   \\| | | | \n"
            + "| |__| | |   / \\   | | |____\n"
            + "|______| |__/   \\__| |______|\n";
    
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();        
    }
    
    
    public void printWelcome() {
        System.out.println(LOGO);
        Ui.printLine();
        System.out.println("SQUAWK!!!");
        System.out.println("How can I help you?");
        Ui.printLine();
    }
    
    public void printBye() {
        printLine();
        System.out.println(GREETING_MESSAGE);
        printLine();
    }
    
    public static void printLine() {
        for(int i = 0; i <= 50; i++) {
            System.out.print("~");
        }
        System.out.println(" ");
    }
    
    public static void printTaskCompletionMsg(int taskNumber) {
        Ui.printLine();
        System.out.println("Oooh I see you've done task " + taskNumber);
        Ui.printLine();
    }
    
    public static void printDeletionMsg(int taskCount, Task deletedTask) {
        Ui.printLine();
        Ui.deleteMessage(deletedTask.getDescription());
        System.out.println("There are currently " + taskCount + " task now!");
        Ui.printLine();
    }
    
    public static void printTaskCount(int taskCount, String command) {
        Ui.printLine();
        echoMessage(command);
        System.out.println("There are currently " + taskCount + " task now!");
        Ui.printLine();
    }
    
    public static void echoMessage(String line) {
        System.out.println("Owl: I've added that!\nOwl: You added this: " + line);
    }
    
    public static void deleteMessage(String line) {
        System.out.println("Owl: I've deleted that!\nOwl: You deleted this: " + line);
    }
    
    public static void printListingMsg() {
        Ui.printLine();
        System.out.println("Squawkkk this is everything you have!!!");
        Ui.printLine();
    }
    
}
