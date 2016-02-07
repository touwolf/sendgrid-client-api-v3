package com.touwolf.sendgrid3.model.contacts.data.list;

import java.util.List;

public class ListsResponse
{
    private List<ListResponse> lists;

    public List<ListResponse> getLists() {
        return lists;
    }

    public void setLists(List<ListResponse> lists) {
        this.lists = lists;
    }
}
