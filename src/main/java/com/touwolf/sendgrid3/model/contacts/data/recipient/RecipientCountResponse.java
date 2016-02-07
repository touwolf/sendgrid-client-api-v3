package com.touwolf.sendgrid3.model.contacts.data.recipient;

import com.google.gson.annotations.SerializedName;

public class RecipientCountResponse
{
    @SerializedName("recipient_count")
    private Integer recipientCount;

    public Integer getRecipientCount() {
        return recipientCount;
    }

    public void setRecipientCount(Integer recipientCount) {
        this.recipientCount = recipientCount;
    }
}
