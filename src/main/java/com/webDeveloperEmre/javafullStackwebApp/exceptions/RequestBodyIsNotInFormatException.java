package com.webDeveloperEmre.javafullStackwebApp.exceptions;

import static java.lang.String.format;

public class RequestBodyIsNotInFormatException extends RuntimeException
{
    public RequestBodyIsNotInFormatException(Object requestBody_Object)
    {
        super(format("Request body must contain all required fields: %s ",requestBody_Object.toString()));
    }


}
