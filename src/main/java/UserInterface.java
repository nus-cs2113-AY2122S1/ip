import java.util.Scanner;

public class UserInterface {
    private static Scanner in = new Scanner(System.in);
    private static String userInput;
    public static void talkToUser() {
        do {
            getUserInput();
            processUserInput();
        } while (true);
    }
    private static void getUserInput() {
        System.out.println();
        userInput = in.nextLine();
        System.out.println(FormatLines.divider);
    }
    private static void processUserInput(){
        if (userInput.contains("done")) {
            int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
            TaskManager.markDone(taskNumber);
            return;
        } else if (userInput.equals("list")) {
            TaskManager.printTasks();
            return;
        } else if (userInput.equals("bye")) {
            System.exit(0);
        }
        TaskManager.addTask(userInput);
        System.out.println("\tadded: " + userInput);
        System.out.println(FormatLines.divider);
    }




}
