import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________\n";
        Scanner in = new Scanner(System.in);
        System.out.println(line + logo +
                " Hello! I'm Duke\n" +
                " What's up? :p\n" + line);

        String [] inputs = new String[100];
        int inputCount = 0;


        while(true) {
            input = in.nextLine();
            if(input.equals("bye")) {
                break;
            }
            else if(input.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < inputCount; i++){
                    System.out.println((i+1) + ". " + inputs[i]);
                }
                System.out.println(line);
            }
            else {
                System.out.println(line + "added:" + input + "\n" + line);
                inputs[inputCount] = input;
                inputCount += 1;
            }
        }

        System.out.println(line + "Bye, seeya!\n" + line);
    }
}
