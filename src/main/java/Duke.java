import java.util.Scanner;

public class Duke {

    private static Scanner in = new Scanner(System.in);
    private static String line = "";
    private static String[] list = new String[100];
    private static int tasks_num = 0;

    public static void add(String args){
        if (args.length() > 0) {
            System.out.println("added: " + args);
            list[tasks_num] = args;
            tasks_num++;
        }
    }

    private static void list_out(){
        for (int i = 1; i <= tasks_num; i++) {
            System.out.println(i + ". " + list[i - 1]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

        System.out.println("Hello from\n" + logo);
        System.out.println(greet);

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                list_out();
            }
            else {
                add(line);
            }
            line = in.nextLine();
        }

        System.out.println(exit);
    }
}
