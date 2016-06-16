package com.xebia.hpbook.rest;

/**
 * APIError class
 */
public class APIError {
    private int statusCode;
    private String message;

    /**
     * Constructor method
     */
    public APIError() {//the constructor
    }

    /**
     *
     * @return the status code error like (600)
     */
    public int status() {
        return statusCode;
    }

    /**
     *
     * @return the description of the error code
     */
    public String message() {
        return message;
    }
}
