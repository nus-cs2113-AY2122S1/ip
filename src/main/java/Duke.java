public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Karlett");
        System.out.println("What can I do for you?");
        drawSplitLine();
        System.out.println("Bye bye! Hope to see you again soon.");
        drawSplitLine();
    }
    public static void drawSplitLine() {
        int n = 70;
        while (n>0) {
            System.out.print("_");
            n--;
        }
        System.out.println();
    }
}
