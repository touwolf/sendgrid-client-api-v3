package com.touwolf.sendgrid3.model.contacts.data.list;

import java.util.List;

public class ListRecipientsResponse
{
    private List<ListRecipients> recipients;

    public List<ListRecipients> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<ListRecipients> recipients) {
        this.recipients = recipients;
    }
}
