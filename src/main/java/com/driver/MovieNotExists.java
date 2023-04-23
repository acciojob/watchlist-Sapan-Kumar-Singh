package com.driver;

public class MovieNotExists extends Throwable {
    public MovieNotExists(String name) {
        super("Movie with name "+name +" is not exists");
    }
}
