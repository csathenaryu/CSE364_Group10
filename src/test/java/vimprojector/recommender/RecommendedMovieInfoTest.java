package vimprojector.recommender;

import org.junit.Assert;
import org.junit.Test;

public class RecommendedMovieInfoTest {
    @Test
    public void RecommendedMovieInfoTest() {
        int id = 1;
        float rating = 5;
        int count = 1;
        RecommendedMovieInfo recommendedMovieInfo = new RecommendedMovieInfo(id, rating, count);
        Assert.assertEquals(recommendedMovieInfo.id, id);
        Assert.assertEquals(recommendedMovieInfo.rating, rating, 0.01);
        Assert.assertEquals(recommendedMovieInfo.count, count);
    }


}
