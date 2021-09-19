package Type.task;

public class Mascot {
    private static final String name = "John Doe";

    //references: https://linuxgazette.net/issue67/orr.html
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
