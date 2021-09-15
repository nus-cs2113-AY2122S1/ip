import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("...................................................");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("...................................................");
        Scanner in = new Scanner(System.in);
        String lineIn = "";
        do{
            lineIn = in.nextLine();
            System.out.println("...................................................");
            System.out.println(lineIn);
            System.out.println("...................................................");
        } while(!lineIn.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("...................................................");
    }
}
