package vimprojector.parsinginputargs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentParserTest {

    @Test
    void parseByDelimiter_OrDelimiter_True() {
        String[] actual = {"a", "b", "c"};
        String[] result = ArgumentParser.parseByDelimiter("a|b|c", "\\|");
        assertArrayEquals(result, actual);
    }

    @Test
    void parseByDelimiter_ColonDelimiter_True() {
        String[] actual = {"a", "b", "c"};
        String[] result = ArgumentParser.parseByDelimiter("a::b::c", "::");
        assertArrayEquals(result, actual);
    }
}