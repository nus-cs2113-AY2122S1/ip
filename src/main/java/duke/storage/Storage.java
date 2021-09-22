package duke.storage;

import duke.Duke;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private static final String FILE_NAME = "duke.txt";
    private static final String DIR_NAME = "data";
    private static final String FILE_PATH = DIR_NAME + "/" + FILE_NAME;

    public static void startDuke() {
        try {
            if (Files.notExists(Paths.get(FILE_PATH))) {
                Files.createDirectories(Paths.get(FILE_PATH).getParent());
                Files.createFile(Paths.get(FILE_PATH));
                Ui.printNewHello();
            } else {
                loadData();
                Ui.printReturningHello();
            }
        } catch (IOException e) {
            Ui.printIOExceptionMessage(e);
        }
    }

    public static void loadData() throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Duke.tasks.add(Parser.parseTaskFromData(s.nextLine()));
        }
    }

    public static void saveData() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : Duke.tasks) {
            fw.write(task.toStorageString() + "\n");
        }
        fw.close();
    }
}
