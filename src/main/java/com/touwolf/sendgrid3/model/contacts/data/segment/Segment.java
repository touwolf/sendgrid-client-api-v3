package com.touwolf.sendgrid3.model.contacts.data.segment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Segment
{
    private Long id;

    private String name;

    @SerializedName("list_id")
    private Long listId;

    private List<Condition> conditions = new ArrayList<>();

    @SerializedName("recipient_count")
    private Long recipientCount;

    public Long getId() {
        return id;
    }

    public Segment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Segment setName(String name) {
        this.name = name;
        return this;
    }

    public Long getListId() {
        return listId;
    }

    public Segment setListId(Long listId) {
        this.listId = listId;
        return this;
    }

    public List<Condition> getConditions() {
        if(null == conditions)
        {
            conditions = new LinkedList<>();
        }
        return conditions;
    }

    public Segment setConditions(List<Condition> conditions) {
        this.conditions = conditions;
        return this;
    }

    public Segment setConditions(Condition condition) {
        getConditions().add(condition);
        return this;
    }

    public Long getRecipientCount() {
        return recipientCount;
    }

    public void setRecipientCount(Long recipientCount) {
        this.recipientCount = recipientCount;
    }
}
