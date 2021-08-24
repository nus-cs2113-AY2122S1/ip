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

    public static void printList(String[] list, int listSize) {
        for (int i = 1; i <= listSize; i++) {
            System.out.println(i + ". " + list[i - 1]);
        }
    }

    public static void main(String[] args) {
        int listSize = 0;
        String item;
        String[] list = new String[100];
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
            }
            else if ((response.length() > 3) &&
            (response.substring(0,3).equals("add"))) {
                item = response.substring(4);
                list[listSize] = item;
                System.out.println("added: " + item);
                System.out.println(lineBreak);
                listSize += 1;
            }
            else if (response.equals("list")){
                printList(list, listSize);
                System.out.println(lineBreak);
            }
            else {
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
