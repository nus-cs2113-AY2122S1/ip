package userManagement;

import FileManager.FileSaver;
import Parser.Parser;
import UI.TextUI;
import exceptions.*;
import tasks.TaskList;

import commands.QuitCommand;
import commands.UserCommand;

import java.time.format.DateTimeParseException;

/**
 * A class to serve user's requests
 */
public class UserServer {
    private String userName;
    private TaskList userTasks;
    private UserManager userManager;
    private TextUI UI = new TextUI();
    private Parser parser = new Parser();

    public UserServer(UserManager userManager) {
        this.userManager = userManager;
    }

    public void run() {
        loadUser();
        serve();
        save(this.userName);
    }


    private void loadUser() {
        this.userName = UI.getUserName();
        if (userManager.userExisted(this.userName)) {
            this.userTasks = userManager.loadUser(userName);
        } else {
            this.userTasks = new TaskList();
        }
    }



    private void serve() {
        UI.showWelcomeMessage(userName);
        UserCommand command = null;

        do {
            String userInput = UI.readInput();
            try {
                command = parser.parseCommand(userInput, this.userTasks);
                UI.printCommandResult(commandExecute(command));
            } catch (DukeException e) {
                UI.showError(e);
            }
        } while (! (command instanceof QuitCommand));
    }

    private void save(String userName) {
        FileSaver saver = new FileSaver(userName);
        saver.save(userTasks);
    }


    private String commandExecute(UserCommand input) throws DukeException {
        return input.execute();
    }

}
