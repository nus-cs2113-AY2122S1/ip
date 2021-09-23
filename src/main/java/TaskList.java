import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param query user raw data input
     * @throws IndexOutOfBoundsException if user keys in done [number] when there is no such task.
     */
    public static void addTask(String query) throws NullPointerException {
        Keyword keyword = Parser.getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            Parser.doDoneTask(tasks, query);
            break;
        case TODO_TASK:
            Parser.makeTodoTask(tasks, query);
            break;
        case EVENT_TASK:
            Parser.makeEventTask(tasks, query);
            break;
        case DEADLINE_TASK:
            Parser.makeDeadlineTask(tasks, query);
            break;
        case LIST_KEYWORD:
            Ui.printList(tasks);
            break;
        case DELETE_KEYWORD:
            Parser.deleteItem(tasks, query);
            break;
        case FIND_KEYWORD:
            Parser.findMatching(tasks, query);
            break;
        case NO_KEYWORD:
            Parser.wrongInputTypeMessage();
            break;
        case GOODBYE_KEYWORD:
            Ui.printGoodbyeMessage();
            break;
        }
    }

}
