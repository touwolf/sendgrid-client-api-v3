package com.touwolf.sendgrid3.model.contacts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.impl.SendGridBuilder;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.contacts.data.list.ListResponse;
import com.touwolf.sendgrid3.model.contacts.data.list.ListsResponse;
import com.touwolf.sendgrid3.model.contacts.data.list.ListRecipientsResponse;
import org.bridje.ioc.Component;

/**
 * All recipient IDs are a URL-safe base64 encoding of the recipient's lower cased email address;
 * for example if a recipient's email is foo@example.com, their recipient ID is Zm9vQGV4YW1wbGUuY29t.
 */

@Component
public class ListsApi
{
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private SendGridBuilder sendGridBuilder;

    public ListsApi builder(SendGridBuilder sendGridBuilder)
    {
        this.sendGridBuilder = sendGridBuilder;
        return this;
    }

    /**
     * Create a List
     *
     * @param listname name of the list
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<ListResponse> createList(String listname) throws SendGridException
    {
        if(StringUtils.isBlank(listname))
        {
            throw new SendGridException("list_name is invalid", 404);
        }

        String payload = "{ \"name\": \"" + listname + "\" }";
        return sendGridBuilder.post("contactdb/lists", payload, ListResponse.class);
    }

    /**
     * Returns an empty list if you GET and no lists exist on your account.
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<ListsResponse> listAllLists() throws SendGridException
    {
        return sendGridBuilder.get("contactdb/lists", ListsResponse.class);
    }

    /**
     * Delete Multiple lists
     *
     * @param ids id list to delete
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteMultipleLists(Long... ids) throws SendGridException
    {
        if(null == ids || ids.length ==0)
        {
            throw new SendGridException("ids are invalids", 404);
        }

        String payload = GSON.toJson(ids);
        return sendGridBuilder.delete("contactdb/lists" , payload, Void.class);
    }

    /**
     * Retrieve a List
     *
     * @param listId The ID of the list
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<ListResponse> retrieveList(Long listId) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        return sendGridBuilder.get("contactdb/lists/" + listId, ListResponse.class);
    }

    /**
     * Update a List
     *
     * @param listId The ID of the list
     * @param newlistname the new list name
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> updateList(Long listId, String newlistname) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        if(StringUtils.isBlank(newlistname))
        {
            throw new SendGridException("new_listname is invalid", 404);
        }

        String payload = "{ \"name\": \"" + newlistname + "\" }";
        return sendGridBuilder.patch("contactdb/lists/" + listId, payload, Void.class);
    }

    /**
     * Delete a List
     *
     * @param listId The ID of the list
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteList(Long listId) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        return sendGridBuilder.delete("contactdb/lists/" + listId, Void.class);
    }

    /**
     * Delete a List without contacts
     *
     * @param listId The ID of the list
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteListWithoutContacs(Long listId) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        String payload = "{\"delete_contacts\" : false}";
        return sendGridBuilder.delete("contactdb/lists/" + listId, payload , Void.class);
    }

    /**
     * List Recipients on a List
     *
     * @param listId The ID of your list
     * @param page Page index of first recipient to return (must be a positive integer) Default: 1
     * @param pageSize Number of recipients to return at a time (must be a positive integer between 1 and 1000) Default: 100
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<ListRecipientsResponse> listRecipientList(Long listId, Integer page, Integer pageSize) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        if(null == page || null == pageSize)
        {
            return listRecipientList(listId);
        }

        Boolean hasPageSize = false;
        String relativeUrl = "contactdb/lists/" + listId + "/recipients";
        if(pageSize > 0 && pageSize < 1000)
        {
            hasPageSize = true;
            relativeUrl += "?page_size=" + pageSize;
        }

        if(page > 0)
        {
            relativeUrl += hasPageSize ? "&page=" + page : "?page=" + page;
        }

        return sendGridBuilder.get(relativeUrl, ListRecipientsResponse.class);
    }

    /**
     * List Recipients on a List
     *
     * @param listId The ID of your list
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<ListRecipientsResponse> listRecipientList(Long listId) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        return sendGridBuilder.get("contactdb/lists/" + listId + "/recipients", ListRecipientsResponse.class);
    }

    /**
     * Add a Single Recipient to a List
     *
     * @param listId The ID of your list
     * @param recipientId The ID of your existing recipient
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> addSingleRecipientToList(Long listId, String recipientId) throws SendGridException
    {
        if(null == listId || StringUtils.isBlank(recipientId))
        {
            throw new SendGridException("list_id or recipient_id are invalids", 404);
        }

        return sendGridBuilder.post("contactdb/lists/" +listId + "/recipients/" + recipientId, Void.class);
    }

    /**
     * Delete a Single Recipient from a Single List
     *
     * @param listId The ID of your list
     * @param recipientId The ID of your existing recipient
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteSingleRecipientToList(Long listId, String recipientId) throws SendGridException
    {
        if(null == listId || StringUtils.isBlank(recipientId))
        {
            throw new SendGridException("list_id or recipient_id are invalids", 404);
        }

        return sendGridBuilder.delete("contactdb/lists/" +listId + "/recipients/" + recipientId, Void.class);
    }

    /**
     * Add Multiple Recipients to a List
     * Adds existing recipients to a list, passing in the recipient IDs to add.
     * Recipient IDs should be passed exactly as they are returned from recipient endpoints.
     * The rate at which recipients may be added to a list is limited to 1 request per second. Recipients may be added in batches of 1000.
     *
     * @param listId The ID of your list
     * @param recipientId The IDs of your existing recipient
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> addMultRecipientToList(Integer listId, String... recipientId) throws SendGridException
    {
        if(null == listId)
        {
            throw new SendGridException("list_id is invalid", 404);
        }

        String payload = GSON.toJson(recipientId);
        return sendGridBuilder.post("contactdb/lists/" +listId + "/recipients", payload, Void.class);
    }
}
