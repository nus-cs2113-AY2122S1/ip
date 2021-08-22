import java.util.Scanner;

public class Patchi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
        System.out.println("Patchi: Hello! I'm Patchi the tasks fairy Œ(ˊᵕˋ)B\n What can I do for you today? Œ(ˊVˋ)B");
        System.out.println("─── .o * : typing... : ¤ o. ───");
        System.out.print("Me: ");
        String input = in.nextLine();

        while (input.equals("bye") == false) {
            System.out.println("Patchi: " + input + " Œ(ˊVˋ)B");
            System.out.println("─── .o * : typing... : ¤ o. ───");
            System.out.print("Me: ");
            input = in.nextLine();
        }

        System.out.println("Patchi: Bye! Hope to see you again soon! Œ(~ˊᵕˋ~)B");
        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
    }
}