package com.bis.exception;

import java.io.Serializable;

public class InvalidNumberException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidNumberException(final String msg) {
        super(msg);
    }
}
