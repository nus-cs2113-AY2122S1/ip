import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ______    _   __   __            \n" +
                "|_   _ \\  (_) [  | [  |           \n" +
                "  | |_) | __   | |  | |   _   __  \n" +
                "  |  __'.[  |  | |  | |  [ \\ [  ] \n" +
                " _| |__) || |  | |  | |   \\ '/ /  \n" +
                "|_______/[___][___][___][\\_:  /   \n" +
                "                         \\__.'    \n";

        String horizontalLine = "____________________________________________________________\n";
        String greetingMessage = horizontalLine +
                " Hello! I'm Billy\n" +
                " What can I do for you?\n" +
                horizontalLine;

        String goodbyeMessage = horizontalLine +
                " Bye. Hope to see you again soon!\n" +
                horizontalLine;
        boolean programIsRunning = true;
        String userInput;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + greetingMessage);

        while(programIsRunning) {
            userInput = in.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println(goodbyeMessage);
                programIsRunning = false;
            } else {
                System.out.println(horizontalLine + userInput + "\n" + horizontalLine);
            }
        }

    }
}
