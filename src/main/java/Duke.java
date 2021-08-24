import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] savedMessages= new String[100];
        int savedMessagesArrayCnter = 1;
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
            if("list".equals(userInput)) {
                System.out.println("______________________________\n");
                for(int i = 1; i < savedMessagesArrayCnter; i++)
                {
                    System.out.print(i + " : ");
                    System.out.print(savedMessages[i] + "\n");
                }
                System.out.println("______________________________\n");
            } else {
                savedMessages[savedMessagesArrayCnter] = userInput;
                savedMessagesArrayCnter++;
                System.out.println("______________________________\n");
                System.out.println("added " + userInput + "\n");
                System.out.println("______________________________\n");
            }
            userInput = sc.nextLine();
        }
        if("bye".equals(userInput)) {
            System.out.print(goodbyeMessage);
        }
    }
}
