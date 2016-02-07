
package com.touwolf.sendgrid3;

import com.touwolf.sendgrid3.impl.SendGridClientImpl;
import com.touwolf.sendgrid3.model.campaign.CampaignApi;
import org.bridje.ioc.Ioc;
import org.junit.*;

public class SendgridCampaignTest
{

    // To execute this test correctly, please fill real username and password values
    private static final String API_USER = "";

    private static final String API_KEY = "";

    public SendgridCampaignTest()
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
            SendGridClient client = new SendGridClientImpl(API_USER, API_KEY);
            CampaignApi campaigns = client.campaigns();

            //Campaign campaign = new Campaign();
            //campaign.setTitle("March Newsletter");
            //campaign.setSubject("New Products for Spring!");

            //campaigns.createCampaign(campaign);
            //SendgridResponse<CampaignResponse> allCampaignResponse = campaigns.getAllCampaign();
            //Assert.assertTrue(allCampaignResponse.getCode() == 200);
        }
        catch (Exception ex)
        {
            Assert.assertTrue(false);
        }
    }
}
