package duke.Command;

import duke.ErrorHandling.CommandException;

/**
 * Represent a general structure for all commands
 */
public abstract class Command {
    protected static final String COMMAND_HELP = "!help";
    protected static final String COMMAND_LIST_HELP = "!list";
    protected static final String COMMAND_EVENT_HELP = "!event";
    protected static final String COMMAND_ECHO_ART = "!echo";
    protected static final String COMMAND_DEADLINE_HELP = "!deadline";
    protected static final String COMMAND_VIEW_LIST = "list";
    protected static final String COMMAND_ECHO = "echo";
    protected static final String COMMAND_COMPLETE_TASK = "done";
    protected static final String COMMAND_DATE_TASK = "date";
    protected static final String COMMAND_ADD_TODO = "todo";
    protected static final String COMMAND_ADD_EVENT = "event";
    protected static final String COMMAND_ADD_DEADLINE = "deadline";
    protected static final String COMMAND_DELETE = "delete";
    protected static final String COMMAND_FIND_WORD = "find";
    protected static final String EVENT_TIME = "at ";
    protected static final String DEADLINE_DATE = "by ";
    protected static final String MESSAGE_TASK_COMPLETE = "Nice! I've marked this task as done: ";
    protected static final String DATE_PATTERN = "\\d{4}-\\d{1,2}-\\d{1,2}";
    protected static final String EMPTY_STRING = "";
    protected static final String SEPARATOR = ",";
    protected static final int START_OF_STRING = 0;


    protected static final String MESSAGE_HELP =  "List of Commands:\n" +
            "   echo - Repeat whatever was typed - !echo to repeat in art form\n" +
            "   list - Display List - !list for details\n" +
            "   todo - Add ToDo Task\n" +
            "   event - Add Event Task - !event for details\n" +
            "   deadline - Add Deadline Task - !deadline for details\n" +
            "   clear - Clear list\n" +
            "   find - Find Task With a specific word\n" +
            "   date - Find Task with a specific date\n" +
            "   bye - Shut Down";
    protected static final String MESSAGE_LIST_HELP = "list displays all tasks\n" +
            "list todo displays all todo tasks\n" +
            "list event displays all event tasks\n" +
            "list deadline displays all deadline tasks";
    protected static final String MESSAGE_EVENT_HELP = "event command requires a timing indicated using \"at\" [timing]\n" +
            "[timing]: HH:MM YYYY-MM-DD";
    protected static final String MESSAGE_DEADLINE_HELP = "deadline command requires a end time indicated using \"by\"[end time]\n" +
            "[timing]: HH:MM YYYY-MM-DD";

    protected String taskInput;

    /**
     * Constructor for Command
     * @param taskInput Command input to process
     */
    public Command(String taskInput){
        this.taskInput = taskInput;
    }

    /**
     * A function to perform what the command is supposed to do
     * @throws CommandException when error encountered for specific command
     */
    public abstract void executeCommand() throws CommandException;
}
