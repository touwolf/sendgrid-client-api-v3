package com.touwolf.sendgrid3.model.contacts.data.recipient;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipientError
{
    private String message;

    @SerializedName("error_indices")
    private List<Integer> errorIndices;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getErrorIndices() {
        return errorIndices;
    }

    public void setErrorIndices(List<Integer> errorIndices) {
        this.errorIndices = errorIndices;
    }
}
