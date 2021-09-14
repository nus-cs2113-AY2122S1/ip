import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String longLine = "____________________________________________________________";

    public static void printLongLine() {
        System.out.println(longLine);
    }

    public static void welcomeMessage() {
        System.out.println(longLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(longLine);
    }

    public static void farewellMessage() {
        System.out.println(longLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(longLine);
    }

    public static void main(String[] args) {
        welcomeMessage();
        ArrayList<Task> entries = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int entriesCount = 0;
        String userMessage = in.nextLine();

        while(!userMessage.equalsIgnoreCase("bye")){
            String userCommand = userMessage.contains(" ")
                    ? userMessage.substring(0, userMessage.indexOf(" ")): userMessage;
            String userCommandDetails = userMessage.contains(" ")
                    ? userMessage.substring(userMessage.indexOf(" ") + 1): userMessage;

            switch (userCommand) {
            case "list":
                printLongLine();
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                for(Task entry : entries){
                    System.out.println((i+1) + ". " + "[" + entry.getSymbol() + "] ["
                            + entry.getStatusIcon() + "] " + entry.getDescription());
                    i++;
                }
                printLongLine();
                break;
            case "todo":
                if (userCommandDetails.equals("todo")) {
                    System.out.println("Hey! The description of a todo cannot be empty...");
                }
                else {
                    entries.add(entriesCount, new ToDo(userCommandDetails));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   [" + entries.get(entriesCount).getSymbol() + "] ["
                            + entries.get(entriesCount).getStatusIcon() + "] " + entries.get(entriesCount).description);
                    entriesCount++;
                    System.out.println("Now you have " + entriesCount + " tasks in the list.");
                }
                break;
            case "deadline":
                int deadlineIndex = userCommandDetails.indexOf("/by");
                String taskDescription = userCommandDetails.substring(0, deadlineIndex - 1);
                String taskDeadline = userCommandDetails.substring(deadlineIndex);
                entries.add(entriesCount, new Deadline(taskDescription, taskDeadline));
                System.out.println("Got it. I've added this task:");
                System.out.println("   [" + entries.get(entriesCount).getSymbol() + "] ["
                        + entries.get(entriesCount).getStatusIcon() + "] " + entries.get(entriesCount).description);
                entriesCount++;
                System.out.println("Now you have " + entriesCount + " tasks in the list.");
                break;
            case "event":
                int eventDateTimeIndex = userCommandDetails.indexOf("/at");
                String eventDescription = userCommandDetails.substring(0, eventDateTimeIndex - 1);
                String eventDateTime = userCommandDetails.substring(eventDateTimeIndex);
                entries.add(entriesCount, new Event(eventDescription, eventDateTime));
                System.out.println("Got it. I've added this task:");
                System.out.println("   [" + entries.get(entriesCount).getSymbol() + "] ["
                        + entries.get(entriesCount).getStatusIcon() + "] " + entries.get(entriesCount).description);
                entriesCount++;
                System.out.println("Now you have " + entriesCount + " tasks in the list.");
                break;
            case "done":
                String stringTaskIndex = userMessage.substring(userMessage.indexOf(" ") + 1);
                int intTaskIndex = Integer.parseInt(stringTaskIndex) - 1;
                entries.get(intTaskIndex).isDone = true;
                printLongLine();
                System.out.println("Nice! I've marked this task as done:\n"
                        + "   [" + entries.get(intTaskIndex).getSymbol()
                        + "] [" + entries.get(intTaskIndex).getStatusIcon() + "] " + entries.get(intTaskIndex).description);
                printLongLine();
                break;
            case "delete":
                stringTaskIndex = userMessage.substring(userMessage.indexOf(" ") + 1);
                intTaskIndex = Integer.parseInt(stringTaskIndex) - 1;
                printLongLine();
                System.out.println("Noted. I've removed this task:\n"
                        + "   [" + entries.get(intTaskIndex).getSymbol()
                        + "] [" + entries.get(intTaskIndex).getStatusIcon() + "] " + entries.get(intTaskIndex).description);
                System.out.println("Now you have " + (entriesCount - 1) + " tasks in the list.");
                printLongLine();
                for (i = intTaskIndex; i < entriesCount - 1; i++) {
                    entries.set(i, entries.get(i + 1));
                }
                entries.remove(entriesCount - 1);
                entriesCount -= 1;
                break;
            default:
                printLongLine();
                System.out.println(userCommand + " is an invalid command, try again");
                printLongLine();
                break;
            }
            userMessage = in.nextLine();
        }
        farewellMessage();
    }
}