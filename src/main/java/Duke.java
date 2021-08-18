public class Duke {
    public static void printLine(){
        System.out.println("                 ...                 ");
    }

    public static void sayHi() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }

    public static void main(String[] args) {
        sayHi();
        sayGoodbye();
    }
}
