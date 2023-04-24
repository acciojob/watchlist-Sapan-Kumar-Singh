package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;



    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }

    public void addDirector(Director director){

           movieRepository.saveDirector(director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        movieRepository.saveMovieDirectorPair(movieName,directorName);

    }

    public Movie getMovieByName(String movieName)  {
        return movieRepository.getByMovieName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getByDirectorName(directorName);

    }





    public List<String> getMovieByDirectorName(String directorName) {
        List<String>movie=movieRepository.getMovieByDirectorName(directorName);
        return movie;
    }

    public List<String> findAllMovie() {
        return movieRepository.findAllMovie();

    }

    public void deleteDirectorByName(String directorName) {
       movieRepository.deleteDirectorByName(directorName);

    }

    public void deleteAllDirector(){
        movieRepository.deleteAllDirector();

    }


}
