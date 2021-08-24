package task;

import java.util.HashMap;

public enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    INVALID;
    private static TaskType[] list = TaskType.values();
    private static final HashMap<TaskType, String> TaskType2Str =
        new HashMap<TaskType, String>() {{
            put(TODO, "todo");
            put(DEADLINE, "deadline");
            put(EVENT, "event");
            put(INVALID, "invalid");
        }};
    // https://stackoverflow.com/questions/6692664/how-to-get-enum-value-from-index-in-java
    public static TaskType getTaskTypebyIndex(int i) {
        return list[i];
    }

    public static String getTaskStrbyTaskType(TaskType taskType) {
        if (!TaskType2Str.containsKey(taskType)) {
            return "invalid";
        } else {
            return TaskType2Str.get(taskType);
        }
    }
}

