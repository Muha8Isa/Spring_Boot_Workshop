package se.lexicon.spring_boot_workshop.exception;

public class DataNotFoundException extends Exception {

    public DataNotFoundException(String message) {
        super(message);
    }

}