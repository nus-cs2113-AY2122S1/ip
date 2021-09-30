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
        int Index = 0;
        int curIndex = 0;
        Scanner in = new Scanner(System.in);
        String string = "";
        Task[] Array = new Task[100];
        while(!string.equals("bye")) {
            string = in.nextLine();
            String[] Input = string.split(" ");
            System.out.println("...................................................");
            if (Input[0].equals("bye")) {
                break;
            }
            if (Input[0].equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < curIndex; i++) {
                    System.out.println((i + 1) + ".[" + Array[i].getStatus() + "] " + Array[i].getName());
                }
                System.out.println("...................................................");
            } else if (Input[0].equals("done")) {
                Index = Integer.parseInt(Input[1]) - 1;
                Array[Index].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + Array[Index].getStatus() + "] " + Array[Index].getName());
                System.out.println("...................................................");
            } else {
                Array[curIndex] = new Task(string);
                System.out.println("Added: " + string);
                curIndex++;
                System.out.println("...................................................");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("...................................................");
    }
}