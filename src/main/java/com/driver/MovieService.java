package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;



    public Boolean addMovies(Movie movie) throws MovieAlreadyExistsException {
        Optional<Movie>movieOpt=movieRepository.getByMovieNamess(movie.getName());
        if(movieOpt.isPresent()){
            throw new MovieAlreadyExistsException(movie.getName());
        }
        return movieRepository.addMoviess(movie);
    }

    public boolean addDirectors(Director director) throws DirectorAlreadyexistsexception {
        Optional<Director>directorOpt=movieRepository.getByDirectorNamess(director.getName());
        if(directorOpt.isPresent()){
            throw new DirectorAlreadyexistsexception(director.getName());
        }
       return movieRepository.addDirectorss(director);
    }

    public Movie getMovieByNames(String movieName) throws MovieNotExists {
        Optional<Movie>movieOpt=movieRepository.getByMovieNamess(movieName);
        if(movieOpt.isEmpty()){
            throw  new MovieNotExists(movieName);
        }
        return movieOpt.get();
    }

    public Director getDirectorByNames(String directorName) throws DirectorNotExists{
        Optional<Director> directorOpt=movieRepository.getByDirectorNamess(directorName);
        if(directorOpt.isEmpty()){
            throw new DirectorNotExists(directorName);
        }
        return directorOpt.get();
    }



    public Boolean addMovieDirectorPairs(String movieName, String directorName) {
        Boolean flag;
        flag=movieRepository.addMovieDirectorPairss(movieName,directorName);
        if(flag==false){
            throw new DirectorMoviePairNotAdded(movieName,directorName);
        }
        return flag;
    }

    public List<String> getMovieByDirectorNames(String directorName) throws MovieListNotFound {
        Optional<List<String>>movieNameOpt=movieRepository.getMovieByDirectorNamess(directorName);
        if(movieNameOpt.isEmpty()){
            throw new MovieListNotFound(directorName);
        }
        return movieNameOpt.get();
    }

    public List<String> findAllMovie() throws MovieListNotFound {
        Optional<List<String>>movieListOpt=movieRepository.findAllMoviess();
        if(movieListOpt.isEmpty()){
            throw new MovieListNotFound("null");
        }
        return movieListOpt.get();
    }

    public boolean deleteDirectorByNames(String directorName) throws DirectorNotExists{
        Optional<Boolean>deleteOpt=movieRepository.deleteDirectorByNamess(directorName);
         if(deleteOpt.isEmpty()){
             throw new DirectorNotExists(directorName);
         }
         return deleteOpt.get();
    }

    public boolean deleteAllDirectorss() throws DirectorNotExists{
        Optional<Boolean>deleteOpt=movieRepository.deleteAllDirectorsss();
        if(deleteOpt.isEmpty()){
            throw new DirectorNotExists("null");
        }
        return deleteOpt.get();
    }


}
