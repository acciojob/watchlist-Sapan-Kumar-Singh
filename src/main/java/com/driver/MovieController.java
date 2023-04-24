package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    // 1. add movie
    @PostMapping("/add-movie")
     public ResponseEntity addMovie(@RequestBody Movie movie){

              movieService.addMovie(movie);
             return new ResponseEntity("Movie successfully added!", HttpStatus.CREATED);
     }

     // 2. add director
     @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
            movieService.addDirector(director);
            return new ResponseEntity("Director successfully added!",HttpStatus.CREATED);

     }

     //3. add director-movie pair

     @PutMapping("/add-movie-director-pair")

     public ResponseEntity addMovieDirectorPair(@RequestParam("name") String movieName ,@RequestParam("name") String directorName){

            movieService.addMovieDirectorPair(movieName,directorName);
            return new ResponseEntity("Movie-Director pair successfully added",HttpStatus.ACCEPTED);
     }



    // 4. get movie
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){

            Movie movie=movieService.getMovieByName(movieName);
            return new ResponseEntity(movie,HttpStatus.OK);

    }


    // 5. get director
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
            Director director=movieService.getDirectorByName(directorName);
            return new ResponseEntity(director,HttpStatus.OK);
    }



    // 6. Get List of movies name
    @GetMapping("/get-movies-by-director-name/{director}")

    public ResponseEntity getMoviesByDirectorName(@PathVariable("name") String directorName){

            List<String>res=movieService.getMovieByDirectorName(directorName);
           return new ResponseEntity(res,HttpStatus.OK);
    }

    // 7. Get list of all movies
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){

            List<String>movielist=movieService.findAllMovie();
            return new ResponseEntity(movielist,HttpStatus.OK);

    }

    // 8. Delete a director and its all movies
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String directorName){

            movieService.deleteDirectorByName(directorName);
            return new ResponseEntity("Deleted successfully",HttpStatus.OK);

    }
    // 9. Delete all directors and all movies
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
          movieService.deleteAllDirector();
            return new ResponseEntity("Delete all director successfully",HttpStatus.OK);

    }


}
