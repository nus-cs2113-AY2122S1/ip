import java.util.Scanner;
/* 
 * Singleton class
 */
public class CommandParser
{
    private static final Scanner scan = new Scanner(System.in);
    private static CommandParser commandParser = null;

    public static CommandParser getCommandParser()
    {
        if (commandParser == null)
            commandParser = new CommandParser();
        return commandParser;
    }

    private  CommandParser()
    {
    }

    public Command parseNextCommand()
    {
        String commandStr = scan.nextLine();
        final String[] commandTypeStrs = Command.getCommandTypeStrs();

        for (int i = 0 ; i < commandTypeStrs.length ; i++)
        {
            if (commandStr.toLowerCase().equals(commandTypeStrs[i]))
            {
                return new Command(Command.CommandType.getCommanTypebyIndex(i));
            }
        }
        return new Command(Command.CommandType.INVALID);
    }
}
