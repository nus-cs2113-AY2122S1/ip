import java.util.Scanner;

public class Patchi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int nextTaskIndex = 0;

        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
        System.out.println("Patchi: Hello! I'm Patchi the tasks fairy Œ(ˊᵕˋ)B\n What can I do for you today? Œ(ˊVˋ)B");
        System.out.println("─── .o * : typing... : ¤ o. ───");
        System.out.print("Me: ");
        String input = in.nextLine();

        while (input.equals("bye") == false) {
            if (input.equals("list") == true) {
                if (nextTaskIndex > 0) {
                    System.out.println("Patchi: Here is the list of tasks you currently have! Work hard~ Œ(˙O˙)B");
                    for (int i = 0; i < nextTaskIndex; i++) {
                        System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    }
                } else {
                    System.out.println("Patchi: You have no tasks for now! Go and relax~ Œ(ˊuˋ)B");
                }
            } else if (input.indexOf("done") == 0) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                if(nextTaskIndex > taskIndex) {
                    tasks[taskIndex].setDone(true);
                    System.out.println("Patchi: Good job! I've marked this task as done on your list. Time for a break? Œ(ˊwˋ)B");
                } else {
                    System.out.println("Patchi: That task doesn't seem to exist... Œ(ˊnˋ)B");
                }
            } else { //add task
                tasks[nextTaskIndex] = new Task(input);
                nextTaskIndex++;
                System.out.println("Patchi: Got it! I have added + " + input + " + to your task list Œ(ˆOˆ)B");
            }
            System.out.println("─── .o * : typing... : ¤ o. ───");
            System.out.print("Me: ");
            input = in.nextLine();
        }

        System.out.println("Patchi: Bye! Hope to see you again soon! Œ(~ˊᵕˋ~)B");
        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
    }
}