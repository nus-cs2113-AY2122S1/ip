public class TaskHandler {

    protected Task[] tasks;

    public TaskHandler() {
        this.tasks = new Task[100];
    }

    public String handleTasks(String line) {
        String lc = line.toLowerCase();
        if (inputIsList(lc)) {
            return listTasks();
        } else if (inputIsDone(lc)) {
            return doTask(lc);
        } else if (inputIsTodo(lc)) {
            return addTodo(line);
        } else if (inputIsDeadline(lc)) {
            if (!deadlineContainsBy(lc)) {
                return returnDeadlineNoBy();
            } else {
                return addDeadline(line);
            }
        } else if (inputIsEvent(lc)) {
            if (!eventContainsAt(lc)) {
                return returnEventNoAt();
            } else {
                return addEvent(line);
            }
        } else {
            return returnInputInvalid();
        }
    }

    public boolean inputIsTodo(String lc) {
        //lc: lowercase line
        return lc.contains("todo") && lc.substring(0, 4).equals("todo");
    }

    public boolean inputIsDeadline(String lc) {
        return lc.contains("deadline") && lc.substring(0, 8).equals("deadline");
    }

    public boolean deadlineContainsBy(String lc) {
        return lc.contains("/by");
    }

    public String returnDeadlineNoBy() {
        //TODO: make random phrases
        return "By when, my liege?";
    }

    public boolean inputIsEvent(String lc) {
        return lc.contains("event") && lc.substring(0, 5).equals("event");
    }

    public boolean eventContainsAt(String lc) {
        return lc.contains("/at");
    }

    public String returnEventNoAt() {
        return "Where or when is this event, my liege?";
    }

    public boolean inputIsDone(String lc) {
        return lc.contains("done") && lc.substring(0, 4).equals("done");
    }

    public boolean inputIsBye(String lc) {
        return lc.equals("bye");
    }

    public String returnAddTaskSuccess() {
        return "As you command. Added: ";
    }

    public String addTodo(String line) {
        Todo newTodo = new Todo(line);
        tasks[Task.getNumOfTasks() - 1] = newTodo;
        return returnAddTaskSuccess() + newTodo.toString();
    }

    public String addDeadline(String line) {
        String lc = line.toLowerCase();
        int dividerPosition = lc.indexOf("/by");
        String description = line.substring(9, dividerPosition).trim();
        String by = line.substring(dividerPosition + 3).trim();
        Deadline newDeadline = new Deadline(description, by);
        tasks[Task.getNumOfTasks() - 1] = newDeadline;
        return returnAddTaskSuccess() + newDeadline.toString();
    }

    public String addEvent(String line) {
        String lc = line.toLowerCase();
        int dividerPosition = lc.indexOf("/at");
        String description = line.substring(6, dividerPosition).trim();
        String at = line.substring(dividerPosition + 3).trim();
        Event newEvent = new Event(description, at);
        tasks[Task.getNumOfTasks() - 1] = newEvent;
        return returnAddTaskSuccess() + newEvent.toString();
    }

    public String returnTaskCompleted() {
        return "Transcendent, my liege. You have completed: ";
    }

    public String returnDoTaskFail() {
        return "Have mercy, for that is beyond my knowledge.";
    }

    public String returnDoTaskNone() {
        return "You have no tasks, my liege.";
    }

    public String doTask(String lc) {
        if (Task.getNumOfTasks() > 0) {
            String inputNumStr = lc.replace("done", "").trim();
            int inputNum = Integer.parseInt(inputNumStr);
            if (inputNum > 0 && inputNum <= Task.getNumOfTasks()) {
                int id = inputNum - 1;
                tasks[id].setDone();
                return returnTaskCompleted() + System.lineSeparator()
                        + InoutputFormatter.printOutputStart() + tasks[id].toString();
            } else {
                return returnDoTaskFail();
            }
        } else {
            return returnDoTaskNone();
        }
    }

    public boolean inputIsList(String lc) {
        return lc.equals("list");
    }

    public String listTasks() {
        String out = "Your magnificent tasks:";
        for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
            //output will be doubly "indented"
            out += System.lineSeparator() + InoutputFormatter.printOutputStart()
                    + tasks[i].toString();
        }
        return out;
    }

    public String returnInputInvalid() {
        return "I do not comprehend, my liege.";
    }

}
