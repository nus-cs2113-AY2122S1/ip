public class Request {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String INVALID_REQUEST = "invalid";

    public static boolean isBye(String request) {
        return !request.trim().equals(BYE);
    }

    public static boolean isList(String request) {
        return request.trim().equals(LIST);
    }

    public static boolean isDone(String request) {
        return request.startsWith(DONE);
    }

    private static boolean isTodo(String request) {
        return request.startsWith(TODO);
    }

    private static boolean isDeadline(String request) {
        return request.startsWith(DEADLINE);
    }

    private static boolean isEvent(String request) {
        return request.startsWith(EVENT);
    }

    private static boolean isSpecialTask(String request) {
        return isDeadline(request) || isEvent(request);
    }

    public static int parseTaskIndex(String request) {
        return Integer.parseInt(request.substring(Task.TASK_INDEX).trim()) - 1;
    }

//    public static String type(String request) {
//        if (isBye(request)) {
//            return BYE;
//        } else if (isList(request)) {
//            return LIST;
//        } else if (isDone(request)) {
//            return DONE;
//        } else if (isDeadline(request)) {
//            return DEADLINE;
//        } else if (isTodo(request)) {
//            return TODO;
//        } else if (isEvent(request)) {
//            return EVENT;
//        } else {
//            return INVALID_REQUEST;
//        }
//    }

    public static Task parseTask(String request) throws Exception {
        System.out.println("parsing task");
        if (Request.isTodo(request)) {
            System.out.println("building todo");
            return buildTodo(request.trim());
        } else if (Request.isEvent(request)) {
            System.out.println("building event");
            return buildEvent(request.trim());
        } else if (Request.isDeadline(request)){
            System.out.println("building deadline");
            return buildDeadline(request.trim());
        }
        System.out.println("invalid command");
        throw new InvalidRequestException();
    }

    private static Task buildTodo(String request) throws Exception{
        try {
            String description = getDescription(request);
            return new Task(description);
        } catch (Exception ex) {
            throw new IncompleteInformationException(TODO,"description");
        }
    }

    private static Task buildEvent(String request) throws Exception {
        try {
            String at = getTime(request);
            String description = getDescription(request);
            return new Event(description, at);
        } catch (Exception ex) {
            if (ex instanceof EmptyTimeException) {
                throw new IncompleteInformationException(EVENT,"time");
            }
            throw new IncompleteInformationException(EVENT,"description");
        }
    }

    private static Task buildDeadline(String request) throws Exception {
        try {
            String by = getTime(request);
            String description = getDescription(request);
            return new Deadline(description, by);
        } catch (Exception ex) {
            if (ex instanceof EmptyTimeException) {
                throw new IncompleteInformationException(DEADLINE,"time");
            }
            throw new IncompleteInformationException(DEADLINE,"description");
        }
    }

    private static String getTime(String request) throws EmptyTimeException{
        try {
            int timeIndex = request.indexOf("/");
            return request.substring(timeIndex + 1);
        } catch (Exception e) {
            throw new EmptyTimeException();
        }
    }

    private static String getDescription(String request) throws EmptyDescriptionException{
        try {
            int descriptionStartIndex = request.indexOf(" ");
            int descriptionEndIndex = Request.isSpecialTask(request) ? request.indexOf("/") : request.length();
            return request.substring(descriptionStartIndex,descriptionEndIndex).trim();
        } catch (Exception e) {
            throw new EmptyDescriptionException();
        }
    }


}
