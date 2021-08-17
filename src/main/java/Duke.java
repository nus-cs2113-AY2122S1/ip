public class Duke {
    public static void sayHello() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void drawHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("\u2500");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawHorizontalLine(32);
        sayHello();
        drawHorizontalLine(32);
        sayGoodbye();
        drawHorizontalLine(32);
    }
}
