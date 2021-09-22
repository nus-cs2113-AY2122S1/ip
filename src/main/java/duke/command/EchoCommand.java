package duke.command;

import java.util.Scanner;

public class EchoCommand extends Command{
    public EchoCommand() {
        super("echo");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("echoing!");
    }

    public static String readInputEchoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("    " + command);
        return command;
    }
}
