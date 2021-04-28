package vimprojector.recommender;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.recommender.RatingCounter;
import java.io.FileNotFoundException;
import java.util.*;


public class TopRating {

    HashMap<Integer, MovieRatingCounter> movieRating;
    ArrayList<HashMap<String, String>> ratingData;
    Bitmap targetMovie;
    Bitmap targetUser;

    public TopRating(ArrayList<HashMap<String, String>> ratData, Bitmap targetMovieList, Bitmap targetUserList){
        movieRating = new HashMap<>();
        ratingData = ratData;
        targetMovie = targetMovieList;
        targetUser = targetUserList;
    }

    public ArrayList<Integer> getTopRating() {

        for (HashMap<String, String> rat : ratingData){
            String movieTitle = rat.get("Title");
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int userID = Integer.parseInt(rat.get("UserID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateMovieRating(movieTitle, movieID, userID, rating);
        }

        ArrayList<IdAndRating> movieAverageRating = getMovieAverageRating();
        sortMovieAverageRating(movieAverageRating);
        return extractTopMovie(movieAverageRating);
    }

    public void updateMovieRating(String movieTitle, int movieID, int userID, int rating) {
        if(targetMovie.getAt(movieID) && targetUser.getAt(userID)){
            if (movieRating.containsKey(movieID)){
                MovieRatingCounter ratingCounter = movieRating.get(movieID);
                ratingCounter.update(rating);
            }
            else{
                MovieRatingCounter ratingCounter = new MovieRatingCounter(movieTitle);
                ratingCounter.update(rating);
                movieRating.put(movieID, ratingCounter);
            }
        }
    }

    public ArrayList<IdAndRating> getMovieAverageRating() {
        ArrayList<IdAndRating> movieAverageRating = new ArrayList<>();
        for (Map.Entry<Integer, MovieRatingCounter> movieRatingCounter : movieRating.entrySet()){
            IdAndRating idAndRating = new IdAndRating(movieRatingCounter.getKey(), movieRatingCounter.getValue().getAverageRating());
            movieAverageRating.add(idAndRating);
        }
        return movieAverageRating;
    }

    public void sortMovieAverageRating(ArrayList<IdAndRating> movieAverageRating) {

        Comparator<IdAndRating> comparator = (o1, o2) -> {
            if (o1.rating > o2.rating){
                return -1;
            } else if (o1.rating < o2.rating){
                return 1;
            } else{
                return Integer.compare(o1.id, o2.id);
            }
        };

        movieAverageRating.sort(comparator);
    }

    public ArrayList<Integer> extractTopMovie(ArrayList<IdAndRating> movieRatingList) {
        int n = Math.min(10, movieRatingList.size());
        ArrayList<Integer> recommendedMovie = new ArrayList<>(10);
        for (int i = 0; i < n; i++) {
            recommendedMovie.add(movieRatingList.get(i).id);
        }
        return recommendedMovie;
    }
}

class IdAndRating{
    int id;
    float rating;

    IdAndRating(int movieID, float averageRating){
        id = movieID;
        rating = averageRating;
    }
}


class MovieRatingCounter extends RatingCounter{
    String title;

    MovieRatingCounter(String movieTitle){
        super();
        title = movieTitle;
    }
}
