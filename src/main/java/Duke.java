import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Init.greet();
        //echo & exit
        boolean flag = true;
        TaskManager tasksList = new TaskManager();
        while(flag){
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            // if user inputs nothing
            if(userInput.isEmpty()){
                System.out.println("Sorry, you need to input something! Please try again");
                continue;
            }
            // if user inputs a task that is added before
            if(tasksList.searchTask(userInput)){
                System.out.println("Sorry, you have already added this task, please change one!");
                continue;
            }

            System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-");
            if(userInput.equals("bye")){
                Init.bye();
                flag = false;
            }
            else if(userInput.equals("list")){
                tasksList.listTasks();
            }
            else if(userInput.startsWith("done")){
                tasksList.tasksDone(userInput);
            }
            else{
                tasksList.addTask(userInput);
            }
            System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-\n");
        }
    }
}
