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
            System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-");
            switch(userInput){
            case "bye": Init.bye();
                        flag = false;
                        break;
            case "list": tasksList.listTasks();
                        break;
            default: tasksList.addTask(userInput);
                    break;
            }
            System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-\n");
        }
    }
}
