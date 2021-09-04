package alfred.manager;

public abstract class ErrorManager {
    private static final String LINE = "____________________________________________________________\n";

    public static void invalidCommandMessage() {
        System.out.println(
                LINE +
                " Perhaps you could rephrase that in a way us civilians could comprehend.\n" +
                LINE
        );
    };

    public static void emptyDescriptionMessage() {
        System.out.println(
                LINE +
                "Master Wayne, if you do not specify your task, I'm afraid I cannot note it down.\n" +
                LINE
        );
    }
}
