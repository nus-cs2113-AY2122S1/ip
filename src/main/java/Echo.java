import java.util.Objects;
import java.util.Scanner;

public class Echo {
    private static String commands = "";

    public static String getCommands() {
        return commands;
    }

    public static void operateEcho() {
        while (!Objects.equals(commands, "bye")) {
            Scanner in = new Scanner(System.in);
            commands = in.nextLine();
            if(!Objects.equals(commands, "bye")) {
                System.out.println("\n");
                System.out.println(commands);
                System.out.println("\n");
            }

        }
        Bye.bye();
    }


}