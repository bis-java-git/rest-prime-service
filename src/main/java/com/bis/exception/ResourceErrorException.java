package com.bis.exception;

import java.io.Serializable;

public class ResourceErrorException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResourceErrorException(String msg) {
        super(msg);
    }
}
