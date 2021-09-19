package processing;
import exceptions.DukeException;
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
    Predicate<Task> taskTypeFilter = task -> {
        if (taskType == TaskType.ANY) {
            return true;
        } else {
            return task.getTaskType() == taskType;
        }
    };

    Predicate<Task> regexFilter = task -> task.getFullDescription().matches(regexTerm);

    /*------------ INTERNAL FUNCTIONS --------- */

    private void setLimitKW(String kw) throws DukeException {
        try {
            queryLimit = Integer.parseInt(kw);
            isLimitFlag = false;
        } catch (NumberFormatException e) {
            throw new DukeException(LIMIT_EXCEPTION + kw + LIMIT_EXAMPLE);
        }
    }

    private void setTypeKW(String kw) throws DukeException {
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
            throw new DukeException(TYPE_EXCEPTION + kw + TYPE_EXAMPLE);
        }
        isTypeFlag = false;
    }

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

    public QueryHandler(String... keywords) throws DukeException {
        isRegexKW = isLimitFlag = isTypeFlag = false;
        regexTerm = REGEX_HEADER;
        regexTermWithoutExpr = "";
        queryLimit = Integer.MAX_VALUE;
        taskType = TaskType.ANY;

        for (String keyword : keywords) {
            processKeyword(keyword.trim());
        }
    }


    public void queryOn(TaskManager taskManager) {
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
