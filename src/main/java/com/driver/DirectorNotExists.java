package com.driver;

public class DirectorNotExists extends RuntimeException {
    public DirectorNotExists(String name) {
        super("Director with name "+name+" is not exists");
    }
}
