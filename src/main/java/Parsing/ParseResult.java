package Parsing;

/**
 * ENUM corresponding to all valid commands that Duke understands
 */
public enum ParseResult {
    BYE,
    DONE, LIST, DELETE,
    TODO, DEADLINE, EVENT,
    FIND
}
