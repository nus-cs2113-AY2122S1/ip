package processing;
import exceptions.DukeException;
import exceptions.QuerySyntaxException;
import org.jetbrains.annotations.NotNull;
import tasks.Task;
import tasks.TaskType;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QueryHandler {
    /*----------- PROCESSING CONSTANTS ---------- */
    private static final String LIMIT_FLAG = "/limit";
    private static final String TYPE_FLAG = "/type";
    private static final String REGEX_HEADER = "(?i).*";
    private static final String REGEX_TAIL = ".*";
    private static final String QUERY_TASK = "Your query returned the following results: ";
    private static final String EMPTY_QUERY = "     >>No tasks can be found with the regex : ";

    /* ----------- PRIVATE VARIABLES ------------- */
    private boolean isRegexKW, isTypeFlag, isLimitFlag;
    private String regexTerm;
    private String regexTermWithoutExpr;
    private TaskType taskType;
    private int queryLimit;

    /* ----------- EXCEPTION MESSAGES ---------*/
    private static final String LIMIT_EXCEPTION = "Sorry! Unable to recognise valid limit : ";
    private static final String LIMIT_EXAMPLE = "\nLimit Flag --> Single Integer eg. /limit 5";
    private static final String TYPE_EXCEPTION = "Sorry! Unable to recognise valid type : ";
    private static final String TYPE_EXAMPLE = "\nSorry! Type Flag --> todo/deadline/event";

    /*------------- FILTERS ---------------*/
    /**
     * Filters the tasks by the task type. If ANY, allows every task
     */
    Predicate<Task> taskTypeFilter = task -> {
        if (taskType == TaskType.ANY) {
            return true;
        } else {
            return task.getTaskType().equals(taskType);
        }
    };

    /**
     * Filters the task by the Regular Expression
     */
    Predicate<Task> regexFilter = task -> task.getFullDescription().matches(regexTerm);

    /*------------ INTERNAL FUNCTIONS --------- */

    /**
     * Parses the keyword if isLimitFlag was set to true, and set the query limit
     * Sets isLimitFlag to false after
     * @param kw keyword from the command
     * @throws DukeException if keyword is unable to be parsed as an integer
     */
    private void setLimitKW(String kw) throws DukeException {
        try {
            queryLimit = Integer.parseInt(kw);
            isLimitFlag = false;
        } catch (NumberFormatException e) {
            throw new QuerySyntaxException(LIMIT_EXCEPTION + kw + LIMIT_EXAMPLE);
        }
    }

    /**
     * Sets the TaskType to be filtered by the query
     * @see TaskType
     * @param kw keyword to determine task type : todo / deadline / event
     * @throws DukeException
     */
    private void setTypeKW(@NotNull String kw) throws DukeException {
        switch (kw.toLowerCase(Locale.ROOT)) {
        case "todo":
            taskType = TaskType.TODO;
            break;
        case "deadline":
            taskType = TaskType.DEADLINE;
            break;
        case "event":
            taskType = TaskType.EVENT;
            break;
        default:
            throw new QuerySyntaxException(TYPE_EXCEPTION + kw + TYPE_EXAMPLE);
        }
        isTypeFlag = false;
    }

    /**
     * Takes in the keyword, and processes it according to the flags set, OR sets a flag if
     * keyword starts with clause. Calls <b>setLimitKW</b> and <b>setTypeKW</b>>
     * @param kw keyword that comes from the parsed command
     * @throws DukeException if setLimitKW or setTypeKW throws an exception
     */
    private void processKeyword(String kw) throws DukeException {

        if (isLimitFlag) {
            setLimitKW(kw);
            return;
        }

        if (isTypeFlag) {
            setTypeKW(kw);
            return;
        }

        if (kw.startsWith(LIMIT_FLAG)) {
            isLimitFlag = true;
            return;
        }

        if (kw.startsWith(TYPE_FLAG)) {
            isTypeFlag = true;
            return;
        }

        // if keyword doesnt start with '-' and is not part of a type/limit flag and
        // if no regex term has been added --> Treat term as regex term
        if (!isRegexKW) {
            isRegexKW = true;
            regexTerm += kw + REGEX_TAIL;
            regexTermWithoutExpr += kw + " ";
        }
    }

    /**
     * Constructor that takes in vaargs of Strings from the parsed Command Handler
     * @param keywords keywords to be processed to build the query
     * @throws DukeException
     */
    public QueryHandler(String @NotNull ... keywords) throws DukeException {
        isRegexKW = isLimitFlag = isTypeFlag = false;
        regexTerm = REGEX_HEADER;
        regexTermWithoutExpr = "";
        queryLimit = Integer.MAX_VALUE;
        taskType = TaskType.ANY;

        for (String keyword : keywords) {
            processKeyword(keyword.trim());
        }
    }

    /**
     * Queries the task list of a Task Manager by the flags set.
     * Filters list by -TaskType -REGEX
     * Limits list by the queryLimit if set
     * Displays resulting task list in iterative order
     * @param taskManager taskManager to be queried by the query
     */
    public void queryOn(@NotNull TaskManager taskManager) {
        List<Task> tasks = taskManager.getTasks();
        System.out.println(QUERY_TASK);
        List<Task> listOfTasks = tasks
                .stream()
                .filter(regexFilter)
                .filter(taskTypeFilter)
                .limit(queryLimit)
                .collect(Collectors.toList());
        taskManager.listTasks(listOfTasks);
        if (listOfTasks.isEmpty()) {
            System.out.println(EMPTY_QUERY + regexTermWithoutExpr);
        }
    }

}
