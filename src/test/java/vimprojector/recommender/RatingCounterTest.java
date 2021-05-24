package vimprojector.recommender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RatingCounterTest {

    @Test
    public void getAverageRating_normal() {
        RatingCounter ratingCounter = new RatingCounter();
        ratingCounter.update(4);
        ratingCounter.update(5);
        ratingCounter.update(6);
        assertEquals(ratingCounter.getAverageRating(), 5, 0.001);

    }
    /*
    @Test
    public void getAverageRating_empty() {
        RatingCounter ratingCounter = new RatingCounter();
        Assert.assertEquals(ratingCounter.getAverageRating(), 0, 0.001);

    }

     */
}
