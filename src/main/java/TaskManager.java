public class TaskManager {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void printList() {
        if (taskCount == 0) {
            UI.printEmptyListMessage();
        } else {
            UI.printList(tasks);
        }
    }

    public String[] decodeInput(String input) {
        return input.split(" ");
    }

    public void crossOff(String[] inputWords) {
        if (inputWords.length != 2) {
            UI.printInvalidMessage();
            return;
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (taskIndex >= taskCount) {
            UI.printInvalidMessage();
            return;
        }
        tasks[taskIndex].setDone();
        UI.printDoneMessage(tasks[taskIndex]);
    }

    public void addTask(String input, String[] inputWords, String command) {
        String description;
        switch (command) {
        case "todo":
            if (inputWords.length <= 1) {
                UI.printInvalidMessage();
                return;
            }
            description = input.substring(5);
            tasks[taskCount] = new ToDo(description);
            break;
        case "deadline":
            if (!input.contains("/by") || input.endsWith("/by")) {
                UI.printInvalidMessage();
                return;
            }
            String endDate;
            description = input.substring(9, input.indexOf("/by") - 1);
            endDate = input.substring(input.indexOf("/by") + 4);
            tasks[taskCount] = new Deadline(description, endDate);
            break;
        case "event":
            if (!input.contains("/at") || input.endsWith("/at")) {
                UI.printInvalidMessage();
                return;
            }
            String startEndTime;
            description = input.substring(6, input.indexOf("/at") - 1);
            startEndTime = input.substring(input.indexOf("/at") + 4);
            tasks[taskCount] = new Event(description, startEndTime);
            break;
        default:
            break;
        }
        taskCount = taskCount + 1;
        UI.printAdditionMessage(tasks[taskCount - 1], taskCount);
    }
}
