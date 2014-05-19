package com.afik;

/**
 * Application exception that contain all available errors in the system.
 */
public class HomeworkException extends Exception {
    public HomeworkException() {
    }

    public HomeworkException(String message) {
        super(message);
    }
}
