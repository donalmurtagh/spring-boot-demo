package com.example.demo;

import org.springframework.boot.jackson.JacksonComponent;
import org.springframework.boot.jackson.ObjectValueSerializer;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;

@JacksonComponent
public class ItemSerializer extends ObjectValueSerializer<Item> {

    @Override
    protected void serializeObject(Item item, JsonGenerator jsonWriter, SerializationContext context) {
        jsonWriter.writeStringProperty("nameOfItem", item.name())
            .writeBooleanProperty("deleted", false);
    }
}
