package duke;
import duke.tasks.Task;
import java.util.Scanner;

public class Ui {

    /**
     * Represents the output on the terminal, based on the Command executed.
     * Command executed based off the user input.
     */

    private static Scanner in = new Scanner(System.in);

    public static void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void showWelcome(){
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm duke.Duke\n"
                + "What can I do for you?";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
        showLine();
    }

    public String readCommand(){
        return in.nextLine();
    }

    public void addTask(Task t, TaskList taskList){
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    public void listTask(TaskList taskList){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currTask = taskList.get(i - 1);
            System.out.println(i + ". " + currTask);
        }
    }

    public void doneTask(int index, TaskList taskList){
        Task currTask = taskList.get(index);
        System.out.println((index + 1) + ". " + currTask);
    }

    public void deleteTask(int index, TaskList taskList){
        Task currTask = taskList.get(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currTask);
        System.out.println("Now you have " + (taskList.size()  - 1) + " tasks on the list.");
    }

    public void findTask(String keyword, TaskList taskList){
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currTask = taskList.get(i - 1);
            if (currTask.getDescription().contains(keyword)) {
                System.out.println(i + ". " + currTask);
            }
        }
    }

    public void showLoadingError(){
        return;
    }

    public void showBye(){
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit);
    }

}
