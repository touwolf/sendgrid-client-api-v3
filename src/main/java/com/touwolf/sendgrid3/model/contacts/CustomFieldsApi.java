package com.touwolf.sendgrid3.model.contacts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.impl.SendGridBuilder;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.contacts.data.customField.CustomField;
import com.touwolf.sendgrid3.model.contacts.data.customField.CustomFieldsResponse;
import com.touwolf.sendgrid3.model.contacts.data.customField.ReservedCustomFieldsResponse;
import org.bridje.ioc.Component;

@Component
public class CustomFieldsApi
{
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private SendGridBuilder sendGridBuilder;

    public CustomFieldsApi builder(SendGridBuilder sendGridBuilder)
    {
        this.sendGridBuilder = sendGridBuilder;
        return this;
    }

    /**
     * Create a Custom Field
     *
     * @param name Field name
     * @param type Field type
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<CustomField> createCustomField(String name, String type) throws SendGridException
    {
        if(StringUtils.isBlank(name) || StringUtils.isBlank(type))
        {
            throw new SendGridException("name or type are invalids", 404);
        }

        String payload = "{ \"name\": \"" + name + "\", \"type\": \"" + type + "\"}";
        return sendGridBuilder.post("contactdb/custom_fields", payload, CustomField.class);
    }

    /**
     * List All Custom Fields
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<CustomFieldsResponse> listAllCustomFields() throws SendGridException
    {
        return sendGridBuilder.get("contactdb/custom_fields", CustomFieldsResponse.class);
    }

    /**
     * Retrieve a Custom Field
     *
     * @param customerFieldId The id of the custom field
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<CustomField> retrieveCustomFields(Long customerFieldId) throws SendGridException
    {
        if(null == customerFieldId)
        {
            throw new SendGridException("customer_field_id is invalid", 404);
        }
        return sendGridBuilder.get("contactdb/custom_fields/" + customerFieldId, CustomField.class);
    }

    /**
     * Delete a Custom Field
     *
     * @param customerFieldId The id of the custom field
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteCustomFields(Long customerFieldId) throws SendGridException
    {
        if(null == customerFieldId)
        {
            throw new SendGridException("customer_field_id is invalid", 404);
        }
        return sendGridBuilder.delete("contactdb/custom_fields/" + customerFieldId, Void.class);
    }

    /**
     * List fields that are reserved and can't be used for custom field names.
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<ReservedCustomFieldsResponse> reservedCustomFields() throws SendGridException
    {
        return sendGridBuilder.get("contactdb/reserved_fields", ReservedCustomFieldsResponse.class);
    }
}
