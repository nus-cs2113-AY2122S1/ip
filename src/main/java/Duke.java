import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        String horizontalLine = "------------------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String userInput = "";
        Task taskAdded;

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke!\n" + "So far, I can create a simple task list for you.\n" + "What can I do for you?\n");
        System.out.println("Use the following commands!\n" + "To add a task: add [taskName]\n" + "To mark a task as done: done [taskNumber]");
        System.out.println("To view your current task list, simply type: list\n" + "To end your chat with me, simply type: bye");
        System.out.println(horizontalLine);

        while(!userInput.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if(userInput.contains("add")) {
                String task = userInput.replace("add","").trim();
                taskAdded = new Task(task);
                taskList.add(taskAdded);
                System.out.println(horizontalLine);
                System.out.println("I can do that! I have added:[" + taskAdded.description + "] to your task list!");
                System.out.println(horizontalLine);
            } else if(userInput.contains("list")){
                int listIndex = 1;
                System.out.println(horizontalLine);
                for (Task task : taskList) {
                    System.out.println(listIndex + ".[" + task.getStatusIcon() + "] " + task.description);
                    listIndex++;
                }
                System.out.println(horizontalLine);
            } else if(userInput.contains("done")){
                String[] splitTaskToBeMarkedAsDone = userInput.split(" ");
                int i = (Integer.parseInt(splitTaskToBeMarkedAsDone[1])) - 1;
                if(i > taskList.size() - 1) {
                    System.out.println("Invalid task number");
                } else {
                    Task taskUpdated = taskList.get(i);
                    taskUpdated.updateIsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + (i + 1) + ".[" + taskUpdated.getStatusIcon() + "] " + taskUpdated.description);
                }
            } else if (!userInput.contains("bye")){
                System.out.println("Aw man! I am unable to " + userInput + " yet! Please specify a different function! :D");
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
