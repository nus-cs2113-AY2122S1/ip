package duke;

import duke.logic.Logic;

/**
 * Duke class acts as entry point to programme.
 */
public class Duke {
    public static final String FILEPATH = "duke.txt";

    public static void main(String[] args) {
        //run logic of programme
        Logic logic = new Logic(FILEPATH);
        logic.run();
    }
}
