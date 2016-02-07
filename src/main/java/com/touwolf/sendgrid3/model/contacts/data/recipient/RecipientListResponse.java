package com.touwolf.sendgrid3.model.contacts.data.recipient;

import java.util.List;

public class RecipientListResponse
{
    private List<Recipient> lists;

    public List<Recipient> getLists() {
        return lists;
    }

    public void setLists(List<Recipient>  lists) {
        this.lists = lists;
    }
}
