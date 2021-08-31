public class TaskManager {
    private Task[] taskList;
    private int listIndex;

    public TaskManager() {
        taskList = new Task[100];
        listIndex = 0;
        initAlfred();
    }

    private String line = "____________________________________________________________\n";

    private void initAlfred() {
        String logo =
                " **********************************\n" +
                " *     _    _  __              _  *\n" +
                " *    / \\  | |/ _|_ __ ___  __| | *\n" +
                " *   / _ \\ | | |_| '__/ _ \\/ _` | *\n" +
                " *  / ___ \\| |  _| | |  __/ (_| | *\n" +
                " * /_/   \\_\\_|_| |_|  \\___|\\__,_| *\n" +
                " **********************************\n";
        System.out.println(logo);
        System.out.println(
                line +
                " Welcome back, Master Wayne.\n" +
                " How may I be of service to you?\n" +
                line
        );
    }

    public void shutdownMessage() {
        System.out.println(line + " Very well sir, I shall leave you to your own devices.\n" + line);
    }

    public void processInput(String userInput) {
        String[] destructuredInput = userInput.split(" ");
        switch (destructuredInput[0]) {
        case "list":
            listTasks();
            break;
        case "done":
            completeTask(userInput);
            break;
        case "todo":
            addTodo(userInput);
            break;
        case "event":
            addEvent(userInput);
            break;
        case "deadline":
            addDeadline(userInput);
            break;
        default:
            invalidCommandMessage();
        }
    }

    private void invalidCommandMessage() {
        System.out.println(line + " Perhaps you could rephrase that in a way us civilians could comprehend.\n" + line);
    };

    private void listTasks() {
        System.out.print(line);
        if (listIndex == 0) {
            System.out.println(" Your schedule is clear, Master Wayne.");
        } else {
            System.out.println(" Your tasks, sir:");
            for (int i = 0; i < listIndex; i++) {
                System.out.println(" " + (i + 1) + "." + taskList[i].toString());
            }
        }
        System.out.println(line);
    }

    private void completeTask(String userInput) {
        String[] destructuredInput = userInput.split(" ");
        int index = Integer.parseInt(destructuredInput[1]) - 1;
        taskList[index].setTaskDone();
        System.out.print(line);
        System.out.println(" Duly noted on completion of task, sir.");
        System.out.println("    " + destructuredInput[1] + "." + taskList[index].toString());
        System.out.println(line);
    }

    private void addTaskMessage(Task t) {
        System.out.print(line);
        System.out.println(" I shall put this in your schedule, Master Wayne: \n    " + t.toString());
        System.out.println(" Sir, the number of Tasks you have scheduled currently amounts to " + listIndex + ".");
        System.out.println(line);
    }

    private void addTodo(String userInput) {
        String[] destructuredInput = userInput.split(" ", 2);
        String todoName = destructuredInput[1];
        Todo t = new Todo(todoName);
        taskList[listIndex] = t;
        listIndex++;
        addTaskMessage(t);
    }

    private void addEvent(String userInput) {
        String[] arguments = userInput.split(" ", 2);
        String[] destructuredArguments = arguments[1].split(" /at ", 2);
        Event e = new Event(destructuredArguments[0], destructuredArguments[1]);
        taskList[listIndex] = e;
        listIndex++;
        addTaskMessage(e);
    }

    private void addDeadline(String userInput) {
        String[] arguments = userInput.split(" ", 2);
        String[] destructuredArguments = arguments[1].split(" /by ", 2);
        Deadline d = new Deadline(destructuredArguments[0], destructuredArguments[1]);
        taskList[listIndex] = d;
        listIndex++;
        addTaskMessage(d);
    }
}
