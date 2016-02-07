package com.touwolf.sendgrid3.model.campaign.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Campaign
{
    private Integer id;

    private String title;

    private String subject;

    @SerializedName("send_at")
    private Long sendAt;

    @SerializedName("sender_id")
    private Integer senderId;

    @SerializedName("list_ids")
    private List<Integer> listIds = new ArrayList<>();

    @SerializedName("segment_ids")
    private List<Integer> segmentIds = new ArrayList<>();

    private List<String> categories = new ArrayList<>();

    @SerializedName("suppression_group_id")
    private Integer suppressionGroupId;

    @SerializedName("custom_unsubscribe_url")
    private String customUnsubscribeUrl;

    @SerializedName("ip_pool")
    private String ipPool;

    @SerializedName("html_content")
    private String htmlContent;

    @SerializedName("plain_content")
    private String plainContent;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public List<Integer> getListIds() {
        return listIds;
    }

    public void setListIds(List<Integer> listIds) {
        this.listIds = listIds;
    }

    public List<Integer> getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(List<Integer> segmentIds) {
        this.segmentIds = segmentIds;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getSuppressionGroupId() {
        return suppressionGroupId;
    }

    public void setSuppressionGroupId(Integer suppressionGroupId) {
        this.suppressionGroupId = suppressionGroupId;
    }

    public String getCustomUnsubscribeUrl() {
        return customUnsubscribeUrl;
    }

    public void setCustomUnsubscribeUrl(String customUnsubscribeUrl) {
        this.customUnsubscribeUrl = customUnsubscribeUrl;
    }

    public String getIpPool() {
        return ipPool;
    }

    public void setIpPool(String ipPool) {
        this.ipPool = ipPool;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getPlainContent() {
        return plainContent;
    }

    public void setPlainContent(String plainContent) {
        this.plainContent = plainContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSendAt() {
        return sendAt;
    }

    public void setSendAt(Long sendAt) {
        this.sendAt = sendAt;
    }
}
