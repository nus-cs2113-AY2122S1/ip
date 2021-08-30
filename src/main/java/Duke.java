import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        showWelcomeMessage();

        TaskManager tasksList = new TaskManager();
        while(true){
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            // if user inputs nothing
            if(userInput.isEmpty()) {
                userInput = in.nextLine();
            }

            lineSeparator();
            if(userInput.equals("bye")){
                Init.bye();
                break;
            }
            else if(userInput.equals("list")){
                tasksList.listTasks();
            }
            else if(userInput.startsWith("done")){
                tasksList.tasksDone(userInput);
            }
            //it is a task then
            else{
                Task task = convertInputToTask(userInput);
                // if user inputs a task that is added before
                if(tasksList.searchTask(task)){
                    System.out.println("Sorry, you have already added this task, please change one!");
                    continue;
                }
                tasksList.addTask(task);
            }
            lineSeparator();
        }
    }




    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Init.greet();
        //echo & exit
    }

    private static void lineSeparator() {
        System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-");
    }

    public static Task convertInputToTask(String userInput){
        if(userInput.startsWith("deadline")){
            return new DeadLine(userInput);
        }
        if(userInput.startsWith("event")){
            return new Event(userInput);
        }
        if(userInput.startsWith("todo")){
            return new ToDo(userInput);
        }
        else
            return new Task(userInput);
    }
}
