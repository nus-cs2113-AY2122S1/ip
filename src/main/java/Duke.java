import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greeting();
        echo();
        drawDivider();
        System.out.println("ヾ(￣▽￣)Bye~Bye~ Hope to see you again soon meow.");
        drawDivider();
    }

    public static void greeting() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)");
        System.out.println("What can I do for you meow?");
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            drawDivider();
            System.out.println(command + " meow!");
            drawDivider();
        }
    }

    public static void drawDivider() {
        int n = 4;
        while (n > 0) {
            System.out.print("ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ");
            n--;
        }
        System.out.println();
    }
}
