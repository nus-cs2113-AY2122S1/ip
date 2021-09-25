package duke.ui;

public class UI {
    static final String LINE = "--------------------------------";

    public static void line() {
        System.out.println(LINE);
    }

    public static void greet() {
        String logo = "  _                                         _   _            \n" +
                " | |__   __ _ _ __ _ __ _   _   _ __   ___ | |_| |_ ___ _ __ \n" +
                " | '_ \\ / _` | '__| '__| | | | | '_ \\ / _ \\| __| __/ _ \\ '__|\n" +
                " | | | | (_| | |  | |  | |_| | | |_) | (_) | |_| ||  __/ |   \n" +
                " |_| |_|\\__,_|_|  |_|   \\__, | | .__/ \\___/ \\__|\\__\\___|_|   \n" +
                "                        |___/  |_|                         \n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(
                "       Messrs Moony, Wormtail, Padfoot, and Prongs\n" +
                        "       Purveyors of Aids to Magical Mischief-Makers\n" +
                        "                  are proud to present\n" +
                        "                 --THE MARAUDER'S MAP--");
    }

    public static void hello() {
        System.out.println("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        System.out.println("- \"I solemnly swear that I am up to no good.\" ");
    }

    public static void exit() {
        System.out.println("Mischief managed.");
    }
}
