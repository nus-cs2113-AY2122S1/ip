import java.util.Scanner;

public class Duke {
    private static final String divisionLine = "    ____________________________________________________________\n";
    private static final String greetings = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String bye = "     Bye. Hope to see you again soon!\n";


    public static void main (String[] args) {
        //greeting page
        System.out.print(divisionLine + greetings + divisionLine);


        //start chatting now!
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            String[] inputSplit = userInput.split(" ");
            UserInput input;

            switch (inputSplit[0]) {
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
            System.out.print(divisionLine);
            input.execute();
            System.out.print(divisionLine);
            userInput = sc.nextLine();
        }

        //chatting finishes. Say Goodbye!
        System.out.println(divisionLine + bye + divisionLine);

    }
}


