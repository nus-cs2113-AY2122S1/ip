import java.util.Scanner;

public class Duke {

    private static Boolean isFinished = false;
    private static int itemCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] items = new Task[100];

        printIntro();

        while (!isFinished) {
            String userInput = sc.nextLine();
            String command = userInput.split(" ")[0];

            if (command.equals("bye")) {
                isFinished = true;
                break;
            } else if (command.equals("list")) {
                drawLine();
                System.out.println("\tHere is your task list:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.print("\t\t" + (i + 1) + ". ");
                    System.out.println(items[i]);
                }
                drawLine();

            } else if (command.equals("done")) {
                String arg = userInput.split(" ")[1];
                int indexToMark = Integer.parseInt(arg) - 1;
                items[indexToMark].markAsDone();
                System.out.println("\tNice! I have marked this task as done:");
                System.out.println("\t\t" + items[indexToMark]);

            } else if (command.equals("todo")){
                String arg = userInput.split(" ",2)[1];
                items[itemCount] = new Todo(arg);
                incrementItemCount(items[itemCount]);

            } else if (command.equals("deadline")){
                String arg = userInput.split(" ",2)[1];
                String[] splitArg = arg.split("/",2);
                String description = splitArg[0].trim();
                String by = splitArg[1].substring(3);

                items[itemCount] = new Deadline(description,by);
                incrementItemCount(items[itemCount]);

            } else if (command.equals("event")){
                String arg = userInput.split(" ",2)[1];
                String[] splitArg = arg.split("/",2);
                String description = splitArg[0].trim();
                String at = splitArg[1].substring(3);

                items[itemCount] = new Event(description, at);
                incrementItemCount(items[itemCount]);

            } else {
                System.out.println("Invalid command, try again");
            }
        }

        drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    private static void incrementItemCount(Task item) {
        System.out.println("\tTask Added:");
        System.out.println("\t\t" + item);
        itemCount++;
        System.out.println("\tYou now have " + itemCount + " tasks");
    }

    private static void printIntro() {
        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
        System.out.println();
    }
    public static void drawLine() {
        System.out.println("____________________________________________________________");
    }
}
