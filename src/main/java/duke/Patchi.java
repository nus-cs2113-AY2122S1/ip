package duke;

import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingTimingException;
import duke.exception.NoTaskSelectedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Patchi {
    static String patchiDataPath = "data/patchidata.txt";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            loadData(tasks);
        } catch (FileNotFoundException e) {
            println("<patchidata.txt file not found>");
        }

        println("─── .o * : *. ¤ .* : ¤ o. ───");
        println("Patchi: Hello! I'm Patchi the tasks fairy Œ(ˊᵕˋ)B\n " +
                "What can I do for you today? Œ(ˊVˋ)B");
        println("─── .o * : typing... : ¤ o. ───");
        print("Me: ");

        String input = in.nextLine();

        while (input.equals("bye") == false) {
            if (input.equals("list") == true) {
                listTasks(tasks);
            } else if (input.startsWith("done")) {
                try {
                    markTaskAsDone(tasks, input);
                } catch (NoTaskSelectedException e) {
                    println("Patchi: You need to select a task... Œ(~ˊnˋ~)B");
                }
            } else if (input.startsWith("delete")) {
                try {
                    deleteTask(tasks, input);
                } catch (NoTaskSelectedException e) {
                    println("Patchi: You need to select a task... Œ(~ˊnˋ~)B");
                }
            } else { //add task OR invalid command
                try {
                    addTask(tasks, input);
                    try {
                        appendToFile(patchiDataPath, input + ",f" + System.lineSeparator());
                    } catch (IOException e) {
                    }
                    println("Patchi: Got it! I have added " + tasks.get(tasks.size() - 1).toString() +
                            " to your task list! Œ(ˆOˆ)B");
                    println("Patchi: You have " + tasks.size() + " tasks now" +
                            "... Too much work... Œ(ˊnˋ)B");
                } catch (InvalidCommandException e) {
                }
            }

            println("─── .o * : typing... : ¤ o. ───");
            print("Me: ");
            input = in.nextLine();
        }

        println("Patchi: Bye! Hope to see you again soon! Œ(~ˊᵕˋ~)B");
        println("─── .o * : *. ¤ .* : ¤ o. ───");
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, String input) throws NoTaskSelectedException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input.substring(5)) - 1;
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoTaskSelectedException();
        }
        if (tasks.size() > taskIndex) {
            tasks.get(taskIndex).setDone(true);

            //change patchidata
            String line;
            try {
                line = getLine(taskIndex);
                line = line.replace(",f", ",t");
                overwriteLine(taskIndex, line);
            } catch (IOException e) {
            }

            println("Patchi: Good job! I've marked this task as done on your list. " +
                    "Time for a break? Œ(ˊwˋ)B");
        } else {
            println("Patchi: That task doesn't seem to exist...... Œ(ˊnˋ)B");
        }
    }

    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            println("Patchi: Here is the list of tasks you currently have! Work hard~ Œ(˙O˙)B");
            for (int i = 0; i < tasks.size(); i++) {
                println((i + 1) + ". " + tasks.get(i).toString());
            }
        } else {
            println("Patchi: You have no tasks for now! Go and relax~ Œ(ˊuˋ)B");
        }
    }

    private static void addTask(ArrayList<Task> tasks, String input)
            throws InvalidCommandException {
        try {
            if (input.startsWith("todo")) {
                addTodo(tasks, input);
            } else if (input.startsWith("deadline")) {
                addDeadline(tasks, input);
            } else if (input.startsWith("event")) {
                addEvent(tasks, input);
            } else {
                throw new InvalidCommandException();
            }
        } catch (MissingDescriptionException e) {
            println("Patchi: You need to add a task description... Œ(ˊnˋ)B");
            throw new InvalidCommandException();
        } catch (MissingTimingException e) {
            println("Patchi: You need to add a timing for this kind of task! Œ(ˊnˋ)B");
            throw new InvalidCommandException();
        } catch (InvalidCommandException e) {
            println("Patchi: I'm sorry, I don't understand what that means... Œ(ˊnˋ)B");
            throw new InvalidCommandException();
        }
    }

    private static void deleteTask(ArrayList<Task> tasks, String input) throws NoTaskSelectedException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input.substring(7)) - 1;
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoTaskSelectedException();
        }

        if (tasks.size() > taskIndex) {
            tasks.remove(taskIndex);
            deleteLine(taskIndex);
            println("Patchi: Whoosh! I've magicked that task away! Œ(ˊwˋ)B");
            println("Patchi: You now have " + tasks.size() + " tasks! Œ(ˊwˋ)B");
        } else {
            println("Patchi: That task doesn't seem to exist...... Œ(ˊnˋ)B");
        }

    }

    private static void addTodo(ArrayList<Task> tasks, String input)
            throws MissingDescriptionException {
        String description;

        try {
            description = input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        tasks.add(new Todo(description));
    }

    private static void addDeadline(ArrayList<Task> tasks, String input)
            throws InvalidCommandException, MissingTimingException, MissingDescriptionException {
        int indexOfBy = input.indexOf("/by");
        String by;
        String description;

        if (indexOfBy == -1) {
            throw new InvalidCommandException();
        }

        try {
            description = input.substring(9, indexOfBy - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        try {
            by = input.substring(indexOfBy + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingTimingException();
        }

        tasks.add(new Deadline(description, by));
    }

    private static void addEvent(ArrayList<Task> tasks, String input)
            throws InvalidCommandException, MissingDescriptionException, MissingTimingException {
        int indexOfAt = input.indexOf("/at");
        String at;
        String description;

        if (indexOfAt == -1) {
            throw new InvalidCommandException();
        }

        try {
            description = input.substring(6, indexOfAt - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        try {
            at = input.substring(indexOfAt + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingTimingException();
        }

        tasks.add(new Event(description, at));
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void overwriteFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(text);
        fw.close();
    }

    private static void loadData(ArrayList<Task> tasks) throws FileNotFoundException {
        File patchiData = new File(patchiDataPath);
        Scanner s = new Scanner(patchiData);
        while (s.hasNext()) {
            String[] taskInfo = s.nextLine().split(",", 0);
            String input = taskInfo[0];
            Boolean isDone = taskInfo[1].equals("t") ? true : false; //t or f
            try {
                addTask(tasks, input);
                tasks.get(tasks.size() - 1).setDone(isDone);
            } catch (InvalidCommandException e) {
            }
        }
    }

    private static void overwriteLine(Integer lineIndex, String newLine) throws IOException {
        File patchiData = new File(patchiDataPath);
        Scanner s = new Scanner(patchiData);
        String newFileData = "";
        Integer i = 0;

        while (s.hasNext()) {
            if (i == lineIndex) {
                newFileData += newLine + System.lineSeparator();
                s.nextLine();
            } else {
                newFileData += s.nextLine() + System.lineSeparator();
            }
            i++;
        }
        overwriteFile(patchiDataPath, newFileData);
    }

    private static void deleteLine(Integer lineIndex) {
        try {
            File patchiData = new File(patchiDataPath);
            Scanner s = new Scanner(patchiData);
            String newFileData = "";
            Integer i = 0;

            while (s.hasNext()) {
                if (i == lineIndex) {
                    s.nextLine();
                } else {
                    newFileData += s.nextLine() + System.lineSeparator();
                }
                i++;
            }
            overwriteFile(patchiDataPath, newFileData);
        } catch (IOException e) {
        }
    }


    private static String getLine(Integer lineIndex) throws IOException {
        File patchiData = new File(patchiDataPath);
        Scanner s = new Scanner(patchiData);
        Integer i = 0;

        while (s.hasNext()) {
            if (i == lineIndex) {
                return s.nextLine();
            }
            i++;
        }

        return null;
    }
}
