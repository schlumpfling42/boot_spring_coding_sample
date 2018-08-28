package net.rka.springboot.sample.service;

/**
 * Exception to handle all the exceptions thrown by the used services
 */
public class ServiceException extends Exception {
    /**
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
