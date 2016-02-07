
package com.touwolf.sendgrid3;

import com.touwolf.sendgrid3.model.SendgridErrors;

public class SendGridException extends Exception
{
    private int code;

    private SendgridErrors error;

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public SendgridErrors getError()
    {
        return error;
    }

    public void setError(SendgridErrors error)
    {
        this.error = error;
    }

    public SendGridException(String message)
    {
        super(message);
    }

    public SendGridException(String message, SendgridErrors error)
    {
        super(message);
        this.error = error;
    }

    public SendGridException(SendgridErrors error, int code)
    {
        super("");
        this.error = error;
        this.code = code;
    }

    public SendGridException(String message, SendgridErrors error, int code)
    {
        super(message);
        this.error = error;
        this.code = code;
    }

    public SendGridException(String message, int code)
    {
        super(message);
        this.error = new SendgridErrors(message);
        this.code = code;
    }

    public SendGridException(String field, String message, int code)
    {
        super(message);
        this.error = new SendgridErrors(field, message);
        this.code = code;
    }

    public SendGridException(Throwable cause)
    {
        super(cause);
    }

    public SendGridException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
