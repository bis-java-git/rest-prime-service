package com.bis.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PrimesExceptionHandler implements ExceptionMapper<InvalidNumberException> {

    @Override
    public Response toResponse(InvalidNumberException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}