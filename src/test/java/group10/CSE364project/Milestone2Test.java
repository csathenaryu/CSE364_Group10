package group10.CSE364project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class Milestone2Test {
    Milestone2 milestone2 = new Milestone2();
    ArrayList<MovieData> movieDataArrayList = new ArrayList<>();

    @Test
    public void TestmainValidInput() {
        String args[] = {"F", "18", "lawyer", "adventure|animation"};
        movieDataArrayList = milestone2.milestone2(args, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());
        for(MovieData movieData: movieDataArrayList) {
            String a = movieData.getGenres();
            assertTrue(a.contains("Adventure") || a.contains("Animation"));
        }
    }

    @Test
    public void TestmainRareInput() {
        String args[] = {"M", "1", "lawyer", "war"};
        movieDataArrayList = milestone2.milestone2(args, "data/ratings.dat");
        // System.out.println("TestmainRareInput" + movieDataArrayList.size());
        for(MovieData movieData: movieDataArrayList) {
            String a = movieData.getGenres();
            assertTrue(a.contains("War"));
        }
    }

    @Test
    public void TestmainNoInput() {
        String args[] = {"", "", "", ""};
        movieDataArrayList = milestone2.milestone2(args, "data/ratings.dat");
        // System.out.println("TestmainNoInput" + movieDataArrayList.size());
        assertEquals(10, movieDataArrayList.size());
    }


    @Test
    public void TestRareAge() {
        String args[] = {"f", "56", "lawyer", ""};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
        // System.out.println("TestRareAge" + movieDataArrayList.size());
        assertEquals(10, movieDataArrayList.size());
    }

    @Test
    public void TestRareGender() {
        String args[] = {"m", "56", "lawyer", ""};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
        // System.out.println("TestRareGender" + movieDataArrayList.size());
        assertEquals(10, movieDataArrayList.size());
    }

    @Test
    public void TestNoGenre() {
        String args[] = {"m", "56", "lawyer", "Thriller"};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
        // System.out.println("TestNoGenre" + movieDataArrayList.size());
        assertEquals(0, movieDataArrayList.size());
    }
    @Test
    public void TestRareGenre() {
        String args[] = {"m", "56", "lawyer", "Animation"};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
        // System.out.println("TestRareGenre" + movieDataArrayList.size());
        for(MovieData movieData: movieDataArrayList) {
            String a = movieData.getGenres();
            assertTrue(a.contains("Animation"));
        }
        assertTrue(10 > movieDataArrayList.size());
    }
    @Test
    public void TestTotalTen() {
        String args[] = {"m", "56", "lawyer", ""};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
        // System.out.println("TestLessthanTen" + movieDataArrayList.size());
        assertTrue(10 == movieDataArrayList.size());
    }
}
