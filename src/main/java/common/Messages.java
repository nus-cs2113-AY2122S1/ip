package common;

import commands.*;


public class Messages {

    public static final String GREETING ="Why are you here again. What do you want";


    public static final String MESSAGE_TASK_COMPLETED = "Wow. You finally completed a task after lazying around all day.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Error. Task does not exist. Try again.";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format.";
    public static final String MESSAGE_EMPTY_TASK_LIST = "There are no tasks in your to-do list. Bet that'll change soon.";
    public static final String MESSAGE_TASKS_FOUND_OVERVIEW = "There are %1$d tasks in your list";
    public static final String MESSAGE_LIST_ALL_TASKS = "Look at that ever-expanding to-do list.\n You really should stop procrastinating.";
    

    public static final String USER_GUIDE = AddTodoCommand.MESSAGE_USAGE + "\n" + AddEventCommand.MESSAGE_SUCCESS + "\n" +
            AddDeadlineCommand.MESSAGE_USAGE + "\n" + MarkAsDoneCommand.MESSAGE_USAGE + "\n" + DeleteCommand.MESSAGE_USAGE;

}
