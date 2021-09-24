package main;

import commands.*;
import exceptions.JimException;
import parser.Parser;
import ui.Ui;
import storage.Storage;
import tasklist.TaskList;


import java.util.Scanner;

public class Jim {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Jim() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(tasks);
        storage.initJim();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        try {
            while (!isExit) {
                String userCommand = ui.readCommand();
                Command command = Parser.parseCommand(userCommand);
                command.execute(tasks);
                isExit = command.getIsExit();
            }
        } catch (JimException e) {
            ui.showInvalidCommandMessage();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new Jim().run();
    }
}