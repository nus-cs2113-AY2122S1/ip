import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        printDividingLine();
        System.out.println("Greetings, human! I'm Duke. \nWhat can I do for you?");
        printDividingLine();
    }

    public static void printDividingLine() {
        System.out.println("________________________________________");
    }

    public static void printFarewell() {
        printDividingLine();
        System.out.println("Closing Duke. Have a nice day!");
        printDividingLine();
    }

    public static void storeTasks() {
        Task[] userTasks = new Task[100];
        int taskIndex = 0;
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        while (!userInputString.equals("bye")) {
            printDividingLine();
            if (userInputString.equals("list")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println((i + 1) + ". " + userTasks[i]);
                }
            } else if (userInputString.startsWith("done")) {
                String[] words = userInputString.split(" ");
                int completeIndex = Integer.parseInt(words[1]) - 1;
                if (completeIndex >= 0 && completeIndex < taskIndex) {
                    userTasks[completeIndex].markComplete();
                    System.out.println("Task " + userTasks[completeIndex].description + " marked as complete.");
                } else {
                    System.out.println("Error: index outside range.");
                }
            } else {
                userTasks[taskIndex] = new Task(userInputString);
                taskIndex++;
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
