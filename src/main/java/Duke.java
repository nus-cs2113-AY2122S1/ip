import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String curCommand = "";
        String line = "________________________________________";
        ui.printWelcome();
        while (!curCommand.equals("bye")) {
            curCommand = input.nextLine();
            System.out.println(line);
            Parser.handleCommand(curCommand);
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        new Duke(dir + "\\data\\duke.txt").run();
    }
}
