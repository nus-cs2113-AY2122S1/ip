import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Level-0
        System.out.println("What can I do for you today?");

        // Level-1
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        line = in.nextLine();
        while (true)
        {
            if (line.equals("bye"))
            {
                break;
            }
            else
            {
                System.out.println(line);
                line = in.nextLine();
            }
        }
        




    }
}
