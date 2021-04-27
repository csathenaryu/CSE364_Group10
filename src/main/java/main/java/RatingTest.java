package main.java;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;


import vimprojector.customdatastructure.*;
import vimprojector.loadingdata.*;
import vimprojector.recommender.RatingCounter;


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

        GetTopRating getTopRating = new GetTopRating();
        //GetTotalRating getTotalRating = new GetTotalRating();

        ArrayList<Integer> recommended_movie = null;
        try {
            recommended_movie = getTopRating.getMovieRating(ratingData, movieList, userList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(recommended_movie);

        //float avgRating = getTotalRating.getTargetRating("data/ratings.dat", movieList, userList);
        //System.out.println(avgRating);
        //System.out.println(String.format("Average rating is %.2f", avgRating));

    }
}

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


class GetTotalRating{

    public float getTargetRating(ArrayList<HashMap<String, String>> ratingData, Bitmap targetMovieList, Bitmap targetUserList) {
        RatingCounter ratingCounter = new RatingCounter();

        for (HashMap<String, String> rat : ratingData){
            int userID = Integer.parseInt(rat.get("UserID"));
            int movieID = Integer.parseInt(rat.get("MovieID"));
            int rating = Integer.parseInt(rat.get("Rating"));
            updateTargetRating(ratingCounter, targetMovieList, targetUserList, movieID, userID, rating);
        }

        // see also: RatingManager
        float AverageRating = ratingCounter.getAverageRating();
        return AverageRating;
    }

    public void updateTargetRating (RatingCounter ratingCounter, Bitmap targetMovieList, Bitmap targetUserList, int movieID, int userID, int rating) {
        if (targetUserList.getAt(userID) && targetMovieList.getAt(movieID)) {
            ratingCounter.update(rating);
        }
    }
}


class ReadFile{

    File file;
    String filename;
    FileReader reader = null;
    BufferedReader buffer;

    String line = "";

    int checkline = 1;

    File getFile(){
        file = new File(filename);
        return file;
    }

    BufferedReader GetBuffer(){
        buffer = new BufferedReader(reader);
        return buffer;
    }

    void tryReading (){
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void readLine(){
        try {
            if ((line = buffer.readLine()) == null)
                checkline = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
