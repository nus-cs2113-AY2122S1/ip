package parser;

import java.util.HashMap;

public enum TokenType {
    TASK_TYPE,
    COMMAND_TYPE,
    TIME_TYPE,
    DESCRIPTION,
    UNDETERMINED;

    private static final HashMap<String, TokenType> TokenStr2Type =
        new HashMap<String, TokenType>() {{
            put("todo", TokenType.TASK_TYPE);
            put("deadline", TokenType.TASK_TYPE);
            put("event", TokenType.TASK_TYPE);

            put("list", TokenType.COMMAND_TYPE);
            put("add", TokenType.COMMAND_TYPE);
            put("done", TokenType.COMMAND_TYPE);
            put("delete", TokenType.COMMAND_TYPE);
            put("find", TokenType.COMMAND_TYPE);
            put("bye", TokenType.COMMAND_TYPE);

            put("jan", TokenType.TIME_TYPE);
            put("january", TokenType.TIME_TYPE);
            put("feb", TokenType.TIME_TYPE);
            put("feburary", TokenType.TIME_TYPE);
            put("mar", TokenType.TIME_TYPE);
            put("march", TokenType.TIME_TYPE);
            put("apr", TokenType.TIME_TYPE);
            put("april", TokenType.TIME_TYPE);
            put("may", TokenType.TIME_TYPE);
            put("jun", TokenType.TIME_TYPE);
            put("june", TokenType.TIME_TYPE);
            put("jul", TokenType.TIME_TYPE);
            put("july", TokenType.TIME_TYPE);
            put("aug", TokenType.TIME_TYPE);
            put("august", TokenType.TIME_TYPE);
            put("sep", TokenType.TIME_TYPE);
            put("september", TokenType.TIME_TYPE);
            put("oct", TokenType.TIME_TYPE);
            put("october", TokenType.TIME_TYPE);
            put("nov", TokenType.TIME_TYPE);
            put("november", TokenType.TIME_TYPE);
            put("dec", TokenType.TIME_TYPE);
            put("december", TokenType.TIME_TYPE);
        }};

    public static TokenType getTokenTypebyStr(String token) {
        if (TokenStr2Type.containsKey(token)) {
            return TokenStr2Type.get(token);
        } else {
            return TokenType.DESCRIPTION;
        }
    }

}
