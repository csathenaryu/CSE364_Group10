package vimprojector.recommender;

import org.junit.Assert;
import org.junit.Test;

public class RatingCounterTest {

    @Test
    public void getAverageRating_normal() {
        RatingCounter ratingCounter = new RatingCounter();
        ratingCounter.update(4);
        ratingCounter.update(5);
        ratingCounter.update(6);
        Assert.assertEquals(ratingCounter.getAverageRating(), 5, 0.001);

    }
    /*
    @Test
    public void getAverageRating_empty() {
        RatingCounter ratingCounter = new RatingCounter();
        Assert.assertEquals(ratingCounter.getAverageRating(), 0, 0.001);

    }

     */
}
