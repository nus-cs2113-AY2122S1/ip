package duke.Type;

/**
 * Represents a virtual chatbot to echo user input
 * References: https://linuxgazette.net/issue67/orr.html
 */
public class Mascot {
    public static void penguinSay(String say) {
        System.out.println("< " + say + "  >\n" +
                " -----------------------\n" +
                "   \\\n" +
                "    \\\n" +
                "        .--.\n" +
                "       |o_o |\n" +
                "       |:_/ |\n" +
                "      //   \\ \\\n" +
                "     (|     | )\n" +
                "    /'\\_   _/`\\\n" +
                "    \\___)=(___/\n" +
                "                  ");
    }
}
