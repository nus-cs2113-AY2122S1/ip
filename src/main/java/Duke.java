import java.util.Scanner;

public class Duke {

    public static void sayHello() {
        String horizontal = ("____________________________________________________________\n");
        System.out.println(horizontal + "Hello! I'm Duke\n" + "What can I do for you?\n" + horizontal);
    }

    public static void sayBye() {
        String horizontal = ("____________________________________________________________\n");
        System.out.println(horizontal + "Bye. Hope to see you again soon!\n" + horizontal);
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        String horizontal = ("____________________________________________________________\n");
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(horizontal + line + "\n" + horizontal);
            line = in.nextLine();
        }
        sayBye();
    }

    public static void main(String[] args) {
        sayHello();
        echo();
    }
}
