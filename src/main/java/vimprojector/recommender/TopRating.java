package vimprojector.recommender;

import vimprojector.customdatastructure.Bitmap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class TopRating {

    HashMap<Integer, RatingCounter> movieRating;
    ArrayList<HashMap<String, String>> ratingData;
    Bitmap targetMovie;
    Bitmap targetUser;
    int limit;

    public TopRating(ArrayList<HashMap<String, String>> ratData, Bitmap targetMovieList, Bitmap targetUserList, int limit){
        movieRating = new HashMap<>();
        ratingData = ratData;
        targetMovie = targetMovieList;
        targetUser = targetUserList;
        this.limit = limit;
    }

    public ArrayList<RecommendedMovieInfo> getTopRating() {

        for (HashMap<String, String> rat : ratingData){
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int userID = Integer.parseInt(rat.get("UserID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateMovieRating(movieID, userID, rating);
        }

        ArrayList<RecommendedMovieInfo> movieAverageRating = getMovieAverageRating();

        sortMovieAverageRating(movieAverageRating);
        return extractTopMovie(movieAverageRating);
    }

    public void updateMovieRating(int movieID, int userID, int rating) {
        if(targetMovie.getAt(movieID) && targetUser.getAt(userID)){
            if (movieRating.containsKey(movieID)){
                RatingCounter ratingCounter = movieRating.get(movieID);
                ratingCounter.update(rating);
            }
            else{
                RatingCounter ratingCounter = new RatingCounter();
                ratingCounter.update(rating);
                movieRating.put(movieID, ratingCounter);
            }
        }
    }

    public ArrayList<RecommendedMovieInfo> getMovieAverageRating() {
        ArrayList<RecommendedMovieInfo> movieAverageRating = new ArrayList<>();
        for (Map.Entry<Integer, RatingCounter> movieRatingCounter : movieRating.entrySet()){
            RecommendedMovieInfo recommendedMovieInfo = new RecommendedMovieInfo(movieRatingCounter.getKey(), movieRatingCounter.getValue().getAverageRating(), movieRatingCounter.getValue().count);
            movieAverageRating.add(recommendedMovieInfo);
        }
        return movieAverageRating;
    }

    public void sortMovieAverageRating(ArrayList<RecommendedMovieInfo> movieAverageRating) {
/*
        Comparator<RecommendedMovieInfo> comparator = (o1, o2) -> {
            if (o1.rating > o2.rating){
                return -1;
            } else if (o1.rating < o2.rating){
                return 1;
            } else{
                return Integer.compare(o1.id, o2.id);
            }
        };
 */
        Comparator<RecommendedMovieInfo> comparator = (o1, o2) -> {
            if (o1.rating > o2.rating){
                return -1;
            } else if (o1.rating < o2.rating){
                return 1;
            } else{
                return Integer.compare(o2.count, o1.count);
            }
        };
        movieAverageRating.sort(comparator);
        /*
        for (int i = 0; i < movieAverageRating.size(); i++)
        {
            System.out.println(movieAverageRating.get(i).rating + " " + movieAverageRating.get(i).count + " " + movieAverageRating.get(i).id);
        }

         */
    }
    public ArrayList<RecommendedMovieInfo> extractTopMovie(ArrayList<RecommendedMovieInfo> movieRatingList) {
        ArrayList<RecommendedMovieInfo> recommendedMovie = new ArrayList<>(limit);



        int size = (int) (movieRatingList.size() * 0.01);
        int i = 0;
        while (recommendedMovie.size() < limit && i < movieRatingList.size() - 1) {
            if (movieRatingList.get(i).rating >= 4 && movieRatingList.get(i).count >= size) {
                recommendedMovie.add(movieRatingList.get(i));
            }
            i++;
        }
        return recommendedMovie;

    }
/*
    public ArrayList<RecommendedMovieInfo> extractTopMovie(ArrayList<RecommendedMovieInfo> movieRatingList, int limits) {
        ArrayList<RecommendedMovieInfo> recommendedMovie = new ArrayList<>(10);

        int size = (int) (movieRatingList.size() * 0.01);
        int i = 0;
        while (recommendedMovie.size() < limits && i < movieRatingList.size() - 1) {
            if (movieRatingList.get(i).rating >= 4 && movieRatingList.get(i).count >= size) {
                recommendedMovie.add(movieRatingList.get(i));
            }
            i++;
        }
        return recommendedMovie;
    }

 */
}