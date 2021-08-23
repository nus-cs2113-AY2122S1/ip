import java.util.Scanner;

public class Duke {

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    private static Boolean isFinished = false;
    private static int itemCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] items = new Task[100];

        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
        System.out.println();

        while (!isFinished) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                isFinished = true;
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here is your task list:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.print("\t" + (i + 1) + ". ");
                    System.out.println("[" + items[i].getStatusIcon() + "] " + items[i].description);
                }
            } else if (userInput.startsWith("done")) {
                String[] splitUserInput = userInput.split(" ");
                int indexToMark = Integer.parseInt(splitUserInput[1]) - 1;
                items[indexToMark].markAsDone();
                System.out.println("\tNice! I have marked this task as done:");
                System.out.println("\t\t[X] " + items[indexToMark].description);
            } else {
                items[itemCount] = new Task(userInput);
                System.out.println("\tadded: " + userInput);
                itemCount++;
            }
        }

        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();

    }
}
