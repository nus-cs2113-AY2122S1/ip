import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Duke Greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //Echo
        String strInput = "";
        while(!strInput.equals("bye")) {
            strInput = in.nextLine();
            if (strInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            }
            else {
                System.out.println("____________________________________________________________");
                System.out.println(strInput);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
