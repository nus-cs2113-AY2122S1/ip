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
        return new Command(commandStr);
    }














}
