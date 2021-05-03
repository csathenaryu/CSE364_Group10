package vimprojector.loadingdata;

import org.junit.Test;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static vimprojector.loadingdata.FilePreprocessing.makeHash;

public class FilePreprocessingTest {

    @Test
    public void loadDataFromTEST() { // 빈 array가 진짜로 출력되는지
        //        ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
        String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
        String charset = "ISO-8859-15";
        ArrayList<HashMap<String, String>> a = new ArrayList();
        assertEquals(FilePreprocessing.loadDataFrom("data/user34s.dat", userLabel, charset), a);
        ArrayList<HashMap<String, String>> b = new ArrayList();
        assertNotEquals(FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset), b);

    }

    @Test
    public void loadHashFromTEST() {
        // HashMap<Integer, String> linkHash = FilePreprocessing.loadHashFrom("data/links.dat", linkLabel, "MovieID", "imdbID", charset);
        String[] linkLabel = {"MovieID", "imdbID"};
        String charset = "ISO-8859-15";
        HashMap<Integer, String> a = new HashMap();
        assertEquals(FilePreprocessing.loadHashFrom("data/lindfdks.dat", linkLabel, "MovieID", "imdbID", charset), a);

        HashMap<Integer, String> b = new HashMap();
        assertNotEquals(FilePreprocessing.loadHashFrom("data/links.dat", linkLabel, "MovieID", "imdbID", charset), b);

    }

    @Test
    public void makeHashTEST() {
        String[] dataLabel = {"1", "2", "3"};
        String[] splitData = {"a", "b", "c"};
        HashMap<String, String> a = makeHash(dataLabel, splitData);
        assertEquals("a", a.get("1"));
        assertNotEquals("b", a.get("3"));
    }
}