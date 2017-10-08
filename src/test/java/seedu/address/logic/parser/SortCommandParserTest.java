package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ATTRIBUTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_MISSING_ATTRIBUTE, SortCommand.MESSAGE_USAGE));
    }

/***
    @Test
    public void parse_validArgs_returnsSortCommand() {
        // test string trim in parser
        SortCommand expectedSortCommand = new SortCommand("name");
        assertParseSuccess(parser, " name ", expectedSortCommand);
    }
***/
}
