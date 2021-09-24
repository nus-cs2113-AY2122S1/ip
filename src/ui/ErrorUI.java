package ui;

public class ErrorUI {

    public static void printInvalidDate() {
        System.out.println("Please enter a valid date");
        System.out.println("Date should be in the form DDMMYYYY or DD/MM/YYYY or DD-MM-YYYY");
    }

    public static void printInvalidCommand() {
        System.out.println("Please enter a valid command");
    }

    public static void printInvalidDescription() {
        System.out.println("Please enter a valid description");
    }

    public static void printInvalidIndex() {
        System.out.println("Please enter a valid task number");
    }

    public static void printInvalidSaveFile() {
        System.out.println("Error loading save file");
    }

    public static void printInvalidDateLoaded() {
        System.out.println("Invalid date in save file duke.data");
    }

    public static void printInvalidWrite() {
        System.out.println("Error writing to save file");
    }
}
