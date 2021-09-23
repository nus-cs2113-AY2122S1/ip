package duke.utility;

import duke.functions.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void printIntro() {
        System.out.println(
                "                              _     _\n" +
                        "                             ( \\---/ )\n" +
                        "                              ) . . (\n" +
                        "________________________,--._(___Y___)_,--._______________________ \n" +
                        "                        `--'           `--'");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void drawLine() {
        System.out.println("____________________________________________________________");
    }

    public void printBye() {
        drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printTaskList(ArrayList<Task> items) {
        drawLine();
        if (!items.isEmpty()) {
            System.out.println("\tHere is your task list:");
            for (int i = 0; i < items.size(); i++) {
                System.out.print("\t\t" + (i + 1) + ". ");
                System.out.println(items.get(i));
            }
        } else {
            System.out.println("\tYou have no tasks");
        }
        drawLine();
    }

    public void printTaskAdded(ArrayList<Task> items) {
        int indexOfLastItem = items.size() - 1;
        System.out.println("\tTask Added:");
        System.out.println("\t\t" + items.get(indexOfLastItem));
        System.out.println("\tYou now have " + items.size() + " tasks");
    }

    public void invalidCommandMessage() {
        System.out.println("\tI don't know what that means");
    }

    public void taskNumNotInt() {
        System.out.println("\tInvalid argument, please enter a valid integer task number!");
    }

    public void taskNumberOutOfBounds() {
        System.out.println("\tNo such task!");
    }

    public void noTaskNumberProvided(Boolean isDoneVersion) {
        if (isDoneVersion) {
            System.out.println("\tWhich task is done?");
        } else {
            System.out.println("\tWhich task to delete?");
        }
    }

    public void emptyDescription(String item) {
        System.out.println("\tDescription for " + item + " cannot be empty!");
    }

    public void wrongDeadlineOrEventFormat(String item) {
        String specifier = item.equals("deadline") ? "/by" : "/at";
        System.out.println("\tWrong format! Try \"" + item + " [description] " + specifier + " [due date]\"");
    }

    public void printMarkAsDone(ArrayList<Task> items, int i) {
        System.out.println("\tNice! I have marked this task as done:");
        System.out.println("\t\t" + items.get(i));
    }

    public void printRemovedItem(Task removedItem) {
        System.out.println("\tItem deleted:");
        System.out.println("\t\t" + removedItem);
    }

    public void readSavedListError() {
        System.out.println("Error creating/reading task list");
    }

    public void writeToFileError() {
        System.out.println("\tError writing to file");
    }

    public void displayFoundItems(TaskList foundItems) {
        drawLine();
        if (!foundItems.items.isEmpty()){
            System.out.println("\tHere are the matching tasks in your list:");
            for (Task item : foundItems.items){
                System.out.println("\t\t" + item);
            }
        } else {
            System.out.println("\tNo matching tasks found");
        }
        drawLine();
    }
}
