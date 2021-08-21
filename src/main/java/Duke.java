import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);

        //calls the Functions class
        Functions function = new Functions();

        while(true){
            command = in.nextLine();

            switch(command){
            //lists all tasks added
            case "list":
                function.listTasks();
                break;
            //exits the program
            case "bye":
                function.exitDuke();
                break;
            //add tasks
            default:
                function.taskAdder(command);
                break;
            }
        }
    }
}
