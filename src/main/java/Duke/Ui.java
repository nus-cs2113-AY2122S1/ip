package Duke;

public class Ui {
    private Duke duke;
    public static final String Line = "    ____________________________________________________________";
    public Ui(Duke duke) {this.duke = duke;}

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greeting() {
        System.out.println(Line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(Line);
    }
}
