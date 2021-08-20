public class Duke {
    static String logo = "\n    ___   ______   ________  _______      ___   ____  _____   .--.\n" +
            "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'.\n" +
            " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'\n" +
            " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"\n" +
            " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`\n" +
            "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)\n" +
            "                                                           (/\\\\||/\n" +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    static String horizontalBar = "__________________" +
                                  "__________________" +
                                  "__________________" +
                                  "_______________";

    public static void greetingMessage() {
        System.out.println(horizontalBar);
        System.out.println(logo);
        System.out.println("  Hello! I'm Oberon");
        System.out.println("  What can I do for you?");
        System.out.println(horizontalBar);
    }

    public static void farewellMessage() {
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(horizontalBar);
    }

    public static void main(String[] args) {
        greetingMessage();
        farewellMessage();
    }
}
