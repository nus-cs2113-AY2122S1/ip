package duke.Ui;
import duke.task.Task;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import duke.Storage.Storage;
import duke.task.TaskList;

public class Ui {

    private Scanner scanner;
    public static final String SEPARATOR = "-----------------------------------";

    public Ui(InputStream input){
        this.scanner = new Scanner(input);
    }

    public Ui() {
        this(System.in);
    }

   public void printDukeLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

       System.out.println("Hello from\n" + logo);
   }

   public void printGreet(){
       System.out.println(SEPARATOR);
       System.out.println("Hello! What can I do for you?");
       System.out.println(SEPARATOR);
   }
   public void printBye(){
       System.out.println(SEPARATOR);
       System.out.println("Bye. Hope to see you again soon!");
       System.out.println(SEPARATOR);
   }

   public void printErrorMessage(String errorMessage){
        System.out.println(SEPARATOR);
        System.out.println(errorMessage);
        System.out.println(SEPARATOR);
   }

   public static void  printAddNormalTask(String command){
       System.out.println(SEPARATOR);
       System.out.println("added:" + " " + command);
       System.out.println(SEPARATOR);
       //listLength++;
   }

   public static void printAddEventMessage(Task event, ArrayList tasks){
       System.out.println(SEPARATOR);
       System.out.println("Got it. I've added this task: ");
       System.out.println(event);
       System.out.println("Now you have " + tasks.size() + " tasks in the list");
       System.out.println(SEPARATOR);
   }

   public static void printAddDeadlineMessage(Task deadline, ArrayList tasks){
       System.out.println(SEPARATOR);
       System.out.println("Got it. I've added this task: ");
       System.out.println(deadline);
       System.out.println("Now you have " + tasks.size() + " tasks in the list");
       System.out.println(SEPARATOR);
   }

   public static void printTodoMessage(Task todo, ArrayList tasks){
       System.out.println(SEPARATOR);
       System.out.println("Got it. I've added this task:");
       System.out.println(todo);
       System.out.println("Now you have" + " "+ tasks.size() + " tasks in the list.");
       System.out.println(SEPARATOR);
   }

   public static void printDeleteMessage(Task deletedTask, ArrayList tasks){
       System.out.println(SEPARATOR);
       System.out.println("Noted. I've removed this task: ");
       System.out.println(deletedTask);
       System.out.println("Now you have " + tasks.size() + " tasks in the list.");
       System.out.println(SEPARATOR);
   }

   public static void printMarkAsDoneMessage(Task done){
       System.out.println(SEPARATOR);
       System.out.println("Nice! I've marked this task as done: ");
       System.out.println(done);
       System.out.println(SEPARATOR);
   }

   public static void printTasks(TaskList tasks){
       if(tasks.getSize() == 0){
           System.out.println(SEPARATOR);
           System.out.println("Hmm...It seems that you have no task now.");
           System.out.println(SEPARATOR);
       }
       else{
           System.out.println(SEPARATOR);
           System.out.println("Here are the tasks in your list:");
           for(int i = 0; i< tasks.getSize();i++){
               System.out.println((i+1) + "." + " " + tasks.getIndexTask(i));
           }
           System.out.println(SEPARATOR);
       }
   }

   public static void printSelectedTasks(ArrayList<Task> result,String keyword){
        System.out.println(SEPARATOR);
        System.out.println("Hey! I found these tasks contains keyword" + " " + "\"" + keyword + "\"" + ":");
        for(int i = 0; i < result.size();i++){
            System.out.println((i+1) + "." + " " + result.get(i));
        }
        System.out.println(SEPARATOR);

   }



}
