package com.touwolf.sendgrid3.model.campaign.data;

import java.util.List;

public class CampaignResponse
{
    private List<Campaign> result;

    public List<Campaign> getResult() {
        return result;
    }

    public void setResult(List<Campaign> result) {
        this.result = result;
    }
}
