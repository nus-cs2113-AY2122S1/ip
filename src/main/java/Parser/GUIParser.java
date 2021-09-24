package Parser;

public class GUIParser {

    public String[] parseTask(String taskType, String taskName, String time) {
        if (taskType.equals("todo")) {
            return new String[] {taskType, taskName, null};
        }

        return new String[] {taskType, taskName, time};
    }
}
