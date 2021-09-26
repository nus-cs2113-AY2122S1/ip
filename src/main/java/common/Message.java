package common;

/**
 * This class contains informative messages that are output after users enter commands
 */
public class Message {
    public static final String MESSAGE_LOGO = " _____    _       _                 \n"
            + "|  ___|  | |     | |                \n"
            + "| |__ ___| |_ ___| |__   __ _ _ __  \n"
            + "|  __/ __| __/ _ \\ '_ \\ / _` | '_ \\ \n"
            + "| |__\\__ \\ ||  __/ |_) | (_| | | | |\n"
            + "\\____/___/\\__\\___|_.__/ \\__,_|_| |_|";
    public static final String MESSAGE_WELCOME = "Hola! Soy Esteban, what can I do for you?";
    public static final String MESSAGE_EXIT = "Gracias! Hope to see you again soon!";
    public static final String MESSAGE_SEPARATOR = "------------------------------------------------------------";

    public static final String SUCCESS_FILE_FOUND = "  (+) Data file found: %s";
    public static final String SUCCESS_DATA_READ = "  (+) Loaded %d entries";

    public static final String ADDED_TASK = "  (+) Added: %s" + System.lineSeparator() +
            "  (i) You have %d tasks in the list";
    public static final String DONE_TASK = "  (+) Marked as Done: %s";
    public static final String DELETE_TASK = "  (-) Removed: %s" + System.lineSeparator() +
            "  (i) You have %d tasks in the list";
}
