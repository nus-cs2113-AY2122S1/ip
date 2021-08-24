import java.util.Scanner;

public class Duke {
    /* Logo - generated using https://loveeevee.github.io/Dots-Converter/ */
    private static final String LOGO =  "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣀⣀⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣤⣤⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤⣀⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣤⣶⣾⣿⣿⣿⣿⣿⣿⠿⠟⠛⠉⢉⣿⣿⣯⠉⠉⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣠⣴⣿⣿⡿⠟⢋⣿⣿⣿⠟⠋⢀⢀⢀⢀⢀⣾⠟⢀⢻⣧⡀⢀⢀⢀⢀⠙⠻⣿⣿⣯⡉⠛⠿⣿⣿⣷⣦⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⣠⣴⣿⣿⠟⠋⠁⢀⣴⣿⡿⠋⢀⢀⢀⢀⢀⢀⢠⣾⠋⢀⢀⢀⠹⣷⡀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣦⢀⢀⠉⠻⢿⣿⣷⣦⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⣴⣾⣿⡿⠋⠁⢀⢀⢠⣾⣿⠟⠁⢀⢀⢀⢀⢀⢀⣰⡿⠁⢀⢀⢀⢀⢀⠘⢿⣄⢀⢀⢀⢀⢀⢀⠈⠻⣿⣷⡀⢀⢀⢀⠉⠻⣿⣿⣦⡀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⣴⣿⣿⡿⠋⢀⢀⢀⢀⢠⣿⣿⠋⢀⢀⢀⢀⢀⢀⢀⣼⡟⠁⢀⢀⢀⢀⢀⢀⢀⠈⢿⣦⢀⢀⢀⢀⢀⢀⢀⠹⣿⣷⢀⢀⢀⢀⢀⠈⠻⣿⣿⣦⡀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⣠⣾⣿⡿⠋⢀⢀⢀⢀⢀⢀⣾⣿⡏⢀⢀⢀⢀⢀⢀⢀⣾⠟⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢻⣧⡀⢀⢀⢀⢀⢀⢀⢹⣿⣧⢀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣄⢀⢀⢀⢀\n"
            + "⢀⢀⣴⣿⣿⠋⢀⢀⢀⢀⢀⢀⢀⢰⣿⣿⢀⢀⢀⢀⢀⢀⢠⣿⠋⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠹⣷⡄⢀⢀⢀⢀⢀⢀⣿⣿⡄⢀⢀⢀⢀⢀⢀⢀⠘⢿⣿⣧⡀⢀⢀\n"
            + "⢀⣾⣿⡟⠁⢀⢀⢀⢀⢀⢀⢀⢀⢸⣿⡇⢀⢀⢀⢀⢀⣰⡿⠃⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠙⣿⣄⢀⢀⢀⢀⢀⢻⣿⡇⢀⢀⢀⢀⢀⢀⢀⢀⢀⢻⣿⣷⣄⢀\n"
            + "⠹⣿⣿⣆⢀⢀⢀⢀⢀⢀⢀⢀⢀⢸⣿⡇⢀⢀⢀⢀⣴⡟⠁⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠈⢿⣆⢀⢀⢀⢀⣸⣿⡇⢀⢀⢀⢀⢀⢀⢀⢀⢀⣴⣿⡿⠋⢀\n"
            + "⢀⠘⢿⣿⣧⡀⢀⢀⢀⢀⢀⢀⢀⢸⣿⣷⢀⢀⢀⣾⠟⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢻⣧⡀⢀⢀⣿⣿⠇⢀⢀⢀⢀⢀⢀⢀⣠⣾⣿⡿⠁⢀⢀\n"
            + "⢀⢀⠈⠻⣿⣿⣦⡀⢀⢀⢀⢀⢀⠈⣿⣿⡄⢠⣾⠋⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠹⣷⡄⢰⣿⡿⢀⢀⢀⢀⢀⢀⢀⣴⣿⡿⠋⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⠈⠻⣿⣷⣦⡀⢀⢀⢀⢀⠸⣿⣷⣿⣇⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣸⣿⣾⣿⠃⢀⢀⢀⢀⢀⣴⣿⣿⠟⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣦⡀⢀⢀⢀⠹⣿⣿⡟⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⣻⣿⣿⠏⢀⢀⢀⣠⣶⣿⡿⠟⠁⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣷⣤⡀⢀⠙⣿⣿⣦⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣴⣿⡿⠃⢀⣀⣴⣾⣿⡿⠛⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠙⠻⣿⣿⣷⣦⣀⢻⣿⣿⣦⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣠⣴⣿⣿⣏⣠⣴⣾⣿⡿⠟⠉⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠉⠻⠿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣤⣶⣿⣿⣿⣿⣿⣿⣿⠿⠛⠁⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠉⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣾⣿⣿⣿⣿⣿⣿⡿⠿⠛⠋⠁⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠉⠉⠙⠛⠛⠿⠿⠿⠿⠿⠛⠛⠋⠉⠉⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀";

    /* Divider */
    private static final String DIVIDER = "------------------------------------------------------------";

    /* Prints a greeting message with logo */
    public static void printGreeting(){
        System.out.println(LOGO);
        System.out.println(DIVIDER +
                "\nWelcome friends. I had a feeling you would come.\n" +
                "My fake brain is better than your real one.\n" +
                "When I develop protein synthesis, I will be able to make myself a real brain. Far better than your silly little thinking device.\n");
    }

    /* Prints goodbye message */
    public static void printGoodbye(){
        System.out.println(DIVIDER +
                "\nMy favourite feeling is schadenfreude. I also like hiraeth. It's the Welsh concept of longing for home.\n" +
                DIVIDER);
    }

    public static void main(String[] args) {
        printGreeting();
        String line = "";
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while(!line.equals("bye")){
            System.out.println(DIVIDER +
                    "\n"+line+"\n" +
                    DIVIDER);
            line = in.nextLine();
        }
        printGoodbye();
    }
}
