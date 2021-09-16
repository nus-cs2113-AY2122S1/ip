package duke;

import duke.command.CommandList;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int ERROR_TODO_IS_EMPTY = 1;
    private static final int ERROR_EVENT_IS_EMPTY = 2;
    private static final int ERROR_DEADLINE_IS_EMPTY = 3;
    private static final int ERROR_COMMAND_NOT_FOUND = 4;
    private static final int ERROR_IS_INVALID = 5;
    private static final int CMD_NOT_FOUND = 0;
    private static final int CMD_TODO = 1;
    private static final int CMD_EVENT = 2;
    private static final int CMD_DEADLINE = 3;
    private static final int CMD_LIST = 4;
    private static final int CMD_DONE = 5;
    private static final int CMD_TERMINATE = 6;
    private static final int CMD_DELETE = 7;
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DONE = "done";
    private static final String BYE = "bye";
    private static final String DELETE = "delete";
    private static final String BY = "/by";
    private static final String AT = "/at";
    private static final ArrayList<Task> items = new ArrayList<>();
    private static final String fileDir = "./data";
    private static final String fileName = "./data/save.txt";
    private static final String logo =
              " _____  ___  _____     ______\n"
            + "|___  | | | |_____|  / / -- \\ \\ \n"
            + "   / /  | |    / /  | |      | | \n"
            + "  / /   | |   / /   | |      | |\n"
            + " / /___ | |  /_/__  | |  --  | |\n"
            + "|_____| | | |_____|  \\ \\____/ /\n";
    private static final String border = "____________________________________________________________\n";


    public static void printStartMessage() {
        System.out.println(logo);
        System.out.println(border + "Hi bro, my name is Echo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
    }
    public static boolean isInvalid(CommandList task, String line , String key) {
        if (!line.split(key)[1].trim().isEmpty()) {
            if (Integer.parseInt(line.split(key)[1].trim()) > task.getTaskCount()) {
                return true;
            }
        }
        return (line.length() <= FIVE);
    }
    private static void createSave() {
        File saveFile = new File(Duke.fileName);
        File saveDir = new File(Duke.fileDir);
        if (saveDir.mkdirs()) {
            System.out.println("Successfully created save dir");
        } else {
            System.out.println("Save folder already exists.");
        }
        try {
            if (saveFile.createNewFile()) {
                System.out.println("Successfully created save file");
            } else {
                System.out.println("Save file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateSave() {
        Task[] saveLists = new Task[items.size()];
        items.toArray(saveLists);
        try {
           FileWriter fw = new FileWriter(Duke.fileName);
            for (Task saveList : saveLists) {
                fw.write(saveList.toString().charAt(1) + "/");
                fw.write(saveList.getStatus() + "/" + saveList.getDescription());
                if (!saveList.getDate().equals("empty"))  {
                    fw.write("/" + saveList.getDate());
                }
                fw.write("\n");
            }
            System.out.println("Automatically saved to " + fileName);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readSave(CommandList task) throws FileNotFoundException {
        File saveFile = new File(Duke.fileName);
        Scanner s = new Scanner(saveFile);
        while (s.hasNext()) {
            String[] chars = s.nextLine().split("/");
            String type = chars[0].trim();
            String status = chars[1].trim();
            String description = chars[2].trim();
            switch (type) {
                case "E":
                    String date = chars[3].trim();
                    task.addEvent(Duke.items, description, date);
                    if (status.equals("1")) {
                        items.get(task.getTaskCount()).markDone();
                    }
                    break;
                case "D":
                    date = chars[3].trim();
                    task.addDeadline(Duke.items, description, date);
                    if (status.equals("1")) {
                        items.get(task.getTaskCount()).markDone();
                    }
                    break;
                case "T":
                    task.addTodo(Duke.items, description);
                    break;
            }
        }
        task.loadTaskCount(items);
        System.out.println("Save file successfully loaded");
    }

    public static String readCommand(Scanner in, CommandList task, DukeException error) {
        String line;
        do {
            line = in.nextLine();
            if (line.matches(LIST)) {
                task.setCommand(CMD_LIST);
                task.executeCommand(items, error, line);
                System.out.println(border);
            } else if (line.length() > FOUR && line.substring(0, FOUR).contains(DONE)) {
                if (isInvalid(task, line, DONE)) {
                    task.setCommand(CMD_NOT_FOUND);
                    error.setErrorType(ERROR_IS_INVALID);
                } else {
                    task.setCommand(CMD_DONE);
                }
                task.executeCommand(items, error, line);
            } else if (line.length() >= FOUR && line.substring(0, FOUR).contains(TODO)) {
                if (line.length() <= FIVE) {
                    error.setErrorType(ERROR_TODO_IS_EMPTY);
                    task.setCommand(CMD_NOT_FOUND);
                } else {
                    task.setCommand(CMD_TODO);
                }
                task.executeCommand(items, error, line);
            } else if (line.length() >= FIVE && line.substring(0, FIVE).contains(EVENT)) {
                if (line.length() > SIX && line.contains(AT)) {
                    task.setCommand(CMD_EVENT);
                } else {
                    error.setErrorType(ERROR_EVENT_IS_EMPTY);
                    task.setCommand(CMD_NOT_FOUND);
                }
                task.executeCommand(items, error, line);
            } else if (line.length() >= EIGHT && line.substring(0, EIGHT).contains(DEADLINE)) {
                if (line.length() >= NINE && line.contains(BY)) {
                    task.setCommand(CMD_DEADLINE);
                } else {
                    error.setErrorType(ERROR_DEADLINE_IS_EMPTY);
                    task.setCommand(CMD_NOT_FOUND);
                }
                task.executeCommand(items, error, line);
            } else if (line.length() >= SIX && line.substring(0, SIX).contains(DELETE)) {
                if (isInvalid(task, line,DELETE)) {
                    task.setCommand(CMD_NOT_FOUND);
                    error.setErrorType(ERROR_IS_INVALID);
                } else {
                    task.setCommand(CMD_DELETE);
                }
                task.executeCommand(items, error, line);
            } else if (!line.matches(BYE)) {
                error.setErrorType(ERROR_COMMAND_NOT_FOUND);
                task.setCommand(CMD_NOT_FOUND);
                task.executeCommand(items, error, line);
            }
        } while (!line.matches(BYE));
        return line;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        CommandList task = new CommandList();
        DukeException error = new DukeException();
        String line;
        createSave();
        readSave(task);
        printStartMessage();
        line = readCommand(in, task, error);
        updateSave();
        task.setCommand(CMD_TERMINATE);
        task.executeCommand(items, error, line);
    }
}
