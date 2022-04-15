public class Command {
    private static final int TODO_INDEX = 5;
    private static final int DEADLINE_INDEX = 9;
    private static final int EVENT_INDEX = 6;
    private static final int DELETE_INDEX = 7;
    private static final int DONE_INDEX = 5;
    private static final int FIND_INDEX = 5;

    /**
     * This method calls the printTaskList method to print all current tasks in the TaskList.
     *
     * @param list the list of tasks in TaskList
     */
    public static void handleListCommand(TaskList list) {
        list.printTaskList();
    }

    /**
     * This method calls the markIsDne method to mark a task as done and
     * writes to the duke text file to visually indicate the task has been done on the text file.
     *
     * @param list the list of tasks in TaskList
     * @param line the input typed in by the user
     */
    public static void handleDoneCommand(TaskList list, String line) {
        try {
            list.markIsDone(Integer.parseInt(line.substring(DONE_INDEX).trim()));
            Storage.writeDukeTextFile(list);
        } catch (NullPointerException e) {
            Ui.printOutOfBounds();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (NumberFormatException e) {
            Ui.printWrongFormat();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (IndexOutOfBoundsException e) {
            Ui.printOutOfBounds();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        }
    }

    /**
     * This method calls the createToDoTask method to create a todo task and
     * writes to the duke text file to add the newly created todo task.
     *
     * @param list the list of tasks in TaskList
     * @param line the input typed in by the user
     */
    public static void handleTodoCommand(TaskList list, String line) {
        String InputType = "Todo";
        try {
            createToDoTask(line, list);
            Storage.writeDukeTextFile(list);
        } catch (TodoEmptyException e) {
            Ui.printInputEmpty(InputType);
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        }
    }

    /**
     * This method calls the createDeadlineTask method to create a deadline task and
     * writes to the duke text file to add the newly created deadline task.
     *
     * @param list the list of tasks in TaskList
     * @param line the input typed in by the user
     */
    public static void handleDeadlineCommand(TaskList list, String line) {
        String InputType = "Deadline";
        try {
            createDeadlineTask(line, list);
            Storage.writeDukeTextFile(list);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printDeadlineNoDate();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (DeadlineEmptyException e) {
            Ui.printInputEmpty(InputType);
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        }
    }

    /**
     * This method calls the handleEventCommand method to create an event task and
     * writes to the duke text file to add the newly created event task.
     *
     * @param list the list of tasks in TaskList
     * @param line the input typed in by the user
     */
    public static void handleEventCommand(TaskList list, String line) {
        String InputType = "Event";
        try {
            createEventTask(line, list);
            Storage.writeDukeTextFile(list);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printEventNoDate();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (EventEmptyException e) {
            Ui.printInputEmpty(InputType);
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        }
    }

    /**
     * This method calls the handleDeleteCommand method to delete task and
     * writes to the duke text file to delete the specified task.
     *
     * @param list the list of tasks in TaskList
     * @param line the input typed in by the user
     */
    public static void handleDeleteCommand(TaskList list, String line) {
        String InputType = "Delete";
        try {
            list.deleteTask(Integer.parseInt(line.substring(DELETE_INDEX).trim()));
            Storage.writeDukeTextFile(list);
        } catch (NullPointerException e) {
            Ui.printOutOfBounds();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (NumberFormatException e) {
            Ui.printWrongFormat();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printInputEmpty(InputType);
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        }
    }

    /**
     * This method calls the handleFindCommand method to find tasks with matching keyword.
     *
     * @param list the list of tasks in TaskList
     * @param line the input typed in by the user
     */
    public static void handleFindCommand(TaskList list, String line) {
        String InputType = "Find";
        try {
            list.findTask(line.substring(FIND_INDEX).trim());
        } catch (NullPointerException e) {
            Ui.printOutOfBounds();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (NumberFormatException e) {
            Ui.printWrongFormat();
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (FindEmptyException e) {
            Ui.printInputEmpty(InputType);
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printInputEmpty(InputType);
            Ui.printReferralMessage();
            Ui.printCommandGuide();
        }
    }

    /**
     * this method handles an invalid command input by calling on several Ui methods
     * to tell the user his/her input is invalid.
     */
    public static void handleInvalidCommand() {
        Ui.printInvalidCommand();
        Ui.printReferralMessage();
        Ui.printCommandGuide();
    }

    /**
     * This method creates a new todo task and calls printEcho method to tell the user a todo task has been added.
     *
     * @param line the list of tasks in TaskList
     * @param list the input typed in by the user
     * @throws TodoEmptyException if the todo input substring consists of only whitespaces or is empty
     */
    public static void createToDoTask(String line, TaskList list) throws TodoEmptyException {
        if (line.substring(TODO_INDEX).trim().isEmpty()) {
            throw new TodoEmptyException();
        } else {
            Task task = new ToDo(line.substring(TODO_INDEX));
            list.addTaskList(task);
            Ui.printEcho(task, list);
        }
    }

    /**
     * This method creates a new deadline task and calls printEcho method to tell the user a deadline task has been added.
     *
     * @param line the list of tasks in TaskList
     * @param list the input typed in by the user
     * @throws ArrayIndexOutOfBoundsException if the deadline has no /by date
     * @throws DeadlineEmptyException         if the deadline input substring consists of only whitespaces or is empty
     */
    public static void createDeadlineTask(String line, TaskList list) throws DeadlineEmptyException, ArrayIndexOutOfBoundsException {
        String[] deadline = line.split("/by ");
        if (deadline[1] == null) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (deadline[0].substring(DEADLINE_INDEX).trim().isEmpty()) {
            throw new DeadlineEmptyException();
        } else if (deadline[1].trim().isEmpty()) {
            throw new DeadlineEmptyException();
        } else {
            Task task = new Deadline(deadline[0].substring(DEADLINE_INDEX), deadline[1]);
            list.addTaskList(task);
            Ui.printEcho(task, list);
        }
    }

    /**
     * This method creates a new event task and calls printEcho method to tell the user an event task has been added.
     *
     * @param line the list of tasks in TaskList
     * @param list the input typed in by the user
     * @throws ArrayIndexOutOfBoundsException if the event has no /at date
     * @throws EventEmptyException            if the event input substring consists of only whitespaces or is empty
     */
    public static void createEventTask(String line, TaskList list) throws EventEmptyException, ArrayIndexOutOfBoundsException {
        String[] event = line.split("/at ");
        if (event[1] == null) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (event[0].substring(EVENT_INDEX).trim().isEmpty()) {
            throw new EventEmptyException();
        } else if (event[1].trim().isEmpty()) {
            throw new EventEmptyException();
        } else {
            Task task = new Event(event[0].substring(EVENT_INDEX), event[1]);
            list.addTaskList(task);
            Ui.printEcho(task, list);
        }
    }

}
