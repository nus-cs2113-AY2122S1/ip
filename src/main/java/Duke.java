import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Program currProgram = new Program();


        String logo = "  _(\\    |@@|\n"
                + "(__/\\__ \\--/ __\n"
                + "   \\___|----|  |   __\n"
                + "       \\ }{ / )_ / _\\\n"
                + "       /\\__/\\ \\__O (__\n"
                + "      (--/\\--)    \\__/\n"
                + "      _)(  )(_\n"
                + "     `---''---`\n";

        System.out.println("\n\nHello! I'm HAL 2113\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("What can I do for you? You can enter the following commands\n1.'task' (replace task with any agenda you wish to add to the list)\n2. list\n3. done 'task index' (to mark a completed task)\n4. bye (to exit the program!");
        System.out.print("Enter command: ");

        while (!currProgram.getTerminateHal()) {
            String input;
            Scanner sc= new Scanner(System.in);
            input = sc.nextLine();

            //get rid of white space
            input = input.trim();
            currProgram.executeTask(input);
//            System.out.println(input);

        }
        System.out.println("Bye boss! HAL will always be there to help you!\nSummon me by running this program again");
        System.out.println("____________________________________________________________");
    }
}
