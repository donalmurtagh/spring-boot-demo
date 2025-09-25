package com.example.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

@JsonComponent
public class ItemSerializer extends JsonObjectSerializer<Item> {

    @Override
    protected void serializeObject(Item item, JsonGenerator jsonWriter, SerializerProvider provider) throws IOException {
        jsonWriter.writeObjectField("id", item.id());
        jsonWriter.writeStringField("name", item.name());
    }
}
