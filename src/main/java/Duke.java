import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        boolean isBye = false;

        while (isBye == false ){
            if (line.equalsIgnoreCase(("bye"))){
                isBye = true;
            }
            else {
                System.out.println(line);
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }


}
