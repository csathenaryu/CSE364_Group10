package vimprojector.recommender;

public class RecommendedMovieInfo {
    public int id;
    public float rating;

    RecommendedMovieInfo(int movieID, float averageRating){
        id = movieID;
        rating = averageRating;
    }
}
