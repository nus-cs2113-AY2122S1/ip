import java.util.Scanner;
public class Duke {

    public static void welcome() {
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Dude ヽ༼ ・ ل͜ ・ ༽ﾉ" + System.lineSeparator() + "What can I do for you?");
        System.out.println("_____________________________________________________");
    }

    public static void exit() {
        System.out.println("_____________________________________________________");
        System.out.println("Bye! Hope to see you again soon! ヽ༼ ͠° ͟ل͜ ͠° ༽ﾉ ");
        System.out.println("_____________________________________________________");
    }

    /** Continuously echoes user inputs and exits when user inputs "Bye" (not case-sensitive) **/
    public static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        boolean hasSaidBye = false;
        do {
            if (input.equalsIgnoreCase("bye")) {
                hasSaidBye = true;
                exit();
            } else {
                System.out.println("_____________________________________________________");
                System.out.println(input);
                System.out.println("_____________________________________________________");
                input = in.nextLine();
            }
        } while (!hasSaidBye);
    }


    public static void main(String[] args) {
      welcome();
      echo();
    }
}


