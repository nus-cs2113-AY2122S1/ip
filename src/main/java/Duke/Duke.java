package Duke;
import java.util.Scanner;

public class Duke {
    public static final String Line = "    ____________________________________________________________";
    private static Task[] tasks = new Task[110];
    private static int taskSum = 0;
    public static void printList() {
        Task now;
        System.out.println(Line);
        System.out.println("     Here are the tasks in your list:");
        for(int i=0; i<taskSum; i++){
            now = tasks[i];
            System.out.println("     " + (i+1) + "." + now.toString());
        }
        System.out.println(Line);
    }
    public static void bye() {
        System.out.println(Line + "\n" + "     Bye. Hope to see you again soon!" + "\n" + Line);
    }
    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(Line);
    }
    public static void main(String[] args) {
        greeting();
        String command;
        Scanner in = new Scanner(System.in);
        do{
            command = in.nextLine();
            if(command.contains("done")){
                String[] number = command.split(" ");
                int taskIndex = Integer.parseInt(number[1]) - 1;
                tasks[taskIndex].complete();
                System.out.println(Line + "\n" + "     Nice! I've marked this task as done:" + "\n     " + tasks[taskIndex].toString() + "\n" + Line);
            }
            else if(command.contains("todo")){
                int first = command.indexOf(" ");
                int check = command.indexOf("d");
                if(check == command.length()-2){
                    System.out.println(Line);
                    System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(Line);
                    continue;
                }
                String item = command.substring(first,command.length());
                tasks[taskSum] = new Todo(item);
                taskSum++;
                System.out.println(Line + "\n" + "     Got it. I've added this task: " + "\n" + "       [T][ ] " + item + "\n" + "     Now you have " + taskSum + " tasks in the list");
                System.out.println(Line);
            }
            else if(command.contains("deadline")){
                int first = command.indexOf(" ");
                int check = command.indexOf("n");
                if(check == command.length()-2){
                    System.out.println(Line);
                    System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(Line);
                    continue;
                }
                int itemEnd = command.indexOf("/");
                String item = command.substring(first,itemEnd);
                String by = command.substring(itemEnd + 1,command.length());
                tasks[taskSum] = new Deadline(item,by);
                taskSum++;
                System.out.println(Line + "\n" + "     Got it. I've added this task: " + "\n" + "       [D][ ] " + item + " (" + by + ")" + "\n" + "     Now you have " + taskSum + " tasks in the list");
                System.out.println(Line);
            }
            else if(command.contains("event")){
                int first = command.indexOf(" ");
                int check = command.indexOf("t");
                if(check == command.length()-1){
                    System.out.println(Line);
                    System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(Line);
                    continue;
                }
                int itemEnd = command.indexOf("/");
                String item = command.substring(first,itemEnd);
                String at = command.substring(itemEnd + 1,command.length());
                tasks[taskSum] = new Event(item,at);
                taskSum++;
                System.out.println(Line + "\n" + "     Got it. I've added this task: " + "\n" + "       [E][ ] " + item + " (" + at + ")" + "\n" + "     Now you have " + taskSum + " tasks in the list");
                System.out.println(Line);
            }
            else{
                switch (command){
                    case "list":
                        printList();
                        break;
                    case "bye":
                        break;
                    default:
                        System.out.println(Line);
                        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(Line);
                }
            }
        }while(!command.equals("bye"));
        bye();
    }
}
