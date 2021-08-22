import java.util.Scanner;

public class Patchi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int nextTaskIndex = 0;

        System.out.println("─── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───");
        System.out.println("Patchi: ⋰˚☆ Hello! I'm Patchi the tasks fairy ⁽⁽ଘ(ˊᵕˋ)ଓ⁾⁾\n⋰˚☆ What can I do for you today? ଘ(ˊᗜˋ)ଓ");
        System.out.println("─── ･ ｡ﾟ☆: typing... :☆ﾟ. ───");
        System.out.print("Me: ");
        String input = in.nextLine();

        while (input.equals("bye") == false) {
            if (input.equals("list") == true) {
                if (nextTaskIndex > 0) {
                    System.out.println("Patchi: Here is the list of tasks you currently have! Work hard~ ଘ(˙༥˙)ଓ");
                    for (int i = 0; i < nextTaskIndex; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                } else {
                    System.out.println("Patchi: You have no tasks for now! Go and relax~ ଘ(ˊ༚ˋ)ଓ");
                }
            } else { //add task
                tasks[nextTaskIndex] = input;
                nextTaskIndex++;
                System.out.println("Patchi: Got it! I have added +" + input + "+ to your task list ଘ(ˆ꒳ˆ)ଓ");
            }
            System.out.println("─── ･ ｡ﾟ☆: typing... :☆ﾟ. ───");
            System.out.print("Me: ");
            input = in.nextLine();
        }

        System.out.println("Patchi: Bye! Hope to see you again soon! ପ(⑅ˊᵕˋ⑅)ଓ");
        System.out.println("─── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───");
    }
}