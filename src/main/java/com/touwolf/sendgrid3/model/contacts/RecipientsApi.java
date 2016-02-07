package com.touwolf.sendgrid3.model.contacts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.impl.SendGridBuilder;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.Recipient;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientCountResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientListResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.adapter.RecipientTypeAdapter;
import org.bridje.ioc.Component;

@Component
public class RecipientsApi
{
    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Recipient.class, new RecipientTypeAdapter())
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private SendGridBuilder sendGridBuilder;

    public RecipientsApi builder(SendGridBuilder sendGridBuilder)
    {
        this.sendGridBuilder = sendGridBuilder;
        return this;
    }

    /**
     * The rate at which recipients may be uploaded is limited to 3 requests every 2 seconds.
     * Recipients may be uploaded in batches of 1000 per request.
     * This results in a maximum upload rate of 1500 recipients per second.
     *
     * @param recipients
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> addRecipients(Recipient... recipients) throws SendGridException
    {
        if(null == recipients || recipients.length == 0)
        {
            throw new SendGridException("recipients are invalids", 404);
        }

        String payload = GSON.toJson(recipients);
        return sendGridBuilder.post("contactdb/recipients", payload, RecipientResponse.class);
    }

    /**
     * Updates one or more recipients. The body is a list of recipient objects.
     *
     * @param recipients
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> updateRecipient(Recipient... recipients) throws SendGridException
    {
        if(null == recipients || recipients.length == 0)
        {
            throw new SendGridException("recipients are invalids", 404);
        }

        String payload = GSON.toJson(recipients);
        return sendGridBuilder.patch("contactdb/recipients", payload, RecipientResponse.class);
    }

    /**
     * Deletes one or more recipients. The body is a list of recipient ids to delete.
     *
     * @param recipientsId IDs of recipients
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteRecipients(String... recipientsId) throws SendGridException
    {
        if(null == recipientsId || recipientsId.length == 0 )
        {
            throw new SendGridException("recipients are invalids", 404);
        }

        String payload = GSON.toJson(recipientsId);
        return sendGridBuilder.delete("contactdb/recipients", payload, Void.class);
    }

    /**
     * List Recipients
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> listRecipients() throws SendGridException
    {
        return sendGridBuilder.get("contactdb/recipients", RecipientResponse.class);
    }

    /**
     * List Recipients
     *
     * @param page Page index of first recipients to return (must be a positive integer) Default: 1
     * @param pageSize Number of recipients to return at a time (must be a positive integer between 1 and 1000) Default: 100
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> listRecipients(Integer page, Integer pageSize) throws SendGridException
    {
        if(null == page && null == pageSize)
        {
            return listRecipients();
        }

        Boolean hasPageSize = false;
        String relativeUrl = "contactdb/recipients";
        if(pageSize > 0 && pageSize < 1000)
        {
            hasPageSize = true;
            relativeUrl += "?page_size=" + pageSize;
        }

        if(page > 0)
        {
            relativeUrl += hasPageSize ? "&page=" + page : "?page=" + page;
        }

        return sendGridBuilder.get(relativeUrl, RecipientResponse.class);
    }

    /**
     * Retrieve a Recipient
     *
     * @param recipientId The ID of the recipient
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Recipient> retriveRecipients(String recipientId) throws SendGridException
    {
        if(StringUtils.isBlank(recipientId))
        {
           throw new SendGridException("recipient_id is invalid", 404);
        }

        return sendGridBuilder.get("contactdb/recipients/" + recipientId, Recipient.class);
    }

    /**
     * Delete a Recipient
     *
     * @param recipientId The ID of the recipient
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteRecipient(String recipientId) throws SendGridException
    {
        if(StringUtils.isBlank(recipientId))
        {
            throw new SendGridException("recipient_id is invalid", 404);
        }

        return sendGridBuilder.delete("contactdb/recipients/" + recipientId, Void.class);
    }

    /**
     * Get the Lists the Recipient Is On
     *
     * @param recipientId The ID of the recipient
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientListResponse> getListRecipientIsOn(String recipientId) throws SendGridException
    {
        if(StringUtils.isBlank(recipientId))
        {
            throw new SendGridException("recipient_id is invalid", 404);
        }

        return sendGridBuilder.get("contactdb/recipients/" + recipientId + "/lists", RecipientListResponse.class);
    }

    /**
     * Get a Count of Billable Recipients
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientCountResponse> getCountBilliableRecipients() throws SendGridException
    {
        return sendGridBuilder.get("contactdb/recipients/billable_count", RecipientCountResponse.class);
    }

    /**
     * Get a Count of Recipients
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientCountResponse> getCountRecipients() throws SendGridException
    {
        return sendGridBuilder.get("contactdb/recipients/count", RecipientCountResponse.class);
    }

    /**
     * Get Recipients Matching Search Criteria
     *
     * @param fieldName is a variable that is substituted for your actual custom field name from your recipient.
     *                  Text fields must be url-encoded.
     *                  Date fields are searchable only by unix timestamp (e.g. 2/2/2015 becomes 1422835200)
     *                  If field_name is a ‘reserved’ date field, such as created_at or updated_at, the system will internally
     *                  convert your epoch time to a date range encompassing the entire day. For example, an epoch time of 1422835600
     *                  converts to Mon, 02 Feb 2015 00:06:40 GMT, but internally the system will search from Mon, 02 Feb 2015 00:00:00
     *                  GMT through Mon, 02 Feb 2015 23:59:59 GMT.
     * @param criteria
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> getRecipientSearch(String fieldName, String criteria) throws SendGridException
    {
        if(StringUtils.isBlank(fieldName) || StringUtils.isBlank(criteria))
        {
            throw new SendGridException("field_name or criteria are invalids", 404);
        }
        return sendGridBuilder.get("contactdb/recipients/search?" + fieldName + "=" + criteria, RecipientResponse.class);
    }
}
