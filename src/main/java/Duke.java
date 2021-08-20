import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        while(!userInput.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if(!userInput.contains("list") && !userInput.contains("bye") && !userInput.contains("done")) {
                taskAdded = new Task(userInput);
                taskList.add(taskAdded);
                System.out.println(horizontalLine);
                System.out.println("Added: " + taskAdded.description);
                System.out.println(horizontalLine);
            } else if(!userInput.contains("bye") && !userInput.contains("done")){
                int listIndex = 1;
                System.out.println(horizontalLine);
                for (Task task : taskList) {
                    System.out.println(listIndex + ".[" + task.getStatusIcon() + "] " + task.description);
                    listIndex++;
                }
                System.out.println(horizontalLine);
            }
            if(userInput.contains("done")){
                String[] splitUserInput = userInput.split(" ");
                int i = (Integer.parseInt(splitUserInput[1])) - 1;
                Task taskUpdated = taskList.get(i);
                taskUpdated.updateIsDone();
                System.out.println("Nice! I've marked this task as done: \n" + (i + 1) + ".[" + taskUpdated.getStatusIcon() + "] " + taskUpdated.description);
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
