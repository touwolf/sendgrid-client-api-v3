package com.touwolf.sendgrid3.model.contacts.data.segment;

public enum Logic
{
    or, and, empty;

    public String toString()
    {
        if("empty".equals(name()))
        {
            return "";
        }

        return name().toLowerCase();
    }
}
