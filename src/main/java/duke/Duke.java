package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import duke.command.DukeException;
import duke.task.*;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException{
        String logo = "____________________________________________________________\n"
                + "Hello! I'm duke.Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(logo);

        ArrayList<Task> todo = new ArrayList<>(100);
        int todo_index = 0;

        //create duke.txt in folder named 'data'
        //idea from https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
        File dir = new File("data");
        dir.mkdirs();
        File tmp = new File(dir, "duke.txt");
        tmp.createNewFile();
        String save = "";

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            if (input.startsWith("done")) {
                int i = Integer.parseInt(input.substring(5));
                todo.get(i - 1).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(todo.get(i - 1).toString());
                System.out.println("____________________________________________________________");
            } else {
                switch (input) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < todo_index; i++) {
                        System.out.println(i + 1 + ". " + todo.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "blah":
                    logo = "____________________________________________________________\n"
                            + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                case "bye":
                    logo = "____________________________________________________________\n"
                            + "Bye. Hope to see you again soon!\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    return;
                default:
                    if (input.substring(0, 4).equals("todo")) {
                        try {
                            String description = input.substring(5);
                            todo.add(new Task(description));
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo.get(todo_index).toString());
                            save += todo.get(todo_index).toString()+"\n";
                            writeToFile("data/duke.txt",save);
                            todo_index += 1;
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println("____________________________________________________________");
                        }
                    } else if (input.startsWith("deadline")) {
                        int descriptionIndex = 9;
                        String task = "deadline";
                        try {
                            ExceptionTry(task, input);
                            String description = input.substring(input.indexOf("deadline") + descriptionIndex, input.indexOf("/by") - 1);
                            String by = input.substring(input.indexOf("/by") + 4);
                            todo.add(new Deadline(description, by));
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo.get(todo_index).toString());
                            save += todo.get(todo_index).toString()+"\n";
                            writeToFile("data/duke.txt",save);
                            todo_index += 1;
                        } catch (DukeException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a deadline must have /by.");
                            System.out.println("____________________________________________________________");
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                            System.out.println("____________________________________________________________");
                        }

                    } else if (input.startsWith("event")) {
                        int descriptionIndex = 6;
                        String task = "event";
                        try {
                            ExceptionTry(task, input);
                            String description = input.substring(input.indexOf("event") + descriptionIndex, input.indexOf("/at") - 1);
                            String at = input.substring(input.indexOf("/at") + 4);
                            todo.add(new Event(description, at));
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo.get(todo_index).toString());
                            save += todo.get(todo_index).toString()+"\n";
                            writeToFile("data/duke.txt",save);
                            todo_index += 1;
                        } catch (DukeException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a event must have /at.");
                            System.out.println("____________________________________________________________");
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a event cannot be empty.");
                            System.out.println("____________________________________________________________");
                        }

                    }else if(input.startsWith("delete")){
                        try {
                            int i = Integer.parseInt(input.substring(7));
                            System.out.println("____________________________________________________________");
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(todo.get(i - 1).toString());
                            todo.remove(i-1);
                            todo_index -= 1;
                        } catch (NumberFormatException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! Delete must follow by a number.");
                            System.out.println("____________________________________________________________");
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! Delete cannot be empty.");
                            System.out.println("____________________________________________________________");
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! Delete a valid item in list.");
                            System.out.println("____________________________________________________________");
                        }
                    }
                    System.out.println("Now you have " + todo_index + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
            }
            input = sc.nextLine();
        }
    }

    private static void ExceptionTry(String task, String input) throws DukeException {
        //throw duke.command.DukeException here if descriptor don't have /by or /at
        switch (task){
        case "deadline":
            if(!input.contains("/by")){
                throw new DukeException();
            }
            break;
        case "event":
            if(!input.contains("/at")){
                throw new DukeException();
            }
            break;
        default:
            break;
        }
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
