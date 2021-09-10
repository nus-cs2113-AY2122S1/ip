package duke;


import duke.command.Command;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataManager {

    private static final String fileName = ".\\src\\main\\java\\duke\\Data.txt";

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                data = data.replace(']', ',');
                data = data.replace('[', ' ');
                String[] dataParts = data.split(",");
                for (int i = 0; i < 3; i ++) {
                    dataParts[i] = dataParts[i].trim();
                }
                addTaskEntry(dataParts);
            }
            sc.close();    
            System.out.println("Loaded saved data successfully!");
            UserInterface.printLine();
        } catch (IOException | DukeBlankDescriptionsException | DukeInvalidTaskIndexException e) {
            e.printStackTrace();
        }
    }

    private static void addTaskEntry(String[] dataParts) throws DukeBlankDescriptionsException, DukeInvalidTaskIndexException {
        if (dataParts[0].equals("T")) {
            TaskManager.addTask(Command.ADD_TODO, dataParts[2]);
            if (dataParts[1].equals("X")) {
                TaskManager.setDone(TaskManager.getNumOfTask());
            }
        } else if (dataParts[0].equals("D")) {
            dataParts[2] = dataParts[2].replace(")", "");
            dataParts[2] = dataParts[2].replace("(by:", "/by");
            TaskManager.addTask(Command.ADD_DEADLINE, dataParts[2]);
            if (dataParts[1].equals("X")) {
                TaskManager.setDone(TaskManager.getNumOfTask());
            }
        } else {
            dataParts[2] = dataParts[2].replace(")", "");
            dataParts[2] = dataParts[2].replace("(at:", "/at");
            TaskManager.addTask(Command.ADD_EVENT, dataParts[2]);
            if (dataParts[1].equals("X")) {
                TaskManager.setDone(TaskManager.getNumOfTask());
            }
        }
    }

    public static void save() {
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        BufferedWriter buffer = new BufferedWriter(writer);
        try {
            for (Task t : TaskManager.tasks) {
                buffer.write(String.valueOf(t));
                buffer.write("\n");
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Saved Successfully!");
    }
}

