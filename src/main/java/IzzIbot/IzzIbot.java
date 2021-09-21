package IzzIbot;

import IzzIbot.command.Command;
import IzzIbot.exceptions.IzzIbotException;

public class IzzIbot {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    /**
     * IzzIbot constructor which initialises the User Interface(UI), Storage, Parser and TaskList.
     * @param filePath file path of IzzIbot_data, which stores data of IzzIbot
     */
    public IzzIbot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);

        try {
            tasks = new TaskList(storage.readFile());
        } catch (IzzIbotException e) {
            ui.printWithLines(e.toString());
            tasks = new TaskList();
        }

        parser = new Parser(ui, storage, tasks);
    }

    /** Reads user command and executes it until the exit command is entered by the user. */
    public void run() {
        ui.printHelloMessage();

        String input;

        input = ui.getUserCommand();

        while (true) {
            try {
                Command userCommand = parser.selectCommand(input);
                userCommand.execute();
                storage.saveFile(tasks);
            } catch (IzzIbotException e) {
                ui.printWithLines(e.getMessage());
            }

            input = ui.getUserCommand();
        }
    }

    public static void main(String[] args) {
        new IzzIbot("IzzIbot_data/store.txt").run();
    }

}
