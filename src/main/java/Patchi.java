import java.util.Scanner;

public class Patchi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("─── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───");
        System.out.println("Patchi: ⋰˚☆ Hello! I'm Patchi ⁽⁽ଘ(ˊᵕˋ)ଓ⁾⁾\n⋰˚☆ What can I do for you? ଘ(ˊᗜˋ)ଓ");
        System.out.println("─── ･ ｡ﾟ☆: typing... :☆ﾟ. ───");
        System.out.print("Me: ");
        String input = in.nextLine();

        while(input.equals("bye") == false) {
            System.out.println("Patchi: " + input + " ଘ(ˆ꒳ˆ)ଓ");
            System.out.println("─── ･ ｡ﾟ☆: typing... :☆ﾟ. ───");
            System.out.print("Me: ");
            input = in.nextLine();
        }

        System.out.println("Patchi: Bye! Hope to see you again soon! ପ(⑅ˊᵕˋ⑅)ଓ");
        System.out.println("─── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───");
    }
}