import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        String logo = "    ____        _        \n"
                + "   |  _ \\ _   _| | _____ \n"
                + "   | | | | | | | |/ / _ \\\n"
                + "   | |_| | |_| |   <  __/\n"
                + "   |____/ \\__,_|_|\\_\\___|";

        String greetStart = "   ____________________________________________________________\n" +
                "       Hello! I'm Duke\n" +
                "       What can I do for you?\n" +
                "   ____________________________________________________________";

        String greetEnd = "   ____________________________________________________________\n" +
                "       Bye. Hope to see you again soon!\n" +
                "   ____________________________________________________________";

        System.out.println(logo);
        System.out.println(greetStart);
        line = in.nextLine();
        while (!line.equals("bye")) {
            reply(line);
            line = in.nextLine();
        }
        System.out.println(greetEnd);
    }

    public static void reply(String line) {
        String[] input = line.split(" ");
        if (input[0].equals("list")) {
            getAndPrintList();
        } else if (input[0].equals("done")) {
            setDone(Integer.parseInt(input[1]));
        } else {
            _printReply(addTask(line));
        }
    }

    private static void _printReply(String input) {
        System.out.println("   ____________________________________________________________\n" +
                "       " + input + "\n" +
                "   ____________________________________________________________");
    }

    public static String addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        return "added: " + input;
    }

    public static void getAndPrintList() {
        int i = 0;

        System.out.println("   ____________________________________________________________");
        System.out.println("       Here are the tasks in your list:");
        for (Task task : tasks) {
            if (i >= taskCount) {
                System.out.println("   ____________________________________________________________");
                return;
            }
            System.out.println("       " + (i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
            i++;
        }
    }

    public static void setDone(int index) {
        if (index - 1 >= taskCount) {
            _printReply("Entry does not exist.");
            return;
        }
        tasks[index - 1].markAsDone();
        _printReply("Nice! I've marked this task as done:\n" +
                "         [X] " + tasks[index - 1].getDescription());
    }
}
