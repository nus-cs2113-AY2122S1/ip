import java.util.Scanner;

public class Duke {

    protected int count = 0;
    protected Task[] list = new Task[100];
    protected Scanner in;

    public static void printAdded(Task task, int count){
        System.out.println("Got it. I've added this task:");
        task.printTask();
        System.out.println("Now you have " + count + " tasks in the list.");
    }
    public int executeCommand(String input){
        String command;
        String description = null;
        String time = null;
        String temp;
        if(input.contains(" ")){
            command = input.substring(0, input.indexOf(" "));
            temp = input.substring(input.indexOf(" ")+1);
            if(temp.contains("/")) {
                description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                time = input.substring(input.indexOf("/")+4);
            }else{
                description = temp;
            }
        }else{
            command = input;
        }
        switch(command){
        case "list":
            for(Task task: list){
                task.printTask();
                if (task.index == count) {
                    break;
                }
            }
                break;
        case "done":
            char num = input.charAt(5);
            int index = Character.getNumericValue(num);
            for (Task task : list) {
                if (task.index == index) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.description);
                    break;
                } else if (task.index == count) {
                    break;
                }
            }
            break;
        case "todo":
            count++;
            Todo todo = new Todo(description, count);
            printAdded(todo,count);
            list[count-1] = todo;
            break;
        case "deadline":
            count++;
            Deadline deadline = new Deadline(description, count, time);
            printAdded(deadline,count);
            list[count-1] = deadline;
            break;
        case "event":
            count++;
            Event event = new Event(description, count, time);
            printAdded(event,count);
            list[count-1] = event;
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return -1;
        }
        return 0;
    }

    public void run(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        in = new Scanner(System.in);
        String input = in.nextLine();
        while(true){
            if (executeCommand(input) == -1) {
                break;
            }
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
