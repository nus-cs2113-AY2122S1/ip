package karlett.ui;

import karlett.Duke;
import karlett.commands.Command;
import karlett.commands.ExitCommand;
import karlett.storage.StorageFile;
import karlett.task.Task;
import karlett.tasklist.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TextUi {

    Scanner s;

    public TextUi() {
        s = new Scanner(System.in);
    }

    public void greet() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)\nLoading data from file now");
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print('.');
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("Please wait... ⓛⰙⓛฅ");
        }
    }

    public String readCommand() {
        System.out.println();
        System.out.println("What can Karlett do for you meow? (ﾐⓛᆽⓛﾐ)✧");
        return s.nextLine();
    }

    public void printFileNotFoundMessage(String filePath) throws IOException {
        System.out.print("Uh-oh...Karlett can't find the file in " + filePath + "(=ಠᆽಠ=)\n");
        printFileNotFoundInstructions();
        Scanner in = new Scanner(System.in);
        String r = in.next();
        try {
            int response = Integer.parseInt(r);
            switch (response) {
            case 1:
                StorageFile storageFile = new StorageFile(filePath);
                storageFile.getFile().createNewFile();
                printNewFileCreatedMessage(filePath);
                break;
            /*case 2:
                System.out.print("Please tell me the new file path meow: ");
                String newFilePath = in.next();
                StorageFile.loadData();
                break;*/
            case 2:
                Command c = new ExitCommand();
                c.execute(null,null,null);
                System.exit(0);
                // Fallthrough
            default:
                printFileNotFoundMessage(filePath);
            }
        } catch (NumberFormatException e) {
            printFileNotFoundMessage(filePath);
        }
    }

    public static void printNewFileCreatedMessage(String filePath) {
        System.out.print("Meow~ Karlett has created the new file here:\n" +
                new File(filePath).getAbsolutePath() +
                "\nKarlett can start tracking your tasks now ಇ/ᐠ ̥ᵔ ̮ᵔ ̥ᐟ\\ಇ\n");
    }

    public static void printFileNotFoundInstructions() {
        System.out.println("What can Karlett do for you meow? (Please key in 1/2)\n" +
                "  1 Create a new text file called \"karlett.txt\" for me please.\n" +
                //"  2 Load data from another file path for me please.\n" +
                "  2 Exit the program now.\n");
    }

    public void printFinishLoadingDataMessage(TaskList tasks) {
        System.out.println("Karlett has finished loading data from the file ✧/ᐠ-ꞈ-ᐟ\\");
        if (tasks.getNumberOfTasks() == 0) {
            System.out.println("There was no task recorded yet.");
        } else {
            System.out.println("Here are the tasks:");
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                System.out.println("ฅ" + (i + 1) + " " + tasks.get(i));
            }
            if (tasks.getNumberOfTasks() == 1) {
                System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
            } else {
                System.out.println("You have " + tasks.getNumberOfTasks() +
                        " tasks in the list now meow (((;꒪ꈊ꒪;)))");
            }
        }
    }

    public void printMissingTimeErrorMessage() {
        drawDivider();
        System.out.println("Karlett needs to know the date/time for this event meow(๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public void printMissingDeadlineErrorMessage() {
        drawDivider();
        System.out.println("Karlett needs to know your deadline for this task meow(๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public void printDoneFormatErrorMessage() {
        drawDivider();
        System.out.println("Karlett was expecting an index after \"done\" meow(๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public void printDeleteFormatErrorMessage() {
        drawDivider();
        System.out.println("Karlett was expecting an index after \"delete\" meow(๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public void printOutOfBoundErrorMessage(TaskList tasks) {
        drawDivider();
        if (tasks.getNumberOfTasks() == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            System.out.println("Karlett can only remember " + tasks.getNumberOfTasks() +
                    " tasks(๑•́ᆽ•̀๑✿)");
            System.out.println("Here they are meow:");
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                System.out.println("ฅ" + (i + 1) + " " + tasks.get(i));
            }
        }
        drawDivider();
    }

    public void printGeneralErrorMessage() {
        drawDivider();
        System.out.println("You are confusing Karlett meowwww(๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public static void printPendingConfirmationToListMessage() {
        drawDivider();
        System.out.println("Do you want Karlett to list the tasks?(๑•́ᆽ•̀๑✿) [y/n]");
        drawDivider();
    }

    public void printEmptyTaskErrorMessage() {
        drawDivider();
        System.out.println("Karlett doesn't know what you need to do meow?(๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public static void drawDivider() {
        int n = 4;
        while (n > 0) {
            System.out.print("ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ");
            n--;
        }
        System.out.println();
    }

    public void printTaskDeletedMessage(TaskList tasks, Task t) {
        drawDivider();
        System.out.println("Meow~ Karlett has deleted this task:\n" +
                "  " + t + "\nYou have " + tasks.getNumberOfTasks() +
                " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        drawDivider();
    }

    public void printNewTaskAddedMessage(TaskList tasks, Task task) {
        drawDivider();
        System.out.println("Karlett now remembers:\n" + "  " + task);
        if (tasks.getNumberOfTasks() == 1) {
            System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
        } else {
            System.out.println("You have " + tasks.getNumberOfTasks() +
                    " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        }
        drawDivider();
    }

    public void printMarkAsDoneMessage(Task task) {
        drawDivider();
        System.out.println("Meow~ Karlett has marked this task as done:\n" +
                "  " + task);
        drawDivider();
    }

    public void printEmptyKeywordMessage() {
        drawDivider();
        System.out.println("Karlett doesn't know what you need to find meow?(๑•́ᆽ•̀๑✿)");
        drawDivider();
    };

    public void printInvalidKeywordMessage() {
        drawDivider();
        System.out.println("Please give Karlett one keyword only meow (๑•́ᆽ•̀๑✿)");
        drawDivider();
    }

    public void printNoMatchedTaskFoundMessage(String keyWord) {
        drawDivider();
        System.out.println("Karlett didn't find any task contaning \" " + keyWord + " \" /ᐠﹷ ‸ ﹷ ᐟ\\ﾉ");
        drawDivider();
    }

    public void printMatchedTasksMessage(TaskList matchedTasks) {
        drawDivider();
        System.out.println("Yayyy! Karlett found these matching tasks in your list:");
        for (int i = 0; i < matchedTasks.getNumberOfTasks(); i++) {
            System.out.println("ฅ" + (i + 1) + " " + matchedTasks.get(i));
        }
    }
}
