package vimprojector.recommender;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.recommender.RatingCounter;
import java.io.FileNotFoundException;
import java.util.*;


public class TopRating {

    HashMap<Integer, RatingCounter> movieRating;
    ArrayList<HashMap<String, String>> ratingData;
    Bitmap targetMovie;
    Bitmap targetUser;

    public TopRating(ArrayList<HashMap<String, String>> ratData, Bitmap targetMovieList, Bitmap targetUserList){
        movieRating = new HashMap<>();
        ratingData = ratData;
        targetMovie = targetMovieList;
        targetUser = targetUserList;
    }

    public ArrayList<Integer> getTopRating() throws FileNotFoundException {

        for (HashMap<String, String> rat : ratingData){
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateMovieRating(movieID, rating);
        }

        ArrayList<IdAndRating> movieAverageRating = getMovieAverageRating();
        sortMovieAverageRating(movieAverageRating);
        return extractTopMovie(movieAverageRating);
    }

    public void updateMovieRating(int id, int rating) {
        if (movieRating.containsKey(id)){
            RatingCounter ratingCounter = movieRating.get(id);
            ratingCounter.update(rating);
        }
        else{
            RatingCounter ratingCounter = new RatingCounter();
            ratingCounter.update(rating);
            movieRating.put(id, ratingCounter);
        }
    }

    public ArrayList<IdAndRating> getMovieAverageRating() {
        ArrayList<IdAndRating> movieAverageRating = new ArrayList<>();
        for (Map.Entry<Integer, RatingCounter> movieRatingCounter : movieRating.entrySet()){
            IdAndRating idAndRating = new IdAndRating(movieRatingCounter.getKey(), movieRatingCounter.getValue().getAverageRating());
            movieAverageRating.add(idAndRating);
        }
        return movieAverageRating;
    }

    public void sortMovieAverageRating(ArrayList<IdAndRating> movieAverageRating) {

        Comparator<IdAndRating> comparator = new Comparator<IdAndRating>() {
            @Override
            public int compare(IdAndRating o1, IdAndRating o2) {
                if (o1.rating > o2.rating){
                    return -1;
                } else if (o1.rating < o2.rating){
                    return 1;
                } else{
                    return Integer.compare(o1.id, o2.id);
                }
            }
        };

        movieAverageRating.sort(comparator);
        System.out.println(movieAverageRating);
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
