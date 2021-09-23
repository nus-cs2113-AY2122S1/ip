public class Duke {
    private static DukeStorage dukeStorage;
    private static TaskList taskList;
    private static Ui ui;
    private static Parser parser;

    public Duke() {
        ui = new Ui();
        dukeStorage = new DukeStorage();
        taskList = new TaskList(dukeStorage.loadTaskList());
        parser = new Parser();

    }

    public void run() {
        ui.welcomeMessage();
        boolean userExit = false;
        while (!userExit) {
            try {
                String userInput = ui.readUserInput();
                Command userCommand = parser.parseUserInput(userInput);
                userCommand.execute(taskList, ui, dukeStorage);
                userExit = userCommand.exit();
            } catch (InvalidTaskTypeException e) {
                e.printExceptionMessage();
            } catch (EmptyDescriptionException e) {
                e.printExceptionMessage();
            } finally {
                ui.printNextLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
