package com.touwolf.sendgrid3.model.contacts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.impl.SendGridBuilder;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientResponse;
import com.touwolf.sendgrid3.model.contacts.data.segment.Segment;
import com.touwolf.sendgrid3.model.contacts.data.segment.SegmentResponse;
import org.bridje.ioc.Component;

/**
 * Valid operators for create and update depend on the type of the field you are segmenting.
 * Dates: "eq", "ne", "lt" (before), "gt" (after) Text: "contains", "eq" (is - matches the full field), "ne"
 * (is not - matches any field where the entire field is not the condition value) Numbers: "eq", "lt",
 * "gt" Email Clicks and Opens: "eq" (opened), "ne" (not opened) Segment conditions using "eq" or "ne" for email
 * clicks and opens should provide a "field" of either clicks.campaign_identifier or opens.campaign_identifier.
 * The condition value should be a string containing the id of a completed campaign.
 * Segments may contain multiple condtions, joined by an "and" or "or" in the "and_or" field.
 * The first condition in the conditions list must have an empty "and_or", and subsequent conditions must all specify an "and_or".
 */
@Component
public class SegmentsApi
{
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private SendGridBuilder sendGriBuilder;

    public SegmentsApi builder(SendGridBuilder sendGriBuilder)
    {
        this.sendGriBuilder = sendGriBuilder;
        return this;
    }

    /**
     * Create a Segment
     *
     * @param segment
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Segment> createSegment(Segment segment) throws SendGridException
    {
        if(null == segment)
        {
            throw new SendGridException("segment is invalid", 404);
        }

        String payload = GSON.toJson(segment);
        return sendGriBuilder.post("contactdb/segments", payload, Segment.class);
    }

    /**
     * List All Segments
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<SegmentResponse> listAllSegments() throws SendGridException
    {
        return sendGriBuilder.get("contactdb/segments", SegmentResponse.class);
    }

    /**
     * Retrieve a Segment
     *
     * @param segmentId The ID of the segment
     * @return
     */
    public SendgridResponse<Segment> retriveSegment(Long segmentId) throws SendGridException
    {
        if(null == segmentId)
        {
            throw new SendGridException("segment_id is invalid", 404);
        }
        return sendGriBuilder.get("contactdb/segments/" + segmentId, Segment.class);
    }

    /**
     * Update a Segment
     *
     * @param segmentId The ID of the segment
     * @param segment
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Segment> updateSegment(Long segmentId, Segment segment) throws SendGridException
    {
        if(null == segmentId || null == segment)
        {
            throw new SendGridException("segment_id is invalid", 404);
        }
        String payload = GSON.toJson(segment);
        return sendGriBuilder.patch("contactdb/segments/" + segmentId, payload, Segment.class);
    }

    /**
     * Delete a Segment
     *
     * @param segmentId The ID of the segment
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteSegment(Long segmentId) throws SendGridException
    {
        if(null == segmentId)
        {
            throw new SendGridException("segment_id is invalid", 404);
        }

        return sendGriBuilder.delete("contactdb/segments/" + segmentId, Void.class);
    }

    /**
     * Delete a Segment without contacts
     *
     * @param segmentId The ID of the segment
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteSegmentWithoutContacts(Long segmentId) throws SendGridException
    {
        if(null == segmentId)
        {
            throw new SendGridException("segment_id is invalid", 404);
        }

        String payload = "{\"delete_contacts\" : false}";
        return sendGriBuilder.delete("contactdb/segments/" + segmentId, payload, Void.class);
    }

    /**
     * List Recipients On a Segment
     *
     * @param segmentId The ID of the segment
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> listRecipientOnSegment(Long segmentId) throws SendGridException
    {
        if(null == segmentId)
        {
            throw new SendGridException("segment_id is invalid", 404);
        }

        return sendGriBuilder.get("contactdb/segments/" + segmentId  + "/recipients", RecipientResponse.class);
    }

    /**
     * List Recipients On a Segment
     *
     * @param segmentId The ID of the segment
     * @param page Page index of recipients to return (must be a positive integer) Default: 1
     * @param pageSize Number of recipients to return at a time (must be a positive integer from 1 to 1000) Default: 100
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<RecipientResponse> listRecipientOnSegment(Long segmentId, Integer page, Integer pageSize) throws SendGridException
    {
        if(null == segmentId)
        {
            throw new SendGridException("segment_id is invalid", 404);
        }

        if(null == page && null == pageSize)
        {
            return listRecipientOnSegment(segmentId);
        }

        Boolean hasPageSize = false;
        String relativeUrl = "contactdb/segments/" + segmentId  + "/recipients";
        if(pageSize > 0 && pageSize < 1000)
        {
            hasPageSize = true;
            relativeUrl += "?page_size=" + pageSize;
        }

        if(page > 0)
        {
            relativeUrl += hasPageSize ? "&page=" + page : "?page=" + page;
        }

        return sendGriBuilder.get(relativeUrl, RecipientResponse.class);
    }
}
