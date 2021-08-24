import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String[] itemList = new String[100];
        int itemCount = 0;
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n" ;

        String goodbye = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +  "____________________________________________________________\n";

        System.out.println(greeting);

        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(goodbye);
                return;
            }
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");

                for (int i = 1; i <= itemCount; i++) {
                    System.out.println(
                            Integer.toString(i)
                            + ". "
                            + itemList[i-1]
                    );
                }

                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n"
                        + "added: "
                        + line + "\n"
                        + "____________________________________________________________\n"
                );
                itemList[itemCount] = line;
                itemCount++;
            }

        }


    }
}
