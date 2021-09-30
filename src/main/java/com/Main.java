package com;

import com.command.Command;
import com.duke.Duke;

import java.util.Scanner;

public class Main {

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
