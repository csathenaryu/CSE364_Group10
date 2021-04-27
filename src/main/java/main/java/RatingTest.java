package main.java;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import vimprojector.parsinginputargs.*;
import vimprojector.customdatastructure.*;


public class RatingTest {

    public static void main(String[] args) {

        GetTopRating getTopRating = new GetTopRating();
        //GetTotalRating getTotalRating = new GetTotalRating();

        ArrayList<Integer> recommended_movie = null;
        try {
            recommended_movie = getTopRating.Get_movie_rating("data/ratings.dat", movieList, userList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(recommended_movie);

        //float avgRating = getTotalRating.getTargetRating("data/ratings.dat", movieList, userList);
        //System.out.println(avgRating);
        //System.out.println(String.format("Average rating is %.2f", avgRating));

    }
}


class GetRating {
    int userID;
    int movieID;
    int rating;

    public GetRating(){
        userID = 0;
        movieID = 0;
        rating = 0;
    }

    public void getRatingInfo (String line) {
        String[] parse = Parser.parseByDelimiter(line,"::");
        userID = Integer.parseInt(parse[0]);
        movieID = Integer.parseInt(parse[1]);
        rating = Integer.parseInt(parse[2]);
    }

}

class GetTopRating extends GetRating {
    HashMap<Integer, Float> movie_rating = new HashMap<Integer, Float>();
    HashMap<Integer, Integer> movie_rating_num = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> movie_rating_sum = new HashMap<Integer, Integer>();


    ArrayList<Integer> Get_movie_rating (String fileName, CustomList targetMovieList, CustomList targetUserList) throws FileNotFoundException {

        ReadFile readfile = new ReadFile();
        readfile.filename = fileName;

        readfile.file = readfile.getFile();

        readfile.tryReading();

        readfile.buffer = readfile.GetBuffer();

        while (true) {
            readfile.readLine();
            if (readfile.checkline == 0)
                break;
            getRatingInfo(readfile.line);
            update_movie_rating(movieID, rating);
        }
        ArrayList<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(movie_rating_num.entrySet());

        for (Entry<Integer, Integer> entry : list_entries) {
            get_movie_average(entry.getKey());
        }
        ArrayList<Entry<Integer, Float>> movie_rating_list = sort_movie_average();
        ArrayList<Integer> recommended_movie = get_top_rating(movie_rating_list);

        return recommended_movie;
    }

    void update_movie_rating (int id, int rating) {

        if (movie_rating_num.get(id) == null) {
            movie_rating_num.put(id, 1);
            movie_rating_sum.put(id, rating);
        }
        movie_rating_num.put(id, movie_rating_num.get(id) + 1); // id에 해당하는 num 1 증가
        movie_rating_sum.put(id, movie_rating_sum.get(id) + rating);// id에 해당하는 rating update
    }

    void get_movie_average (Integer id) {
        float count = (float) movie_rating_num.get(id);
        float rating_sum = (float) movie_rating_sum.get(id);
        float average_rating = 0;
        if (count != 0) {
            average_rating = rating_sum / count;
        }
        movie_rating.put(id, average_rating);
    }

    ArrayList<Entry<Integer, Float>>
    sort_movie_average () {

        //movie_rating value 기준으로 내림차순 정렬
        ArrayList<Entry<Integer, Float>> movie_rating_list = new ArrayList<Entry<Integer, Float>>(movie_rating.entrySet());

        // 비교함수 Comparator
        Collections.sort(movie_rating_list, new Comparator<Entry<Integer, Float>>() {
            @Override
            // compare로 값을 비교
            public int compare(Entry<Integer, Float> mov1, Entry<Integer, Float> mov2) {
                // 내림 차순으로 정렬
                return mov2.getValue().compareTo(mov1.getValue());
            }
        });
        System.out.println(movie_rating_list);
        return movie_rating_list;
    }

    ArrayList<Integer>
    get_top_rating (ArrayList<Entry<Integer, Float>> movie_rating_list) {
        //movie_rating_list에서 상위 10개의 id를 recommended (arraylist) 로 받아옴
        int n = 10;
        int size = movie_rating_list.size();
        if (size < 10) {
            n = size;
        }

        ArrayList<Integer> recommended_movie = new ArrayList<>(10);
        for (int i = 0; i < n; i++) {
            recommended_movie.add(movie_rating_list.get(i).getKey());
        }
        //return recommended (arraylist)
        return recommended_movie;
    }

}


class GetTotalRating extends GetRating {


    public float getTargetRating(String fileName, CustomList targetMovieList, CustomList targetUserList) {
        RatingManager ratingManager = new RatingManager();

        ReadFile readfile = new ReadFile();
        readfile.filename = fileName;

        readfile.file = readfile.getFile();

        readfile.tryReading();

        readfile.buffer = readfile.GetBuffer();

        while (true) {
            readfile.readLine();
            if (readfile.checkline == 0)
                break;
            getRatingInfo(readfile.line);
            updateTargetRating(ratingManager, targetMovieList, targetUserList);
        }

        // see also: RatingManager

        float AverageRating = ratingManager.getAverageRating();
        return AverageRating;
    }

    public void updateTargetRating (RatingManager ratingManager, CustomList targetMovieList, CustomList targetUserList) {
        if (targetUserList.getAt(userID) && targetMovieList.getAt(movieID)) {
            ratingManager.update(rating);
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
