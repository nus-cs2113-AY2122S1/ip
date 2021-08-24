import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMessage = "______________________________\n"
                + "Hello! I'm Friday\n"
                + "What can I do for you?\n"
                + "______________________________\n";
        String goodbyeMessage = "______________________________\n"
                + "Bye! Hope to see you again soon!\n"
                + "______________________________\n";
        System.out.print(welcomeMessage);
        String userInput = sc.nextLine();
        while(!"bye".equals(userInput)){
            System.out.println("______________________________\n");
            System.out.println(userInput + "\n");
            System.out.println("______________________________\n");
            userInput = sc.nextLine();
        }
        System.out.print(goodbyeMessage);
    }
}
