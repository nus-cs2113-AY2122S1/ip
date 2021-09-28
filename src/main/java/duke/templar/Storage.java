package duke.templar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File saveFile;
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.saveFile = new File(filePath);
    }


    public void createFile() throws IOException {
        Files.createDirectories(Path.of(saveFile.getParent()));
        saveFile.createNewFile();

    }

    public Parser loadSave (ArrayList<Task> tasks) throws FileNotFoundException {

        Parser TM = new Parser(tasks);
        try {
            Scanner in = new Scanner(saveFile);

            if (in != null) {
                while (in.hasNext()) {
                    String line = in.nextLine();

                    if (line.contains("[T]")) {
                        String[] todoSplit = line.split("] ", 2);
                        String todoDescription = todoSplit[1];
                        ToDo newToDo = new ToDo(todoDescription);
                        TM.tasks.add(newToDo);
                    }
                    else if (line.contains("[D]")) {
                        String[] firstDeadlineSplit = line.split("] ", 2);
                        String[] secondDeadlineSplit = firstDeadlineSplit[1].split(" \\(by: ", 2); //settles description
                        String[] thirdDeadlineSplit = secondDeadlineSplit[1].split("\\)", 2); //settles date
                        String deadlineDescription = secondDeadlineSplit[0];
                        String deadlineDate = thirdDeadlineSplit[0];
                        LocalDateTime deadlineFormatted = Parser.parseDateTime(deadlineDate, "MMM dd yyyy h.mma");
                        Deadline newDeadline = new Deadline(deadlineDescription, deadlineFormatted);
                        TM.tasks.add(newDeadline);
                    }
                    else if (line.contains("[E]")) {
                        String[] firstEventSplit = line.split("] ", 2);
                        String[] secondEventSplit = firstEventSplit[1].split(" \\(at: ", 2); //settles description
                        String[] thirdEventSplit = secondEventSplit[1].split("\\)", 2); //settles date
                        String eventDescription = secondEventSplit[0];
                        String eventDate = thirdEventSplit[0];
                        Event newEvent = new Event(eventDescription, eventDate);
                        TM.tasks.add(newEvent);
                    }
                }
            }

        }
        catch (FileNotFoundException fileNotFoundException) {
            Ui.printFileNotFoundExceptionMsg();
            try {
                createFile();
            } catch (IOException ioException) {
                Ui.printCreateFailMsg();
            }
        }
        return TM;
    }

    public void writeSave(Parser TM) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < TM.tasks.size(); i++) {
                fileWriter.write(TM.tasks.get(i) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.printWriteFailMsg();
        }
    }


}
