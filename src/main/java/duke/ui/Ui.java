package duke.ui;

import duke.commands.CommandResult;
import duke.exceptions.DukeException;

public class Ui {

    private static final String LOGO = " ______        _\n"
            + "(______)      | |\n"
            + " _     _ _   _| |  _ _____\n"
            + "| |   | | | | | |_/ ) ___ |\n"
            + "| |__/ /| |_| |  _ (| ____|\n"
            + "|_____/ |____/|_| \\_)_____)\n";
    private static final String GREET_MESSAGE = "Welcome to\n" + LOGO
                    + "Hello there! I'm Duke, your very helpful personal assistant chat bot.\n"
                    + "Enter \"help\" to see what I can do for you!";
    private static final String DIVIDER = "____________________________________________________________";

    private void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showGreetMessage() {
        System.out.println(GREET_MESSAGE);
        showDivider();
    }

    public void showFeedbackToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
        showDivider();
    }

    public void showErrorMessage(DukeException exception) {
        System.out.println(exception.getMessage());
        showDivider();
    }
}
