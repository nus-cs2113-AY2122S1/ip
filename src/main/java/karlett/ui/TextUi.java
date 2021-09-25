package karlett.ui;

import karlett.Duke;
import karlett.storage.StorageFile;
import karlett.task.Task;

import java.io.IOException;
import java.util.Scanner;

public class TextUi {

    public static void greet() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)");
    }

    public static void printFileNotFoundMessage() throws IOException {
        System.out.print("Uh-oh...Karlett can't find the file in " + StorageFile.filePath + "(=ಠᆽಠ=)\n");
        printFileNotFoundInstructions();
        Scanner in = new Scanner(System.in);

        String r = in.next();
        try {
            int response = Integer.parseInt(r);
            switch (response) {
            case 1:
                StorageFile.file.createNewFile();
                printNewFileCreatedMessage();
                break;
            case 2:
                System.out.print("Please tell me the new file path meow: ");
                StorageFile.filePath = in.next();
                StorageFile.loadDataFromStorageFile();
                break;
            case 3:
                Task.exit();
                // Fallthrough
            default:
                printFileNotFoundMessage();
            }
        } catch (NumberFormatException e) {
            printFileNotFoundMessage();
        }
    }

    private static void printNewFileCreatedMessage() {
        System.out.print("Meow~ Karlett has created the new file here:\n" +
                StorageFile.file.getAbsolutePath() +
                "\nKarlett can start tracking your tasks now ಇ/ᐠ ̥ᵔ ̮ᵔ ̥ᐟ\\ಇ\n");
    }

    private static void printFileNotFoundInstructions() {
        System.out.println("What can Karlett do for you meow? (Please key in 1/2/3)\n" +
                "  1 Create a new text file called \"karlett.txt\" for me please.\n" +
                "  2 Load data from another file path for me please.\n" +
                "  3 Exit the program now.\n");
    }

    public static void printFinishLoadingDataMessage() {
        System.out.println("Karlett has finished loading data from the file ✧/ᐠ-ꞈ-ᐟ\\");
        if (Duke.list.size() == 0) {
            System.out.println("There was no task recorded yet.");
        } else {
            System.out.println("Here are the tasks:");
            for (int i = 0; i < Duke.list.size(); i++) {
                System.out.println("ฅ" + (i + 1) + " " + Duke.list.get(i));
            }
            if (Duke.list.size() == 1) {
                System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
            } else {
                System.out.println("You have " + Duke.list.size() +
                        " tasks in the list now meow (((;꒪ꈊ꒪;)))");
            }
        }
        System.out.println("What can I do for you meow?");
    }

    public static void printMissingTimeErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett needs to know the date/time for this event meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    public static void printMissingDeadlineErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett needs to know your deadline for this task meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    public static void printDoneFormatErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett was expecting an index after \"done\" meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    public static void printDeleteFormatErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett was expecting an index after \"delete\" meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    public static void printOutOfBoundErrorMessage() {
        Task.drawDivider();
        if (Duke.list.size() == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            System.out.println("Karlett can only remember " + Duke.list.size() + " tasks(๑•́ᆽ•̀๑✿)");
            System.out.println("Here they are meow:");
            for (int i = 0; i < Duke.list.size(); i++) {
                System.out.println("ฅ" + (i + 1) + " " + Duke.list.get(i));
            }
        }
        Task.drawDivider();
    }

    public static void printGeneralErrorMessage() {
        Task.drawDivider();
        System.out.println("You are confusing Karlett meowwww(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    public static void printPendingConfirmationToListMessage() {
        Task.drawDivider();
        System.out.println("Do you want Karlett to list the tasks?(๑•́ᆽ•̀๑✿) [y/n]");
        Task.drawDivider();
    }

    public static void printEmptyTaskErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett doesn't know what you need to do meow?(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }
}
