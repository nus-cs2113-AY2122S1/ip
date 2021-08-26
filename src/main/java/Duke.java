import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________\n";
        Scanner in = new Scanner(System.in);
        System.out.println(line + logo +
                " Hello! I'm Duke\n" +
                " What's up? :p\n" + line);

        Task [] inputs = new Task[100];
        int inputCount = 0;


        while(true) {
            input = in.nextLine();
            System.out.println(line);
            if(input.equals("bye")) {
                break;
            }
            else if(input.equals("list")) {
                for(int i = 0; i < inputCount; i++){
                    System.out.println((i+1) + ". [" + inputs[i].getStatusIcon() + "] " +
                            inputs[i].getDescription());
                }
            }
            //assumes user will follow format "done x"
            else if(input.startsWith("done")) {
                String[] words = input.split(" ");
                int taskCompleted = Integer.parseInt(words[1]) - 1;
                if (inputs[taskCompleted].isDone() == true) {
                    System.out.println("You have already marked this task as done! Time to move on :)");
                }
                else {
                    inputs[taskCompleted].markAsDone();
                    System.out.println("Awesome! You've completed the following task:");
                    System.out.println(" [X] " + inputs[taskCompleted].getDescription());
                }

            }
            else {
                System.out.println("added:" + input);
                Task newTask = new Task(input);
                inputs[inputCount] = newTask;
                inputCount += 1;
            }
            System.out.println(line);
        }

        System.out.println(line + "Bye, see you!\n" + line);
    }
}
