import java.util.Scanner;

public class Duke {
    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE = "     Bye. Hope to see you again soon!\n";


    public static void main (String[] args) {
        //greeting page
        System.out.print(DIVISIONLINE + GREETINGS + DIVISIONLINE);


        //start chatting now!
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            String[] inputSplits = userInput.split(" ");
            UserInput input;

            switch (inputSplits[0]) {
            case "list":
                input = new ListCommand(userInput);
                break;
            case "blah":
                input = new Blah(userInput);
                break;
            case "done":
                input = new Done(userInput);
                break;
            default:
                input = new AddList(userInput);
            }
            System.out.print(DIVISIONLINE);
            input.execute();
            System.out.print(DIVISIONLINE);
            userInput = sc.nextLine();
        }

        //chatting finishes. Say GoodBYE!
        System.out.println(DIVISIONLINE + BYE + DIVISIONLINE);

    }
}


