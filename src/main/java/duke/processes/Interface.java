package duke.processes;

public class Interface {
    public static void lineBreak() {
        String lineBreak = "..........................." +
                ".......................................";
        System.out.println(lineBreak);
    }

    /**
     * Greet user
     */
    public static void introductoryMessage() {
        String logo = "  /\\ _ /\\\n"
                + " #  @ @  #    Welcome to IKAROS!\n"
                + " #   ^   #  Your one and only butler\n"
                + " #########";
        lineBreak();

        System.out.println(logo);
        lineBreak();
        System.out.println("Below is your current list of tasks."
                + System.lineSeparator()
                + "What further assistance do you require?");
        lineBreak();
    }

    /**
     * Bid Farewell to the user
     */
    public static void goodbyeMessage() {
        System.out.println("GoodBye, Ikaros awaits for future commands");
        lineBreak();
    }
}
