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

    public static void printList(String[] list, int listLength) {
        for (int i = 0; i < listLength; i++) {
            System.out.println((i+1) + ". " + list[i]);
        }
    }

    public static void createList() {
        String[] list = new String[100];
        int listLength = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list, listLength);
            } else {
                list[listLength] = line;
                listLength++;
                System.out.println("added: " + line);
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
