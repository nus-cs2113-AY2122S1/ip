import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static String divider = "\t__________________________________________________________";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Greeting.openingGreet();
        talkToUser();
        Greeting.closingGreet();
    }
    private static void talkToUser() {
        Scanner in=new Scanner(System.in);
        String userInput;
        do {
            System.out.println();
            userInput = in.nextLine();
            System.out.println(divider);
            if (userInput.contains("done")){
                int taskNumber=Integer.parseInt(userInput.substring(5))-1;
                markDone(taskNumber);
                continue;
            }
            else if(userInput.equals("list")){
                printTasks();
                continue;
            }
            else if (userInput.equals("bye")){
                break;
            }
            addTask(userInput);
            System.out.println("\tadded: " + userInput);
            System.out.println(divider);
        } while(true);
    }
    private static void addTask(String userInput){
        Task newTask= new Task(userInput);
        tasks.add(newTask);
    }
    private static void printTasks(){
        for (int i=0; i<tasks.size();i++){
            boolean isDone = tasks.get(i).getIsDone();
            System.out.print(i+1);
            if(isDone==true){
                System.out.print(".[X] ");
            }
            else{
                System.out.print(".[ ] ");
            }
            System.out.println(tasks.get(i).getName());
        }
        System.out.println(divider);
    }
    private static void markDone(int taskNumber){
        if (taskNumber>=tasks.size()){
            System.out.println("No such task");
            System.out.println(divider);
            return;
        }
        tasks.get(taskNumber).setIsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[X] "+ tasks.get(taskNumber).getName());
        System.out.println(divider);
    }
}
