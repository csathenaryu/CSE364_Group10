package main.java;

import org.junit.Test;

public class Milestone2Test {

    String userDataPath = "data/users.dat";
    String ratingDataPath = "data/ratings.dat";
    String linkDataPath = "data/links.dat";
    String movieDataPath = "data/movies.dat";

    @Test
    public void main_InputNoArgument() {
        String[] args = {"", "", "", ""};
        Milestone2.main(args);
    }

    @Test
    public void main_InputAllArgument() {
        String[] args = {"m", "1", "lawyer", "animation"};
        Milestone2.main(args);
    }

    @Test
    public void main_InputSpecificCase() {
        String[] args = {"m", "40", "doctor", "horror"};
        Milestone2.main(args);
    }

    @Test
    public void milestone2_InputSpecificCase1(){
        movieDataPath = "src/test/data/testMovie.dat";
        String[] args = {"f", "40", "", "romance"};
        Milestone2.milestone2(args, userDataPath, movieDataPath, ratingDataPath, linkDataPath);
    }

    @Test
    public void milestone2_InputSpecificCase2(){
        movieDataPath = "src/test/data/testMovie.dat";
        String[] args = {"m", "40", "doctor", "horror"};
        Milestone2.milestone2(args, userDataPath, movieDataPath, ratingDataPath, linkDataPath);
    }

    @Test
    public void milestone2_InputSpecificCase3(){
        movieDataPath = "src/test/data/testMovie.dat";
        String[] args = {"m", "17", "doctor", "horror"};
        Milestone2.milestone2(args, userDataPath, movieDataPath, ratingDataPath, linkDataPath);
    }

    @Test
    public void milestone2_InputSpecificCase4(){
        userDataPath = "src/test/data/testUser.dat";
        movieDataPath = "src/test/data/testMovie.dat";
        String[] args = {"m", "17", "doctor", "romance"};
        Milestone2.milestone2(args, userDataPath, movieDataPath, ratingDataPath, linkDataPath);
    }

}
