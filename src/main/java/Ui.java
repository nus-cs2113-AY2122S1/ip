import java.util.Scanner;

public class Ui {
    private static final String SEPARATING_LINE = "\t-------------------------------------";

    Scanner scanner = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(SEPARATING_LINE);
        System.out.println("\t" + msg);
        System.out.println(SEPARATING_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void greet() {
        print("Yo what's up I'm Jesse. Need help?");
    }

    public void bye() {
        print("So long!");
    }

    public Ui() {

    }

}
