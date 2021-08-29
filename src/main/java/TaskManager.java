public class TaskManager {
    private Task[] tasks;
    private int numberOfTasks;

    public TaskManager() {
        this.tasks = new Task[100];
        this.numberOfTasks = 0;
    }

    public void processUserInput(String userInput) {
        if(userInput.equals("list")) {
            printAllTasks();
        } else if (userInput.startsWith("done ")) {
            String[] inputs = userInput.split(" ");
            int taskDoneNumber = Integer.parseInt(inputs[1]);
            setTaskDone(taskDoneNumber - 1);
        } else {
            addTask(userInput);
        }
    }

    private void addTask(String taskInput) {
        if(taskInput.startsWith("todo ")) {
            addTodoTask(taskInput);
        } else if (taskInput.startsWith("deadline ")) {
            addDeadlineTask(taskInput);
        } else if(taskInput.startsWith("event ")) {
            addEventTask(taskInput);
        } else {
            addNormalTask(taskInput);
        }
        printHorizontalLine();
        System.out.println("     Got it. I've added this task: \n"
                + "      " + tasks[numberOfTasks - 1]
                + "\n     Now you have " + numberOfTasks
                + (numberOfTasks == 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    private void addNormalTask(String taskInput) {
        tasks[numberOfTasks] = new Task(taskInput);
        numberOfTasks++;
    }

    private void addTodoTask(String taskInput) {
        String taskName = taskInput.substring(5);
        tasks[numberOfTasks] = new Todo(taskName);
        numberOfTasks++;
    }

    private void addDeadlineTask(String taskInput) {
        String taskDescriptionAndDeadline = taskInput.substring(9);
        int deadlineStartIndex = taskDescriptionAndDeadline.indexOf("/by");

        String taskDescription = taskDescriptionAndDeadline.substring(0, deadlineStartIndex);
        String deadline = taskDescriptionAndDeadline.substring(deadlineStartIndex + 4);
        tasks[numberOfTasks] = new Deadline(taskDescription, deadline);
        numberOfTasks++;
    }

    private void addEventTask(String taskInput) {
        String taskDescriptionAndStartTime = taskInput.substring(6);
        int startTimeStartIndex = taskDescriptionAndStartTime.indexOf("/at");

        String taskDescription = taskDescriptionAndStartTime.substring(0, startTimeStartIndex);
        String deadline = taskDescriptionAndStartTime.substring(startTimeStartIndex + 4);
        tasks[numberOfTasks] = new Event(taskDescription, deadline);
        numberOfTasks++;
    }

    private void printAllTasks() {
        printHorizontalLine();
        for(int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
        printHorizontalLine();
    }


    private void setTaskDone(int taskIndex) {
        if(taskIndex < 0 || taskIndex > numberOfTasks - 1) {
            printHorizontalLine();
            if(taskIndex < 0) {
                System.out.println("     Please enter a valid task number!");
            } else {
                System.out.println("     There is only " + numberOfTasks
                        + " item(s) in the list!");
            }
            printHorizontalLine();
            return;
        }

        tasks[taskIndex].markAsDone();
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + tasks[taskIndex]);
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println("    _____________________________________"
                +"_______________________");
    }
}
