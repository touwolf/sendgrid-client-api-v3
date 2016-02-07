package com.touwolf.sendgrid3.impl;
        
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.touwolf.sendgrid3.SendGridException;
import com.touwolf.sendgrid3.model.SendgridErrors;
import com.touwolf.sendgrid3.model.SendgridResponse;
import com.touwolf.sendgrid3.model.contacts.data.recipient.Recipient;
import com.touwolf.sendgrid3.model.contacts.data.recipient.adapter.RecipientTypeAdapter;

public class SendGridBuilder
{    
    private static final Logger logger = LoggerFactory.getLogger(SendGridBuilder.class);

    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Recipient.class, new RecipientTypeAdapter())
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final Integer timeout;

    private final String basicAuth;

    private final String urlBase;

    private OkHttpClient client;

    public SendGridBuilder(String urlBase, String apiUser, String apiKey, Integer timeout)
    {
        this.urlBase = urlBase;
        this.timeout = timeout;

        this.basicAuth = Credentials.basic(apiUser, apiKey);
        this.client = new OkHttpClient();
    }

    /**
     * Retrieve a resource or group of resouces
     *
     * @param url
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> get(String url, Class<T> clazzResp) throws SendGridException
    {
        return send(url, null, "GET", clazzResp);
    }

    /**
     * Create a new resource
     * @param url
     * @param payload
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> post(String url, String payload, Class<T> clazzResp) throws SendGridException
    {
        return send(url, payload, "POST", clazzResp);
    }

    /**
     * Create a new resource
     * @param url
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> post(String url, Class<T> clazzResp) throws SendGridException
    {
        return send(url, "", "POST", clazzResp);
    }

    /**
     * Update an existing resource
     * @param url
     * @param payload
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> put(String url, String payload, Class<T> clazzResp) throws SendGridException
    {
        return send(url, payload, "PUT", clazzResp);
    }

    /**
     * Update an existing resource
     * @param url
     * @param payload
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> patch(String url, String payload, Class<T> clazzResp) throws SendGridException
    {
        return send(url, payload, "PATCH", clazzResp);
    }

    /**
     * Delete an existing resource
     * @param url
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> delete(String url, Class<T> clazzResp) throws SendGridException
    {
        return send(url, null, "DELETE", clazzResp);
    }

    /**
     * Delete an existing resource
     * @param url
     * @param payload
     * @param clazzResp
     * @param <T>
     * @return
     * @throws SendGridException
     */
    public <T> SendgridResponse<T> delete(String url, String payload, Class<T> clazzResp) throws SendGridException
    {
        return send(url, payload, "DELETE", clazzResp);
    }

    private <T> SendgridResponse<T> send(String url, String payload, String verb, Class<T> clazzResp) throws SendGridException
    {
        try
        {
            RequestBody body = null;
            if(null != payload)
            {
                body = RequestBody.create(JSON, payload);
            }

            Request.Builder builder = new Request.Builder()
                    .header("Accept", "application/json")
                    .header("Authorization", basicAuth)
                    .url(urlBase + url);

            Request request = null;
            switch (verb)
            {
                case "GET" :
                {
                    request = builder.get().build();
                    break;
                }

                case "POST" :
                {
                    request = builder.post(body).build();
                    break;
                }

                case "PATCH" :
                {
                    request = builder.patch(body).build();
                    break;
                }

                case "DELETE" :
                {
                    request = null == body ? builder.delete().build() : builder.delete(body).build();
                    break;
                }

                case "PUT" :
                {
                    request = builder.put(body).build();
                    break;
                }
            }

            Response response = client.newCall(request).execute();
            String result = response.body().string();

            if(result.contains("errors") && response.code() != 201)
            {
                throw new SendGridException(GSON.fromJson(result, SendgridErrors.class), response.code());
            }

            SendgridResponse<T> sendGridResponse = new SendgridResponse<>(url, payload, result);
            sendGridResponse.setData(GSON.fromJson(result, clazzResp));
            sendGridResponse.setCode(response.code());

            return sendGridResponse;
        }
        catch (IOException ex)
        {
            throw new SendGridException(ex.getMessage());
        }
    }
}