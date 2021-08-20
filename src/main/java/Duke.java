import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hey there! I'm Duke.");
        System.out.println(" How may I help you?");

        String line;
        Scanner in = new Scanner(System.in);
        String exitString = "bye";
        line = in.nextLine();

        while (!line.equals(exitString)) {
            System.out.println("____________________________________________________________");
            System.out.println(" " + line);
            System.out.println("____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Goodbye! Hope to see you again soon.");
        System.out.println("____________________________________________________________");
    }
}
