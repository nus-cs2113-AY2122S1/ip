import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {
    /**
     * Prints the task list
     *
     * @param list Task list represented in array.
     * @param index Index number of last task in list array.
     */
    public static void printList(Task[] list, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            String taskStatus = list[i].getStatusIcon();
            System.out.println((i + 1) + "." + taskStatus + " " + list[i].description);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________\n";
        String helloGreeting = "Hello! I'm Duke \n" + "What can I do for you?\n";
        String byeGreeting = "Bye. Hope to see you again soon!\n";
        System.out.println(line + helloGreeting + line);

        Scanner input = new Scanner(System.in);
        String command = "";
        Task[] tasks = new Task[100];
        int index = 0;

        while (!(command.equalsIgnoreCase("bye"))) {
            command = input.nextLine();
            if ((command.equalsIgnoreCase("bye"))) {
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                System.out.println(line);
                printList(tasks, index);
                System.out.println(line);
            } else if ((command.toLowerCase()).contains("done")) {
                String[] extractedCommand = command.split(" ");
                int itemNo = parseInt(extractedCommand[1]);
                tasks[itemNo - 1].markAsDone();
                String taskStatus = tasks[itemNo - 1].getStatusIcon();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskStatus + " " + tasks[itemNo - 1].description);
                System.out.println(line);
            } else {
                tasks[index] = new Task(command);
                System.out.println(line + "added: " + command + "\n" + line);
                index++;
            }
        }
        System.out.println(line + byeGreeting + line);
    }
}
