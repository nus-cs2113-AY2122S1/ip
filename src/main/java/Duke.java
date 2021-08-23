import java.util.Scanner;

public class Duke {
    public static void line(){
        System.out.println("____________________________________________________________");
    }

    private static Boolean isFinished = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();

        while(!isFinished){
            String userInput = sc.nextLine();
            if (userInput.equals("bye")){
                isFinished = true;
                break;
            }
            line();
            System.out.println(userInput);
            line();
        }

        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();

    }
}