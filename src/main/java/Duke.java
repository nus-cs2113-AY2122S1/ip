import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String[] entries = new String[100];
        int entriesIndex = 0;
        Scanner in = new Scanner(System.in);
        String userMessage = in.nextLine();
        while(!userMessage.equalsIgnoreCase("bye")){
            if (!userMessage.equalsIgnoreCase("list")){
                System.out.println("Added " + userMessage);
                entries[entriesIndex] = userMessage;
                entriesIndex++;
                userMessage = in.nextLine();
            }
            else {
                System.out.println(Arrays.toString(Arrays.copyOf(entries,entriesIndex)));
                userMessage = in.nextLine();
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}