import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        line = in.nextLine();

        while (!(line.equals("bye"))) {
            printLine();
            System.out.println(line);
            printLine();
            line = in.nextLine();
        }
        
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }
}
