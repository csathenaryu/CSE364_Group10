package vimprojector.parsinginputargs;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingOccupationTest {

    @Test
    public void parseProperty_academic_1() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("academic", "\\|");
        String[] answer = {"1"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_ACADEMIC_1() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("ACADEMIC", "\\|");
        String[] answer = {"1"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_Artist_2() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("artist", "\\|");
        String[] answer = {"2"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_Other_0() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("dentist", "\\|");
        String[] answer = {"0"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_RepeatedOccupation_1() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("academic|academic", "\\|");
        String[] answer = {"1"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_RepeatedOccupation_0() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("dentist|dentist", "\\|");
        String[] answer = {"0"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_SameOccupationField_1() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("academic|educator", "\\|");
        String[] answer = {"1"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_SameOccupationField_0() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("dentist|dancer", "\\|");
        String[] answer = {"0"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_MultipleOccupation_13() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("academic|clerical", "\\|");
        String[] answer = {"1", "3"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_MultipleIncludeOther_03() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("clerical|dentist", "\\|");
        String[] answer = {"3", "0"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_MultipleAndRepeatedProperties_10310() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("artist|artist|dentist|dancer|clerical|k-12student", "\\|");
        String[] answer = {"2", "0", "3", "10"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_NoProperty_0() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.parseProperty("", "\\|");
        String[] answer = {"0"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void getAllProperty() {
        ParsingOccupation parsingOccupation = new ParsingOccupation();
        String[] expected = parsingOccupation.getAllProperty();
        String[] answer = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    }
}