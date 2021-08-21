import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello from\n" + logo + "\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        List list = new List();
        keepAsking: while (true) {
            String command = in.nextLine();
            switch (command){
            case "bye":
                break keepAsking;
            case "list":
                list.printList();
                break;
            default:
                list.addItem(command);
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");

    }
}
