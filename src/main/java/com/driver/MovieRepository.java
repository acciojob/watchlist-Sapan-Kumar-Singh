package com.driver;

import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {
    HashMap<String,Movie>movieData=new HashMap<>();
    HashMap<String,Director>directorData=new HashMap<>();
    HashMap<String,List<String>>movieDirectorPairData=new HashMap<>();



    public void saveMovie(Movie movie) {
        movieData.put(movie.getName(),movie);
    }

    public void saveDirector(Director director) {
        directorData.put(director.getName(), director);

    }

    public void saveMovieDirectorPair(String movieName, String directorName) {
        if(movieDirectorPairData.containsKey(directorName)){
            List<String>temp=movieDirectorPairData.get(directorName);
            temp.add(movieName);
            movieDirectorPairData.put(directorName,temp);
        }
        else{
            List<String>newList=new ArrayList<>();
            newList.add(movieName);
            movieDirectorPairData.put(directorName,newList);
        }

    }

    public Movie getByMovieName(String movieName) {
            return movieData.get(movieName);
    }


    public Director getByDirectorName(String directorName) {
            return directorData.get(directorName);

    }


    public List<String>getMovieByDirectorName(String directorName) {
            return movieDirectorPairData.get(directorName);
    }

    public List<String> findAllMovie() {
        List<String>res=new ArrayList<>();
        for(String key : movieData.keySet()){
            res.add(key);
        }
        return res;
    }

    public void deleteDirectorByName(String directorName) {
        if(directorData.containsKey(directorName)){
            directorData.remove(directorName);
           List<String>movie=movieDirectorPairData.get(directorName);
           for(String movieName : movie){
               movieData.remove(movieName);
           }
          movieDirectorPairData.remove(directorName);
        }
    }

    public void deleteAllDirector() {
          for(String directorName : directorData.keySet()){
              directorData.remove(directorName);
              List<String>movies=movieDirectorPairData.get(directorName);
              for (String movie : movies){
                  movieData.remove(movie);
              }
              movieDirectorPairData.remove(directorName);
          }

    }


}
