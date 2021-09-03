public class UserInterface {
    protected final String LOGO = System.lineSeparator()
            + "    ___   ______   ________  _______      ___   ____  _____   .--." + System.lineSeparator()
            + "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'." + System.lineSeparator()
            + " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'" + System.lineSeparator()
            + " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"" + System.lineSeparator()
            + " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`" + System.lineSeparator()
            + "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)" + System.lineSeparator()
            + "                                                           (/\\\\||/" + System.lineSeparator()
            + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    protected final String HORIZONTAL_BAR = "__________________"
            + "__________________"
            + "__________________"
            + "_______________";



    /**
     * Reads in a string and prints it with a horizontal bar above & below it
     *
     * @param input String to be echo-ed
     */
    public void echo(String input) {
        System.out.println(HORIZONTAL_BAR + System.lineSeparator() + input
                + System.lineSeparator() + HORIZONTAL_BAR);
    }

    /**
     * Print greeting message upon starting the program
     */
    public void displayGreetingMessage() {
        echo(LOGO + System.lineSeparator() + "  Hello! I'm Oberon"
                + System.lineSeparator() + "  What can I do for you?");
    }

    /**
     * Print farewell message upon exiting the program
     */
    public void displayFarewellMessage() {
        echo("  Goodbye. Hope to see you again soon!" + System.lineSeparator() + LOGO);
    }



}
