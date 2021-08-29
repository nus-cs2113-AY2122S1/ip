import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void printList() {
        Task current;
        String line = "____________________________________________________________\n";
        System.out.println(" Here are the tasks in your list:");
        System.out.print(line);
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(" " + i + ". " + task.listTask());
            i = i + 1;
        }
        System.out.println(line);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        String input;
        String command;
        String description;
        Scanner in = new Scanner(System.in);
        String addition;
        String bye = "____________________________________________________________\n"
                +  " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        String doneMessage;
        String invalidInput = "____________________________________________________________\n"
                +  " Oops! Looks like I can't read that yet! Please input a valid command.\n"
                + "____________________________________________________________\n";
        do {
            input = in.nextLine();
            String[] inputWords = input.split(" ");
            command = inputWords[0];
            switch (command) {
            case "bye":
                break;
            case "done":
                int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                tasks[taskIndex].setDone();
                doneMessage = "____________________________________________________________\n"
                        + "Nice! I've marked this task as done: \n"
                        + "[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description + "\n"
                        + "____________________________________________________________\n";
                System.out.println(doneMessage);
                break;
            case "todo":
                description = input.substring(5);
                tasks[taskCount] = new ToDo(description);
                taskCount = taskCount + 1;
                addition = "____________________________________________________________\n"
                    + " added: " + description + "\n"
                    + "____________________________________________________________\n";
                System.out.println(addition);
                break;
            case "deadline":
                if (!input.contains("/by")) {
                    System.out.println(invalidInput);
                    break;
                }
                String endDate;
                description = input.substring(9, input.indexOf("/by") - 1);
                endDate = input.substring(input.indexOf("/by") + 4);
                tasks[taskCount] = new Deadline(description, endDate);
                taskCount = taskCount + 1;
                addition = "____________________________________________________________\n"
                        + " added: " + description + "\n"
                        + "____________________________________________________________\n";
                System.out.println(addition);
                break;
            case "event":
                if (!input.contains("/at")) {
                    System.out.println(invalidInput);
                    break;
                }
                String startEndTime;
                description = input.substring(6, input.indexOf("/at") - 1);
                startEndTime = input.substring(input.indexOf("/at") + 4);
                tasks[taskCount] = new Event(description, startEndTime);
                taskCount = taskCount + 1;
                addition = "____________________________________________________________\n"
                        + " added: " + description + "\n"
                        + "____________________________________________________________\n";
                System.out.println(addition);
                break;
            case "list":
                printList();
                break;
            default:
                break;
            }

        } while (!command.equals("bye"));
        System.out.println(bye);
    }
}
