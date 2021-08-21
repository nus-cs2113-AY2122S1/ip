import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "    ——————————————————————————————————————————————————————————————";
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(divider);
        String line;
        String[] lists = new String[100];
        int number = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(divider);
                for(int i = 0; i < number; i++) {
                    System.out.print("    " + (i+1) + ". ");
                    System.out.println(lists[i]);
                }
                System.out.println(divider);
                line = in.nextLine();
            } else {
                lists[number++] = line;
                System.out.println(divider);
                System.out.print("    ");
                System.out.println("added: " + line);
                System.out.println(divider);
                line = in.nextLine();
            }
        }
        System.out.println(divider);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
