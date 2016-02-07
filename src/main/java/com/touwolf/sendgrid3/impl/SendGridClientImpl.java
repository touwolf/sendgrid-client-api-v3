package com.touwolf.sendgrid3.impl;

import org.bridje.ioc.Ioc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.touwolf.sendgrid3.SendGridClient;
import com.touwolf.sendgrid3.model.bounces.BouncesApi;
import com.touwolf.sendgrid3.model.campaign.CampaignApi;
import com.touwolf.sendgrid3.model.contacts.ContactsApi;

public class SendGridClientImpl implements SendGridClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SendGridClientImpl.class);

    private BouncesApi bounce;

    private ContactsApi contact;

    private CampaignApi campaign;


    private SendGridBuilder sendGridBuilder;

    private String urlBase = "https://api.sendgrid.com/v3/";

    private Integer timeout = 1000;

    private String apiUser;

    private String apiKey;

    public SendGridClientImpl(String apiUser, String apiKey)
    {
        this(apiUser, apiKey, 1000);
    }

    public SendGridClientImpl(String apiUser, String apiKey, Integer timeout)
    {
        this.timeout = timeout;
        this.apiKey = apiKey;
        this.apiUser = apiUser;

        this.sendGridBuilder = new SendGridBuilder(urlBase, apiUser, apiKey, timeout);
    }

    private SendGridBuilder getBuilder()
    {
        if(null == sendGridBuilder)
        {
            sendGridBuilder = new SendGridBuilder(urlBase, apiUser, apiKey, timeout);
        }

        return sendGridBuilder;
    }

    public BouncesApi getBounce()
    {
        if(null == bounce)
        {
            bounce = Ioc.context().find(BouncesApi.class);
        }
        return bounce;
    }

    public ContactsApi getContact()
    {
        if(null == contact)
        {
            contact = Ioc.context().find(ContactsApi.class);
        }
        return contact;
    }

    public CampaignApi getCampaign()
    {
        if(null == campaign)
        {
            campaign = Ioc.context().find(CampaignApi.class);
        }
        return campaign;
    }

    @Override
    public BouncesApi bounces()
    {
        return getBounce().builder(getBuilder());
    }

    @Override
    public ContactsApi contacts()
    {
        return getContact().builder(getBuilder());
    }

    @Override
    public CampaignApi campaigns()
    {
        return getCampaign().builder(getBuilder());
    }
}