import java.util.Scanner;

public class Duke {
    public static void echo(String userMessage){
        if (userMessage.equalsIgnoreCase("bye")){
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }
        else {
            System.out.println(userMessage);
            Scanner in = new Scanner(System.in);
            String nextMessage = in.nextLine();
            echo(nextMessage);
        }
    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);
        String userMessage = in.nextLine();
        echo(userMessage);
    }
}
