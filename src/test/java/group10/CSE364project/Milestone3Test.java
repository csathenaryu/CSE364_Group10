package group10.CSE364project;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static vimprojector.parsinginputargs.ArgumentParser.parseByDelimiter;

public class Milestone3Test {
    Milestone3 milestone3 = new Milestone3();
    ArrayList<MovieData> movieDataArrayList = new ArrayList<>();

    @Test
    public void test_normal() {
        String title = "Toy Story (1995)"; // Animation|Children's|Comedy
        int limit = 20;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        assertEquals("Animation|Children's|Comedy", movieDataArrayList.get(0).getGenres());
    }
    @Test
    public void Test_step1() {
        String title = "Father of the Bride Part II (1995)"; // Comedy
        int limit = 20;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());
        //System.out.println(movieDataArrayList.get(0).getGenres());
        assertEquals("Comedy", movieDataArrayList.get(19).getGenres());
    }

    @Test
    public void Test_step2() {
        String title = "Sabrina (1995)"; // Comedy|Romance
        int limit = 20;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        String[] inputArray = {"Comedy", "Romance"};
        String inputValue = "Comedy, Romance";
        String[] a = parseByDelimiter(movieDataArrayList.get(19).getGenres(), "\\|");
    }


    @Test
    public void Test_step3() {
        String title = "Toy Story (1995)"; // Animation|Children's|Comedy
        int limit = 5;
        movieDataArrayList = milestone3.milestone3(title, limit, "src/test/data/testratings.dat");
        String[] inputArray = {"Animation", "Children's", "Comedy"};
        String[] a = parseByDelimiter(movieDataArrayList.get(4).getGenres(), "\\|");
        boolean TF = false;
        for(String i: a) {
            for(String j: inputArray) {
                System.out.println(j);
                if(i.equals(j)) {
                    TF = true;
                    break;
                };
            }
        }
        assertTrue(TF);
    }
    @Test
    public void Test_step4() {
        String title = "Toy Story (1995)"; // // Animation|Children's|Comedy
        int limit = 10;
        movieDataArrayList = milestone3.milestone3(title, limit, "src/test/data/testratings.dat");
        String[] inputArray = {"Animation", "Children's", "Comedy"};
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());
        String[] a = parseByDelimiter(movieDataArrayList.get(9).getGenres(), "\\|");
        boolean TF = false;
        for(String i: a) {
            for(String j: inputArray) {
                System.out.println(j);
                if(i.equals(j)) {
                    TF = true;
                    break;
                };
            }
        }
        assertFalse(TF);
    }
    @Test
    public void Test_empty_limit() {
        String title = "Father of the Bride Part II (1995)"; // Comedy
        int limit = 0;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());
        assertEquals(10, movieDataArrayList.size());
    }

    @Test
    public void Test_empty_title() {
        String title = "";
        int limit = 10;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());
        assertEquals("Raiders of the Lost Ark (1981)", movieDataArrayList.get(9).getTitle());
    }

    @Test
    public void Test_invalid_title() {
        String title = "Toi Stori";
        int limit = 10;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        assertEquals("Raiders of the Lost Ark (1981)", movieDataArrayList.get(9).getTitle());

    }

    @Test
    public void Test_negative_limit() {
        String title = "Father of the Bride Part II (1995)";
        int limit = -4;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());
        assertEquals(10, movieDataArrayList.size());
    }
}
