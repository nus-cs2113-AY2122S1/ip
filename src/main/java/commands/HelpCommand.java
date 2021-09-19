package commands;


public class HelpCommand extends UserCommand {

    @Override
    public String execute() {
        return "     list --list all tasks to be done\n" +
        "     todo <task description> --add a todo type task into the list\n" +
        "     deadline <task description> /<YYYY-MM-DD> [HH:SS]  --add a deadline type task into the list\n" +
        "     event <task_description> /<YYYY-MM-DD> <HH:SS> --add a event type task into the list\n" +
        "     sort --sort all the tasks inside the list based on emergency\n" +
        "     find <keyword> --find task(s) with the keyword\n" +
        "     delete <i> --delete the ith task in the list\n" +
        "     done <i> --set the ith task as completed\n" +
        "     bye --quit the program and save all your tasks\n";
    }
}
