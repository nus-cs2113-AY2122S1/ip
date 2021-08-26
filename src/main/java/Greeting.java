public class Greeting {
    static String closingLine = "\t__________________________________________________________";

    public static void openingGreet() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(closingLine);
    }

    public static void closingGreet() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(closingLine);
    }
}
