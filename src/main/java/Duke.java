import java.util.Scanner;

public class Duke {

    public static void echo(String lineBreak) {
        System.out.println("Life is a mirror and will reflect back to "
                + "the thinker what\nhe thinks into it, echoing commencing");
        System.out.println(lineBreak);
        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        System.out.println(lineBreak);
        while (!response.equals("exit")) {
            System.out.println(response);
            System.out.println(lineBreak);
            response = in.nextLine();
            System.out.println(lineBreak);
        }
    }

    public static void printList(Task[] list, int listSize) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listSize; i++) {
            System.out.println(i + ".[" + list[i - 1].getStatusIcon() +
                    "] " + list[i - 1].description);
        }
    }

    public static void main(String[] args) {
        int listSize = 0;
        Task task;
        String item;
        Task[] list = new Task[100];
        Scanner in = new Scanner(System.in);
        String response = null;

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String lineBreak = "__________________" +
                "_________________________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(lineBreak);
        System.out.println("What can I do for you today?");
        System.out.println(lineBreak);
        response = in.nextLine();
        System.out.println(lineBreak);
        while (!response.equals("bye")) {

            if (response.equals("echo")) {
                echo(lineBreak);
            } else if ((response.length() > 3) &&
                    (response.substring(0, 3).equals("add"))) {
                item = response.substring(4);
                task = new Task(item);
                list[listSize] = task;

                System.out.println("added: " + item);
                System.out.println(lineBreak);
                listSize += 1;
            } else if (response.equals("list")) {
                printList(list, listSize);
                System.out.println(lineBreak);
            } else if ((response.length() > 4) &&
                    (response.substring(0, 4).equals("done"))) {
                int i = Integer.parseInt(response.substring(5)) - 1;
                list[i].markAsDone();
                System.out.println("Nice! i have marked this task as done:\n ["
                        + list[i].getStatusIcon() + "] " + list[i].getDescription());
                System.out.println(lineBreak);
            } else {
                System.out.println("bad command");
                System.out.println(lineBreak);
            }
            response = in.nextLine();
            System.out.println(lineBreak);
        }

        System.out.println("GoodBye, Hope to see again soon!");
        System.out.println(lineBreak);
    }
}
