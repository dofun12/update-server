package org.lemanoman.updateserver.exception;

public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException() {
        super("Id not found");
    }
}
