import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printList(String[] list) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.length; i++) {
            System.out.println(" " + (i + 1) + ". " + list[i]);
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
        String[] taskList = new String[100];
        int index = 0;

        while (!inWord.equals(exitString)) {
            if (inWord.equals("list")) {
                printList(Arrays.copyOf(taskList, index));
            } else {
                taskList[index] = inWord;
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