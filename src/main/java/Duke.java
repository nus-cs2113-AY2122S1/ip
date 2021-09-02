import java.util.Scanner;

public class Duke {

    public static void done(String line, Task[] taskList) {
        String taskNumber = line.substring(5);
        int taskListElement = Integer.parseInt(taskNumber) - 1;
        taskList[taskListElement].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList[taskListElement].getDescription());
    }

    private static void list(Task[] taskList, int listCount) {
        int taskCount = 1;
        for (int i = 0; i < listCount; i++) {
            System.out.println(taskCount + "." + taskList[i]);
            taskCount++;
        }
    }

    private static Todo todo(String[] stringList, Task[] taskList, int listCount, String line) {
        char taskType = line.toUpperCase().charAt(0);
        String taskDisplay = line.substring(5);

        stringList[listCount] = taskDisplay;
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList[listCount] = todoTask;
        return todoTask;
    }

    private static Deadline deadline(String[] stringList, Task[] taskList, int listCount, String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList[listCount] = taskDisplay;
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList[listCount] = deadlineTask;
        return deadlineTask;
    }

    private static Event event(String[] stringList, Task[] taskList, int listCount, String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList[listCount] = taskDisplay;
        Event eventTask = new Event(taskDisplay, taskType, doBy);
        taskList[listCount] = eventTask;
        return eventTask;
    }

    private static void printTask(Task task, int listCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String[] stringList = new String[100];
        Task[] taskList = new Task[100];
        int listCount = 0;

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.startsWith("done")) {
                done(line, taskList);

            } else if (line.equals("list")) {
                list(taskList, listCount);

            } else if (line.startsWith("todo")) {
                Todo todoTask = todo(stringList, taskList, listCount, line);
                listCount++;
                printTask(todoTask, listCount);

            } else if (line.startsWith("deadline")) {
                Deadline deadlineTask = deadline(stringList, taskList, listCount, line);
                listCount++;
                printTask(deadlineTask, listCount);

            } else if (line.startsWith("event")) {
                Event eventTask = event(stringList, taskList, listCount, line);
                listCount++;
                printTask(eventTask, listCount);

            } else {
                System.out.println("Invalid command");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}





