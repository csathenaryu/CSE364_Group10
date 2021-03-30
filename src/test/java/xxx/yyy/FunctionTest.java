package xxx.yyy;

import org.junit.Test;

import static org.junit.Assert.*;
import static xxx.yyy.App.path_of_movies;

public class FunctionTest extends Function {

    @Test
    public void testMakeTargetTable() {
        String[] inputvalue = {"Horror", "Comedy"};
        Function func = new Function();
        CustomList a = func.makeTargetTable(path_of_movies, inputvalue, 2);
        org.junit.Assert.assertEquals("Results", false, a.dataList.get(12));
    }
}