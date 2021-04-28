package vimprojector.recommender;

public class RecommendedMovieInfo {
    public int id;
    public float rating;
    public String title;

    RecommendedMovieInfo(int movieID, float averageRating, String movieTitle){
        id = movieID;
        rating = averageRating;
        title = movieTitle;
    }
}
