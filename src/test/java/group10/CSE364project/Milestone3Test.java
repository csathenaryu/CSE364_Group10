package group10.CSE364project;

import org.junit.Test;

import java.util.ArrayList;

public class Milestone3Test {
    Milestone3 milestone3 = new Milestone3();
    ArrayList<MovieData> movieDataArrayList = new ArrayList<>();

    @Test
    public void test_normal() {
        String title = "Toy Story (1995)";
        int limit = 20;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }
    @Test
    public void Test_step1() {
        String title = "Father of the Bride Part II (1995)";
        int limit = 20;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }

    @Test
    public void Test_step2() {
        String title = "Sabrina (1995)";
        int limit = 20;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }


    @Test
    public void Test_step3() {
        String title = "Toy Story (1995)";
        int limit = 5;
        movieDataArrayList = milestone3.milestone3(title, limit, "src/test/data/testratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }
    @Test
    public void Test_step4() {
        String title = "Toy Story (1995)";
        int limit = 10;
        movieDataArrayList = milestone3.milestone3(title, limit, "src/test/data/testratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }
    @Test
    public void Test_empty_limit() {
        String title = "Father of the Bride Part II (1995)";
        int limit = 0;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }

    @Test
    public void Test_empty_title() {
        String title = "";
        int limit = 10;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }

    @Test
    public void Test_invalid_title() {
        String title = "Toi Stori";
        int limit = 10;
        movieDataArrayList = milestone3.milestone3(title, limit, "data/ratings.dat");
        // System.out.println("TestmainValidInput" + movieDataArrayList.size());

    }
}
