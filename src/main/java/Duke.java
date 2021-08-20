import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Duke Greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //Initialize Variables
        String strInput = "";
        int listLength = 0;
        String[] list = new String[100];

        //Main loop
        while(!strInput.equals("bye")) {
            strInput = in.nextLine();
            switch (strInput) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < listLength; i++) {
                        System.out.println((i+1) + ". " + list[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;

                default:
                    list[listLength] = strInput;
                    listLength++;
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + strInput);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}
