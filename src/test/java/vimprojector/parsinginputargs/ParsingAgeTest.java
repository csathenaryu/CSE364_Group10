package vimprojector.parsinginputargs;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingAgeTest {

    @Test
    public void parseProperty_BoundaryAge_MappedAge() {
        String[] inputProperty = {"0", "17", "18", "24", "25", "34", "35", "44", "45", "49", "50", "55", "56", "100"};
        String[] answers = {"1", "1", "18", "18", "25", "25", "35", "35", "45", "45", "50", "50", "56", "56"};
        int size = inputProperty.length;
        for(int i=0; i<size; i++){
            ParsingAge parsingAge = new ParsingAge();
            String[] expected = parsingAge.parseProperty(inputProperty[i], "\\|");
            String[] answer = {answers[i]};
            assertArrayEquals(expected, answer);
        }
    }

    @Test
    public void parseProperty_NormalAge_MappedAge() {
        String[] inputProperty = {"1", "3", "20", "23", "27", "30", "36", "42", "47", "48", "51", "53", "57", "2500"};
        String[] answers = {"1", "1", "18", "18", "25", "25", "35", "35", "45", "45", "50", "50", "56", "56"};
        int size = inputProperty.length;
        for(int i=0; i<size; i++){
            ParsingAge parsingAge = new ParsingAge();
            String[] expected = parsingAge.parseProperty(inputProperty[i], "\\|");
            String[] answer = {answers[i]};
            assertArrayEquals(expected, answer);
        }
    }

    @Test
    public void parseProperty_MultipleAge_MappedAge() {
        ParsingAge parsingAge = new ParsingAge();
        String[] expected = parsingAge.parseProperty("3|36|70", "\\|");
        String[] answer = {"1", "35", "56"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_RepeatedAge_MappedAge() {
        ParsingAge parsingAge = new ParsingAge();
        String[] expected = parsingAge.parseProperty("3|3", "\\|");
        String[] answer = {"1"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_NegativeAge_All() {
        ParsingAge parsingAge = new ParsingAge();
        String[] expected = parsingAge.parseProperty("-1", "\\|");
        String[] answer = {"1", "25", "50", "18", "45", "56", "35"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_FloatAge_All() {
        ParsingAge parsingAge = new ParsingAge();
        String[] expected = parsingAge.parseProperty("1.2", "\\|");
        String[] answer = {"1", "25", "50", "18", "45", "56", "35"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_Normal_All() {
        ParsingAge parsingAge = new ParsingAge();
        String[] expected = parsingAge.parseProperty("25.3|-30|3|3|47", "\\|");
        String[] answer = {"1", "45"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void getAllProperty() {
        ParsingAge parsingAge = new ParsingAge();
        String[] expected = parsingAge.getAllProperty();
        String[] answer = {"1", "25", "50", "18", "45", "56", "35"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void addProperty() {
    }
}