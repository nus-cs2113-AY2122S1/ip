package duke.save;

import duke.commandHandler.SaveCommandHandling;
import duke.taskType.Deadline;
import duke.taskType.Event;
import duke.taskType.Task;
import duke.taskType.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveTaskListToText {
    public static File directoryOfTaskText = new File("./data");
    public static File dukeTaskText = new File("./data/duke.txt");
    public static int numberOfTasksAdded = 0;


    public static void removeLineFromFile(String file, String lineToRemove) {
        try {
            File inFile = new File(file);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            // Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }

            pw.close();
            br.close();

            // Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static void fileTaskCopy(ArrayList<Task> tasks) throws IOException {
        Scanner scanText = new Scanner(dukeTaskText); // create a Scanner using the File as the source
        while (scanText.hasNext()) {
            String currentCommand = scanText.nextLine();
            SaveCommandHandling commandHandle = new SaveCommandHandling(currentCommand);

            if (commandHandle.isToDo()) {
                int done = Integer.parseInt(currentCommand.split("-/-")[1]);
                String taskName = currentCommand.split("-/-")[2];

                tasks.add(new ToDo(taskName));

                if (done == 1) {
                    tasks.get(numberOfTasksAdded).markAsDone();
                }

                numberOfTasksAdded += 1;
            } else if (commandHandle.isEvent()) {
                int done = Integer.parseInt(currentCommand.split("-/-")[1]);
                String taskName = currentCommand.split("-/-")[2];
                String at = currentCommand.split("-/-")[3];

                tasks.add(new Event(taskName, at));

                if (done == 1) {
                    tasks.get(numberOfTasksAdded).markAsDone();
                }

                numberOfTasksAdded += 1;
            } else if (commandHandle.isDeadline()) {
                int done = Integer.parseInt(currentCommand.split("-/-")[1]);
                String taskName = currentCommand.split("-/-")[2];
                String by = currentCommand.split("-/-")[3];

                tasks.add(new Deadline(taskName, by));

                if (done == 1) {
                    tasks.get(numberOfTasksAdded).markAsDone();
                }

                numberOfTasksAdded += 1;
            }
        }

        scanText.close();
    }

    /**
     * Checks if directory exist first. If it doesn't, it means the duke.txt is also missing.
     * In such a case, make directory and then create duke.txt file there. Then if directory
     * exist, but duke.txt is missing, add duke.txt.
     *
     * @param tasks
     * @return
     */
    public static int loadTask(ArrayList<Task> tasks) throws IOException {
        if (directoryOfTaskText.exists() == false) {
            directoryOfTaskText.mkdir();
            dukeTaskText.createNewFile();
        } else if (dukeTaskText.exists() == false) {
            dukeTaskText.createNewFile();
        } else {
            fileTaskCopy(tasks);
        }

        return numberOfTasksAdded;
    }

    public static boolean checkLineExist(String stringToCheck) {
        File file = new File("./data/duke.txt");

        try {
            Scanner scanner = new Scanner(file);

            int lineNum = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;

                if (line.equals(stringToCheck)) {
                    scanner.close(); // Don't forget!
                    return true;
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }

        return false;
    }

    public static boolean saveToDo(String taskName) {
        String textToWrite1 = "t-/-0-/-" + taskName;
        String textToWrite2 = "t-/-1-/-" + taskName;

        try {
            if (checkLineExist(textToWrite1) == false && checkLineExist(textToWrite2) == false) {
                String dukeDirectory = "data/duke.txt";

                File file = new File(dukeDirectory);
                boolean fileIsEmpty = false;

                if (file.length() == 0) {
                    fileIsEmpty = true;
                }

                FileWriter addToDo = new FileWriter(dukeDirectory, true); //the true will append the new data
                if (fileIsEmpty == false) {
                    addToDo.write("\n"); //appends the string to the file
                }
                addToDo.write(textToWrite1);
                addToDo.close();
            } else {
                return false;
            }
        } catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

        return true;
    }

    public static boolean saveEvent(String taskName, String at) {
        String eventToWrite1 = "e-/-0-/-" + taskName + "-/-/at" + at.substring(3);
        String eventToWrite2 = "e-/-1-/-" + taskName + "-/-/at" + at.substring(3);

        try {
            if (checkLineExist(eventToWrite1) == false && checkLineExist(eventToWrite2) == false) {
                String dukeDirectory = "data/duke.txt";
                File file = new File(dukeDirectory);
                boolean fileIsEmpty = false;

                if (file.length() == 0) {
                    fileIsEmpty = true;
                }

                FileWriter addToDo = new FileWriter(dukeDirectory, true); //the true will append the new data
                if (fileIsEmpty == false) {
                    addToDo.write("\n");//appends the string to the file
                }
                addToDo.write(eventToWrite1);
                addToDo.close();
            } else {
                return false;
            }
        } catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
        return true;
    }

    public static boolean saveDeadline(String taskName, String by) {
        String deadlineToWrite1 = "d-/-0-/-" + taskName + "-/-/by" + by.substring(3);
        String deadlineToWrite2 = "d-/-1-/-" + taskName + "-/-/by" + by.substring(3);

        try {
            if (checkLineExist(deadlineToWrite1) == false && checkLineExist(deadlineToWrite2) == false) {
                String dukeDirectory = "data/duke.txt";
                File file = new File(dukeDirectory);
                boolean fileIsEmpty = false;

                if (file.length() == 0) {
                    fileIsEmpty = true;
                }

                FileWriter addToDo = new FileWriter(dukeDirectory, true); //the true will append the new data
                if (fileIsEmpty == false) {
                    addToDo.write("\n");//appends the string to the file
                }
                addToDo.write(deadlineToWrite1);
                addToDo.close();
            } else {
                return false;
            }
        } catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
        return true;
    }

    public static void textFileTaskDoneMarker(String oldLine, Character taskType) throws IOException {
        String filePath = "./data/duke.txt";
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer buffer = new StringBuffer();

        while (sc.hasNextLine() == true) {
            buffer.append(sc.nextLine());
            if (sc.hasNextLine() == true) {
                buffer.append(System.lineSeparator());
            }
        }

        String fileContents = buffer.toString();

        String newLine = "";
        if (taskType == 't') {
            newLine += "t-/-1-/-" + oldLine.substring(8);
        } else if (taskType == 'e') {
            newLine += "e-/-1-/-" + oldLine.substring(8);
        } else {
            newLine += "d-/-1-/-" + oldLine.substring(8);
        }

        sc.close();

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
        writer.close();
    }

    public static String textToDoSaveFormatter(String rawTaskDescription) throws IOException {
        String taskDecription = rawTaskDescription.substring(7);
        return "t-/-0-/-" + taskDecription;
    }

    public static String textEventSaveFormatter(String rawTaskDescription) throws IOException {
        String taskFullDecription = rawTaskDescription.substring(7);

        String taskDescription = taskFullDecription.split("at: ")[0];
        int indexOfDescriptionEnd = taskDescription.length() - 2;
        taskDescription = taskDescription.substring(0, indexOfDescriptionEnd);

        String taskAt = taskFullDecription.split("at: ")[1];
        int indexOfByEnd = taskAt.length() - 1;
        taskAt = taskAt.substring(0, indexOfByEnd);

        return "e-/-0-/-" + taskDescription + " -/-/at" + taskAt;
    }

    public static String textDeadlineSaveFormatter(String rawTaskDescription) throws IOException {
        String taskFullDecription = rawTaskDescription.substring(7);

        String taskDescription = taskFullDecription.split("by: ")[0];
        int indexOfDescriptionEnd = taskDescription.length() - 2;
        taskDescription = taskDescription.substring(0, indexOfDescriptionEnd);

        String taskBy = taskFullDecription.split("by: ")[1];
        int indexOfByEnd = taskBy.length() - 1;
        taskBy = taskBy.substring(0, indexOfByEnd);

        return "d-/-0-/-" + taskDescription + " -/-/by" + taskBy;
    }

    public static void saveFinishedTask(String rawTaskDescription) throws IOException {
        if (rawTaskDescription.substring(1,2).equals("T") == true) {
            String formattedDescription = textToDoSaveFormatter(rawTaskDescription);
            textFileTaskDoneMarker(formattedDescription, 't');
        } else if (rawTaskDescription.substring(1,2).equals("E") == true) {
            String formattedDescription = textEventSaveFormatter(rawTaskDescription);
            textFileTaskDoneMarker(formattedDescription, 'e');
        } else {
            String formattedDescription = textDeadlineSaveFormatter(rawTaskDescription);
            textFileTaskDoneMarker(formattedDescription, 'd');
        }
    }
}
