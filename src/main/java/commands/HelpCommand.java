package commands;


public class HelpCommand extends UserCommand {

    @Override
    public String execute() {
        return "     list                                               --list all tasks to be done\n" +
        "     todo <task description>                            --add a todo type task\n" +
        "     deadline <task description> /<YYYY-MM-DD> [HH:MM]  --add a deadline type task (HH:MM is optional) \n" +
        "     event <task_description> /<YYYY-MM-DD> <HH:MM>     --add a event type task (HH:MM is compulsory) \n" +
        "     sort                                               --sort all the tasks based on emergency\n" +
        "     find <keyword>                                     --find task(s) containing the keyword\n" +
        "     delete <i>                                         --delete the ith task (1-based)\n" +
        "     done <i>                                           --set the ith task as completed (1-based)\n" +
        "     bye                                                --quit and save all your tasks\n";
    }
}
