package com.interview.csvfile.exception;

public class WrongInputFileException extends RuntimeException {

    public WrongInputFileException(Exception ex){
        super(ex);
    }
}
