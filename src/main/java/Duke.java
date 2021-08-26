import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greeting();
    }

    public static void greeting(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
        Scanner in = new Scanner(System.in);
        String order = in.nextLine();
        while (!order.equals("bye")){
            System.out.println("____________________________________________________________");
            System.out.println(order);
            System.out.println("____________________________________________________________");
            order = in.nextLine();
        }

        System.out.println("Bye. Hope to see you a gain soon!\n");
        System.out.println("____________________________________________________________");
    }
}
