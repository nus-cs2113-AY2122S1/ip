package duke.storage;

import duke.exception.EmptyTaskException;
import duke.task.*;
import duke.ui.Ui;
import java.io.*;

public class Storage {
    private String listFilePath;
    private String doneListFilePath;

    public Storage(String listFilePath, String doneListFilePath) {
        this.listFilePath = listFilePath;
        this.doneListFilePath = doneListFilePath;
    }

    /**
     * Read the context of files into list and doneList
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     * @throws IOException If the path is invalid and some other errors occur,
     * exception occurs.
     */
    public void readFiles(TaskList list, TaskDoneList doneList, Ui ui) throws IOException {
        try {
            File listFile = new File(this.listFilePath);
            File listDoneFile = new File(this.doneListFilePath);
            BufferedReader bufferList = new BufferedReader(new FileReader(listFile));
            BufferedReader bufferDoneList = new BufferedReader(new FileReader(listDoneFile));
            String str;
            try {
                while ((str = bufferList.readLine()) != null) {
                    String[] tasks = str.split(",");
                    for (int i = 0; i < tasks.length; i++) {
                        String description = tasks[i].substring(8);
                        if (i == tasks.length - 1) {
                            description = description.substring(0, description.length() - 1);
                        }
                        switch (tasks[i].substring(2, 3)) {
                        case "T":
                            if (tasks[i].contains("X")) {
                                Todo todo = new Todo(description);
                                todo.description = todo.description.replaceFirst(" ", "X");
                                todo.isDone = true;
                                list.addTask(todo);
                            } else {
                                list.addTask(new Todo(description));
                            }
                            continue;
                        case "D":
                            String by = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                            if (tasks[i].contains("X")) {
                                Deadline deadline = new Deadline(description.substring(0, description.indexOf("(") - 1), by);
                                deadline.description = deadline.description.replaceFirst(" ", "X");
                                deadline.isDone = true;
                                list.addTask(deadline);
                            } else {
                                list.addTask(new Deadline(description.substring(0, description.indexOf("(") - 1), by));
                            }
                            continue;
                        case "E":
                            String at = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                            if (tasks[i].contains("X")) {
                                Event event = new Event(description.substring(0, description.indexOf("(") - 1), at);
                                event.description = event.description.replaceFirst(" ", "X");
                                event.isDone = true;
                                list.addTask(event);
                            } else {
                                list.addTask(new Event(description.substring(0, description.indexOf("(") - 1), at));
                            }
                            continue;
                        default:
                            System.out.println("There are errors in reading in files.");
                        }
                    }
                }
                ui.printList(list, ui);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printList(list, ui);
            }
            try {
                while ((str = bufferDoneList.readLine()) != null) {
                    String[] tasks = str.split(",");
                    for (int i = 0; i < tasks.length; i++) {
                        String description = tasks[i].substring(8);
                        if (i == tasks.length - 1) {
                            description = description.substring(0, description.length() - 1);
                        }
                        switch (tasks[i].substring(2, 3)) {
                        case "T":
                            Todo todo = new Todo(description);
                            todo.description = todo.description.replaceFirst(" ", "X");
                            todo.isDone = true;
                            doneList.addDoneTask(todo);
                            continue;
                        case "D":
                            String by = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                            Deadline deadline = new Deadline(description.substring(0, description.indexOf("(") - 1), by);
                            deadline.description = deadline.description.replaceFirst(" ", "X");
                            deadline.isDone = true;
                            doneList.addDoneTask(deadline);
                            continue;
                        case "E":
                            String at = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                            Event event = new Event(description.substring(0, description.indexOf("(") - 1), at);
                            event.description = event.description.replaceFirst(" ", "X");
                            event.isDone = true;
                            doneList.addDoneTask(event);
                            continue;
                        default:
                            System.out.println("    There are errors in reading in files.");
                        }
                    }
                }
                ui.printDoneList(doneList, ui);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printDoneList(doneList, ui);
            }
        } catch (FileNotFoundException e) {
            System.out.println("    No files saved. Please start using Duke now!");
            ui.printSplitLine();
        }
    }

    /**
     * Update all the remaining tasks into a file
     *
     * @param list The list of tasks known
     * @throws IOException If the path is invalid and some other errors occur,
     *                     exception occurs.
     */
    public void createListFile(TaskList list) throws IOException {
        File dukeData = new File(this.listFilePath);
        if (!dukeData.exists()) {
            dukeData.createNewFile();
        }
        FileWriter writer = new FileWriter(dukeData);
        BufferedWriter out = new BufferedWriter(writer);
        out.write(String.valueOf(list));
        out.flush();
        out.close();
    }

    /**
     * Update all the remaining tasks which have been finished into a file
     *
     * @param doneList The list of tasks known
     * @throws IOException If the path is invalid and some other errors occur,
     *                     exception occurs.
     */
    public void createDoneListFile(TaskDoneList doneList) throws IOException {
        File dukeDoneData = new File(this.doneListFilePath);
        if (!dukeDoneData.exists()) {
            dukeDoneData.createNewFile();
        }
        FileWriter writer = new FileWriter(dukeDoneData);
        BufferedWriter out = new BufferedWriter(writer);
        out.write(String.valueOf(doneList));
        out.flush();
        out.close();
    }
}
