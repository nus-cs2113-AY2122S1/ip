import java.util.Scanner;

public class Patchi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int nextTaskIndex = 0;

        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
        System.out.println("Patchi: Hello! I'm Patchi the tasks fairy Œ(ˊᵕˋ)B\n " +
                "What can I do for you today? Œ(ˊVˋ)B");

        System.out.println("─── .o * : typing... : ¤ o. ───");
        System.out.print("Me: ");
        String input = in.nextLine();

        while (input.equals("bye") == false) {
            if (input.equals("list") == true) {
                if (nextTaskIndex > 0) {
                    System.out.println("Patchi: Here is the list of tasks you currently have! Work hard~ Œ(˙O˙)B");
                    for (int i = 0; i < nextTaskIndex; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toString());
                    }
                } else {
                    System.out.println("Patchi: You have no tasks for now! Go and relax~ Œ(ˊuˋ)B");
                }
            } else if (input.startsWith("done")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                if (nextTaskIndex > taskIndex) {
                    tasks[taskIndex].setDone(true);
                    System.out.println("Patchi: Good job! I've marked this task as done on your list. " +
                            "Time for a break? Œ(ˊwˋ)B");
                } else {
                    System.out.println("Patchi: That task doesn't seem to exist... Œ(ˊnˋ)B");
                }
            } else { //add task OR invalid command
                boolean isValidCommand = true;
                if (input.startsWith("todo")) {
                    String description = input.substring(5);
                    tasks[nextTaskIndex] = new Todo(description);
                } else if (input.startsWith("deadline")) {
                    int indexOfBy = input.indexOf("/by");
                    if (indexOfBy == -1) {
                        isValidCommand = false;
                    } else {
                        String description = input.substring(9, indexOfBy - 1);
                        String by = input.substring(indexOfBy + 4);
                        tasks[nextTaskIndex] = new Deadline(description, by);
                    }
                } else if (input.startsWith("event")) {
                    int indexOfAt = input.indexOf("/at");
                    if (indexOfAt == -1) {
                        isValidCommand = false;
                    } else {
                        String description = input.substring(6, indexOfAt - 1);
                        String at = input.substring(indexOfAt + 4);
                        tasks[nextTaskIndex] = new Event(description, at);
                    }
                } else {
                    isValidCommand = false;
                }

                if (isValidCommand == true) {
                    System.out.println("Patchi: Got it! I have added " + tasks[nextTaskIndex].toString() +
                            " to your task list Œ(ˆOˆ)B");
                    nextTaskIndex++;
                    System.out.println("Patchi: You have " + nextTaskIndex + " tasks now" +
                            "... Too much work... Œ(ˊnˋ)B");
                } else {
                    System.out.println("Patchi: I'm sorry, I don't understand what that means... Œ(ˊnˋ)B");
                }
            }

            System.out.println("─── .o * : typing... : ¤ o. ───");
            System.out.print("Me: ");
            input = in.nextLine();
        }

        System.out.println("Patchi: Bye! Hope to see you again soon! Œ(~ˊᵕˋ~)B");
        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
    }

    public static void printTransition() {
        System.out.println("─── .o * : typing... : ¤ o. ───");
        System.out.print("Me: ");
    }
}