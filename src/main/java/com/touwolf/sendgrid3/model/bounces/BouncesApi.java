package com.touwolf.sendgrid3.model.bounces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.touwolf.sendgrid3.model.bounces.data.Bounces;
import org.apache.commons.lang.StringUtils;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.impl.SendGridBuilder;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.bounces.data.BouncesResponse;
import org.bridje.ioc.Component;

@Component
public class BouncesApi
{
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private SendGridBuilder sendGrid;

    public BouncesApi builder(SendGridBuilder sendGrid)
    {
        this.sendGrid = sendGrid;
        return this;
    }

    /**
     * List all bounces
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Bounces[]> listAllBounces() throws SendGridException
    {
        return sendGrid.get("suppression/bounces", Bounces[].class);
    }

    /**
     * List all bounces
     *
     * @param startTime Refers start of the time range in unix timestamp when a bounce was created (inclusive). Example: 1443651141
     * @param endTime Refers end of the time range in unix timestamp when a bounce was created (inclusive). Example: 1443651154
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Bounces[]> listAllBounces(Long startTime, Long endTime) throws SendGridException
    {
        if(null == startTime && null == endTime)
        {
            return listAllBounces();
        }

        String relativeUrl = "suppression/bounces";
        Boolean hasStartTime = false;
        if(null != startTime && startTime > 0)
        {
            hasStartTime = true;
            relativeUrl += "?start_time=" + startTime;
        }

        if(null != endTime && endTime > 0)
        {
            relativeUrl += hasStartTime ? "&end_time=" + endTime : "?end_time=" + endTime;
        }

        return sendGrid.get(relativeUrl, Bounces[].class);
    }

    /**
     * There's two bounce delete options: 1) deleting all bounces by specifying "delete_all" to true in the request body, or
     * 2) deleteing some emails by specificy "emails" and an array of emails in the response body.
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteAllBounces() throws SendGridException
    {
        String payload = "{\"delete_all\": true \"}";
        return sendGrid.delete("suppression/bounces", payload, Void.class);
    }

    /**
     * There's two bounce delete options: 1) deleting all bounces by specifying "delete_all" to true in the request body, or
     * 2) deleteing some emails by specificy "emails" and an array of emails in the response body.
     *
     * @param emails Emails to delete
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteBounces(String... emails) throws SendGridException
    {
        if(null == emails || emails.length == 0)
        {
            throw new SendGridException("emails are invalid", 404);
        }

        String payload = "{\"emails\": " + GSON.toJson(emails) + " \"}";
        return sendGrid.delete("suppression/bounces", payload, Void.class);
    }

    /**
     * Get a bounce
     *
     * @param email Email address of bounce entry to retrieve Example: bounce1@test.com
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<BouncesResponse> getBounce(String email) throws SendGridException
    {
        if(StringUtils.isBlank(email))
        {
            throw new SendGridException("email is invalid", 404);
        }

        return sendGrid.get("suppression/bounces/" + email, BouncesResponse.class);
    }

    /**
     * Delete a bounce
     *
     * @param email Email address of bounce entry to retrieve Example: bounce1@test.com
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteBounce(String email) throws SendGridException
    {
        if(StringUtils.isBlank(email))
        {
            throw new SendGridException("email is invalid", 404);
        }

        return sendGrid.delete("suppression/bounces/" + email, Void.class);
    }
}
