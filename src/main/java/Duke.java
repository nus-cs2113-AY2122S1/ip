import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________\n";
        Scanner in = new Scanner(System.in);
        System.out.println(line + logo +
                " Hello! I'm Duke\n" +
                " What's up? :p\n" + line);

        input = in.nextLine();
        while(true) {
            if(input.equals("bye")) {
                break;
            }
            System.out.println(line + input + "\n" + line);
            input = in.nextLine();
        }

        System.out.println(line + "Bye, seeya!\n" + line);
    }
}
