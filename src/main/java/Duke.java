import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        printBanner();
        String cmd;
        Scanner in = new Scanner(System.in);

        Pattern r = Pattern.compile("done [0-9]");
        while (true) {
            System.out.print("> ");
            cmd = in.nextLine();
            Matcher m = r.matcher(cmd);
            if (cmd.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye, Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (cmd.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i+1) + ": " + "[" + tasks.get(i).getStatus() +
                            "] " + tasks.get(i).getDescription());
                }
                System.out.println("____________________________________________________________");
            } else if (m.find()) {
                String[] values = cmd.split(" ");
                int value = Integer.parseInt(values[1]);
                if (value > tasks.size() || value < 1) {
                    System.out.println(" (!) Invalid Value! ");
                } else {
                    tasks.get(value - 1).setStatus(true);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked the task as done:");
                    System.out.println("[" +tasks.get(value - 1).getStatus() + "] "
                            + tasks.get(value - 1).getDescription());
                    System.out.println("____________________________________________________________");
                }

            } else {
                Task newTask = new Task(cmd);
                tasks.add(newTask);
                System.out.println(" (+) Added: "+cmd);
            }
        }
    }

    public static void printBanner() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke, what can I do for you?");
    }
}
