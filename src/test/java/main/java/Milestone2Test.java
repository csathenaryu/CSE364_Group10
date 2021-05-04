package main.java;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.Milestone2;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;
import vimprojector.loadingdata.DataFiltering;
import vimprojector.loadingdata.FilePreprocessing;
import vimprojector.parsinginputargs.*;
import vimprojector.recommender.RecommendedMovieInfo;
import vimprojector.recommender.TopRating;

import java.util.ArrayList;
import java.util.HashMap;

public class Milestone2Test {

    @Test
    public void TestmainValidInput() {

        //valid input
        String args[] = {"F", "18", "lawyer", "adventure|animation"};
        Milestone2.main(args);
    }

    @Test
    public void TestmainRareInput() {

        //valid input
        String args[] = {"M", "1", "lawyer", "war"};
        Milestone2.main(args);
    }

    @Test
    public void TestmainNoInput() {

        //valid input
        String args[] = {"", "", "", ""};
        Milestone2.main(args);
    }
}