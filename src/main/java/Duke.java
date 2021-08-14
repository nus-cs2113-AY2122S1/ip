public class Duke {
    public static void addLine() {
        System.out.println("----------------------");
    }

    public static void dukeGreet() {
        System.out.println("Hello!, I'm Duke");
        System.out.println("How can I help you?");
    }

    public static void dukeBye() {
        System.out.println("Bye, see you again!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        addLine();

        dukeGreet();
        addLine();
        
        dukeBye();
        addLine();
    }
}
