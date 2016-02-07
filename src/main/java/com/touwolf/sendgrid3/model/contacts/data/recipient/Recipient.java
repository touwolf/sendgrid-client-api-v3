package com.touwolf.sendgrid3.model.contacts.data.recipient;

import com.google.gson.annotations.SerializedName;
import com.touwolf.sendgrid3.model.contacts.data.customField.CustomField;

import java.util.*;

public class Recipient
{
    private Map<String, String> data;

    @SerializedName("custom_fields")
    private List<CustomField> customFields;

    public Map<String, String> getData() {
        if(null == data)
        {
            data = new HashMap<>();
        }
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public List<CustomField> getCustomFields() {
        if(null == customFields)
        {
            customFields = new LinkedList<>();
        }
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    public Recipient put(String key, String value)
    {
        getData().put(key, value);
        return this;
    }

    public Recipient email(String email)
    {
        put("email", email);
        return this;
    }

    public Recipient firstName(String firstName)
    {
        put("first_name", firstName);
        return this;
    }

    public Recipient lastName(String lastName)
    {
        put("last_name", lastName);
        return this;
    }

    public Recipient createdAt(Date createdAt)
    {
        put("created_at", createdAt.toString());
        return this;
    }

    public Recipient updatedAt(Date updatedAt)
    {
        put("updated_at", updatedAt.toString());
        return this;
    }

    public Recipient lastEmailed(Date lastEmailed)
    {
        put("last_emailed", lastEmailed.toString());
        return this;
    }

    public Recipient lastClicked(Date lastClicked)
    {
        put("last_clicked", lastClicked.toString());
        return this;
    }

    public Recipient lastOpened(Date lastOpened)
    {
        put("last_opened", lastOpened.toString());
        return this;
    }
}
