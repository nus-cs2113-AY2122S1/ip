import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    static String horizontal = " __________________________________________________\n";
    static String introduction = horizontal + "   Hello! I'm Duke\n" + "   What can I do for you?\n" + horizontal;
    static String farewell = horizontal + "   Bye. Hope to see you again soon!\n" + horizontal;
//    static Task[] taskList = new Task[100];
    static ArrayList<Task> taskList = new ArrayList<>();
    static int taskCount = 0;
    static int descriptionIndex = 0;

    public static void main(String[] args) {
        System.out.println(introduction);

        try {
            FileClass.loadTasks(taskList);
            taskCount = taskList.size();
            System.out.println(horizontal + "Here are the tasks in your list:\n");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + ". [" + taskList.get(i).getTaskType() + "]" + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
            }
            System.out.println(horizontal);
        } catch (FileNotFoundException e) {
            System.out.println("Task file could not be found");
        }

        String line;
        String[] splicedLine;
        String commandWord;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String task;
        String taskDate;

        while (!line.equals("bye")) {

            splicedLine = line.split(" ");
            commandWord = splicedLine[0];

            if ((commandWord.equals("done") || commandWord.equals("todo") || commandWord.equals("deadline") || commandWord.equals("event")) && splicedLine.length == 1) {
                System.out.println(horizontal + "Oops! The description of " + commandWord + " cannot be empty!\n" + horizontal);
            } else if (line.equals("list")) {
                if (taskCount == 0) {
                    System.out.println(horizontal + "You don't have any items on your list! Add something :)\n" + horizontal);
                } else {
                    System.out.println(horizontal + "Here are the tasks in your list:\n");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + ". [" + taskList.get(i).getTaskType() + "]" + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
                    }
                    System.out.println(horizontal);
                }
            } else if (commandWord.equals("done")) {
                descriptionIndex = Integer.parseInt(splicedLine[1]);
                line = taskList.get(descriptionIndex-1).getDescription();
                System.out.println(horizontal + "Nice! I\'ve marked this task as done:\n" + "   [" + taskList.get(descriptionIndex-1).getTaskType() + "][X] " + line + "\n" + horizontal);
                taskList.get(descriptionIndex-1).markAsDone();
            } else if (commandWord.equals("todo")) {
                task = line.substring(line.indexOf(' '));
                taskDate = "";
                taskList.add(new Todo(task, false));
                System.out.println(horizontal + taskList.get(taskCount).toString() + "Now you have " + ++taskCount + " tasks on the list.\n" + horizontal);
            } else if (commandWord.equals("deadline")) {
                task = line.substring(line.indexOf(' ')+1, line.indexOf('/')-1);
                taskDate = line.substring(line.indexOf('/')+4);
                taskList.add(new Deadline(task, false, taskDate));
                System.out.println(horizontal + taskList.get(taskCount).toString() + "Now you have " + ++taskCount + " tasks on the list.\n" + horizontal);
            } else if (commandWord.equals("event")) {
                task = line.substring(line.indexOf(' ')+1, line.indexOf('/')-1);
                taskDate = line.substring(line.indexOf('/')+4);
                taskList.add(new Event(task, false, taskDate));
                System.out.println(horizontal + taskList.get(taskCount).toString() + "Now you have " + ++taskCount + " tasks on the list.\n" + horizontal);
            } else if (commandWord.equals("delete")) {
                descriptionIndex = Integer.parseInt(splicedLine[1]);
                line = taskList.get(descriptionIndex - 1).getDescription();
                System.out.println(horizontal + "Noted. I\'ve removed this task:\n" + "   [" + taskList.get(descriptionIndex-1).getTaskType() + "][" + taskList.get(descriptionIndex-1).getStatusIcon() + "] " + line);
                System.out.println("Now you have " + --taskCount + " tasks on the list.\n" + horizontal);
                taskList.remove(taskList.get(descriptionIndex-1));
            } else {
                System.out.println(horizontal + "Oops! I don't know what that means :-(\n" + horizontal);
            }
            try {
                FileClass.writeToFile("../data/duke.txt", taskList);
            } catch ( IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            line = in.nextLine();
        }
        System.out.println(farewell);
    }
}
