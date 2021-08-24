import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printList(Task[] taskList) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            System.out.println(" " + (i + 1) + ". [" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greeting the User
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        //Echo commands entered by the user
        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();

        String exitString = "bye";
        String taskCommand = "list";
        Task[] taskList = new Task[100];
        int index = 0;

        while (!inWord.toLowerCase().equals(exitString)) {
            if (inWord.toLowerCase().equals(taskCommand)) {
                printList(Arrays.copyOf(taskList, index));
            } else if (inWord.toLowerCase().startsWith("done")) {
                int taskDoneIndex = Integer.parseInt(inWord.split(" ")[1]);
                if (taskDoneIndex <= 0 || taskDoneIndex > index) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Item out of Index");
                    System.out.println("____________________________________________________________");
                } else {
                    taskList[taskDoneIndex - 1].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   [" + taskList[taskDoneIndex - 1].getStatusIcon() + "] " + taskList[taskDoneIndex - 1].description);
                    System.out.println("____________________________________________________________");
                }
            } else {
                taskList[index] = new Task(inWord);
                index += 1;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + inWord);
                System.out.println("____________________________________________________________");
                System.out.println();
            }
            inWord = scan.nextLine();
        }

        //Exits when user types "bye"
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}