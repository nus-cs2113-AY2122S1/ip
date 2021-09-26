package Duke;

import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Command {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskSum = 0;

    public static void printList() {
        Task now;
        System.out.println("     Here are the tasks in your list:");
        for(int i=0; i<taskSum; i++){
            now = tasks.get(i);
            System.out.println("     " + (i+1) + "." + now.toString());
        }
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!" );
    }

    public static void done(String command){
        String[] number = command.split(" ");
        int taskIndex = Integer.parseInt(number[1]) - 1;
        tasks.get(taskIndex).complete();
        System.out.println("     Nice! I've marked this task as done:" + "\n     " +  tasks.get(taskIndex).toString());
    }
    public static void delete(String command){
        String[] number = command.split(" ");
        int taskIndex = Integer.parseInt(number[1]) - 1;
        Task temp = tasks.get(taskIndex);
        taskSum--;
        System.out.println("     Noted. I've removed this task: " + "\n     " +  tasks.get(taskIndex).toString() + "\n" + "     Now you have " + taskSum + " tasks in the list");
        tasks.remove(taskIndex);
    }
    public static void todo(String command){
        int first = command.indexOf(" ");
        String item = command.substring(first,command.length());
        Task temp = new Todo(item);
        tasks.add(temp);
        taskSum++;
        System.out.println("     Got it. I've added this task: " + "\n" + "       [T][ ] " + item + "\n" + "     Now you have " + taskSum + " tasks in the list");
    }
    public static void deadline(String command){
        int first = command.indexOf(" ");
        int itemEnd = command.indexOf("/");
        String item = command.substring(first,itemEnd);
        String by = command.substring(itemEnd + 1,command.length());
        Task temp = new Deadline(item,by);
        tasks.add(temp);
        taskSum++;
        System.out.println( "     Got it. I've added this task: " + "\n" + "       [D][ ] " + item + " (" + by + ")" + "\n" + "     Now you have " + taskSum + " tasks in the list");

    }
    public static void event(String command){
        int first = command.indexOf(" ");
        int itemEnd = command.indexOf("/");
        String item = command.substring(first,itemEnd);
        String at = command.substring(itemEnd + 1,command.length());
        Task temp = new Event(item,at);
        tasks.add(temp);
        taskSum++;
        System.out.println("     Got it. I've added this task: " + "\n" + "       [E][ ] " + item + " (" + at + ")" + "\n" + "     Now you have " + taskSum + " tasks in the list");
    }
    public static void find(String command){
        System.out.println("     Here are the matching tasks in your list:");
        int first = command.indexOf(" ");
        Task now;
        String item = command.substring(first,command.length());
        for(int i=0; i<taskSum; i++) {
            now = tasks.get(i);
            if (now.toString().contains(item)) {
                System.out.println("     " + (i + 1) + "." + now.toString());
            }
        }
    }
    public static void printerror(){
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i=0; i<taskSum; i++){
            Task now = tasks.get(i);
            fw.write(String.format("%d ",i+1));
            fw.write(now.toString());
            fw.write("\n");
        }
        fw.close();
    }
}