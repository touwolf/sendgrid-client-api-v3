package com.touwolf.sendgrid3.model.contacts.data.customField;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomFieldsResponse
{
    @SerializedName("custom_fields")
    private List<CustomField> customFields;

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }
}
