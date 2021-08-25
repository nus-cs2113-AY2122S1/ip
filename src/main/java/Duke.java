import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        Task[] list = new Task[100];
        int count = 0;
        Scanner in = new Scanner(System.in);
        Task task = new Task(in.nextLine());
        task.index = count + 1;
        String input = "null";
        if(task.description.length() > 4) {
            input = task.description.substring(0, 4);
        }
        while(!(task.description.equals("bye"))){
            if(task.description.equals("list")){
                for(Task value : list){
                    System.out.println(value.index + ". " + "[" + value.getStatusIcon() + "] " + value.description);
                    if(value.index == count){
                        break;
                    }
                }
            }else if(input.equals("done")){
                char num = task.description.charAt(5);
                int numm = Character.getNumericValue(num);
                for(Task value : list){
                    if(value.index==numm){
                        value.markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("[" + value.getStatusIcon() + "] " + value.description);
                        break;
                    }else if(value.index == count){
                        break;
                    }
                }
            }else {
                System.out.println("added: " + task.description);
                list[count] = task;
                count++;
            }
            task = new Task(in.nextLine());
            task.index = count + 1;
            if(task.description.length() > 4) {
                input = task.description.substring(0, 4);
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
