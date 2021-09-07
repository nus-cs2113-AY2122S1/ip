public class Request {
    public static boolean isBye(String request) {
        return !request.equals("bye");
    }

    public static boolean isList(String request) {
        return request.trim().equals("list");
    }

    public static boolean isDone(String request) {
        return request.startsWith("done");
    }

    private static boolean isTodo(String request) {
        return request.startsWith("todo");
    }

    private static boolean isDeadline(String request) {
        return request.startsWith("deadline");
    }

    public static int parseTaskIndex(String request) {
        return Integer.parseInt(request.substring(TaskList.TASK_INDEX)) - 1;
    }

    public static Task parseTask(String request) {
        int descriptionBeginIndex = request.indexOf(" ") + 1;
        int dateIndex = request.indexOf("/");
        if (Request.isTodo(request)) {
            String description = request.substring(descriptionBeginIndex);
            return new Task(description);
        } else {
            String description = request.substring(descriptionBeginIndex, dateIndex);
            String date = request.substring(dateIndex + 1);
            if (Request.isDeadline(request)) {
                return new Deadline(description,date);
            }
            return new Event(description,date);
        }
    }
}
