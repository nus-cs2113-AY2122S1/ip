import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] entries = new Task[100];
        Scanner in = new Scanner(System.in);
        int entriesCount = 0;
        String userMessage = in.nextLine();

        while(!userMessage.equalsIgnoreCase("bye")){
            String userCommand = userMessage.contains(" ") ? userMessage.substring(0, userMessage.indexOf(" ")): userMessage;
            String userCommandDetails = userMessage.contains(" ") ? userMessage.substring(userMessage.indexOf(" ") + 1): userMessage;

            switch (userCommand) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                for(Task entry : Arrays.copyOf(entries,entriesCount)){
                    System.out.println((i+1) + ". " + "[" + entry.getSymbol() + "] ["
                            + entry.getStatusIcon() + "] " + entry.getDescription());
                    i++;
                }
                break;
            case "todo":
                entries[entriesCount] = new ToDo(userCommandDetails);
                System.out.println("Got it. I've added this task:");
                System.out.println("   [" + entries[entriesCount].getSymbol() + "] ["
                        + entries[entriesCount].getStatusIcon() + "] " + entries[entriesCount].description);
                entriesCount++;
                System.out.println("Now you have " + entriesCount + " tasks in the list.");
                break;
            case "deadline":
                int deadlineIndex = userCommandDetails.indexOf("/by");
                String taskDescription = userCommandDetails.substring(0, deadlineIndex - 1);
                String taskDeadline = userCommandDetails.substring(deadlineIndex);
                entries[entriesCount] = new Deadline(taskDescription, taskDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("   [" + entries[entriesCount].getSymbol() + "] ["
                        + entries[entriesCount].getStatusIcon() + "] " + entries[entriesCount].description);
                entriesCount++;
                System.out.println("Now you have " + entriesCount + " tasks in the list.");
                break;
            case "event":
                int eventDateTimeIndex = userCommandDetails.indexOf("/at");
                String eventDescription = userCommandDetails.substring(0, eventDateTimeIndex - 1);
                String eventDateTime = userCommandDetails.substring(eventDateTimeIndex);
                entries[entriesCount] = new Event(eventDescription, eventDateTime);
                System.out.println("Got it. I've added this task:");
                System.out.println("   [" + entries[entriesCount].getSymbol() + "] ["
                        + entries[entriesCount].getStatusIcon() + "] " + entries[entriesCount].description);
                entriesCount++;
                System.out.println("Now you have " + entriesCount + " tasks in the list.");
                break;
            case "done":
                String stringTaskIndex = userMessage.substring(userMessage.indexOf(" ") + 1);
                int intTaskIndex = Integer.parseInt(stringTaskIndex) - 1;
                entries[intTaskIndex].isDone = true;
                System.out.println("Nice! I've marked this task as done:\n"
                        + "   [" + entries[intTaskIndex].getSymbol()
                        + "] [" + entries[intTaskIndex].getStatusIcon() + "] " + entries[intTaskIndex].description);
                break;
            default:
                System.out.println(userCommand + " is an invalid command, try again");
                break;
            }
            userMessage = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}