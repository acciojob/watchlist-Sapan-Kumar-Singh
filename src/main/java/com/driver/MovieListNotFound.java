package com.driver;

public class MovieListNotFound extends RuntimeException {
    public MovieListNotFound(String directorName) {
        super("Movie List of Director name "+directorName+" is not found");
    }
}
