package duke;

public class Ui {

    //prints program output
    public static void printMessage(String line) {
        if (line.isEmpty()) {
            return;
        }
        System.out.println(line);
    }

    public static void printGreeting() {
        printMessage("Hello there! Kao here! ⸜(˃ ᵕ ˂ )⸝");
    }

    public static void printPrompt() {
        printMessage( "How can Kao be of assistance? ( •̀ ᗜ •́ )");
    }

    public static void printExit() {
        printMessage("Bye bye! Kao will be waiting for you ｡:ﾟ( ´°ω°｀ )ﾟ:｡");
    }
}
