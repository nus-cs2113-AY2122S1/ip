package Duke;

import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Command {
    /** The data structure to store all task information**/
    private static ArrayList<Task> tasks = new ArrayList<>();
    /** The integer to store the sum of tasks**/
    private static int taskSum = 0;

    /**
     * The methods to print all tasks.
     */
    public static void printList() {
        Task now;
        System.out.println("     Here are the tasks in your list:");
        for(int i=0; i<taskSum; i++){
            now = tasks.get(i);
            System.out.println("     " + (i+1) + "." + now.toString());
        }
    }

    /**
     * The methods to close the system.
     */
    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!" );
    }

    /**
     * The methods to mark the task as done.
     * @param command A String to store the information of done command.
     */
    public static void done(String command){
        String[] number = command.split(" ");
        int taskIndex = Integer.parseInt(number[1]) - 1;
        tasks.get(taskIndex).complete();
        System.out.println("     Nice! I've marked this task as done:" + "\n     " +  tasks.get(taskIndex).toString());
    }

    /**
     * The methods to delete a task from the list.
     * @param command A String to store the information of delete command.
     */
    public static void delete(String command){
        String[] number = command.split(" ");
        int taskIndex = Integer.parseInt(number[1]) - 1;
        taskSum--;
        System.out.println("     Noted. I've removed this task: " + "\n     " +  tasks.get(taskIndex).toString() + "\n" + "     Now you have " + taskSum + " tasks in the list");
        tasks.remove(taskIndex);
    }

    /**
     * The methods to add a todo tyoe into the list.
     * @param command A String to store the information of todo command.
     */
    public static void todo(String command){
        int first = command.indexOf(" ");
        String item = command.substring(first+1,command.length());
        Task temp = new Todo(item);
        tasks.add(temp);
        taskSum++;
        System.out.println("     Got it. I've added this task: " + "\n" + "       [T][ ] " + item + "\n" + "     Now you have " + taskSum + " tasks in the list");
    }

    /**
     * The methods to add a deadline tyoe into the list.
     * @param command A String to store the information of deadline command.
     */
    public static void deadline(String command){
        int first = command.indexOf(" ");
        int itemEnd = command.indexOf("/");
        String item = command.substring(first+1,itemEnd);
        String by = command.substring(itemEnd + 1,command.length());
        Task temp = new Deadline(item,by);
        tasks.add(temp);
        taskSum++;
        System.out.println( "     Got it. I've added this task: " + "\n" + "       [D][ ] " + item + "(" + by + ")" + "\n" + "     Now you have " + taskSum + " tasks in the list");

    }

    /**
     * A String to store the information of event command.
     * @param command A String to store the information of event command.
     */
    public static void event(String command){
        int first = command.indexOf(" ");
        int itemEnd = command.indexOf("/");
        String item = command.substring(first+1,itemEnd);
        String at = command.substring(itemEnd + 1,command.length());
        Task temp = new Event(item,at);
        tasks.add(temp);
        taskSum++;
        System.out.println("     Got it. I've added this task: " + "\n" + "       [E][ ] " + item +  at  + "\n" + "     Now you have " + taskSum + " tasks in the list");
    }

    /**
     * The methods to find a task by searching for a keyword.
     * @param command A String to store the information of event command.
     */
    public static void find(String command){
        int first = command.indexOf(" ");
        int sum = 0 ;
        Task now;
        String item = command.substring(first+1,command.length());
        for(int i=0; i<taskSum; i++) {
            now = tasks.get(i);
            if (now.toString().contains(item)) {
                if(sum == 0){
                    System.out.println("     Here are the matching tasks in your list:");
                }
                System.out.println("     " + (++sum) + "." + now.toString());
            }
        }
        if(sum == 0){
            System.out.println("     Here are no matching tasks in your list:");
        }
    }

    /**
     * The methods means that Duke cannot understand the command.
     */
    public static void printerror(){
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * The methods to write the output into the file.
     * @param filePath A string store the path of the output file.
     * @throws IOException Exception strows when cannot find the path of the file.
     */
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

    /**
     * process information and add a Todo into list
     * @param command A string store the raw information of this Todo
     */
    public static void addTodo(String command){
        String item = command.substring(8,command.length());
        Task temp = new Todo(item);
        if(command.charAt(6) == 'X'){
            temp.complete();
        }
        tasks.add(temp);
        taskSum++;
    }

    /**
     * process information and add an Event into list
     * @param command A string store the raw information of this Event
     */
    public static void addEvent(String command){
        String content = command.substring(8,command.length());
        int itemEnd = content.indexOf("(");
        String item = content.substring(0,itemEnd);
        String at = content.substring(itemEnd+1,content.length()-1);
        Task temp = new Event(item,at);
        if(command.charAt(6) == 'X'){
            temp.complete();
        }
        tasks.add(temp);
        taskSum++;
    }

    /**
     * process information and add a Deadline into list
     * @param command A string store the raw information of this Deadline
     */
    public static void addDeadline(String command){
        String content = command.substring(8,command.length());
        int itemEnd = content.indexOf("(");
        String item = content.substring(0,itemEnd);
        String by = content.substring(itemEnd+1,content.length()-1);
        Task temp = new Deadline(item,by);
        if(command.charAt(6) == 'X'){
            temp.complete();
        }
        tasks.add(temp);
        taskSum++;
    }

}