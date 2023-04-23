package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    // 1. add movie
    @PostMapping("POST/movies/add-movie")
     public ResponseEntity addMovie(@RequestBody Movie movie){
         try {
              movieService.addMovies(movie);
             return new ResponseEntity("success", HttpStatus.CREATED);
         }
         catch (MovieAlreadyExistsException ex){
             return new ResponseEntity("movie already exists",HttpStatus.BAD_REQUEST);
         }

     }

     // 2. add director
     @PostMapping("POST/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        try {
            movieService.addDirectors(director);
            return new ResponseEntity("success",HttpStatus.CREATED);
        }
        catch (DirectorAlreadyexistsexception ex){
            return new ResponseEntity("Director already exists",HttpStatus.BAD_REQUEST);
        }
     }

     //3. add director-movie pair

     @PutMapping("PUT /movies/add-movie-director-pair")

     public ResponseEntity addMovieDirectorPair(@RequestParam String movieName ,@RequestParam String directorName){
        try {
            movieService.addMovieDirectorPairs(movieName,directorName);
            return new ResponseEntity("success",HttpStatus.ACCEPTED);
        }
        catch (DirectorMoviePairNotAdded ex){
            return new ResponseEntity("Director-Movie pair Not added",HttpStatus.INSUFFICIENT_STORAGE);
        }

     }



    // 4. get movie
    @GetMapping(" GET /movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String movieName){
        try {
            Movie movie=movieService.getMovieByNames(movieName);
            return new ResponseEntity(movie,HttpStatus.OK);
        }
        catch (MovieNotExists ex){
            return new ResponseEntity("movie not exists",HttpStatus.NOT_FOUND);
        }
    }


    // 5. get director
    @GetMapping("GET /movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String directorName){
        try {
            Director director=movieService.getDirectorByNames(directorName);
            return new ResponseEntity(director,HttpStatus.OK);
        }
        catch (DirectorNotExists ex){
            return new ResponseEntity("Director not exists",HttpStatus.NOT_FOUND);
        }
    }



    // 6. Get List of movies name
    @GetMapping("GET /movies/get-movies-by-director-name/{director}")

    public ResponseEntity getMoviesByDirectorName(@PathVariable String directorName){
        try {
            List<String>res=movieService.getMovieByDirectorNames(directorName);
           return new ResponseEntity(res,HttpStatus.OK);
        }
       catch (MovieListNotFound ex){
           return new ResponseEntity("Movie List Not Found",HttpStatus.NOT_FOUND);
       }
    }

    // 7. Get list of all movies
    @GetMapping("GET /movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        try {
            List<String>movielist=movieService.findAllMovie();
            return new ResponseEntity(movielist,HttpStatus.OK);
        }
        catch (MovieListNotFound ex){
            return new ResponseEntity("Movie List Not Found",HttpStatus.NOT_FOUND);
        }

    }

    // 8. Delete a director and its all movies
    @DeleteMapping("DELETE /movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String directorName){
        try {
            boolean flag=movieService.deleteDirectorByNames(directorName);
            return new ResponseEntity("success",HttpStatus.OK);
        }
         catch (DirectorNotExists ex){
            return new ResponseEntity("Director Not found",HttpStatus.NOT_FOUND);
         }
    }
    // 9. Delete all directors and all movies
    @DeleteMapping("DELETE /movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){

        try{
            movieService.deleteAllDirectorss();
            return new ResponseEntity("success",HttpStatus.OK);
        }
        catch (DirectorNotExists ex){
            return new ResponseEntity("Director Not Exists",HttpStatus.NOT_FOUND);
        }
    }


}
