package karen;

import karen.program.ProgramManager;

public class Karen {
    public static void main(String[] args) {
//        TaskList taskList = new TaskList();
//        Parser parser = new Parser(taskList);
//        FileManager.bootUpData(parser, taskList);
//        Ui.printWelcomeMessage(parser.getIsFirstRun());
        ProgramManager programManager = new ProgramManager();
        programManager.startProgram();
//        do {
//            String rawUserInput = Ui.getUserInput();
//            parser.processInput(rawUserInput);
//        } while (parser.getIsRunning());
    }
}

