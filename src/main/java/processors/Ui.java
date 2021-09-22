package processors;

import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;
import exceptions.DukeException;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    private static final Integer ZERO = 0;
    private static final Integer ARRAY_INDEX_FINDER = 1;

    private static final String ADD_TASK_REPLY = "     Got it. I've added this task:\n";
    private static final String TASK_COMPLETED = "     Nice! I've marked this task as done:\n";
    private static final String LINE = "________________________________________________________________";
    private static final String LINE_DIVIDER = "________________________________________________________________\n";
    private static final String GAP = "     ";
    private static final String DELETED_TASK = "     Noted. I've removed this task:\n";
    private static final String EMPTY_LIST = "Nothing Has Been Added To The List Yet\n";
    private static final String FIND_LIST_RESULT = "     Here are the matching tasks in your list:\n";
    private static final String FIND_LIST_NO_RESULT = "     No relevant results found :(\n";

    public void printTaskMessage(Task task, Integer index) {
        String output = LINE_DIVIDER + ADD_TASK_REPLY
                + GAP + task.toString();
        System.out.println(output);
        getAddTaskReturn(index);
    }

    public void printDoneMessage(Task task) {
        String output = LINE_DIVIDER + TASK_COMPLETED + GAP + "[X] "
                + task.getDescription() + "\n" + LINE;
        System.out.println(output);
    }

    public void printListMessage(ArrayList<Task> list) {
        String output = LINE_DIVIDER;
        for (int i = 0; list.size() > i; i++) {
            String record = GAP + (i + ARRAY_INDEX_FINDER) + "." + list.get(i).toString() + "\n";
            output = output.concat(record);
        }
        if (list.size() == ZERO) {
            output = output.concat(GAP + EMPTY_LIST);
        }
        output = output.concat(LINE);
        System.out.println(output);
    }

    public void printDeleteMessage(Task task, Integer index) {
        String output = LINE_DIVIDER + DELETED_TASK + GAP + task.toString();
        System.out.println(output);
        getAddTaskReturn(index - ARRAY_INDEX_FINDER);
    }

    public void printFindMessage(ArrayList<Task> list) {
        String output = LINE_DIVIDER;
        if (list.size() > ZERO) {
            output = output.concat(FIND_LIST_RESULT);
        } else {
            output = output.concat(FIND_LIST_NO_RESULT);
        }
        for (int i = 0; list.size() > i; i++) {
            String record = GAP + (i + ARRAY_INDEX_FINDER) + "." + list.get(i).toString() + "\n";
            output = output.concat(record);
        }
        output = output.concat(LINE);
        System.out.println(output);


    }

    public void getAddTaskReturn(Integer i) {
        String output = "     Now you have " + (i) + " tasks in the list.\n" + LINE;
        System.out.println(output);
    }

    public void goodbyeMessage() {
        String output = "File has been saved!\n"
                + LINE_DIVIDER + GAP + "Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(output);
    }

    public void welcomeMessage() {
        String output = LINE_DIVIDER
                + "     Hello! I'm\n"
                + "     ____        _\n"
                + "    |  _ \\ _   _| | _____\n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n"
                + LINE;
        System.out.println(output);
    }

    public String help() {
        return LINE_DIVIDER
                + "     Please input a valid request\n"
                + "     Duke can do the follow instructions\n"
                + "     1. Record Todo Task: todo (description)\n"
                + "     2. Record Deadline Task: task (description) /by (date)\n"
                + "     3. Record Event Task: event (description) /at (date)\n"
                + "     4. List Down Recorded Tasks: list\n"
                + "     5. Set Task After Completion: done (index on list)\n"
                + "     6. Exit From Program: bye\n" + LINE_DIVIDER;
    }

    public void printEventException(EventException e) {
        String output = LINE_DIVIDER + e.printStatement() +
                "Invalid Event Request. Format: event (description) /at (Date)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printDeadlineException(DeadlineException e) {
        String output = LINE_DIVIDER + e.printStatement()
                + "Invalid Deadline Request. Format: deadline (description) /by (Date)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printTodoException(TodoException e) {
        String output = LINE_DIVIDER + e.printStatement()
                + "Invalid Todo Request. Format: todo (description)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printDoneException(DoneException e) {
        String output = LINE_DIVIDER + e.printStatement()
                + "Invalid Done Request. Format: done (number)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printDeleteException(DeleteException e) {
        String output = LINE_DIVIDER + e.printStatement()
                + "Invalid Delete Request. Format: delete (number)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printDukeException(DukeException e) {
        String output = e.printStatement() + help();
        System.out.print(output);
    }

    public void printIOException(IOException e) {
        String output = LINE_DIVIDER + e.getMessage()
                + "Invalid Delete Request. Format: delete (number)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printSecurityException(SecurityException e) {
        String output = LINE_DIVIDER + e.getMessage()
                + "Invalid Delete Request. Format: delete (number)\n" + LINE_DIVIDER;
        System.out.println(output);
    }

    public void printLoadMessage() {
        System.out.println("Welcome back to Duke!");
        System.out.println("Give me a moment while I set things up for you");
        System.out.println("Loading Tasks...");
    }

    public void printLoadMessageComplete() {
        System.out.println("Task Successfully Imported\n");
    }

    public void printCorruptedLoadMessage() {
        System.out.println("Successfully Imported Uncorrupted Task");
    }

    public void printGreetings() {
        System.out.println("I am your very own schedule assistant here to enhance your everyday life");
        System.out.println("Type help to see what i can do!");
    }

    public void printSavedMessage() {
        String output = LINE_DIVIDER + GAP + "Your tasks has been saved\n" + LINE_DIVIDER;
        System.out.println(output);
    }
}
