package com.touwolf.sendgrid3.model;

import java.util.LinkedList;
import java.util.List;

public class SendgridErrors
{
    List<SendgridErrorField> errors;

    public SendgridErrors(){}

    public SendgridErrors(String message)
    {
        getErrors().add(new SendgridErrorField(null, message));
    }

    public SendgridErrors(String field, String message)
    {
        getErrors().add(new SendgridErrorField(field, message));
    }

    public List<SendgridErrorField> getErrors()
    {
        if(null == errors)
        {
            errors = new LinkedList<>();
        }

        return errors;
    }

    public void setErrors(List<SendgridErrorField> errors)
    {
        this.errors = errors;
    }
}
