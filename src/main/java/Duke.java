import java.util.Scanner;

public class Duke {

    public static void printGreetingMessage(LizTextBanner liz) {
        System.out.println("Howdy! It's\n" + liz.getLizText() + liz.getLizLogo());
        printLine();
        System.out.println("Hey! I'm Lizzy the Lizard!");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void printInvalidDoneMessage() {
        System.out.println("Sorry bud, you can't check off what is not yet there :/");
    }

    public static void printGenericErrorMessage() {
        System.out.println("Error! Something is up with your inputs!");
    }

    public static void printTaskList(int taskIndex, Task tasks[]) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskIndex; i++) {
            if (tasks[i].getType().equals("T")) {
                System.out.printf("[%s][%s] %s%n",tasks[i].getType(), tasks[i].getStatusIcon(),tasks[i].getDescription());
            } else if (tasks[i].getType().equals("D")){
                System.out.printf("[%s][%s] %s(by:%s)%n",tasks[i].getType(), tasks[i].getStatusIcon(),tasks[i].getDescription(), tasks[i].getByDateTime());
            } else {
                System.out.printf("[%s][%s] %s(at:%s)%n",tasks[i].getType(), tasks[i].getStatusIcon(), tasks[i].getDescription(), tasks[i].getStartAndEndTime());
            }
        }
    }

    public static void addNewTodo(String description, int taskIndex, Task tasks[]) {
        tasks[taskIndex] = new ToDo(description);
    }

    public static void addNewDeadline(String description, String byDateTime, int taskIndex, Task tasks[]) {
        tasks[taskIndex] = new Deadline(description, byDateTime);
    }

    public static void addNewEvent(String description, String startAndEndTime, int taskIndex, Task tasks[]) {
        tasks[taskIndex] = new Event(description, startAndEndTime);
    }

    public static void printAddedTask(Task task, int taskIndex) {
        System.out.println("Got it. I've added this task:");
        if (task.getType().equals("T")) {
            System.out.printf("[%s][ ] %s%n",task.getType(),task.getDescription());
        } else if (task.getType().equals("D")){
            System.out.printf("[%s][ ] %s(by:%s)%n",task.getType(),task.getDescription(), task.getByDateTime());
        } else {
            System.out.printf("[%s][ ] %s(at:%s)%n",task.getType(),task.getDescription(), task.getStartAndEndTime());
        }

        System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list.");
    }



    public static void markAsDone(Task task) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println("Slow down there bud! You've already completed this task!");
        } else {
            task.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("[%s][X] %s%n",task.getType(), task.getDescription());
        }
    }

    public static void main(String[] args) {

        LizTextBanner liz = new LizTextBanner();
        Scanner in = new Scanner(System.in);

        Task tasks[] = new Task[100];
        int taskIndex = 0;
        printGreetingMessage(liz);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            printLine();
            String[] lineArgs = line.split(" ");
            if (line.equals("list")) {
                printTaskList(taskIndex, tasks);
            } else if (lineArgs[0].equals("todo")) {
                addNewTodo(line.substring(5), taskIndex, tasks);
                printAddedTask(tasks[taskIndex], taskIndex);
                taskIndex++;
            } else if (lineArgs[0].equals("deadline")) {
                int backslashIndex = line.indexOf('/');
                addNewDeadline(line.substring(9, backslashIndex), line.substring(backslashIndex + 3), taskIndex, tasks);
                printAddedTask(tasks[taskIndex], taskIndex);
                taskIndex++;
            } else if (lineArgs[0].equals("event")) {
                int backslashIndex = line.indexOf('/');
                addNewEvent(line.substring(6, backslashIndex), line.substring(backslashIndex + 3), taskIndex, tasks);
                printAddedTask(tasks[taskIndex], taskIndex);
                taskIndex++;
            } else if (lineArgs[0].equals("done")) {
                int doneIndex = Integer.parseInt(lineArgs[1]) - 1;
                if (doneIndex >= taskIndex || doneIndex < 0) {
                    printInvalidDoneMessage();
                } else {
                    markAsDone(tasks[doneIndex]);
                }
            } else {
                printGenericErrorMessage();
            }

            printLine();
            line = in.nextLine();
        }
        printExitMessage();
    }
}
