package vimprojector.loadingdata;

import group10.CSE364project.model.Link;
import group10.CSE364project.model.Movie;
import group10.CSE364project.model.Rating;
import group10.CSE364project.model.User;
import group10.CSE364project.repository.LinkRepository;
import group10.CSE364project.repository.MovieRepository;
import group10.CSE364project.repository.RatingRepository;
import group10.CSE364project.repository.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import vimprojector.customdatastructure.StaticFieldList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilePreprocessing {
    // file 이 존재하지 않는 경우 empty arrayList 가 반환됨
/*
    public static ArrayList<HashMap<String, String>> loadDataFrom(String dataName, String[] dataLabel, MongoRepository mongoRepository){
        // try 문 안으로 넣어야 할까?
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);
        switch(dataName) {
            case "user":
                fromUserRepository(staticFieldList, (UserRepository) mongoRepository);
            case "movie":
                fromMovieRepository(staticFieldList, mongoRepository);
            case "rating":
                fromRatingRepository(staticFieldList, mongoRepository);

// repository 변환시 문제 생김
        }

        return staticFieldList.getLoadedData();
    }


 */

    public static ArrayList<HashMap<String, String>> fromUserRepository(String[] dataLabel, UserRepository userRepository) {
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);

        int count = (int) userRepository.count();
        List<User> list = userRepository.findAll();

        for (int i = 0; i < count; i++) {
            User user = list.get(i);

            String[] splitData = new String[]{String.valueOf(user.getUserId()), user.getGender(),
                    user.getAge(), user.getOccupation(), user.getZipCode()};
            // String splitData[] = user.getUserInformation();
            staticFieldList.push(splitData);
        }
        return staticFieldList.getLoadedData();
    }

    public static ArrayList<HashMap<String, String>> fromMovieRepository(String[] dataLabel, MovieRepository movieRepository) {
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);

        int count = (int) movieRepository.count();
        List<Movie> list = movieRepository.findAll();

        for (int i = 0; i < count; i++) {
            Movie movie = list.get(i);

            String[] splitData = new String[]{String.valueOf(movie.getMovieId()), movie.getTitle(), movie.getGenres()};
            // String splitData[] = movie.getMovieInformation();
            staticFieldList.push(splitData);
        }
        return staticFieldList.getLoadedData();
    }

    public static ArrayList<HashMap<String, String>> fromRatingRepository(String[] dataLabel, RatingRepository ratingRepository) {
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);

        int count = (int) ratingRepository.count();
        List<Rating> list = ratingRepository.findAll();

        for (int i = 0; i < count; i++) {
            Rating rating = list.get(i);

            String[] splitData = new String[]{
                    String.valueOf(rating.getUserId()), String.valueOf(rating.getMovieId()),
                    String.valueOf(rating.getRating()), rating.getTimestamp()};
            //String splitData[] = rating.getRatingInformation();
            staticFieldList.push(splitData);
        }
        return staticFieldList.getLoadedData();
    }


    public static ArrayList<HashMap<String, String>> fromLinkRepository(String[] dataLabel, LinkRepository linkRepository) {
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);

        int count = (int) linkRepository.count();
        List<Link> list = linkRepository.findAll();

        for (int i = 0; i < count; i++) {
            Link link = list.get(i);
            String[] splitData = new String[]{String.valueOf(link.getMovieId()), link.getImdbId()};


            // String splitData[] = link.getLinkInformation();
            staticFieldList.push(splitData);
        }
        return staticFieldList.getLoadedData();
    }
/*

    public static ArrayList<HashMap<String, String>> loadDataFrom(String fileName, String[] dataLabel, String charset){
        // try 문 안으로 넣어야 할까?
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
            String dataLine;
            while ((dataLine = bufReader.readLine()) != null) {
                String[] splitData = dataLine.split("::");
                staticFieldList.push(splitData);
            }
            bufReader.close();
        } catch(Exception e) {
            System.out.printf("There is no such file: %s%n", fileName);
        }
        return staticFieldList.getLoadedData();
    }
    public static HashMap<Integer, String> intStringloadHashFrom(String fileName, String[] dataLabel, String key, String value, String charset){
        // try 문 안으로 넣어야 할까?
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
            String dataLine;
            while ((dataLine = bufReader.readLine()) != null) {
                String[] splitData = dataLine.split("::");
                HashMap<String, String> hash = makeHash(dataLabel, splitData);
                hashMap.put(Integer.parseInt(hash.get(key)), hash.get(value));
            }
            bufReader.close();
        } catch(Exception e) {
            System.out.printf("There is no such file: %s%n", fileName);
        }
        return hashMap;
    }

    public static HashMap<String, String> stringStringloadHashFrom(String fileName, String[] dataLabel, String key, String value, String charset){
        // try 문 안으로 넣어야 할까?
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
            String dataLine;
            while ((dataLine = bufReader.readLine()) != null) {
                String[] splitData = dataLine.split("::");
                HashMap<String, String> hash = makeHash(dataLabel, splitData);
                hashMap.put(hash.get(key), hash.get(value));
            }
            bufReader.close();
        } catch(Exception e) {
            System.out.printf("There is no such file: %s%n", fileName);
        }
        return hashMap;
    }

    public static HashMap<String, String> makeHash(String[] dataLabel, String[] splitData){
        HashMap<String, String> hashMap = new HashMap<>();
        int size = dataLabel.length;
        for(int i=0; i<size; i++){
            hashMap.put(dataLabel[i], splitData[i]);
        }
        return hashMap;
    }

 */
}
