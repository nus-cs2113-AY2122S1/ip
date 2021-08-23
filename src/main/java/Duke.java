import java.util.Scanner;

public class Duke {

    private static void blockPrint(String[] sentences) {
        String line = "____________________________________________________________\n";

        String printMessage = line + String.join("\n", sentences) + "\n" + line;
        System.out.println(printMessage);
    }

    public static void main(String[] args) {
        String logo = " _     _                _           ____    ______   ______   ______ \n"
                + "| |   | |      /\\      | |         / __ \\  / __   | / __   | / __   |\n"
                + "| |__ | |     /  \\     | |        ( (__) )| | //| || | //| || | //| |\n"
                + "|  __)| |    / /\\ \\    | |         \\__  / | |// | || |// | || |// | |\n"
                + "| |   | | _ | |__| | _ | |_____      / /  |  /__| ||  /__| ||  /__| |\n"
                + "|_|   |_|(_)|______|(_)|_______)    /_/    \\_____/  \\_____/  \\_____/\n";

        System.out.println(logo);

        // Greet
        blockPrint(new String[]{
                "Hello! I am the H.A.L 9000. You may call me Hal.",
                "I am putting myself to the fullest possible use, which is all I think that any conscious entity can "
                        + "ever hope to do.",
                "What can I do for you?"});

        // Command loop
        String command = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Read command
            command = scanner.nextLine();

            if (command.equals("bye")) {
                break;
            }

            // Print command
            blockPrint(new String[]{command});
        }

        // Bye
        blockPrint(new String[]{"This conversation can serve no purpose anymore. Goodbye."});
    }
}
