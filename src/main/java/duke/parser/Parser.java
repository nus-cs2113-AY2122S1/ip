package duke.parser;

import java.util.regex.Pattern;

public class Parser {
    // Code below inspired by
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
    public static final String MATCH_GROUP_COMMAND = "command";
    public static final String MATCH_GROUP_ARGS = "args";
    /** Used for initial separation of command word and args. */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "(?<" + MATCH_GROUP_COMMAND + ">\\S+)"
                    + "(?<" + MATCH_GROUP_ARGS + ">.*)");

    public static final String MATCH_GROUP_DESCRIPTION = "description";
    public static final String MATCH_GROUP_BY = "by";
    public static final String MATCH_GROUP_AT = "at";
    public static final Pattern TASK_ARGS_FORMAT = Pattern.compile(
            "(?<" + MATCH_GROUP_DESCRIPTION + ">[^/]+)"
                    + "( /by (?<" + MATCH_GROUP_BY + ">[^/]+))?"
                    + "( /at (?<" + MATCH_GROUP_AT + ">[^/]+))?");
}
