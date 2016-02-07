package com.touwolf.sendgrid3.model.campaign;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.impl.SendGridBuilder;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.campaign.data.Campaign;
import com.touwolf.sendgrid3.model.campaign.data.CampaignResponse;
import com.touwolf.sendgrid3.model.campaign.data.CampaignTest;
import org.bridje.ioc.Component;

/**
 * A campaign requires a title to be created. In order to send or schedule the campaign, you will be required to
 * provide a subject, sender ID, content (we suggest both html and plain text), and at least one list or segment ID.
 */
@Component
public class CampaignApi
{
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private SendGridBuilder sendGrid;

    public CampaignApi builder(SendGridBuilder sendGrid)
    {
        this.sendGrid = sendGrid;
        return this;
    }

    /**
     * Create a Campaign
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> createCampaign(Campaign campaign) throws SendGridException
    {
        if(null == campaign)
        {
            throw new SendGridException("campaign is invalid", 404);
        }

        return sendGrid.post("campaigns", Campaign.class);
    }

    /**
     * Returns campaigns in reverse order they were created (newest first) Returns an empty array if no campaigns exist
     *
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<CampaignResponse> getAllCampaign() throws SendGridException
    {
        return sendGrid.get("campaigns", CampaignResponse.class);
    }

    /**
     * Campaigns in reverse order they were created (newest first) Returns an empty array if no campaigns exist
     *
     * @param limit The maximum number of campaigns to return Default: 10
     * @param offset The index of the first campaign to return, where 0 is the first campaign Default: 0
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<CampaignResponse> getAllCampaign(Integer limit, Integer offset) throws SendGridException
    {
        if(null == limit && null == offset)
        {
            return getAllCampaign();
        }

        String relativeUrl = "campaigns";
        Boolean hasLimit = false;
        if(null != limit && limit > 0)
        {
            hasLimit = true;
            relativeUrl += "?limit=" + limit;
        }

        if(null != offset && offset >= 0)
        {
            relativeUrl += hasLimit ? "&offset=" + offset : "?offset=" + offset;
        }

        return sendGrid.get(relativeUrl, CampaignResponse.class);
    }

    /**
     * View a Campaign
     *
     * @param campaignId The id of the campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> viewCampaign(Long campaignId) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        return sendGrid.get("campaigns/" + campaignId, Campaign.class);
    }

    /**
     * Delete a Campaign
     *
     * @param campaignId The id of the campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> deleteCampaign(Long campaignId) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        return sendGrid.delete("campaigns/" + campaignId, Void.class);
    }

    /**
     * Update a Campaign
     *
     * @param campaignId The id of the campaign
     * @param campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> updateCampaign(Long campaignId, Campaign campaign) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign is invalid", 404);
        }

        String payload = GSON.toJson(campaign);
        return sendGrid.patch("campaigns/" + campaignId, payload, Campaign.class);
    }

    /**
     * Send a Campaign
     *
     * @param campaignId The id of the
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> sendCampaign(Long campaignId) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign is invalid", 404);
        }

        return sendGrid.post("campaigns/" + campaignId + "/schedules/now", Campaign.class);
    }

    /**
     * Schedule a Campaign
     *
     * @param campaignId The id of the campaign
     * @param campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> scheduleCampaign(Long campaignId, Campaign campaign) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        String payload = GSON.toJson(campaign);
        return sendGrid.post("campaigns/" + campaignId + "/schedules", payload, Campaign.class);
    }

    /**
     * Update a Scheduled Campaign
     *
     * @param campaignId The id of the campaign
     * @param campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> updateScheduleCampaign(Long campaignId, Campaign campaign) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        String payload = GSON.toJson(campaign);
        return sendGrid.patch("campaigns/" + campaignId + "/schedules", payload, Campaign.class);
    }

    /**
     * View Scheduled Time of a Campaign
     *
     * @param campaignId The id of the campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Campaign> viewScheduleCampaign(Long campaignId) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        return sendGrid.get("campaigns/" + campaignId + "/schedules", Campaign.class);
    }

    /**
     * Unschedule a Scheduled Campaign. A successful unschedule will return a 204. If the specified campaign is in the
     * process of being sent, the only option is to cancel (a different method).
     *
     * @param campaignId The id of the campaign
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<Void> unScheduleCampaign(Long campaignId) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        return sendGrid.delete("campaigns/" + campaignId + "/schedules", Void.class);
    }

    /**
     * Send a Test Campaign. To send to multiple addresses, use an array for the JSON "to" value ["one@address","two@address"]
     *
     * @param campaignId The id of the campaign
     * @param to
     * @return
     * @throws SendGridException
     */
    public SendgridResponse<CampaignTest> sendTestCampaign(Long campaignId, String... to) throws SendGridException
    {
        if(null == campaignId)
        {
            throw new SendGridException("campaign_id is invalid", 404);
        }

        if(null == to || to.length == 0)
        {
            throw new SendGridException("to is invalid", 404);
        }

        return sendGrid.post("campaigns/" + campaignId + "/schedules/test", CampaignTest.class);
    }
}
