package com.touwolf.sendgrid3.model.contacts.data.customField;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ReservedCustomFieldsResponse
{
    @SerializedName("reserved_fields")
    private List<CustomField> reservedFields;

    public List<CustomField> getReservedFields() {
        return reservedFields;
    }

    public void setReservedFields(List<CustomField> reservedFields) {
        this.reservedFields = reservedFields;
    }
}
