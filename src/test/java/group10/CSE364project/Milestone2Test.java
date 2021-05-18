package group10.CSE364project;

import org.junit.Test;

import java.util.ArrayList;

public class Milestone2Test {
    Milestone2 milestone2 = new Milestone2();
    ArrayList<MovieData> movieDataArrayList = new ArrayList<>();

    @Test
    public void TestmainValidInput() {
        String args[] = {"F", "18", "lawyer", "adventure|animation"};
        movieDataArrayList = milestone2.milestone2(args, "data/ratings.dat");

    }

    @Test
    public void TestmainRareInput() {
        String args[] = {"M", "1", "lawyer", "war"};
        movieDataArrayList = milestone2.milestone2(args, "data/ratings.dat");
    }

    @Test
    public void TestmainNoInput() {
        String args[] = {"", "", "", ""};
        movieDataArrayList = milestone2.milestone2(args, "data/ratings.dat");
    }


    @Test
    public void TestRareAge() {
        String args[] = {"f", "56", "lawyer", ""};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
    }

    @Test
    public void TestRareGender() {
        String args[] = {"m", "56", "lawyer", ""};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
    }

    @Test
    public void TestRareGenre() {
        String args[] = {"m", "56", "lawyer", "Thriller"};
        movieDataArrayList = milestone2.milestone2(args, "src/test/data/testratings.dat");
    }
}
