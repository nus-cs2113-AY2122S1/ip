public class Parser {

    public Parser() {}

    public String findCommand(String line) {
        int spaceIndex = line.indexOf(" ");
        return spaceIndex == -1 ? line : line.substring(0, spaceIndex);
    }

    public String findContent(String line) {
        int spaceIndex = line.indexOf(" ");
        return spaceIndex == -1 ? line : line.substring(spaceIndex);
    }

}
