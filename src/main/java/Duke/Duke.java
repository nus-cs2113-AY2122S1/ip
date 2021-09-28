package Duke;

public class Duke {

    public static void main(String[] args) {

        Storage.loadData();
        Ui ui = new Ui();
        ui.printIntro();
        Parser.parseCommand();

    }
}
