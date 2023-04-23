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

    public Optional<Movie> getByMovieNamess(String movieName) {
        if(movieData.containsKey(movieName)){
            return Optional.of(movieData.get(movieName));
        }
        return Optional.empty();
    }

    public Boolean addMoviess(Movie movie) {
        movieData.put(movie.getName(),movie);
        return true;
    }

    public boolean addDirectorss(Director director) {
        directorData.put(director.getName(), director);
        return true;
    }

    public Optional<Director> getByDirectorNamess(String directorName) {
        if(directorData.containsKey(directorName)==false){
            return Optional.empty();
        }
        return Optional.of(directorData.get(directorName));
    }

    public boolean addMovieDirectorPairss(String movieName, String directorName) {
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
        return true;
    }

    public Optional<List<String>> getMovieByDirectorNamess(String directorName) {

        if(movieDirectorPairData.containsKey(directorName)){
            return Optional.of(movieDirectorPairData.get(directorName));
        }
        return Optional.empty();
    }

    public Optional<List<String>> findAllMoviess() {
        List<String>res=new ArrayList<>();
        for(String key : movieData.keySet()){
            res.add(key);
        }
        return Optional.of(res);
    }

    public Optional<Boolean> deleteDirectorByNamess(String directorName) {
        if(directorData.containsKey(directorName)){
            directorData.remove(directorName);
            for(String dirName : movieDirectorPairData.keySet()){
                List<String>movielist=movieDirectorPairData.get(dirName);
                for( String movie : movielist){
                    movieData.remove(movie);
                }
                movieDirectorPairData.remove(dirName);
            }

            Optional.of(true);
        }
         return Optional.empty();
    }

    public Optional<Boolean> deleteAllDirectorsss() {
        if(directorData.size()==0)
            return Optional.empty();
          for(String directorName : directorData.keySet()){
              directorData.remove(directorName);
              List<String>movies=movieDirectorPairData.get(directorName);
              for (String movie : movies){
                  movieData.remove(movie);
              }
              movieDirectorPairData.remove(directorName);
          }
          return  Optional.of(true);
    }


}
