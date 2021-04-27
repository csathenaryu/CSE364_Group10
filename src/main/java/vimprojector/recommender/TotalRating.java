package vimprojector.recommender;

import vimprojector.customdatastructure.Bitmap;
import java.util.ArrayList;
import java.util.HashMap;

public class TotalRating {
    RatingCounter ratingCounter;
    ArrayList<HashMap<String, String>> ratingData;
    Bitmap targetMovie;
    Bitmap targetUser;

    public TotalRating(ArrayList<HashMap<String, String>> ratData, Bitmap targetMovieList, Bitmap targetUserList){
        ratingCounter = new RatingCounter();
        ratingData = ratData;
        targetMovie = targetMovieList;
        targetUser = targetUserList;
    }

    public float getTotalRating() {

        for (HashMap<String, String> rat : ratingData){
            int userID = Integer.parseInt(rat.get("UserID"));
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateRating(movieID, userID, rating);
        }
        return ratingCounter.getAverageRating();
    }

    public void updateRating(int movieID, int userID, int rating) {
        if (targetMovie.getAt(movieID) && targetUser.getAt(userID)) {
            ratingCounter.update(rating);
        }
    }
}
