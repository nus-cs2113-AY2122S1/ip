import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "_________________________________________________\n";

        System.out.println(logo + "Hello! I'm Duke\n" + "Whatchu want\n" + logo);

        Scanner in = new Scanner(System.in);

        String[] List = new String[100];
        String word;
        int Count = 0;

        do {
            word = in.nextLine();
            if (!isBye(word) && !isList(word)) {
                List[Count] = word;
                System.out.println(logo + "added: " + word + "\n" + logo);
                Count++;
            }

            if (isList(word)) {
                System.out.print(logo);
                for (int i = 0; i < Count; i++) {
                    System.out.println((i + 1) + ". " + List[i]);
                }
                System.out.print(logo);
            }


        } while (!isBye(word));

        System.out.println(logo + "Bye. Hope to see you again soon!\n" + logo);

    }

    public static boolean isBye(String word) {
        return word.equals("Bye") || word.equals("bye");
    }

    public static boolean isList(String word) {
        return word.equals("List") || word.equals("list");
    }


}
