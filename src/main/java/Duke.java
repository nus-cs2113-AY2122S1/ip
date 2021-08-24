import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String user_input;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        user_input = in.nextLine();
        while (!(user_input.contentEquals("bye"))) {
            System.out.println(user_input);
            user_input = in.nextLine();
        }

        System.out.println("byee");
    }
}
