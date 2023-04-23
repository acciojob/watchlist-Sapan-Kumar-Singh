package com.driver;

public class DirectorAlreadyexistsexception extends RuntimeException {
    public DirectorAlreadyexistsexception(String name) {
        super("Director with name "+name +"is alraeady Exists");
    }
}
