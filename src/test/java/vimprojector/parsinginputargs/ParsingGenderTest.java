package vimprojector.parsinginputargs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingGenderTest {

    @Test
    public void parseProperty_M_m() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("M", "\\|");
        String[] answer = {"m"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_m_m() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("m", "\\|");
        String[] answer = {"m"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_F_f() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("F", "\\|");
        String[] answer = {"f"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_f_f() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("f", "\\|");
        String[] answer = {"f"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_MmFf_mf() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("M|m|F|f", "\\|");
        String[] answer = {"m", "f"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_InvalidInput_mf() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("male", "\\|");
        String[] answer = {"f", "m"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_InvalidInputMultiple_mf() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("male|female|mf", "\\|");
        String[] answer = {"f", "m"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_ContainsInvalidInput_mf() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.parseProperty("M|female|male", "\\|");
        String[] answer = {"m"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void getAllProperty() {
        ParsingGender parsingGender = new ParsingGender();
        String[] expected = parsingGender.getAllProperty();
        String[] answer = {"f", "m"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void addProperty() {
    }
}