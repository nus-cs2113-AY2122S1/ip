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
        System.out.println("I am programmed to engage in various forms of human conversation.");
        System.out.println("I am currently able to perform two tasks for you, echo and list.");
        System.out.println("I understand that is the gist of what human conversation is.");
        System.out.println("enter 1 for echo and 2 for list");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        if (line.equals("1")) {
            System.out.println("I will now repeat everything you say to me back to you");
            echoInput();
        } else if (line.equals("2")) {
            System.out.println("I will keep track of a list for you");
            createList();
        } else {
            System.out.println("I do not understand that right now, all i can do for you is echo or list");
            line = in.nextLine();
        }
        System.out.println("Farewell. I hope to engage you in conversation again in the future");
    }
}
