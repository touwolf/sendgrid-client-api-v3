package com.touwolf.sendgrid3.model.contacts;

import com.touwolf.sendgrid3.impl.SendGridBuilder;
import org.bridje.ioc.Component;
import org.bridje.ioc.Inject;

@Component
public class ContactsApi
{
    @Inject
    private CustomFieldsApi customFieldsApi;

    @Inject
    private ListsApi listsApi;

    @Inject
    private RecipientsApi recipientsApi;

    @Inject
    private SegmentsApi segmentsApi;

    private SendGridBuilder sendGridBuilder;

    public ContactsApi builder(SendGridBuilder sendGridBuilder)
    {
        this.sendGridBuilder = sendGridBuilder;
        return this;
    }

    /**
     * Custom field API
     *
     * @return
     */
    public CustomFieldsApi customFieldsApi()
    {
        return customFieldsApi.builder(sendGridBuilder);
    }

    /**
     * List API
     *
     * @return
     */
    public ListsApi listsApi()
    {
        return listsApi.builder(sendGridBuilder);
    }

    /**
     * Recipient API
     *
     * @return
     */
    public RecipientsApi recipientsApi()
    {
        return recipientsApi.builder(sendGridBuilder);
    }

    /**
     * Segment API
     *
     * @return
     */
    public SegmentsApi segmentsApi()
    {
        return segmentsApi.builder(sendGridBuilder);
    }
}
