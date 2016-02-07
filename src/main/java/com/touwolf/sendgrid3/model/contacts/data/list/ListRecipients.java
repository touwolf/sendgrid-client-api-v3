package com.touwolf.sendgrid3.model.contacts.data.list;

import com.google.gson.annotations.SerializedName;

public class ListRecipients
{
    @SerializedName("created_at")
    private Long createdAt;

    private String email;

    @SerializedName("first_name")
    private String firstName;

    private String id;

    @SerializedName("last_clicked")
    private String lastClicked;

    @SerializedName("last_emailed")
    private String lastEmailed;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("last_opened")
    private String lastOpened;

    @SerializedName("updated_at")
    private Long updatedAt;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastClicked() {
        return lastClicked;
    }

    public void setLastClicked(String lastClicked) {
        this.lastClicked = lastClicked;
    }

    public String getLastEmailed() {
        return lastEmailed;
    }

    public void setLastEmailed(String lastEmailed) {
        this.lastEmailed = lastEmailed;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastOpened() {
        return lastOpened;
    }

    public void setLastOpened(String lastOpened) {
        this.lastOpened = lastOpened;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
