import java.util.ArrayList;

public class Tasks {
    boolean completed;
    String name;

    public Tasks(String input){
        completed = false;
        name = input;
    }

    public void complete(){
        completed = true;
    }

    public String getName(){
        return name;
    }

    public String mark(){
        if (completed) return "X";
        else return " ";
    }

    //Function to execute the list program
    static void list(String input, ArrayList<Tasks> tasksAL) {
        if (input.equalsIgnoreCase("list")) {
            for (int i = 0; i < tasksAL.size(); i++) {
                System.out.println(i + 1 + ". [" + tasksAL.get(i).mark() + "] " + tasksAL.get(i).getName());
            }

        } else if(input.startsWith("done")){
            int taskNumber;
            if (input.length() == 6) taskNumber = Character.getNumericValue(input.charAt(5));
            else if (input.length() == 7) taskNumber = 10*Character.getNumericValue(input.charAt(5)) + Character.getNumericValue(input.charAt(6));
            else taskNumber = 100*Character.getNumericValue(input.charAt(5)) + 10*Character.getNumericValue(input.charAt(6)) + Character.getNumericValue(input.charAt(7));
            tasksAL.get(taskNumber - 1).complete();
            System.out.println("[" + tasksAL.get(taskNumber - 1).mark() + "] " + tasksAL.get(taskNumber - 1).getName());

        } else {
            tasksAL.add(new Tasks(input));
            System.out.println("added: " + input);
        }
    }
}
