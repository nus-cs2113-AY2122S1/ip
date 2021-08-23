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

    public String getName() {
        return name;
    }

    public String mark(){
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    //Function to execute the list program
    static void list(String input, ArrayList<Tasks> tasksAL) {
        if (input.equalsIgnoreCase("list")) {
            if (tasksAL.size() == 0){
                System.out.println("There is nothing in your list! Try typing something else.");
            }
            for (int i = 0; i < tasksAL.size(); i++) {
                System.out.println(i + 1 + ". [" + tasksAL.get(i).mark() + "] " + tasksAL.get(i).getName());
            }

        } else if(input.startsWith("done") && input.split(" ").length == 2) {
            try {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasksAL.get(taskNumber - 1).makeComplete();
                System.out.println("Great job! You have completed the following task:");
                System.out.println("[X] " + tasksAL.get(taskNumber - 1).getName());
            } catch(IndexOutOfBoundsException e){
                System.out.println("Number does not exist in list, please try again.");
            } catch(NumberFormatException e){
                tasksAL.add(new Tasks(input));
                System.out.println("added: " + input);
            }
        } else {
            tasksAL.add(new Tasks(input));
            System.out.println("added: " + input);
        }
    }
}
