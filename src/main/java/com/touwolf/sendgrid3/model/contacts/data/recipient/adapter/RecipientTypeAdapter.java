package com.touwolf.sendgrid3.model.contacts.data.recipient.adapter;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang.StringUtils;
import com.touwolf.sendgrid3.model.contacts.data.customField.CustomField;
import com.touwolf.sendgrid3.model.contacts.data.recipient.Recipient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecipientTypeAdapter extends TypeAdapter<Recipient> {

    @Override
    public void write(JsonWriter out, Recipient recipient) throws IOException
    {
        out.beginObject();
        for(Map.Entry<String, String> entry : recipient.getData().entrySet())
        {
            out.name(entry.getKey()).value(entry.getValue());
        }

        if(null != recipient.getCustomFields() && !recipient.getCustomFields().isEmpty() )
        {
            out.name("custom_fields").beginArray();
            for (CustomField customField : recipient.getCustomFields())
            {
                out.beginObject();
                out.name("id").value(customField.getId());
                out.name("name").value(customField.getName());
                out.name("type").value(customField.getType());
                if (StringUtils.isNotBlank(customField.getValue()))
                {
                    out.name("value").value(customField.getValue());
                }
                out.endObject();
            }
            out.endArray();
        }
        out.endObject();
    }

    @Override
    public Recipient read(JsonReader in) throws IOException
    {
        Recipient recipient = new Recipient();
        in.beginObject();
        while(in.hasNext())
        {
            String name = in.nextName();
            switch (name)
            {
                case "custom_fields":
                {
                    recipient.setCustomFields(customFields(in));
                    break;
                }
                default:
                {
                    if(in.peek() == JsonToken.NULL)
                    {
                        in.skipValue();
                    }
                    else
                    {
                        recipient.put(name, in.nextString());
                    }
                    break;
                }
            }

        }
        in.endObject();
        return recipient;
    }

    private List<CustomField> customFields(JsonReader in) throws IOException
    {
        in.beginArray();
        List<CustomField> customFields = new LinkedList<>();
        while (in.hasNext())
        {
            in.beginObject();
            final CustomField customField = new CustomField();
            while (in.hasNext())
            {
                switch (in.nextName())
                {
                    case "id":
                    {
                        if(in.peek() == JsonToken.NULL)
                        {
                            in.skipValue();
                        }
                        else
                        {
                            customField.setId(in.nextLong());
                        }
                        break;
                    }
                    case "name":
                    {
                        if(in.peek() == JsonToken.NULL)
                        {
                            in.skipValue();
                        }
                        else
                        {
                            customField.setName(in.nextString());
                        }
                        break;
                    }
                    case "type":
                    {
                        if(in.peek() == JsonToken.NULL)
                        {
                            in.skipValue();
                        }
                        else
                        {
                            customField.setType(in.nextString());
                        }
                        break;
                    }
                    case "value":
                    {
                        if(in.peek() == JsonToken.NULL)
                        {
                            in.skipValue();
                        }
                        else
                        {
                            customField.setValue(in.nextString());
                        }
                        break;
                    }
                }
            }
            customFields.add(customField);
            in.endObject();
        }
        in.endArray();

        return customFields;
    }
}
