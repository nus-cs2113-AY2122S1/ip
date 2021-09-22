package duke.command;

import Type.Mascot;

import java.util.Scanner;

public class MascotCommand extends Command{
    public MascotCommand() {
        super("mascot");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("saying stuff!");
    }
    private static void mascotSay(Scanner in) {
        Mascot jim = new Mascot();
        String text = in.nextLine();
        Mascot.penguinSay(text);
    }

}
