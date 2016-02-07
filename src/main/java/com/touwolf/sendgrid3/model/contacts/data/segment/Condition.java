package com.touwolf.sendgrid3.model.contacts.data.segment;

import com.google.gson.annotations.SerializedName;

public class Condition
{
    private String field;

    private String value;

    private Operator operator;

    @SerializedName("and_or")
    private Logic andOr;

    public String getField() {
        return field;
    }

    public Condition setField(String field) {
        this.field = field;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Condition setValue(String value) {
        this.value = value;
        return this;
    }

    public Operator getOperator() {
        return operator;
    }

    public Condition setOperator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public Logic getAndOr() {
        return andOr;
    }

    public Condition setAndOr(Logic andOr) {
        this.andOr = andOr;
        return this;
    }
}
