
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Welcome Message

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        System.out.println("--------------------");

        // Echo Chamber

        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        int counter = 0;
        String[] list = new String[100];

        while(!isBye){
            input = in.nextLine();
            if(!input.equals("bye") && !input.equals("list") ){
                System.out.println("--------------------");
                System.out.println("added: " + input);
                System.out.println("--------------------");
                list[counter] = input;
                counter += 1;
            } else if (input.equals("list")) {
                System.out.println("--------------------");
                if (list[0] != null){
                    for(int i = 0; i < counter; i += 1){
                        System.out.println(i + 1 + ". " +list[i]);
                    }
                } else {
                    System.out.println("List is empty. Time to get productive!");
                }
                System.out.println("--------------------");
            } else {
                isBye = true;
            }
        }

        // Goodbye Message

        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        System.out.println("--------------------");
    }
}
