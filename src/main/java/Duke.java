import java.util.Scanner;

public class Duke {

    public static final String LINE_BREAK_SINGLE = "____________________________________________________________";
    public static final String ENTER_COMMAND_TEXT = "Enter command: ";
    public static final String GOODBYE_MESSAGE = "Bye boss! HAL will always be there to help you!\nSummon me by running this program again";
    public static final String LOGO_HAL2113 = "  _(\\    |@@|\n"
            + "(__/\\__ \\--/ __\n"
            + "   \\___|----|  |   __\n"
            + "       \\ }{ / )_ / _\\\n"
            + "       /\\__/\\ \\__O (__\n"
            + "      (--/\\--)    \\__/\n"
            + "      _)(  )(_\n"
            + "     `---''---`\n";

    public static void main(String[] args) {
        Program currProgram = new Program();
        String userInput;
        Scanner sc = new Scanner(System.in);

        String logo = LOGO_HAL2113;

        System.out.println("\nHello! I'm HAL 2113\n" + logo);
        System.out.println(LINE_BREAK_SINGLE);
        System.out.println("What can I do for you? You can enter the following commands\n" +
                "1. 'todo task' (replace task with any agenda you wish to add to the list)\n" +
                "2. 'deadline task /by date' (eg. deadlitodone task1 /by Sunday)\n" +
                "3. 'event task /at time' (eg. event running /at Sunday 2-4pm)\n" +
                "4. 'list (to list all saved tasks)\n" +
                "5. 'done task index (to mark a completed task)\n" +
                "6. 'bye' (to exit the program!");
        System.out.print("Enter command: ");

        while (!currProgram.getCanTerminateHal()) {
            if (sc.hasNextLine()) {
                userInput = sc.nextLine();
                //get rid of white space
                userInput = userInput.trim();
                try {
                    currProgram.parseAndExecuteTask(userInput);
                } catch (HalException invalidUserInput) {
                    System.out.println(invalidUserInput);
                    System.out.println(LINE_BREAK_SINGLE);
                    System.out.print(ENTER_COMMAND_TEXT);
                }
            }
        }

        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE_BREAK_SINGLE);
    }
}
