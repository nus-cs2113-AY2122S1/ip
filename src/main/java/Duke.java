import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int taskListIndex;
        Task currentTask = new Task();
        LinkedList<Task> savedTasks = new LinkedList<Task>();
        TaskList taskList = new TaskList(savedTasks);
        String welcomeMessage = "______________________________\n"
                + "Hello! I'm Friday\n"
                + "What can I do for you?\n"
                + "______________________________\n";
        String goodbyeMessage = "______________________________\n"
                + "Bye! Hope to see you again soon!\n"
                + "______________________________\n";
        System.out.print(welcomeMessage);

        String userInput = sc.nextLine();
        while(!"bye".equals(userInput)){
            if("list".equals(userInput)) {
                System.out.println("______________________________\n");
                taskList.listTasks();
                System.out.println("______________________________\n");
            }  else if(userInput.contains("done")){
                taskListIndex = Integer.parseInt(identifyUserInput(userInput));
                currentTask = taskList.findTask(taskListIndex);
                currentTask.markTaskAsDone();
            }else {
                Task task = new Task(userInput, false);
                taskList.addTasks(task);
                System.out.println("______________________________\n");
                System.out.println(task.taskName + " has been added!\n");
                System.out.println("______________________________\n");
            }
            userInput = sc.nextLine();
        }
        if("bye".equals(userInput)) {
            System.out.print(goodbyeMessage);
        }
    }

    public static String identifyUserInput(String userInput){
        String[] parts = userInput.split(" ");
        String taskIndex = parts[1];
        return taskIndex;
    }
}
