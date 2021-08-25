import java.util.Scanner;
import java.util.Arrays;

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
        String[] inputs = line.split(" ");
        if (inputs[0].equals("list")) {
            getAndPrintList();
        } else if (inputs[0].equals("done")) {
            setDone(filterIndexes(Arrays.copyOfRange(inputs, 1, inputs.length)));
        } else {
            _printReply(addTask(line));
        }
    }

    private static void _printReply(String input) {
        System.out.println("   ____________________________________________________________\n" +
                "       " + input + "\n" +
                "   ____________________________________________________________");
    }

    public static int[] filterIndexes(String[] inputs) {
        int[] indexes = new int[inputs.length];
        int indexCount = 0;

        for (String input : inputs) {
            try {
                int index = Integer.parseInt(input);
                indexes[indexCount] = index;
                indexCount++;
            } catch (NumberFormatException nfe) {
                System.out.println(inputs[indexCount] + " is not a valid index");
            }
        }

        return Arrays.copyOf(indexes, indexCount);
    }

    public static String addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        return "added: " + input;
    }

    public static void getAndPrintList() {
        int i = 0;

        if (taskCount == 0) {
            _printReply("There are no tasks in your list.");
            return;
        }

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

    public static void setDone(int[] indexes) {
        int[] invalidIndexes = new int[indexes.length];
        int[] validIndexes = new int[indexes.length];
        int invalidCount = 0;
        int validCount = 0;

        System.out.println("   ____________________________________________________________");
        for (int index : indexes) {
            if (index - 1 >= taskCount) {
                invalidIndexes[invalidCount] = index;
                invalidCount++;
            } else {
                tasks[index - 1].markAsDone();
                validIndexes[validCount] = index;
                validCount++;
            }
        }

        if (validCount != 0) {
            System.out.println("       Nice! I've marked these tasks as done:");
            for (int i = 0; i < validCount; i++) {
                System.out.println("         [X] " + tasks[validIndexes[i] - 1].getDescription());
            }
        }

        if (invalidCount != 0) {
            System.out.println("");
            for (int i = 0; i < invalidCount; i++) {
                System.out.println("       Entry " + invalidIndexes[i] + " does not exist.");
            }
        }

        System.out.println("   ____________________________________________________________");
    }
}
