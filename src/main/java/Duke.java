import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] entries = new Task[100];
        int entriesCount = 0;
        Scanner in = new Scanner(System.in);
        String userMessage = in.nextLine();
        while(!userMessage.equalsIgnoreCase("bye")){
            if (userMessage.equalsIgnoreCase("list")){
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                for(Task entry : Arrays.copyOf(entries,entriesCount)){
                    System.out.println((i+1) + ". " + "[" + entry.getStatusIcon() + "] " + entry.description);
                    i++;
                }
            } else if (userMessage.startsWith("done")){
                String stringTaskIndex = userMessage.substring(userMessage.indexOf(" ") + 1);
                int intTaskIndex = Integer.parseInt(stringTaskIndex) - 1;
                entries[intTaskIndex].isDone = true;
                System.out.println("Nice! I've marked this task as done:\n" + "   ["
                        + entries[intTaskIndex].getStatusIcon() + "] " + entries[intTaskIndex].description);
            } else {
                System.out.println("Added " + userMessage);
                //Task t = new Task(userMessage);
                entries[entriesCount] = new Task(userMessage);
                entriesCount++;
            }
            userMessage = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}