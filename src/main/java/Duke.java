
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
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please key in an integer");
            } catch (NullPointerException e) {
                System.out.println(ErrorMessage.EXCEPTION_MESSAGE_INPUT_NOT_INT);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Number.");
            }
            isConversation = !words[0].equals(Command.COMMAND_BYE);
        } while (isConversation);

    }
}
