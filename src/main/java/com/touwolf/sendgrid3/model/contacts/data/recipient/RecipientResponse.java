package com.touwolf.sendgrid3.model.contacts.data.recipient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecipientResponse
{
    private List<Recipient> recipients;

    @SerializedName("error_count")
    private Integer errorCount;

    @SerializedName("error_indices")
    private List<Integer> errorIndices = new ArrayList<>();

    @SerializedName("new_count")
    private Integer newCount;

    @SerializedName("persisted_recipients")
    private List<String> persistedRecipients = new ArrayList<>();

    @SerializedName("updated_count")
    private Integer updatedCount;

    @SerializedName("errors")
    private List<RecipientError> errors = new ArrayList<>();

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public List<Integer> getErrorIndices() {
        return errorIndices;
    }

    public void setErrorIndices(List<Integer> errorIndices) {
        this.errorIndices = errorIndices;
    }

    public Integer getNewCount() {
        return newCount;
    }

    public void setNewCount(Integer newCount) {
        this.newCount = newCount;
    }

    public List<String> getPersistedRecipients() {
        return persistedRecipients;
    }

    public void setPersistedRecipients(List<String> persistedRecipients) {
        this.persistedRecipients = persistedRecipients;
    }

    public Integer getUpdatedCount() {
        return updatedCount;
    }

    public void setUpdatedCount(Integer updatedCount) {
        this.updatedCount = updatedCount;
    }

    public List<RecipientError> getErrors() {
        return errors;
    }

    public void setErrors(List<RecipientError> errors) {
        this.errors = errors;
    }
}
