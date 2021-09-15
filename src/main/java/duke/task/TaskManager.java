package duke.task;
import duke.exception.InvalidInputException;
import java.util.ArrayList;


public class TaskManager {
    private static ArrayList<Todo> tasks = new ArrayList<Todo>();

    public static void addTodo(String userInput) {
        try {
            if (userInput.length()<5) {
                throw new InvalidInputException("OOPS!!! Description of todo cannot be empty :(");
            }
            int startIndexOfTask = userInput.indexOf(' ');
            Todo newTask = new Todo(userInput);
            tasks.add(newTask);
            printTaskAddedConfirmation(newTask);
        } catch (InvalidInputException e){
            System.out.println(e.toString().substring(23));
        }
    }

    public static void addDeadline(String userInput) {
        try {
            if (userInput.length()<9){
                throw new InvalidInputException("OOPS!!! Description of deadline cannot be empty :(");
            }
            if (!userInput.contains("/by ")){
                throw new InvalidInputException("OOPS!! Please input date of deadline");
            }
            int startIndexOfTask = userInput.indexOf(' ');
            int endIndexOfTask = userInput.indexOf('/') - 1;

            int startIndexOfDeadline = userInput.indexOf('/') + 4;
            String taskName = userInput.substring(startIndexOfTask, endIndexOfTask);
            String deadline = userInput.substring(startIndexOfDeadline);
            Deadline newTask = new Deadline(taskName, deadline);
            tasks.add(newTask);
            printTaskAddedConfirmation(newTask);
        } catch (InvalidInputException e){
            System.out.println(e.toString().substring(23));
        }
    }

    public static void addEvent(String userInput) {
        try {
            if (userInput.length()<6) {
                throw new InvalidInputException("OOPS!!! Description of event cannot be empty :(");
            }
            if (!userInput.contains("/at ")){
                throw new InvalidInputException("OOPS!! Please input time of event");
            }
            int startIndexOfTask = userInput.indexOf(' ');
            int endIndexOfTask = userInput.indexOf('/') - 1;
            int startIndexOfDate = userInput.indexOf('/') + 4;
            String taskName = userInput.substring(startIndexOfTask, endIndexOfTask);
            String deadline = userInput.substring(startIndexOfDate);
            Event newTask = new Event(taskName, deadline);
            tasks.add(newTask);
            printTaskAddedConfirmation(newTask);
        } catch (InvalidInputException e){
            System.out.println(e.toString().substring(23));
        }

    }

    public static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("\tNo tasks added");
            return;
        }
        int count =1;
        for (Todo task : tasks) {
            System.out.println("\tHere are the tasks in your list");
            System.out.println("\t"+count+"." + task);
            count++;
        }
    }

    public static void markDone(String userInput) {
        int indexOfTaskNumber = userInput.indexOf(' ') + 1;
        int taskNumber = Integer.parseInt(userInput.substring(indexOfTaskNumber));
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            System.out.println("\tNo such task");
            return;
        }
        Todo task = tasks.get(taskNumber - 1);
        task.setIsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public static void deleteTask(String userInput){
        int indexOfTaskNumber = userInput.indexOf(' ') + 1;
        int taskNumber = Integer.parseInt(userInput.substring(indexOfTaskNumber));
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            System.out.println("\tNo such task");
            return;
        }
        Todo task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber-1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t"+task.toString());
        System.out.println("\t Now you have "+tasks.size()+" tasks in the list.");
    }

    private static void printTaskAddedConfirmation(Todo task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.format("\tNow you have %d tasks in the list.\n", tasks.size());
    }
}
