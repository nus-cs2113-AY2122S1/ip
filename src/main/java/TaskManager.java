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
            // addEvent(userInput);
            break;

        case "deadline":
            // addDeadline(userInput);
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
                System.out.println(" " + (i + 1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
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
        System.out.println("   [X] " + taskList[index].getDescription());
        System.out.println(line);
    }

    // Change to type Todo later on
    private void addTodo(String userInput) {
        String[] destructuredInput = userInput.split(" ", 2);
        String todoName = destructuredInput[1];
        Task t = new Task(todoName);
        taskList[listIndex] = t;
        listIndex++;
        System.out.print(line);
        System.out.println(" I shall put this in your schedule, Master Wayne: \"" + todoName + "\".\n" );
        System.out.println(line);
    }
}
