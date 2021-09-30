package duke.common;

/**
 * Container for general user messages
 */
public class Messages {
    public static final String MESSAGE_WELCOME = " Hello! I'm Duke, your task management robot!";
    public static final String MESSAGE_GOODBYE = " Bye! Do visit next time!";
    public static final String MESSAGE_INVALID_COMMAND = "BEEP BEEP BOOP! ERROR... CANNOT UNDERSTAND...";
    public static final String MESSAGE_EMPTY_TASK_DESCRIPTION = "Please input in a description/ index after the command! ";
    public static final String MESSAGE_INVALID_TASK_INDEX = "Oops! The task index doesn't exist! Pls try again";
    public static final String MESSAGE_INIT_NEW_STORAGE_FILE = "No saved task lists found! Created a new one for you :-)";
    public static final String MESSAGE_INIT_FAILED = "We're unable to make a new data folder... perhaps you already have one but no file? \n|| In any case, adding a new storage file should work fine! :-)";
    public static final String MESSAGE_ENTER_COMMAND = "What would you like to do?  Type 'help' if you're confused!";
}
