import java.util.ArrayList;

public class Tasks {
    boolean isCompleted;
    String name;

    public Tasks(String input) {
        isCompleted = false;
        name = input;
    }

    public void makeComplete() {
        isCompleted = true;
    }

    public String mark(){
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    public String getName() {
        return name;
    }

    //Function to execute the list program
    static void list(String input, ArrayList<Tasks> tasksAL) {
        if (input.equalsIgnoreCase("list")) {
            if (tasksAL.size() == 0){
                System.out.println("There is nothing in your list! Try adding something into your list.");
            }
            for (int taskNumber = 0; taskNumber < tasksAL.size(); taskNumber++) {
                System.out.println(taskNumber + 1 + ". " + tasksAL.get(taskNumber).getName());
            }

        } else if(input.toLowerCase().startsWith("done") && input.split(" ").length == 2) {
            try {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (tasksAL.get(taskNumber - 1).mark() == "X"){
                    System.out.println("You have already completed that task!");
                } else {
                    tasksAL.get(taskNumber - 1).makeComplete();
                    System.out.println("Great job! You have completed the following task:");
                    System.out.println(tasksAL.get(taskNumber - 1).getName());
                    System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");
                }

            } catch(IndexOutOfBoundsException e){
                System.out.println("Number does not exist in list, please try again.");

            } catch(NumberFormatException e){
                System.out.println("Please add 'todo', 'deadline' or 'event' in the front of your task!");
            }

        } else if (input.toLowerCase().startsWith("todo")) {
            tasksAL.add(new TodoTasks(input.substring(5)));
            System.out.println("added: " + tasksAL.get(tasksAL.size() - 1).getName());
            System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");

        } else if (input.toLowerCase().startsWith("deadline")) {
            try {
                tasksAL.add(new DeadlineTasks(input.substring(9, input.indexOf("/")), input.substring(input.indexOf("/") + 1)));
                System.out.println("added: " + tasksAL.get(tasksAL.size() - 1).getName());
                System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please use / followed by a deadline");
            }

        } else if (input.toLowerCase().startsWith("event")) {
            try {
                tasksAL.add(new EventTasks(input.substring(6, input.indexOf("/")), input.substring(input.indexOf("/") + 1)));
                System.out.println("added: " + tasksAL.get(tasksAL.size() - 1).getName());
                System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please use / followed by a date or time");
            }

        } else {
            System.out.println("Please add 'todo', 'deadline' or 'event' in the front of your task!");
        }
    }
}
