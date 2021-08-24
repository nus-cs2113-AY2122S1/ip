import java.util.Scanner;
public class Duke {

    public static void greetingsMessage() {
        String helloMessage = "Greetings, I'm DAHNAM. I'm definitely a human, not a machine!\nHow may I help you?\n";
        System.out.println(helloMessage);
    }

    public static void goodbyeMessage() {
        String goodbyeMessage = "Alas, our lovely time together has to come to an end. Au revoir!\n";
        System.out.println(goodbyeMessage);
    }

    public static void denyBotNature() {
        String denyBotNature = "No, I am definitely not a bot. Why do you ask?\n";
        System.out.println(denyBotNature);
    }

    public static void main(String[] args) {
        String lineBar = "____________________________________________________________\n";
        greetingsMessage();
        Scanner readUserInput = new Scanner(System.in);
        Task taskList = new Task();
        String userInput;

        while (true) {
            userInput = readUserInput.nextLine();
            switch(userInput) {
            case "bye":
                System.out.println(lineBar);
                goodbyeMessage();
                System.out.println(lineBar);
                break;

            case "are you a bot?":
                System.out.println(lineBar);
                denyBotNature();
                System.out.println(lineBar);
                continue;

            case "list":
                System.out.println(lineBar);
                taskList.displayTasks();
                System.out.println(lineBar);
                continue;

            default:
                System.out.println(lineBar);
                taskList.setUserInput(userInput);
                System.out.println(lineBar);
            }
        }
    }
}