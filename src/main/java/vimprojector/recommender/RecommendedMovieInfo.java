package vimprojector.recommender;

public class RecommendedMovieInfo {
    int id;
    float rating;
    String title;

    RecommendedMovieInfo(int movieID, float averageRating, String movieTitle){
        id = movieID;
        rating = averageRating;
        title = movieTitle;
    }
}
