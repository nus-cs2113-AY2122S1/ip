import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void greeting() {
        String greeting = " Hello! I'm pepepopo\n" +
                " What can I do for you?";
        System.out.println(greeting);
        divider();
    }

    public static void divider() {
        String divider = "\n____________________________________________________________";
        System.out.println(divider);
    }

    public static void bye() {
        String bye = " Bye. pepepopo loves you <3!";
        System.out.println(bye);
        divider();

    }

    public static void added(String text) {
        System.out.println("oooo, pepepopo has added a new task: " + text);
    }

    public static void done(Task[] list, int taskNumber) {
        System.out.println("Yay! pepepopo has marked your task as done:");
        System.out.println(list[taskNumber].getStatusIcon() + " " + list[taskNumber].getDescription());
        divider();
    }

    public static String input() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public static boolean isDone(String input) {
        if (!input.contains("done")) {
            return false;
        } else if (!Character.isDigit(input.charAt(input.length() - 1))) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        greeting();

        Task[] tasks = new Task[100];
        int taskCount = 0;

        boolean isBye;
        boolean isList;
        boolean isDone;

        do {
            String input = input();
            isBye = input.equals("bye");
            isList = input.equals("list");
            isDone = isDone(input);

            if (isBye) {
                bye();
            }
            else if (isList) {
                Task[] listPrint = Arrays.copyOf(tasks, taskCount);
                int curr = 1;
                for (Task item : listPrint) {
                    System.out.println(curr + "." + item.getStatusIcon() + " " + item.getDescription());
                    curr += 1;
                }
                divider();
            } else if (isDone) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskNumber].setDone();
                done(tasks, taskNumber);
            } else {
                Task t = new Task(input);
                tasks[taskCount] = t;
                taskCount += 1;
                added(input);
                divider();
            }

        } while (!isBye);

    }
}
