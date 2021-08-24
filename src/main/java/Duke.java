import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

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
            System.out.println(response);
            System.out.println(lineBreak);
            response = in.nextLine();
            System.out.println(lineBreak);
        }
        System.out.println("GoodBye, Hope to see again soon!");
        System.out.println(lineBreak);
    }
}
