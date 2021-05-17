package vimprojector.recommender;

public class RecommendedMovieInfo {
    public int id;
    public float rating;
    public int count;

    RecommendedMovieInfo(int movieID, float averageRating, int countRating){
        id = movieID;
        rating = averageRating;
        count = countRating;
    }
}