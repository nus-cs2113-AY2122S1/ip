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

/**
 * Loads and interprets the saved task list from the previous run
 * as well as writes the next save once the user ends the session
 */
public class Storage
{

    private final File saveFile;
    private final String filePath;

    public Storage(String filePath)
    {
        this.filePath = filePath;
        this.saveFile = new File(filePath);
    }

    /**
     * creates new file to save the new task list in
     * @throws IOException
     */
    public void createFile() throws IOException
    {
        Files.createDirectories(Path.of(saveFile.getParent()));
        saveFile.createNewFile();

    }

    /**
     * Makes sense of the current saved file for the user to view on the command line
     * @param tasks
     * @return the parser used
     * @throws FileNotFoundException
     */
    public Parser loadSave (ArrayList<Task> tasks) throws FileNotFoundException
    {
        int completedTaskIndex = 0;
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
                        if (line.contains("[x]")) {
                            TM.tasks.get(completedTaskIndex).setDone(true);
                        }
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
                        if (line.contains("[x]")) {
                            TM.tasks.get(completedTaskIndex).setDone(true);
                        }
                    }
                    else if (line.contains("[E]")) {
                        String[] firstEventSplit = line.split("] ", 2);
                        String[] secondEventSplit = firstEventSplit[1].split(" \\(at: ", 2); //settles description
                        String[] thirdEventSplit = secondEventSplit[1].split("\\)", 2); //settles date
                        String eventDescription = secondEventSplit[0];
                        String eventDate = thirdEventSplit[0];
                        LocalDateTime eventFormatted = Parser.parseDateTime(eventDate, "MMM dd yyyy h.mma");
                        Event newEvent = new Event(eventDescription, eventFormatted);
                        TM.tasks.add(newEvent);
                        if (line.contains("[x]")) {
                            TM.tasks.get(completedTaskIndex).setDone(true);
                        }
                    }
                    completedTaskIndex++;
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

    /**
     * Writes the new task list to the created file
     * @param TM
     * @throws IOException
     */
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
