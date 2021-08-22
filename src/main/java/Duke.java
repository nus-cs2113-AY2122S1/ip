import java.util.Scanner;

public class Duke {
    static String[] todolist = new String[100];
    static int itemCount = 0;

    public static void Echo(String message) {

        if (message.equalsIgnoreCase("bye")) {
            return;
        }

        if (message.equalsIgnoreCase("list")) {
            System.out.println("    ____________________________________________________________");
            for (int i = 0; i < itemCount; i++) {
                System.out.printf("    %d. %s%n", i+1, todolist[i]);
            }
            System.out.println("    ____________________________________________________________");
            return;
        }

        //else
        todolist[itemCount] = message; //add to todolist
        itemCount++;
        System.out.println("    ____________________________________________________________");
        System.out.println("     added:" + message);
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String line = "";
        Scanner in = new Scanner(System.in);
        while (!line.equalsIgnoreCase("bye")) { //while user input != bye, case insensitive
            line = in.nextLine();
            Echo(line);
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ___________________________________________________________");
        System.exit(0);//end of program
    }
}
