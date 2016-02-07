
package com.touwolf.sendgrid3;

import com.touwolf.sendgrid3.impl.SendGridClientImpl;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.contacts.CustomFieldsApi;
import com.touwolf.sendgrid3.model.contacts.ListsApi;
import com.touwolf.sendgrid3.model.contacts.RecipientsApi;
import com.touwolf.sendgrid3.model.contacts.SegmentsApi;
import com.touwolf.sendgrid3.model.contacts.data.customField.CustomField;
import com.touwolf.sendgrid3.model.contacts.data.customField.CustomFieldsResponse;
import com.touwolf.sendgrid3.model.contacts.data.customField.ReservedCustomFieldsResponse;
import com.touwolf.sendgrid3.model.contacts.data.list.ListRecipientsResponse;
import com.touwolf.sendgrid3.model.contacts.data.list.ListResponse;
import com.touwolf.sendgrid3.model.contacts.data.list.ListsResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.Recipient;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientCountResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientListResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.RecipientResponse;
import com.touwolf.sendgrid3.model.contacts.data.segment.Condition;
import com.touwolf.sendgrid3.model.contacts.data.segment.Operator;
import com.touwolf.sendgrid3.model.contacts.data.segment.Segment;
import com.touwolf.sendgrid3.model.contacts.data.segment.SegmentResponse;
import org.bridje.ioc.Ioc;
import org.junit.*;

import java.util.List;

public class SendgridContactTest
{
    private static final String BAD_USERNAME = "tralala";

    private static final String BAD_PASSWD = "trololo";

    // To execute this test correctly, please fill real username and password values
    private static final String GOOD_USERNAME = "";

    private static final String GOOD_PASSWD = "";

    public SendgridContactTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testSendgridBadRequest() throws Exception
    {

        try
        {
            SendGridClient client = new SendGridClientImpl(BAD_USERNAME, BAD_PASSWD);

            CustomFieldsApi customFieldsApi = client.contacts().customFieldsApi();

            try {
                //unauthorised test
                customFieldsApi.createCustomField("my_field", "text");
            } catch (SendGridException ex) {
                Assert.assertTrue(ex.getError() != null);
                Assert.assertTrue(ex.getError().getErrors().size() == 1);
                Assert.assertTrue("authorization required".equals(ex.getError().getErrors().get(0).getMessage()));
            }

            client = new SendGridClientImpl(GOOD_USERNAME, GOOD_PASSWD);
            customFieldsApi = client.contacts().customFieldsApi();
            try {
                //bad field type
                customFieldsApi.createCustomField("my_field", "text2");
            } catch (SendGridException ex) {
                Assert.assertTrue(ex.getError() != null);
                Assert.assertTrue(ex.getError().getErrors().size() == 1);
                Assert.assertTrue("Must provide a field type.".equals(ex.getError().getErrors().get(0).getMessage()));
            }

            try {
                //reserve field type
                customFieldsApi.createCustomField("first_name", "text");
            } catch (SendGridException ex) {
                Assert.assertTrue(ex.getError() != null);
                Assert.assertTrue(ex.getError().getErrors().size() == 1);
                Assert.assertTrue("You have used a reserved field name for your custom field.".equals(ex.getError().getErrors().get(0).getMessage()));
            }

            try {
                //invalid field
                customFieldsApi.createCustomField("first_name", null);
            } catch (SendGridException ex) {
                Assert.assertTrue(ex.getError() != null);
                Assert.assertTrue(ex.getError().getErrors().size() == 1);
                Assert.assertTrue("You have used a reserved field name for your custom field.".equals(ex.getError().getErrors().get(0).getMessage()));
            }
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSendgridCustomField() throws Exception
    {
        try
        {
            SendGridClient client = new SendGridClientImpl(GOOD_USERNAME, GOOD_PASSWD);

            CustomFieldsApi customFieldsApi = client.contacts().customFieldsApi();

            SendgridResponse<CustomField> customFieldResponse = customFieldsApi.createCustomField("phone", "text");
            Assert.assertTrue(customFieldResponse.getCode() == 201);
            Assert.assertTrue(customFieldResponse.getData().getId() != null);

            SendgridResponse<CustomFieldsResponse> customFieldsResponseResponse = customFieldsApi.listAllCustomFields();
            Assert.assertTrue(customFieldsResponseResponse.getCode() == 200);

            SendgridResponse<CustomField> customFieldRetrieveResponse = customFieldsApi.retrieveCustomFields(customFieldResponse.getData().getId());
            Assert.assertTrue(customFieldRetrieveResponse.getCode() == 200);

            SendgridResponse<Void> voidResponse = customFieldsApi.deleteCustomFields(customFieldResponse.getData().getId());
            Assert.assertTrue(voidResponse.getCode() == 202);

            SendgridResponse<ReservedCustomFieldsResponse> reservedCustomFieldsResponseResponse = customFieldsApi.reservedCustomFields();
            Assert.assertTrue(reservedCustomFieldsResponseResponse.getCode() == 200);
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSendgridList() throws Exception
    {
        try
        {
            SendGridClient client = new SendGridClientImpl(GOOD_USERNAME, GOOD_PASSWD);

            ListsApi listsApi = client.contacts().listsApi();

            SendgridResponse<ListResponse> listResponse = listsApi.createList("test-new-api-list");
            Assert.assertTrue(listResponse.getCode() == 201);

            SendgridResponse<ListsResponse> listsAllResponse = listsApi.listAllLists();
            Assert.assertTrue(listsAllResponse.getCode() == 200);

            SendgridResponse<ListResponse> retrievedResponse = listsApi.retrieveList(listResponse.getData().getId());
            Assert.assertTrue(retrievedResponse.getCode() == 200);

            SendgridResponse<Void> updateListResponse = listsApi.updateList(listResponse.getData().getId(), "test-update-api-list");
            Assert.assertTrue(updateListResponse.getCode() == 200);

            SendgridResponse<ListRecipientsResponse> listRecipientsResponse = listsApi.listRecipientList(listResponse.getData().getId());
            Assert.assertTrue(listRecipientsResponse.getCode() == 200);

            SendgridResponse<Void> deleteListResponse = listsApi.deleteList(listResponse.getData().getId());
            Assert.assertTrue(deleteListResponse.getCode() == 202);

            SendgridResponse<ListResponse> listResponse1 = listsApi.createList("test-new-api-list-1");
            Assert.assertTrue(listResponse.getCode() == 201);

            SendgridResponse<ListResponse> listResponse2 = listsApi.createList("test-new-api-list-2");
            Assert.assertTrue(listResponse.getCode() == 201);

            SendgridResponse<Void> deleteMultResponse = listsApi.deleteMultipleLists(listResponse1.getData().getId(), listResponse2.getData().getId());
            Assert.assertTrue(deleteMultResponse.getCode() == 204);
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSendgridRecipients() throws Exception
    {
        try
        {
            SendGridClient client = new SendGridClientImpl(GOOD_USERNAME, GOOD_PASSWD);

            RecipientsApi recipientsApi = client.contacts().recipientsApi();

            Recipient recipient = new Recipient().email("jones@example.com").lastName("Jones");
            Recipient recipient1 = new Recipient().email("jones2@example.com").lastName("Jones2");
            Recipient recipient2 = new Recipient().email("jonesexample.com").lastName("Jones2");

            SendgridResponse<RecipientResponse> recipientSendgridResponse = recipientsApi
                    .addRecipients(recipient, recipient1, recipient2);

            Assert.assertTrue(recipientSendgridResponse.getCode() == 201);
            Assert.assertTrue(recipientSendgridResponse.getData().getErrorCount() == 1);
            Assert.assertTrue(recipientSendgridResponse.getData().getNewCount() == 2);

            recipient.lastName("Jonas update");
            SendgridResponse<RecipientResponse> recipientUpdateResponse = recipientsApi.updateRecipient(recipient);
            Assert.assertTrue(recipientUpdateResponse.getCode() == 201);
            List<String> persistedRecipients = recipientUpdateResponse.getData().getPersistedRecipients();
            Assert.assertTrue(persistedRecipients.size() == 1);

            SendgridResponse<Void> deleteResponse = recipientsApi.deleteRecipients(
                    recipientUpdateResponse.getData().getPersistedRecipients().get(0)
            );
            Assert.assertTrue(deleteResponse.getCode() == 204);

            SendgridResponse<RecipientResponse> recipientlistResponse = recipientsApi.listRecipients();
            Assert.assertTrue(recipientlistResponse.getCode() == 200);
            Assert.assertTrue(recipientlistResponse.getData().getRecipients().size() > 0);

            String recipientId = recipientSendgridResponse.getData().getPersistedRecipients().get(1);
            SendgridResponse<Recipient> retreiveResponse = recipientsApi.retriveRecipients(recipientId);
            Assert.assertTrue(retreiveResponse.getCode() == 200);

            SendgridResponse<RecipientListResponse> listRecipientIsOn = recipientsApi.getListRecipientIsOn(recipientId);
            Assert.assertTrue(listRecipientIsOn.getCode() == 200);

            SendgridResponse<RecipientResponse> search = recipientsApi.getRecipientSearch("email", "jones2@example.com");
            Assert.assertTrue(search.getCode() == 200);

            deleteResponse = recipientsApi.deleteRecipient(recipientId);
            Assert.assertTrue(deleteResponse.getCode() == 204);

            SendgridResponse<RecipientCountResponse> countBilliableRecipients = recipientsApi.getCountBilliableRecipients();
            Assert.assertTrue(countBilliableRecipients.getCode() == 200);
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSendgridSegment() throws Exception
    {
        try
        {
            SendGridClient client = new SendGridClientImpl(GOOD_USERNAME, GOOD_PASSWD);

            ListsApi listsApi = client.contacts().listsApi();
            SendgridResponse<ListResponse> myListToSegment = listsApi.createList("MyListToSegment");
            Assert.assertTrue(myListToSegment.getCode() == 201);

            RecipientsApi recipientsApi = client.contacts().recipientsApi();

            Recipient recipient = new Recipient().email("jones@example.com").lastName("Jones");

            SendgridResponse<RecipientResponse> recipientSendgridResponse = recipientsApi.addRecipients(recipient);
            Assert.assertTrue(recipientSendgridResponse.getCode() == 201);

            String recipientId = recipientSendgridResponse.getData().getPersistedRecipients().get(0);

            SendgridResponse<Void> voidSendgridResponse = listsApi.addSingleRecipientToList(myListToSegment.getData().getId(), recipientId);
            Assert.assertTrue(voidSendgridResponse.getCode() == 201);

            SegmentsApi segmentsApi = client.contacts().segmentsApi();

            Condition cond = new Condition()
                    .setField("last_name")
                    .setValue("Jones")
                    .setOperator(Operator.eq);

            Segment segment = new Segment()
                    .setName("Last Name Miller")
                    .setListId(myListToSegment.getData().getId())
                    .setConditions(cond);

            SendgridResponse<Segment> segmentResponse = segmentsApi.createSegment(segment);
            Assert.assertTrue(segmentResponse.getCode() == 201);

            SendgridResponse<SegmentResponse> segmentListAllResponse = segmentsApi.listAllSegments();
            Assert.assertTrue(segmentListAllResponse.getCode() == 200);

            SendgridResponse<Segment> segmentRetriveResponse = segmentsApi.retriveSegment(segmentResponse.getData().getId());
            Assert.assertTrue(segmentRetriveResponse.getCode() == 200);

            //FIX: return "List id is not a valid integer"
            //segment.setName("The Millers");
            //SendgridResponse<Segment> segmentUpdateResponse = segmentsApi.updateSegment(segmentResponse.getData().getId(), segment);
            //Assert.assertTrue(segmentUpdateResponse.getCode() == 200);

            SendgridResponse<RecipientResponse> segmentRecipientResponse = segmentsApi.listRecipientOnSegment(segmentResponse.getData().getId());
            Assert.assertTrue(segmentRecipientResponse.getCode() == 200);

            SendgridResponse<Void> deleteSegmentResponse = segmentsApi.deleteSegmentWithoutContacts(segmentResponse.getData().getId());
            Assert.assertTrue(deleteSegmentResponse.getCode() == 204);

            SendgridResponse<Void> deleteRecipientResponse = recipientsApi.deleteRecipient(recipientId);
            Assert.assertTrue(deleteRecipientResponse.getCode() == 204);

            SendgridResponse<Void> deleteListResponse = listsApi.deleteList(myListToSegment.getData().getId());
            Assert.assertTrue(deleteListResponse.getCode() == 202);
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }
}
