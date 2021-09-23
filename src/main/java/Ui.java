public class Ui {
    public static void printlnTab(String str) {
        System.out.println("\t" + str);
    }

    public static void printDivider() {
        System.out.println("\t______________________________________________________________________");
        System.out.println();
    }

    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println(logo);
        printlnTab("Hello! I'm Duke\n\tWhat can I do for you?");
        printDivider();
    }
}