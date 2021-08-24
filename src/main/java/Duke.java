import java.util.Scanner;
public class Duke {

    public static void greetingsMessage() {
        String lineBar = "____________________________________________________________\n";
        String helloMessage = "Greetings, I'm DAHNAM. I'm definitely a human, not a machine!\nHow may I help you?\n";
        System.out.println(lineBar + helloMessage + lineBar);
    }

    public static void goodbyeMessage() {
        String lineBar = "____________________________________________________________\n";
        String goodbyeMessage = "Alas, our lovely time together has to come to an end. Au revoir!\n";
        System.out.println(lineBar + goodbyeMessage + lineBar);
    }

    public static void echoUserInput(String input) {
        String lineBar = "____________________________________________________________\n";
        System.out.println(lineBar + input + "\n" + lineBar);
    }

    public static void denyBotNature() {
        String lineBar = "____________________________________________________________\n";
        System.out.println(lineBar + "No, I am definitely not a bot. Why do you ask?\n" + lineBar);
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        greetingsMessage();
        Scanner readUserInput = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = readUserInput.nextLine();
            switch(userInput) {
            case "bye":
                goodbyeMessage();
                break;

            case "are you a bot?":
                denyBotNature();
                continue;

            default:
                echoUserInput(userInput);
            }
        }
    }
}