import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("...................................................");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("...................................................");
        Scanner in = new Scanner(System.in);
        String lineIn = "";
        Task[] listIn = new Task[100];
        int inputIndex = 0;
        int currentIndex = 0;
        while(!lineIn.equals("bye")) {
            lineIn = in.nextLine();
            String[] lineInput = lineIn.split(" ");
            System.out.println("...................................................");
            if (lineInput[0].equals("bye")) {
                break;
            }
            if (lineInput[0].equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < currentIndex; i++) {
                    System.out.println((i + 1) + ".[" + listIn[i].getStatus() + "] " + listIn[i].getName());
                }
                System.out.println("...................................................");
            } else if (lineInput[0].equals("done")) {
                inputIndex = Integer.parseInt(lineInput[1]) - 1;
                listIn[inputIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + listIn[inputIndex].getStatus() + "] " + listIn[inputIndex].getName());
                System.out.println("...................................................");
            } else {
                listIn[currentIndex] = new Task(lineIn);
                System.out.println("Added: " + lineIn);
                currentIndex++;
                System.out.println("...................................................");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("...................................................");
    }
}
