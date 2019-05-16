package com.logonlabs;

import net.servicestack.client.WebServiceException;

/**
 * Class to encapsulate LogonLabs exceptions
 */
public class LogonLabsException extends Exception {

    private int httpStatusCode;
    private String errorCode;

    LogonLabsException(WebServiceException ex) {
        super(ex.getErrorMessage(), ex);
        httpStatusCode = ex.getStatusCode();
        errorCode = ex.getErrorCode();
    }

    /**
     * Gets the httpStatusCode value
     * @return the int status code
     */
    public int getHttpStatusCode()
    {
        return httpStatusCode;
    }

    /**
     * Gets the errorCode value
     * @return the String error code
     */
    public String getErrorCode()
    {
        return errorCode;
    }
}
