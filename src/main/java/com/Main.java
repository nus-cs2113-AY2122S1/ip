package com;

import com.command.Command;
import com.duke.Duke;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * The Main logic and function flow of the project
 */
public class Main {

    /**
     * The main function that the project execute
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Duke duke = new Duke("Task.txt");
        Command command = new Command(duke);

        String line;

        do{
            line = in.nextLine();
        }while(command.handle(line));
    }
}
