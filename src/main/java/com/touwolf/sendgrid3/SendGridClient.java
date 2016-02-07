package com.touwolf.sendgrid3;

import com.touwolf.sendgrid3.model.bounces.BouncesApi;
import com.touwolf.sendgrid3.model.campaign.CampaignApi;
import com.touwolf.sendgrid3.model.contacts.ContactsApi;

public interface SendGridClient
{
    /**
     * Bounces API
     *
     * @return a Bounces API Object
     */
    BouncesApi bounces();

    /**
     * Contacts API
     *
     * @return a Contact API Object
     */
    ContactsApi contacts();

    /**
     * Camapign API
     *
     * @return a Campaign API Object
     */
    CampaignApi campaigns();
}
