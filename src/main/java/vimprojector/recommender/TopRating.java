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

    ArrayList<Integer> getTopRating() throws FileNotFoundException {

        for (HashMap<String, String> rat : ratingData){
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateMovieRating(movieID, rating);
        }

        ArrayList<Map.Entry<Integer, Integer>> listEntries = new ArrayList<Map.Entry<Integer, Integer>>(movieRatingNum.entrySet());

        for (Map.Entry<Integer, Integer> entry : listEntries) {
            getMovieAverage(entry.getKey());
        }
        ArrayList<Map.Entry<Integer, Float>> movieRatingList = sortMovieAverage();
        ArrayList<Integer> recommendedMovie = getTopRating(movieRatingList);

        return recommendedMovie;
    }

    void updateMovieRating(int id, int rating) {
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

    void getMovieAverage(Integer id) {
        float count = (float) movieRatingNum.get(id);
        float ratingSum = (float) movieRatingSum.get(id);
        float averageRating = 0;
        if (count != 0) {
            averageRating = ratingSum / count;
        }
        movieRating.put(id, averageRating);
    }

    ArrayList<Map.Entry<Integer, Float>> sortMovieAverage() {

        //movie_rating value 기준으로 내림차순 정렬
        ArrayList<Map.Entry<Integer, Float>> movieRatingList = new ArrayList<Map.Entry<Integer, Float>>(movieRating.entrySet());

        // 비교함수 Comparator
        Collections.sort(movieRatingList, new Comparator<Map.Entry<Integer, Float>>() {
            @Override
            // compare로 값을 비교
            public int compare(Map.Entry<Integer, Float> mov1, Map.Entry<Integer, Float> mov2) {
                // 내림 차순으로 정렬
                return mov2.getValue().compareTo(mov1.getValue());
            }
        });
        System.out.println(movieRatingList);
        return movieRatingList;
    }

    ArrayList<Integer> getTopRating(ArrayList<Map.Entry<Integer, Float>> movieRatingList) {
        //movie_rating_list 에서 상위 10개의 id를 recommended (arraylist) 로 받아옴
        int n = Math.min(10, movieRatingList.size());

        ArrayList<Integer> recommendedMovie = new ArrayList<>(10);
        for (int i = 0; i < n; i++) {
            recommendedMovie.add(movieRatingList.get(i).getKey());
        }
        //return recommended (arraylist)
        return recommendedMovie;
    }
}
