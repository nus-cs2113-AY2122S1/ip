import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task task = new Task();
        LinkedList<String> savedTasks = new LinkedList<String>();
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
            }  else if("done".equals(userInput)){
                task.markTaskAsDone();
            }else {
                task.taskName = userInput;
                taskList.addTasks(task.taskName);
            }
            userInput = sc.nextLine();
        }
        if("bye".equals(userInput)) {
            System.out.print(goodbyeMessage);
        }
    }
}
