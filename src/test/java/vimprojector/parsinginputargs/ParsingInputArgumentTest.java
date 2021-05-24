package vimprojector.parsinginputargs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingInputArgumentTest {

    @Test
    public void addProperty() {
        ParsingInputArgument parsingInputArgument = new ParsingInputArgument();
        ArrayList<String> propertyList = new ArrayList<>();
        propertyList.add("read");
        String property = "good";
        parsingInputArgument.addProperty(propertyList, property);
        ArrayList<String> answer = new ArrayList<>();
        answer.add("read");
        answer.add("good");
        assertArrayEquals(propertyList.toArray(new String[0]), answer.toArray(new String[0]));
    }
}