package DukeUtility;

import TypeOfTasks.Task;

public class PrintManager {
    public static final String GREETING_MESSAGE = "SQUAWK! See you next time! :)";
    public static final String LOGO = " ______   _       _   _\n"
            + "|  __  | | | ___ | | | | \n"
            + "| |  | | | |/   \\| | | | \n"
            + "| |__| | |   / \\   | | |____\n"
            + "|______| |__/   \\__| |______|\n";
    
    
    
    public static void printWelcome() {
        System.out.println(LOGO);
        PrintManager.printLine();
        System.out.println("SQUAWK!!!");
        System.out.println("How can I help you?");
        PrintManager.printLine();
    }
    
    public static void printBye() {
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
        PrintManager.printLine();
        System.out.println("Oooh I see you've done task " + taskNumber);
        PrintManager.printLine();
    }
    
    public static void printDeletionMsg(int taskCount, Task deletedTask) {
        PrintManager.printLine();
        PrintManager.deleteMessage(deletedTask.getDescription());
        System.out.println("There are currently " + taskCount + " task now!");
        PrintManager.printLine();
    }
    public static void printTaskCount(int taskCount, String command) {
        PrintManager.printLine();
        echoMessage(command);
        System.out.println("There are currently " + taskCount + " task now!");
        PrintManager.printLine();
    }
    
    public static void echoMessage(String line) {
        System.out.println("Owl: I've added that!\nOwl: You added this: " + line);
    }
    public static void deleteMessage(String line) {
        System.out.println("Owl: I've deleted that!\nOwl: You deleted this: " + line);
    }
    
    public static void printListingMsg() {
        PrintManager.printLine();
        System.out.println("This are all the things I've remembered for you:");
        PrintManager.printLine();
    }
    
    
}
