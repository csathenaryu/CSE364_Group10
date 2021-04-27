package main.java;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;


import vimprojector.customdatastructure.*;
import vimprojector.loadingdata.*;
import vimprojector.recommender.*;


public class RatingTest {

    public static void main(String[] args) {


        // Data Label
        // rating: UserID::MovieID::Rating::Timestamp
        String[] ratingLabel = {"UserID", "MovieID", "Rating", "Timestamp"};
        // user: UserID::Gender::Age::Occupation::Zip-code
        String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
        // movie: MovieID::Title::Genres
        String[] movieLabel = {"MovieID", "Title", "Genres"};


        // Load Data
        String charset = "ISO-8859-15";
        ArrayList<HashMap<String, String>> movieData = FilePreprocessing.loadDataFrom("data/movies.dat", movieLabel, charset);
        ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
        ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.loadDataFrom("data/ratings.dat", ratingLabel, charset);


        // Target Property
        String[] occupationProperty = {"15"};
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        String[] genresProperty = {"action"};
        OneToMany genresTargetProperty = new OneToMany("Genres", genresProperty);

        OneToMany[] userFilteringCriteria = {occupationTargetProperty};
        OneToMany[] movieFilteringCriteria = {genresTargetProperty};

        Bitmap filteredUser = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
        Bitmap filteredMovie = DataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
        filteredUser.print();
        filteredMovie.print();


        // extract top 10 movie
        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<Integer> recommendedMovie = topRating.getTopRating();
        System.out.println(recommendedMovie);


        // Top 10
        /*GetTopRating getTopRating = new GetTopRating();

        ArrayList<Integer> recommendedMovie = null;
        try {
            recommendedMovie = getTopRating.getMovieRating(ratingData, filteredMovie, filteredUser);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(recommendedMovie);*/

        //GetTotalRating getTotalRating = new GetTotalRating();
        //float avgRating = getTotalRating.getTargetRating("data/ratings.dat", movieList, userList);
        //System.out.println(avgRating);
        //System.out.println(String.format("Average rating is %.2f", avgRating));

    }
}
/*

class GetTopRating {
    HashMap<Integer, Float> movieRating = new HashMap<Integer, Float>();
    HashMap<Integer, Integer> movieRatingNum = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> movieRatingSum = new HashMap<Integer, Integer>();


    ArrayList<Integer> getMovieRating(ArrayList<HashMap<String, String>> ratingData, Bitmap targetMovieList, Bitmap targetUserList) throws FileNotFoundException {

        for (HashMap<String, String> rat : ratingData){
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateMovieRating(movieID, rating);
        }

        ArrayList<Entry<Integer, Integer>> listEntries = new ArrayList<Entry<Integer, Integer>>(movieRatingNum.entrySet());

        for (Entry<Integer, Integer> entry : listEntries) {
            getMovieAverage(entry.getKey());
        }
        ArrayList<Entry<Integer, Float>> movieRatingList = sortMovieAverage();
        ArrayList<Integer> recommendedMovie = getTopRating(movieRatingList);

        return recommendedMovie;
    }

    void updateMovieRating(int id, int rating) {

        if (movieRatingNum.get(id) == null) {
            movieRatingNum.put(id, 1);
            movieRatingSum.put(id, rating);
        }
        movieRatingNum.put(id, movieRatingNum.get(id) + 1); // id에 해당하는 num 1 증가
        movieRatingSum.put(id, movieRatingSum.get(id) + rating);// id에 해당하는 rating update
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

    ArrayList<Entry<Integer, Float>> sortMovieAverage() {

        //movie_rating value 기준으로 내림차순 정렬
        ArrayList<Entry<Integer, Float>> movieRatingList = new ArrayList<Entry<Integer, Float>>(movieRating.entrySet());

        // 비교함수 Comparator
        Collections.sort(movieRatingList, new Comparator<Entry<Integer, Float>>() {
            @Override
            // compare로 값을 비교
            public int compare(Entry<Integer, Float> mov1, Entry<Integer, Float> mov2) {
                // 내림 차순으로 정렬
                return mov2.getValue().compareTo(mov1.getValue());
            }
        });
        System.out.println(movieRatingList);
        return movieRatingList;
    }

    ArrayList<Integer>
    getTopRating(ArrayList<Entry<Integer, Float>> movieRatingList) {
        //movie_rating_list에서 상위 10개의 id를 recommended (arraylist) 로 받아옴
        int n = 10;
        int size = movieRatingList.size();
        if (size < 10) {
            n = size;
        }

        ArrayList<Integer> recommendedMovie = new ArrayList<>(10);
        for (int i = 0; i < n; i++) {
            recommendedMovie.add(movieRatingList.get(i).getKey());
        }
        //return recommended (arraylist)
        return recommendedMovie;
    }

}

*/
