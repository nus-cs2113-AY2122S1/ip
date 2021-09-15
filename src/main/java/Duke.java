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
        System.out.println("Hi! I'm Duke\n" + "How can I help make your life easier?");
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
                System.out.println("Wonderful! This task is now marked as done: ");
                System.out.println("[" + listIn[inputIndex].getStatus() + "] " + listIn[inputIndex].getName());
                System.out.println("...................................................");
            } else {
                listIn[currentIndex] = new Task(lineIn);
                System.out.println("Added: " + lineIn);
                currentIndex++;
                System.out.println("...................................................");
            }
        }
        System.out.println("Byebye! Have a wonderful day!");
        System.out.println("...................................................");
    }
}
