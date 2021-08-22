import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);

        int taskNumber = 1;
        Task[] taskArray = new Task[101];

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                Task task = new Task(userInput, taskNumber);
                String taskType = task.getTask();

                if (taskType.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 1; i < taskArray.length; i ++) {
                        Task currentTask = taskArray[i];
                        if (currentTask == null) {
                            break;
                        } else {
                            System.out.println(currentTask.getTaskNumber() + "." + currentTask.toString());
                        }
                    }
                    System.out.println();

                } else if (taskType.contains("done")) {
                    String[] donetaskArray = taskType.split(" ");
                    int doneTaskNumber = Integer.parseInt(donetaskArray[1]);

                    Task doneTask = taskArray[doneTaskNumber];
                    System.out.println("Nice! I've marked this task as done:");
                    doneTask.setTaskAsDone();

                } else {
                    taskArray[taskNumber] = task;
                    System.out.println("added: " + task.getTask());
                    System.out.println();
                    taskNumber ++;
                }

            }
        }
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
}
