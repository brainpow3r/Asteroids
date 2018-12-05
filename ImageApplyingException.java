package com.company;

public class ImageApplyingException extends Exception {
    public ImageApplyingException(String errorMessage) {
        super(errorMessage);
    }

    public ImageApplyingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
