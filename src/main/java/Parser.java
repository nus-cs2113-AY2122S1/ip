import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Parser {
    public static Command parse(String input, Tasks tasks, Ui ui, Storage storage) throws DukeException{
        if (input.contentEquals("bye")) {
            return new Bye(ui);
        } else if (input.startsWith("todo ")) {
            String taskDescription = input.replaceFirst("todo", "").trim();
            if (taskDescription.isEmpty()) {
                throw new DukeException("Description cannot be empty!");
            }
            Task task = new Task(taskDescription);
            return new AddTask(tasks, task, ui, storage);
        } else if (input.startsWith("event ")) {
            input = input.replaceFirst("event", "").trim();
            String[] parsedInput = input.split("/at");
            if (parsedInput.length < 2 || parsedInput[1].trim().isEmpty()) {
                throw new DukeException("Description cannot be empty!");
            }

            Task task = new Event(parsedInput[0], parsedInput[1].trim());
            return new AddTask(tasks, task, ui,storage);
        } else if (input.startsWith("deadline ")) {
            input = input.replaceFirst("deadline", "").trim();
            String[] parsedInput = input.split("/by");
            if (parsedInput.length < 2 || parsedInput[1].trim().isEmpty()) {
                throw new DukeException("Description cannot be empty!");
            }
            Task task = new Deadline(parsedInput[0], parsedInput[1].trim());
            return new AddTask(tasks, task, ui,storage);
        } else if (input.startsWith("done ")) {
            String[] parsedInput = input.split(" ");
            if (parsedInput.length < 2) {
                throw new DukeException("Sorry done ____ cannot  be empty");
            }
            if (Integer.parseInt(parsedInput[1]) > tasks.size() || Integer.parseInt(parsedInput[1]) < 1) {
                throw new DukeException("That is not in the list");
            }
            int index = Integer.parseInt(parsedInput[1]) - 1;
            return new Done(tasks, index, ui,storage);
        } else if (input.startsWith("delete ")) {
            String[] parsedInput = input.split(" ");
            if (parsedInput.length < 2) {
                throw new DukeException("Sorry delete ____ cannot  be empty");
            }
            if (Integer.parseInt(parsedInput[1]) > tasks.size() || Integer.parseInt(parsedInput[1]) < 1) {
                throw new DukeException("That is not in the list");
            }
            int index = Integer.parseInt(parsedInput[1]) - 1;
            return new Delete(tasks, index, ui, storage);
        } else if (input.startsWith("find ")){
            String keyword = input.replaceFirst("find", "").trim();
            if (keyword.isEmpty()) {
                throw new DukeException("Sorry find ____ cannot  be empty");
            }
            return new Find(tasks, ui, keyword);
        } else if (input.startsWith("list")) {
            return new List(tasks, ui);
        } else{
            throw new DukeException("Sorry I do not understand");
        }
    }
}
