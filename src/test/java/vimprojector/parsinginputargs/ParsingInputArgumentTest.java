package vimprojector.parsinginputargs;

import org.junit.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

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