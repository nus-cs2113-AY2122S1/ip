import java.util.Scanner;


public class Duke {

    public static void printMessage(String text) {
        System.out.println("\n____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int count = 0;

        printLine();
        System.out.println("Howdy there! I'm Fluke");
        System.out.println("What can I do for you today master?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                message = " ";
                printLine();
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
                }
                printLine();
            } else if (message.contains("done")) {
                String[] arrOfStr = message.split(" ");
                int index = Integer.parseInt(arrOfStr[arrOfStr.length - 1]) - 1;
                tasks[index].isDone();
                message = " ";
            } else if (!message.equals(" ")) {
                printMessage("added: " + message);
                tasks[count] = new Task(message);
                message = scanner.nextLine();
                count++;
            } else {
                message = scanner.nextLine();
            }
        }

        printMessage("Bye. Hope to serve you again master!");
        scanner.close();
    }
}
