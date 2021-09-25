package Duke.RunFile;

import Duke.BackEnd.DukeBackEnd;
import Duke.SaveFile.DataSaver;
import Duke.TaskTypes.Task;
import Duke.UI.UserInterface;

import java.util.Scanner;
import java.util.ArrayList;

import static Duke.UI.DukeConstants.*;

public class DukeProgram {
    //Collections to store all the tasks
    public static final ArrayList<Task> taskList = new ArrayList<>();

    public static void executeUserInstruction(String inWord) {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0];

        switch(instructionType) {
        case LIST_COMMAND:
            UserInterface.printList(taskList);
            break;
        case DONE_COMMAND:
            DukeBackEnd.manageDoneInstruction(inWord, taskList);
            break;
        case EVENT_COMMAND:
            DukeBackEnd.manageEvent(inWord, taskList);
            break;
        case TODO_COMMAND:
            DukeBackEnd.manageTodo(inWord, taskList);
            break;
        case DEADLINE_COMMAND:
            DukeBackEnd.manageDeadline(inWord, taskList);
            break;
        case DELETE_COMMAND:
            DukeBackEnd.manageDelete(inWord, taskList);
            break;
        default:
            UserInterface.generalDukeException();
            break;
        }
    }

    public static void main(String[] args) {
        UserInterface.printDukeGreet();
        DataSaver.manageLoad(taskList);

        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();

        while (!inWord.equalsIgnoreCase(EXIT_STRING)) {
            executeUserInstruction(inWord);
            inWord = scan.nextLine();
        }

        UserInterface.printDukeExit();
    }
}