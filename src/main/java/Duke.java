import java.util.Scanner;

public class Duke {

    public static void echoInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
    }

    public static void printList(Task[] list, int listSize) {
        for (int i = 0; i < listSize; i++) {
            System.out.println((i + 1) + "." + list[i].getStatusSymbol() + " " + list[i].getName());
        }
    }

    public static void createList() {
        Task[] list= new Task[100];
        int listSize = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list, listSize);
            } else if (line.contains("done")) {
                int taskNumber = Integer.parseInt(line.substring(5));
                list[taskNumber - 1].setDone();
            } else {
                list[listSize] = new Task(line);
                listSize++;
            }
            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        createList();
        System.out.println("Bye. Hope to see you again!");
    }
}
