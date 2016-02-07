
package com.touwolf.sendgrid3;

import com.touwolf.sendgrid3.impl.SendGridClientImpl;
import com.touwolf.sendgrid3.model.bounces.data.Bounces;
import org.junit.*;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.bounces.BouncesApi;
import com.touwolf.sendgrid3.model.bounces.data.BouncesResponse;

public class SendgridBounceTest
{
    // To execute this test correctly, please fill real username and password values
    private static final String API_USER = "";

    private static final String API_KEY = "";

    public SendgridBounceTest()
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
    public void testSendgridRequest() throws Exception
    {

        try
        {
            SendGridClient client = new SendGridClientImpl(API_USER, API_KEY);
            BouncesApi bounces = client.bounces();
            SendgridResponse<Bounces[]> bouncesSendgridResponse = bounces.listAllBounces();
            Assert.assertTrue(bouncesSendgridResponse.getCode() == 200);
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }

}
