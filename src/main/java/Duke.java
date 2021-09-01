import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( logo +
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner in = new Scanner(System.in);
        while(true) {
            String echo = in.nextLine();
            String echoLower = echo.toLowerCase();
            if (echoLower.equals("bye")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;
            }else {
                System.out.println("____________________________________________________________\n" + echo + System.lineSeparator() + "____________________________________________________________");
            }
        }
    }
}
