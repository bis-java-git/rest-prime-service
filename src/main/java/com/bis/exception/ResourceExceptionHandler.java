package com.bis.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ResourceExceptionHandler implements ExceptionMapper<ResourceErrorException> {

    @Override
    public Response toResponse(ResourceErrorException exception) {
        return Response.status(Response.Status.EXPECTATION_FAILED).entity(exception.getMessage()).build();
    }
}
