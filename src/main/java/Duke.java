import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Hello from\n" + logo);
        Greet.printWelcomeMessage();

        boolean isConversation;
        do {
            input = in.nextLine();
            String[] words = input.split(" ");
            try {
                FilterInput.checkCommand(words);
            } catch(DukeEmptyDescriptionException e){
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } catch(DukeUnrecognisedCommandException e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            isConversation = !words[0].equals("bye");
        } while (isConversation);

    }
}
