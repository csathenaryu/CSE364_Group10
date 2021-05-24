package vimprojector.recommender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendedMovieInfoTest {
    @Test
    public void RecommendedMovieInfoTest() {
        int id = 1;
        float rating = 5;
        int count = 1;
        RecommendedMovieInfo recommendedMovieInfo = new RecommendedMovieInfo(id, rating, count);
        assertEquals(recommendedMovieInfo.id, id);
        assertEquals(recommendedMovieInfo.rating, rating, 0.01);
        assertEquals(recommendedMovieInfo.count, count);
    }


}
