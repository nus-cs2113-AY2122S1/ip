package duke;

import java.util.Scanner;

import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.io.IOException;

public class Duke {

    private static ArrayList<Task> tasks;

    private static Ui userInterface;

    private static Storage storage;

    private static Parser parser;



    //program flow when running chatbot
    public static void run() throws IOException{
        initialiseDuke();

        runDuke();

        byeDuke();
    }

    //initialise chatbot by setting up ArrayList, userInterface, storage and parser
    public static void initialiseDuke() throws IOException {
        tasks = new ArrayList<>();

        userInterface = new Ui();

        storage = new Storage();

        parser = new Parser();

        try {

            storage.saveFileInitialise(tasks);

        } catch (FileNotFoundException e) {

            storage.createSaveFile();

        }
    }

    //run the programme
    public static void runDuke() {

        Scanner in = new Scanner(System.in);

        String line;

        userInterface.printWelcomeMessage();

        line = in.nextLine();

        parser.parseInputs(in, line, tasks);

    }

    //print out bye message to the shell
    public static void byeDuke() {
        try {

            storage.writeToSave(tasks);

        } catch (IOException e) {

            System.out.println("Oops! Something went wrong! " + e.getMessage());

        }

        userInterface.printBye();
    }

    //main function of Duke class
    public static void main(String[] args) throws IOException {

        new Duke().run();

    }
}
