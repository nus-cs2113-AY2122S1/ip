package main.java.duke.parser;

/** Parses the given user command to make sense of it by getting the key user command. */
public class Parser {

    /**
     * Splits the full string of user input into to 2 parts to get
     * the command word which needs to be executed. The command words
     * are "list","bye","delete","done","deadline","event","find" and "todo".
     *
     * @param fullCommand Full string of user input provided in CLI.
     * @return The command word that needs to be executed.
     */
    public static String getCommandWord(String fullCommand) {
        String[] parameter;
        parameter = fullCommand.split(" ");
        return parameter[0];
    }

}
