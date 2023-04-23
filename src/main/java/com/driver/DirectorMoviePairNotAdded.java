package com.driver;

public class DirectorMoviePairNotAdded extends RuntimeException {
    public DirectorMoviePairNotAdded(String movie, String director) {
        super(movie+" and "+director+" is not added");
    }
}
