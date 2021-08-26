import java.util.Scanner;

public class Duke {

    private static int byeFlag = 0;

    // sendCommands() is a method used to allow the user to send his/her commands to C3PO
    private static void sendCommands() {
        String line;
        Scanner in = new Scanner(System.in);
        while ( byeFlag != 1 ) {
            System.out.println("____________________________________________________________\n");
            System.out.print("Type something: ");
            line = in.nextLine();
            System.out.println("____________________________________________________________\n");
            checkCommands(line);
        };
    };

    // checkCommands() is a method that allows us to determine when the user says bye.
    private static void checkCommands(String line) {
        if (line.equals("bye")) {
            byeFlag = 1;
        } else {
            System.out.println(line);
        }
    };

    public static void sayBye() {
        System.out.println("Goodbye master! May the force be with you!\n");
        System.out.println("____________________________________________________________\n");
    };

    public static void main(String[] args) {
        String logo = "       /~\\\n"
                + "      |oo )\n"
                + "      _\\=/_\n"
                + "     /     \\\n"
                + "    //|/.\\|\\\\\n"
                + "   ||  \\_/  ||\n"
                + "   || |\\ /| ||\n"
                + "    # \\_ _/  #\n"
                + "      | | |\n"
                + "      | | |\n"
                + "      []|[]\n"
                + "      | | |\n"
                + "     /_]_[_\\\n";
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I am C3P0! Human-cyborg relations! \n" + logo);
        System.out.println("What can I do for you my master?\n");
        sendCommands();
        sayBye();
    }
}
