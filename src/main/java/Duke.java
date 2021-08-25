
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);

        while(!isBye){
            input = in.nextLine();
            if(!input.equals("bye")){
                System.out.println("--------------------");
                System.out.println(input);
                System.out.println("--------------------");
            } else {
                isBye = true;
            }
        }

        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        System.out.println("--------------------");
    }
}
