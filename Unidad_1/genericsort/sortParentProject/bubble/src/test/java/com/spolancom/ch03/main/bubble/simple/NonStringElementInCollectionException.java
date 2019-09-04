package com.spolancom.ch03.main.bubble.simple;

public class NonStringElementInCollectionException extends RuntimeException {
    public NonStringElementInCollectionException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
