package com.touwolf.sendgrid3.model.contacts.data.list;

import com.google.gson.annotations.SerializedName;

public class ListResponse
{
    private Long id;

    private String name;

    @SerializedName("recipient_count")
    private Integer recipientCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRecipientCount() {
        return recipientCount;
    }

    public void setRecipientCount(Integer recipientCount) {
        this.recipientCount = recipientCount;
    }
}
