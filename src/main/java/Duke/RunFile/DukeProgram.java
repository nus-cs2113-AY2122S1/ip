package Duke.RunFile;

import Duke.BackEnd.DukeBackEnd;
import Duke.Commands.*;
import Duke.SaveFile.DataSaver;
import Duke.TaskTypes.Task;
import Duke.UI.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;



import static Duke.UI.DukeConstants.*;

public class DukeProgram {

    public static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        UserInterface.printDukeGreet();
        DataSaver.manageLoad(taskList);

        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();

        while (!inWord.equalsIgnoreCase(EXIT_STRING)) {
            DukeBackEnd.executeUserInstruction(inWord);
            inWord = scan.nextLine();
        }

        ExitCommand.dukeExit();
    }
}